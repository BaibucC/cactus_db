package com.cactus.dao;

import java.util.List;

import com.cactus.entity.User;
import com.cactus.entity.Bike;
import com.cactus.entity.Cactus;
import java.util.ArrayList;

public interface UserDAO {

    public void newEmployee(User user);

    public void delete(int userId);

    public User get(int userId);

    public List<User> list();

    public List<User> optionUser();

    public void saveOrUpdateBike(Bike bike);

    public void deleteBike(int bikeId);

    public void removeBike(int userId);

    public Bike getBike(int bikeId);

    public List<Bike> listBike();

    public List<Bike> listAvailable();

    public void saveBikeEmployee(User user);
    
    public ArrayList<Cactus> getAll();

    public ArrayList<Cactus> getAllGenera();
}
