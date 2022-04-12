package uz.pdp.olchauzcloneapp.service;

import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import uz.pdp.olchauzcloneapp.entity.*;
import uz.pdp.olchauzcloneapp.entity.enums.OrderStatus;
import uz.pdp.olchauzcloneapp.repository.OrderItemsRepository;
import uz.pdp.olchauzcloneapp.repository.TransactionHistoryProductsRepository;
import uz.pdp.olchauzcloneapp.repository.TransactionHistoryRepository;

import java.util.List;


// Zuhridin Bakhriddinov 4/12/2022 11:09 AM
@Service
public class PurchaseService {
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    TransactionHistoryRepository transactionHistoryRepository;
    @Autowired
    TransactionHistoryProductsRepository  transactionHistoryProductsRepository;


    public boolean fulfillOrder(Session session) {

        List<OrderItem> orderItems = orderItemsRepository.findAllByCreatedByAndOrderStatus(Long.valueOf(session.getClientReferenceId()), OrderStatus.
        NEW);
      if (orderItems.size()!=0){

        for (OrderItem orderItem : orderItems) {
            TransactionHistory savedTransactionHistory = transactionHistoryRepository.save(new TransactionHistory());
            transactionHistoryProductsRepository.save(new TransactionHistoryProducts(savedTransactionHistory,orderItem.getProduct(),orderItem.getQuantity()));

        }
          return true;
      }
      else
          return false;

    }

    public ResponseEntity<?> getStripeSession(User user, List<SessionCreateParams.LineItem> lineItems, List<OrderItem> orderItems) {
        for (OrderItem ticket : orderItems) {

            double ticketPrice = (ticket.getProduct().getPrice()*100+30)/(1-2.9/100);
            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                    .builder()
                    .setName(ticket.getProduct().getName())
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setProductData(productData)
                    .setCurrency("usd")
                    .setUnitAmount((long) (ticketPrice * 100))
                    .build();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity(1L)
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





}
