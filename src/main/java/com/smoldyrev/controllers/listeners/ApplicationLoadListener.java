package com.smoldyrev.controllers.listeners;

import com.smoldyrev.common.utils.LectionNotificator;
import com.smoldyrev.models.pojo.Lection;
import com.smoldyrev.services.StudentService;
import org.apache.log4j.Logger;
import com.smoldyrev.services.LectionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by smoldyrev on 24.02.17.
 */
//начало работы сайта
public class ApplicationLoadListener implements ServletContextListener {

    private static Logger logger = Logger.getLogger(ApplicationLoadListener.class);

    @Autowired
    private LectionNotificator lectionNotificator;
    @Autowired
    private LectionService lectionService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        logger.trace("site was started");
        notifyByNearestLection();

        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(60 * 60 * 1000);
                        notifyByNearestLection();
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                }
            }
        });

        th1.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.trace("site was stopped");
    }

    public void notifyByNearestLection() {
        List<Lection> lections = lectionService.getNearedLection();
        if (lections.size() > 0) {
            logger.trace("lections founded");
            for (Lection lection :
                    lections) {
                lectionNotificator.notifyByLection(lection);
            }
        } else {
            logger.trace("neared lections not found");
        }
    }
}
