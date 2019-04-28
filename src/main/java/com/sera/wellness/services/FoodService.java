package com.sera.wellness.services;
import com.sera.wellness.models.EatenProduct;
import com.sera.wellness.models.Product;
import com.sera.wellness.models.User;

import java.util.List;

public interface FoodService {
    List<Product> getAllProductsToUser(User user);
    void addPersonalProduct(Product personalProduct);
    boolean addEatenProducr(EatenProduct eatenProduct);
    
}
