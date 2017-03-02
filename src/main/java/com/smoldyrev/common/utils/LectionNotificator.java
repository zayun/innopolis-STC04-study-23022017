package com.smoldyrev.common.utils;

import com.smoldyrev.models.pojo.Lection;
import com.smoldyrev.models.pojo.Student;
import org.apache.log4j.Logger;
import com.smoldyrev.services.StudentService;

import java.util.List;


/**
 * Created by smoldyrev on 24.02.17.
 */
public class LectionNotificator {
    private static Logger logger = Logger.getLogger(LectionNotificator.class);

    public static void notifyByLection(Lection lection){
        List<Student> students =
            StudentService.getStudentsByGroupId(lection.getGroupid());
        logger.trace(students.size() + "founded");
        for (Student student:
             students) {
            Mailer.sendMail(student.getEmail(),"lection is neared", lection.getSubject());
        }
    }
}
