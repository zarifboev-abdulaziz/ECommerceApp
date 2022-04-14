package uz.pdp.olchauzcloneapp.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import uz.pdp.olchauzcloneapp.dto.AddressDto;
import uz.pdp.olchauzcloneapp.entity.OrderItem;
import uz.pdp.olchauzcloneapp.entity.User;
import uz.pdp.olchauzcloneapp.entity.address.Street;
import uz.pdp.olchauzcloneapp.entity.enums.OrderStatus;
import uz.pdp.olchauzcloneapp.repository.OrderItemsRepository;
import uz.pdp.olchauzcloneapp.repository.UserRepository;
import uz.pdp.olchauzcloneapp.service.PurchaseService;

import javax.servlet.http.HttpServletResponse;

import java.util.*;

// Zuhridin Bakhriddinov 4/12/2022 11:05 AM
@RestController
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderItemsRepository orderItemsRepository;

    @Value("${WEBHOOK_KEY}")
    private String webhookKey;
    @Value("${STRIPE_API_KEY}")
    private String stripeApiKey;

    //    @SneakyThrows
    @PostMapping("/webhook")
    public void handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String signHeader, HttpServletResponse response) {
        String endpointSecret = webhookKey;
        Stripe.apiKey = stripeApiKey;
//      to activate:  stripe listen --forward-to localhost:8080/webhook


        Event event = null;
        try {
            event = Webhook.constructEvent(payload, signHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
        }

        System.out.println("Order fulfilled");
        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();
            //    Optional<User> optionalUser = userRepository.findById((long) Integer.parseInt(session.getClientReferenceId()));
            //          List<Ticket> allByCartIdAndStatus =
            //                 ticketRepository.findAllByUserIdAndTicketStatus(optionalUser.get().getId(), TicketStatus.NEW);*/

            purchaseService.fulfillOrder(session);


        }

    }



    @PostMapping("/purchase-products")
    public HttpEntity<?> createStripeSession(@RequestBody AddressDto addressDto) {

        Stripe.apiKey = stripeApiKey;
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.get();
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        List<OrderItem> orderItems = orderItemsRepository.findAllByCreatedByAndOrderStatus(1L, OrderStatus.NEW);
        Street street = purchaseService.saveAddress(addressDto);
        if (street == null){
            return ResponseEntity.status(404).body("District Not Found");
        }

        return purchaseService.getStripeSession(user, lineItems, orderItems, street);
    }

}
