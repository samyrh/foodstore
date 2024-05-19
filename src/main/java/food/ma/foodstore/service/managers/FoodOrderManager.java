package food.ma.foodstore.service.managers;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.entities.FoodOrder;

import java.util.List;

public interface FoodOrderManager {

    FoodOrder createOrder(FoodOrder order);
    FoodOrder getOrderById(Long order);
    FoodOrder updateOrder(FoodOrder order);
    void deleteOrder(Long orderFoodId);
    List<FoodOrder> getOrdersByCustomer(Customer customer);
}
