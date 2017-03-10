package com.smoldyrev.controllers;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.pojo.User;
import com.smoldyrev.services.IUserService;
import com.smoldyrev.services.UserService;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = "userSession")
public class LoginController {

    private static Logger logger = Logger.getLogger(LoginController.class);

    static {
        DOMConfigurator.configure("log4j.xml");
    }

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model,
                                @RequestParam(name = "msg", required = false) String msg) {

        model.addAttribute("msg", msg);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model,
                        @RequestParam(name = "login") String login,
                        @RequestParam(name = "password") String password) {

        try {
            User user = userService.authorise(login, password);
            if (user.getId() != 0) {
                model.addAttribute("userID", user.getId());
                model.addAttribute("userSession", user.getId());
                logger.trace("auth");
                return "redirect:" + "/list";
            } else {
                logger.trace("noauth");
                model.addAttribute("msg", "check login/password");
                return "login";
            }
        } catch (UserDAOException e) {
            logger.error(e);
            return "error";
        }
    }
}
