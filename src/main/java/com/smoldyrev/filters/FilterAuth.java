package com.smoldyrev.filters;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by smoldyrev on 25.02.17.
 * Фильтр доступа по сессии
 * проверяет id пользователя
 * записанный при авторизации
 */
public class FilterAuth implements Filter {

    private static Logger logger = Logger.getLogger(FilterAuth.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        System.out.println("88888///////"+(httpRequest.getSession().getAttribute("userSession")));
        if (httpRequest.getSession().getAttribute("userSession") != null) {
            logger.trace("Authentificator is true");
        } else {
            logger.trace("Authentificator is false, go to auth");
            request.setAttribute("msg", "Сначала авторизируйтесь");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
