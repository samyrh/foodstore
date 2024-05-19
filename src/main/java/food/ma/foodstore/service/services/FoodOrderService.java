package food.ma.foodstore.service.services;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.FoodOrder;
import food.ma.foodstore.dao.repositories.FoodOrderRepository;
import food.ma.foodstore.service.managers.FoodOrderManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOrderService implements FoodOrderManager {

    private final FoodOrderRepository foodOrderRepository;

    @Autowired
    public FoodOrderService(FoodOrderRepository foodOrderRepository) {
        this.foodOrderRepository = foodOrderRepository;
    }

    @Override
    public FoodOrder createOrder(FoodOrder order) {
        return foodOrderRepository.save(order);
    }

    @Override
    public FoodOrder getOrderById(Long orderId) {
        return foodOrderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    @Override
    public FoodOrder updateOrder(FoodOrder order) {
        return foodOrderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        foodOrderRepository.deleteById(orderId);
    }


    @Override
    public List<FoodOrder> getOrdersByCustomer(Customer customer) {
        return foodOrderRepository.findByCustomer(customer);
    }
}
