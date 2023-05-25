package com.codegym.controller;

import com.codegym.dao.userDAO.UserDAO;
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

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
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
                case "edit":
                    updateUser(request, response);
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
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
//                    deleteUser(request, response);
                    deleteUserUsingStore(request, response);
                    break;
                case "search":
                    searchCity(request, response);
                    break;
                case "sort":
                    sortUserByName(request, response);
                    break;
                case "permission":
                    addUserPermission(request, response);
                    break;
                case "test-without-tran":
                    testWithoutTran(request, response);
                    break;
                case "test-use-tran":
                    testUseTran(request, response);
                    break;
                case "allUser":
                    showAllUserStore(request, response);
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        System.out.println(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(request);
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User newUser = new User(name, email, country);
        userDAO.insertUserStore(newUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        System.out.println(request);
        User book = new User(id, name, email, country);
        userDAO.updateUser(book);
        listUser(request,response);
    }

    private void searchCity(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String query = request.getParameter("search");
        List<User> listUser = userDAO.selectUsersByCity(query);
        request.setAttribute("listUserByCity", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/user-by-city.jsp");
        dispatcher.forward(request, response);
    }

    private void sortUserByName(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.sortUsersByName();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void addUserPermission(HttpServletRequest request, HttpServletResponse response) {

        User user = new User("quan1", "quan1.nguyen@codegym.vn", "vn");

        int[] permission = {1, 2, 4};

        userDAO.addUserTransaction(user, permission);

    }

    private void testWithoutTran(HttpServletRequest request, HttpServletResponse response) {

        userDAO.insertUpdateWithoutTransaction();

    }

    private void testUseTran(HttpServletRequest request, HttpServletResponse response) {

        userDAO.insertUpdateUseTransaction();

    }

    private void showAllUserStore(HttpServletRequest request, HttpServletResponse response) {
        userDAO.selectAllUsersStore();
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
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

}

