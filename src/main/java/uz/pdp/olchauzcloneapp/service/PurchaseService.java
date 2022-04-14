package uz.pdp.olchauzcloneapp.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import uz.pdp.olchauzcloneapp.dto.AddressDto;
import uz.pdp.olchauzcloneapp.entity.*;
import uz.pdp.olchauzcloneapp.entity.address.District;
import uz.pdp.olchauzcloneapp.entity.address.Street;
import uz.pdp.olchauzcloneapp.entity.enums.OrderStatus;
import uz.pdp.olchauzcloneapp.repository.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


// Zuhridin Bakhriddinov 4/12/2022 11:09 AM
@Service
public class PurchaseService {
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    TransactionHistoryProductsRepository transactionHistoryProductsRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    StreetRepository streetRepository;
    @Autowired
    PayTypeRepository payTypeRepository;


    public boolean fulfillOrder(Session session) {

        Map<String, String> metadata = session.getMetadata();
        String addressId = metadata.get("addressId");
        Optional<Street> optionalStreet = streetRepository.findById(Long.valueOf(addressId));
        Optional<PayType> optionalPayType = payTypeRepository.findByName("Stripe");


        List<OrderItem> orderItems = orderItemsRepository.findAllByCreatedByAndOrderStatus(
                Long.valueOf(session.getClientReferenceId()), OrderStatus.NEW);

        if (orderItems.size() == 0 || !optionalStreet.isPresent() || !optionalPayType.isPresent()) {
            return false;
        }

        TransactionHistory transactionHistory = new TransactionHistory(optionalPayType.get(), (session.getAmountTotal().doubleValue() / 100), optionalStreet.get(), session.getPaymentIntent());
        transactionHistory.setCreatedBy(1L);
        TransactionHistory savedTransactionHistory = transactionHistoryRepository.save(transactionHistory);


        for (OrderItem orderItem : orderItems) {
            transactionHistoryProductsRepository.save(new TransactionHistoryProducts(savedTransactionHistory, orderItem.getProduct(), orderItem.getQuantity()));
            orderItem.setOrderStatus(OrderStatus.ORDERED);
            orderItemsRepository.save(orderItem);
        }

        return true;
    }

    public ResponseEntity<?> getStripeSession(User user, List<SessionCreateParams.LineItem> lineItems, List<OrderItem> orderItems, Street street) {
        for (OrderItem product : orderItems) {

            double productPrice = (product.getProduct().getPrice() * 100 + 0.3) / (1 - 2.9 / 100);
            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                    .builder()
                    .setName(product.getProduct().getName())
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setProductData(productData)
                    .setCurrency("usd")
                    .setUnitAmount((long) (productPrice))
                    .build();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity((long) product.getQuantity())
                    .build();


            lineItems.add(lineItem);


        }

        SessionCreateParams params = SessionCreateParams
                .builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://localhost:8080/failed")
                .setSuccessUrl("http://localhost:8080/success")
                .setClientReferenceId(user.getId().toString())
                .addAllLineItem(lineItems)
                .putMetadata("addressId", street.getId().toString())
                .build();
        try {
            Session session = Session.create(params);
            String checkoutUrl = session.getUrl();

            return ResponseEntity.ok(checkoutUrl);

        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }


    public Street saveAddress(AddressDto addressDto) {
        Optional<District> optionalDistrict = districtRepository.findById(addressDto.getDistrictId());
        if (!optionalDistrict.isPresent()) return null;

        Street street = new Street(optionalDistrict.get(), addressDto.getStreetName(), addressDto.getApartmentNumber(),
                addressDto.getFlatNumber(), addressDto.getEntranceNumber(), addressDto.getFloor());

        Street savedStreet = streetRepository.save(street);
        return savedStreet;
    }
}
