package com.smoldyrev.services;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.UserDAO;
import com.smoldyrev.models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by smoldyrev on 23.02.17.
 */

@Service("userService")
@Component
public class UserService {

    private static Logger logger = Logger.getLogger(UserService.class);

    private UserDAO userDAO = new UserDAO();

    private int anInt = 0;

    public User authorise(String login, String password) throws UserDAOException {
        if (anInt == 0) {
            Random rand = new Random();
            anInt = rand.nextInt();
        }
        logger.debug("Random anInt: "+anInt);
        return userDAO.getUserByLoginAndPassword(login, password);
    }

    public boolean registration(String login, String password, String email) throws UserDAOException {
        return UserDAO.registrationUser(login, password,email);
    }
}
