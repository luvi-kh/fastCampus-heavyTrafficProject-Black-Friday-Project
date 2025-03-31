package com.example.PaymentService.controller;

import com.example.PaymentService.dto.PaymentMethodDto;
import com.example.PaymentService.dto.ProcessPaymentDto;
import com.example.PaymentService.entity.Payment;
import com.example.PaymentService.entity.PaymentMethod;
import com.example.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment/methods")
    public PaymentMethod registerPaymentMethod(@RequestBody PaymentMethodDto dto) {
        return paymentService.registerPaymentMethod(
              dto.userId,
              dto.paymentMethodType,
              dto.creditCardNumber
        );
    }

    @PostMapping("/payment/process-payment")
    public Payment processPayment(@RequestBody ProcessPaymentDto dto) throws Exception {
        return paymentService.processPayment(
                dto.userId,
                dto.orderId,
                dto.amountKRW,
                dto.paymentMethodId
        );
    }

    @GetMapping("/payment/users/{userId}/first-method")
    public PaymentMethod getPaymentMethod(@PathVariable Long userId) {
        return paymentService.getPaymentMethodByUser(userId);
    }
    @GetMapping("/payment/payments/{paymentId}")
    public Payment getPayment(@PathVariable Long paymentId) {
        return paymentService.getPayment(paymentId);
    }
}
