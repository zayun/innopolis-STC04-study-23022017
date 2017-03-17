package com.smoldyrev.services;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.StudentDao;
import com.smoldyrev.models.pojo.Student;
import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by smoldyrev on 23.02.17.
 */
@Service
@Secured("ROLE_ADMIN")
public class StudentService implements IStudentService{

    private static Logger logger = Logger.getLogger(StudentService.class);

    public List<Student> getAllStudents(){
//        return StudentDao.getAllStudents();
        return StudentDao.getAllStudentsHiber();
    }

    public Student getStudentById(int id) {
        Student student = null;
        try {
            student = StudentDao.getStudentById(id);
        } catch (UserDAOException e) {
            logger.error(e);
        }
        return student;
    }


    public int deleteStudentOnId(int id){

        return StudentDao.deleteStudent(id);
    }

    public int updateStudentOnId(Student student){

        return StudentDao.updateStudent(student);
    }

    public int insertStudent(Student student){

        return StudentDao.insertStudent(student);
    }

    public List<Student> getStudentsByGroupId(int groupid){
        return StudentDao.getStudentsByGroup(groupid);
    }

}
