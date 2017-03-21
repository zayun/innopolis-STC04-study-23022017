package com.smoldyrev.services;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.LectionDAO;
import com.smoldyrev.models.pojo.Lection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by smoldyrev on 24.02.17.
 */
@Service
@Secured("ROLE_ADMIN")
public class LectionService implements ILectionService {

    @Autowired
    private LectionRepository lectionRepository;

    public List<Lection> getAllLections(){
        return
                lectionRepository.findAll();
    }

    public int deleteLectioOnId(int id){
        lectionRepository.delete(id);
        return 0;
    }

    public int updateLectionOnId(Lection lection){

        return lectionRepository.saveAndFlush(lection).getId();
    }

    public Lection getLectionOnId(int id){
        return lectionRepository.findOne(id);
    }


    public int insertLection(Lection lection){

        return lectionRepository.save(lection).getId();
    }

    public List<Lection> getNearedLection() {
        return LectionDAO.getNearedLections();
    }
}
