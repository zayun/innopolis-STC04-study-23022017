package com.smoldyrev.services;

import com.smoldyrev.models.dao.StudentDao;
import com.smoldyrev.models.pojo.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by smoldyrev on 23.02.17.
 */
@Service
@Secured("ROLE_ADMIN")
public class StudentService implements IStudentService{

    private static Logger logger = Logger.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        System.out.println("////////"+78978979);
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findOne(id);
    }


    public int deleteStudentOnId(int id){
        studentRepository.delete(id);
        return 1;
    }

    public int updateStudentOnId(Student student){
        return studentRepository.saveAndFlush(student).getId();
    }

    public int insertStudent(Student student){

        return studentRepository.save(student).getId();
    }

    public List<Student> getStudentsByGroupId(int groupid){
//        return StudentDao.getStudentsByGroup(groupid);
        return studentRepository.findByGroup(groupid);
    }

}
