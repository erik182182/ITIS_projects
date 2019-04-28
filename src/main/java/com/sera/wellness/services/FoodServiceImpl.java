package com.sera.wellness.services;

import com.sera.wellness.models.EatenProduct;
import com.sera.wellness.models.Product;
import com.sera.wellness.models.User;
import com.sera.wellness.repositories.EatenProductSpringDataRepository;
import com.sera.wellness.repositories.ProductSpringDataRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Data
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private ProductSpringDataRepository productRepository;
    @Autowired
    private EatenProductSpringDataRepository eatenProductRepository;

    @Override
    public List<Product> getAllProductsToUser(User user) {
        return productRepository.findAllToUser(user.getId());
    }

    @Override
    public void addPersonalProduct(Product personalProduct) {
        productRepository.save(personalProduct);
    }

    @Override
    public boolean addEatenProducr(EatenProduct eatenProduct) {
        if (eatenProductRepository.existsByProductAndUserAndDate(eatenProduct.getProduct(), eatenProduct.getUser(), eatenProduct.getDate())) {
            eatenProductRepository.save(eatenProduct);
            return false;
        } else {
            eatenProductRepository.save(eatenProduct);
            return true;
        }
    }

    @Override
    public List<Product> getAllPersonalProducts(User user) {
        return productRepository.findAllPersonalByUserId(user.getId());
    }

}
