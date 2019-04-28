package com.sera.wellness.repositories;

import com.sera.wellness.models.EatenProduct;
import com.sera.wellness.models.Product;
import com.sera.wellness.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDate;

public interface EatenProductSpringDataRepository extends JpaRepository<EatenProduct,Long> {
    boolean existsByProductAndUserAndDate(Product product, User user, Date date);
}
