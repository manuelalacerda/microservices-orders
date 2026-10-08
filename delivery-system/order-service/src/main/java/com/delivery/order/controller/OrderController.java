package com.delivery.order.controller;

import com.delivery.order.client.PaymentClient;
import com.delivery.order.dto.OrderRequest;
import com.delivery.order.dto.OrderResponse;
import com.delivery.order.dto.PaymentRequest;
import com.delivery.order.dto.PaymentResponse;
import com.delivery.order.entity.OrderEntity;
import com.delivery.order.exception.PaymentFailedException;
import com.delivery.order.repository.OrderRepository;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;

    public OrderController(PaymentClient paymentClient, OrderRepository orderRepository) {
        this.paymentClient = paymentClient;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        PaymentResponse paymentResponse = paymentClient.processPayment(
                new PaymentRequest(request.totalAmount(), request.paymentMethod())
        );

        if (!"APPROVED".equals(paymentResponse.status())) {
            throw new PaymentFailedException("O pagamento foi recusado pela operadora.");
        }

        String orderId = UUID.randomUUID().toString();

        OrderEntity entity = new OrderEntity(
                orderId,
                request.customerName(),
                request.totalAmount(),
                "CONFIRMED",
                paymentResponse.paymentId()
        );
        orderRepository.save(entity);

        return new OrderResponse(
                orderId,
                request.customerName(),
                request.totalAmount(),
                "CONFIRMED",
                paymentResponse
        );
    }
}