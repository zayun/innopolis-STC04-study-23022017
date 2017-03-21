package com.smoldyrev.services;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.UserDAO;
import com.smoldyrev.models.pojo.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * Created by smoldyrev on 23.02.17.
 */

@Service
@Secured("ROLE_ADMIN")
public class UserService implements IUserService{

    private static Logger logger = Logger.getLogger(UserService.class);

    private UserDAO userDAO = new UserDAO();

    @Autowired
    private UserRepository userRepository;

    public User authorise(String login, String password) throws UserDAOException {
        return userRepository.findByLoginAndPassword(login,password);
    }

    public boolean registration(String login, String password, String email) throws UserDAOException {
        User user = new User(login, password,"ROLE_USER",email);
        return (userRepository.save(user)!=null);
    }
}
