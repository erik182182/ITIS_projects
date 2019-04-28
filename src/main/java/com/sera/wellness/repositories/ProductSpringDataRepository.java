package com.sera.wellness.repositories;
import com.sera.wellness.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductSpringDataRepository extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.user.id = ?1 OR  null ")
    List<Product> findAllToUser(Long userId);
}
