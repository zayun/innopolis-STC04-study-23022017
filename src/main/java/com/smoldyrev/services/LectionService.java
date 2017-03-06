package com.smoldyrev.services;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.LectionDAO;
import com.smoldyrev.models.pojo.Lection;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by smoldyrev on 24.02.17.
 */
@Service
public class LectionService {

    public List<Lection> getAllLections(){
        return LectionDAO.getAllLections();
    }

    public int deleteLectioOnId(int id){

        return LectionDAO.deleteLection(id);
    }

    public int updateLectionOnId(Lection lection){

        return LectionDAO.updateLection(lection);
    }

    public Lection getLectionOnId(int id){

        try {
            return LectionDAO.getLectionById(id);
        } catch (UserDAOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int insertLection(Lection lection){

        return LectionDAO.insertLection(lection);
    }

    public List<Lection> getNearedLection() {
        return LectionDAO.getNearedLections();
    }
}
