package com.sbt.servlets.utils;

import com.sbt.DAO.DAO;
import com.sbt.DAO.DAOException;
import com.sbt.DAO.Database;
import com.sbt.model.User;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by kate_ on 30.01.2017.
 */
@WebServlet(name = "Authentication", urlPatterns = {"/Authentication"})
public class Authentication extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String username = request.getParameter("username").toLowerCase().trim();
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        try {
            DAO dao = new Database();
            User user = dao.authorizationUser(username, password);
            if (user != null) {
                session.removeAttribute("logout");
                session.setAttribute("user", username);
                out.println(new JSONObject().put("success", true).toString());
            } else {
                out.println(new JSONObject().put("success", false).toString());
            }
            dao.closeConnection();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
