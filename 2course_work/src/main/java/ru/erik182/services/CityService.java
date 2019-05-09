package ru.erik182.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.erik182.models.City;
import ru.erik182.repositories.CityRepository;
import ru.erik182.utils.Geocoder;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public String getCoordinates(Long cityId) {
        City city = cityRepository.getOne(cityId);
        try {
            return Geocoder.getCoordinatesOfCity(city).get("Point").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
