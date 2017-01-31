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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kate_ on 30.01.2017.
 */
@WebServlet(name = "Registration", urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {
    private final String REG_LOGIN = "^[a-z0-9]{4,}$";
    private final String REG_PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HashMap data;
        String username = request.getParameter("username").toLowerCase().trim();
        String password = request.getParameter("password");
        String password_doubling = request.getParameter("password_doubling");
        try (PrintWriter out = response.getWriter()) {
            data = checkingData(username, password, password_doubling);
            if (!data.containsKey("message")) {
                DAO dao = new Database();
                dao.createUser(username, password);
                dao.closeConnection();
                out.println(new JSONObject().put("message", "Created").toString());
            } else {
                out.println(new JSONObject().put("message", data.get("message")).toString());
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private HashMap checkingData(String username, String password, String password_doubling) throws DAOException {
        HashMap data = new HashMap();
        if (!password.equals(password_doubling)) {
            data.put("message", "Пароль и повтор пароля не совпадают");
        } else {
            if (!reg(REG_PASSWORD, password)) {
                data.put("message", "Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов.");
            }
        }
        if (!reg(REG_LOGIN, username)) {
            data.put("message", "Имя пользователя должно быть длиннее 4 символов и состоять из цифр и букв английского алфавита.");
        }
        if (userExist(username)) {
            data.put("message", "Пользователь с таким именем уже зарегестрирован.");
        }
        return data;
    }

    private boolean reg(String reg, String inputStr) {
        Pattern p = Pattern.compile(reg, Pattern.UNICODE_CASE);
        Matcher mat = p.matcher(inputStr);
        return mat.matches();
    }

    public boolean userExist(String username) throws DAOException {
        DAO dao = new Database();
        List<User> users = (List<User>) dao.findAll('u');
        for (User user : users) {
            if (user.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
