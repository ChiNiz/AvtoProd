package com.taibov.vkr.repositories;

import com.taibov.vkr.entities.Model;
import com.taibov.vkr.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer>
{
    Car findCarById(Integer id);
    List<Car> findCarsByModel(Model model);
    Car findCarByName(String name);
}
