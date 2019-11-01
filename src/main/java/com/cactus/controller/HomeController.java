package com.cactus.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.cactus.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cactus.dao.UserDAO;
import com.cactus.entity.Bike;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    private UserDAO userDAO;

    //homepage controller
    @RequestMapping(value = "/")
    public ModelAndView listUser(ModelAndView model) throws IOException {
        List<User> listUser = userDAO.list();
        model.addObject("listUser", listUser);
        List<Bike> listBike = userDAO.listBike();
        model.addObject("listBike", listBike);
        List<Bike> listAvailable = userDAO.listAvailable();
        model.addObject("listAvailable", listAvailable);
        Bike bike = new Bike();
        String availablestring = String.valueOf(bike.getAvailable());
        model.addObject(availablestring);
        String statusstring = String.valueOf(bike.getStatus());
        model.addObject(statusstring);
        model.setViewName("home");
        return model;
    }

    //controller for new employee form
    @RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
    public ModelAndView newEmployee(ModelAndView model) {
        User newUser = new User();
        model.addObject("user", newUser);
        model.setViewName("EmployeeForm");
        return model;
    }

    //controller for new bike form
    @RequestMapping(value = "/newBike", method = RequestMethod.GET)
    public ModelAndView newBike(ModelAndView model) {
        Bike newBike = new Bike();
        model.addObject("bike", newBike);
        model.setViewName("BikeForm");
        return model;
    }

    //controller for saving employee
    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public ModelAndView saveEmployee(@ModelAttribute User user) {
        ModelAndView model = new ModelAndView("EmployeeForm");
        model.addObject("user", user);
        if (user.getEmployee().isEmpty()) {
            model.addObject("error", "Employee name required!");
        } else {
            userDAO.newEmployee(user);
            return new ModelAndView("redirect:/");
        }

        return model;
    }

    //controller for saving new bike
    @RequestMapping(value = "/saveBike", method = RequestMethod.POST)
    public ModelAndView saveBike(@ModelAttribute Bike bike) {
        ModelAndView model = new ModelAndView("BikeForm");
        model.addObject("bike", bike);
        bike.getStatus();
        try {
            if (bike.getBikename().isEmpty() || bike.getStatus().equals(null)) {
                if (bike.getBikename().isEmpty() || bike.getStatus().equals(null)) {
                    model.addObject("error", "All fields required!");
                } else {
                    userDAO.saveOrUpdateBike(bike);
                    return new ModelAndView("redirect:/");
                }
            }
        } catch (Exception e) {
            System.out.println(bike.getStatus());
        }

        return model;
    }

    //controller for removing bike from employee
    @RequestMapping(value = "/removeBike", method = RequestMethod.GET)
    public ModelAndView removeBike(HttpServletRequest request
    ) {
        int userId = Integer.parseInt(request.getParameter("id"));
        userDAO.removeBike(userId);
        return new ModelAndView("redirect:/");
    }

    //controller for deleting employee from list
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest request
    ) {
        int userId = Integer.parseInt(request.getParameter("id"));
        userDAO.delete(userId);
        return new ModelAndView("redirect:/");
    }

    //controller for deleting bike from list
    @RequestMapping(value = "/deleteBike", method = RequestMethod.GET)
    public ModelAndView deleteBike(HttpServletRequest request
    ) {
        int bikeId = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteBike(bikeId);
        return new ModelAndView("redirect:/");
    }

    //controller for associating bike with an employee
    @RequestMapping(value = "/saveUserBike", method = RequestMethod.POST)
    public ModelAndView saveUserBike(@ModelAttribute User user
    ) {
        ModelAndView model = new ModelAndView("BikeEmployeeForm");
        if (!user.getName().equals("NONE") || !user.getDate1().isEmpty() || !user.getDate2().isEmpty()) {
            model.addObject("error", "All fields required!");
            return new ModelAndView("BikeEmployeeForm");
        }
            userDAO.saveBikeEmployee(user);
            return new ModelAndView("redirect:/");
    }

    //controller for editing user/bike list, opening editing form
    @RequestMapping(value = "/editUserBike", method = RequestMethod.GET)
    public ModelAndView editUserBike(HttpServletRequest request
    ) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = userDAO.get(userId);
        //open form
        ModelAndView model = new ModelAndView("BikeEmployeeForm");
        model.addObject("user", user);
        //list of bikes
        List<Bike> listAvailable = userDAO.listAvailable();
        ArrayList<String> options = new ArrayList<>();
        //get current bike name associated with employee
        String bikenameCurrent = user.getName();
        //add current bike to the list
        if (!bikenameCurrent.isEmpty()) {
            options.add(bikenameCurrent);
        }
        //listing other available bikes to choose from
        String bikename;
        for (int i = 0; i < listAvailable.size(); i++) {
            bikename = listAvailable.get(i).getBikename();
            options.add(bikename);
        }
        model.addObject("listAvailable", options);
        //display employee name
        ArrayList<String> options2 = new ArrayList<>();
        String username = user.getEmployee();
        options2.add(username);
        model.addObject("optionUser", options2);
        return model;
    }

    //controller for editing bike
    @RequestMapping(value = "/editBike", method = RequestMethod.GET)
    public ModelAndView editBike(HttpServletRequest request
    ) {
        int bikeId = Integer.parseInt(request.getParameter("id"));
        Bike bike = userDAO.getBike(bikeId);
        ModelAndView model = new ModelAndView("BikeForm");
        model.addObject("bike", bike);

        return model;
    }

}
