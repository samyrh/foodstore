package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.Payment;

public interface PaymentManager {

    void processPayment(Long orderId, Double amount, String paymentMethod);
    Payment getPaymentById(Long paymentId);
    Payment updatePayment(Payment payment);
    void deletePayment(Long paymentId);
}
