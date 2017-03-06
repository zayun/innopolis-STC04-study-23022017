package com.smoldyrev.controllers;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by smoldyrev on 06.03.17.
 */
@Controller
public class RegistrationController {

    private static Logger logger = Logger.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String login(Model model,
                        @RequestParam(name = "login") String login,
                        @RequestParam(name = "password") String password,
                        @RequestParam(name = "email") String email) {

        try {
            if(userService.registration(login, password,email)){

                return "login";
            }else{
                logger.trace("false");
                return "error";
            }
        } catch (UserDAOException e) {
            logger.error(e);
        }
        return "registration";
    }
}
