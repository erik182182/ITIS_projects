package ru.erik182.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.erik182.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
