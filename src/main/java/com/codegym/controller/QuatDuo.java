package com.codegym.controller;

import com.codegym.dao.AccountDao;
import com.codegym.dao.UserDAO;
import com.codegym.model.Account;
import com.codegym.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "QuatDuo", urlPatterns = "/quat")
public class QuatDuo extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    System.out.println(request.getParameter(request.getLocalName()));
                    insertUser(request, response);
                    break;
                case "login":
                    System.out.println(request.getParameter(request.getLocalName()));
                    confirmLogin(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "delete":
//                    deleteUser(request, response);
                    deleteUserUsingStore(request, response);
                    break;
                case "login":
                    showFormLogin(request, response);
                default:

                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User();
        userDAO.insertUserStore(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUserUsingStore(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUserStore(id);
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    private void confirmLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        AccountDao accountDao = new AccountDao();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountDao.confirmLogin(name,password);

        if(account==null){
            response.sendRedirect("jsp/login.jsp");
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Home.html");
            request.setAttribute("account", account);
            dispatcher.forward(request, response);
        }
    }
}
