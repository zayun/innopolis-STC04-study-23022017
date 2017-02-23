package services;

import common.exceptions.UserDAOException;
import models.dao.UserDAO;

/**
 * Created by smoldyrev on 23.02.17.
 */
public class UserService {

    public static boolean authorise(String login, String password) throws UserDAOException {
        if(UserDAO.getUserByLoginAndPassword(login, password) != null){
            return true;
        }
        return false;
    }

    public static boolean registration(String login, String password, String email) throws UserDAOException {
        return UserDAO.registrationUser(login, password,email);
    }

}
