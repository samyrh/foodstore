package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.Payment;
import food.ma.foodstore.dao.repositories.PaymentRepository;
import food.ma.foodstore.service.managers.PaymentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


    @Service
    public class PaymentService implements PaymentManager {

        private final PaymentRepository paymentRepository;

        @Autowired
        public PaymentService(PaymentRepository paymentRepository) {
            this.paymentRepository = paymentRepository;
        }

        public void processPayment(Long orderId, Double amount, String paymentMethod) {

        }

        public Payment getPaymentById(Long paymentId) {

            return paymentRepository.findById(paymentId).orElse(null);
        }

        public Payment updatePayment(Payment payment) {
            // Implement logic to update payment information
            return paymentRepository.save(payment);
        }


        public void deletePayment(Long paymentId) {
            // Implement logic to delete a payment
            paymentRepository.deleteById(paymentId);
        }
    }


