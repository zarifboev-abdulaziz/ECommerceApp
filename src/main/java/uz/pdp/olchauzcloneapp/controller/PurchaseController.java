package uz.pdp.olchauzcloneapp.controller;

import com.stripe.Stripe;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;


import uz.pdp.olchauzcloneapp.entity.OrderItem;
import uz.pdp.olchauzcloneapp.entity.User;
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

    @SneakyThrows
    @PostMapping("/webhook")
    public void handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String signHeader, HttpServletResponse response) {
//        String endpointSecret = "whsec_caf7231c252f6538d81bf44b2f1ee721c5b42440b2211aa88ed4dbc1aa3393b8";
//        Stripe.apiKey = "sk_test_51KhfDrGNKbQ4R3wKLw6i1KUhcMkIpIxduTX2JOaooftmI9u3lxS8j4apN9kYJ9UZVRl9230Jn1kWBALtzysklSEx007WRYy1hA";

        Stripe.apiKey = "sk_test_51Kiz3rEhoGDMoOBR40fygRgoRauXuzCNiAQjVjT62m8VunOcTYCmk7SVBQ07AaYeoe7jXZiRw0D17kUviPfAdbYr00hNGzkbE8";
        String endpointSecret = "whsec_9d3fa95ded35d69e4b0c61d55739c534f9158550397f11a6bdabcf576c70941d";

//      to activate:  stripe listen --forward-to localhost:8080/webhook
        Event event = Webhook.constructEvent(payload, signHeader, endpointSecret);

        System.out.println("Order fulfilled");
        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();
       //    Optional<User> optionalUser = userRepository.findById((long) Integer.parseInt(session.getClientReferenceId()));
  //          List<Ticket> allByCartIdAndStatus =
   //                 ticketRepository.findAllByUserIdAndTicketStatus(optionalUser.get().getId(), TicketStatus.NEW);*/

                purchaseService.fulfillOrder(session);





            }

        }




    @GetMapping("/charge")
    public HttpEntity<?> createStripeSession() {

        Stripe.apiKey = "sk_test_51KhfDrGNKbQ4R3wKLw6i1KUhcMkIpIxduTX2JOaooftmI9u3lxS8j4apN9kYJ9UZVRl9230Jn1kWBALtzysklSEx007WRYy1hA";
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.get();
        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

   //     List<CustomTicketForCart> ticketList = ticketRepository.getTicketByUserId(user.getId());
        List<OrderItem> orderItems = orderItemsRepository.findAllByCreatedByAndOrderStatus(1L, OrderStatus.NEW);

        return purchaseService.getStripeSession(user, lineItems, orderItems);
    }

}
