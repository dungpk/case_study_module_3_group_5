package com.codegym.controller;

import com.codegym.dao.*;
import com.codegym.model.*;

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
                case "login":
                    System.out.println(request.getParameter(request.getLocalName()));
                    confirmLogin(request, response);
                    break;
                case "search_player":
                    request.setAttribute("id",Integer.parseInt(request.getParameter("account_id")));
                    searchPlayer(request, response);
                    break;
                case "createUser":
                    createUser(request, response);
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
                case "login":
                    showFormLogin(request, response);
                    break;

                case "search_player_by_game":
                    request.setAttribute("id",Integer.parseInt(request.getParameter("account_id")));
                    searchPlayerByGame(request, response);
                    break;
                case "register":
                    register(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "goHomePage":
                    request.setAttribute("id",Integer.parseInt(request.getParameter("account_id")));
                    goHomePage(request, response);
                    break;
                case "display_player":
                    request.setAttribute("id",Integer.parseInt(request.getParameter("account_id")));
                    displayPlayer(request, response);

                case "profile":
                    request.setAttribute("id",Integer.parseInt(request.getParameter("account_id")));
                    displayProfile(request, response);
                    break;
                case "playerRegister":
                    playerRegister(request,response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
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
            request.setAttribute("id",account.getId());
            goHomePage(request,response);
        }
    }

    private void goHomePage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        PlayerDAO playerDAO = new PlayerDAO();
        GameDAO gameDAO = new GameDAO();
        List<Game> gameList = gameDAO.getAllGame();
        List<Player> vipList = playerDAO.vipPlayer();
        List<Player> hotList = playerDAO.hotPlayer();
        List<Player> playerList = playerDAO.listPlayer();
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Home.jsp");
        System.out.println(request.getAttribute("id"));
        request.setAttribute("hotList", hotList);
        request.setAttribute("playerList", playerList);
        request.setAttribute("vipList", vipList);
        request.setAttribute("gameList", gameList);
        dispatcher.forward(request, response);
    }

    private void searchPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlayerDAO playerDao = new PlayerDAO();
        GameDAO gameDAO = new GameDAO();
        List<Game> gameList = gameDAO.getAllGame();
        request.setAttribute("gameList", gameList);
        String name = request.getParameter("search");
        List<Player> playerList = playerDao.searchPlayer(name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SearchPages.jsp");
        request.setAttribute("playerList", playerList);
        dispatcher.forward(request, response);
    }

    private void searchPlayerByGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        PlayerDAO playerDao = new PlayerDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        List<Player> playerList = playerDao.searchPlayerByGame(id);
        GameDAO gameDAO = new GameDAO();
        List<Game> gameList = gameDAO.getAllGame();
        request.setAttribute("gameList", gameList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SearchPages.jsp");
        request.setAttribute("playerList", playerList);
        dispatcher.forward(request, response);
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Register.jsp");
        dispatcher.forward(request, response);
    }
    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String name = request.getParameter("name");
        AccountDao accountDao = new AccountDao();
        UserDAO userDao = new UserDAO();
         boolean checkAccount= accountDao.checkAccountExist(userName);
        if(checkAccount || !password.equals(confirm)){
            response.sendRedirect("jsp/Register.jsp");
        }else{
            accountDao.createAccount(userName,password,"user");
            int idForeign = accountDao.getIdByUserName(userName);
            userDao.createUser(name,0,idForeign);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/successfulRegistration.html");
            dispatcher.forward(request,response);
        }
    }

    private void playerRegister(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/playerRegister.html");
        dispatcher.forward(request,response);
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("jsp/welcome.jsp");
    }

    private void displayPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int playerId = Integer.parseInt(request.getParameter("id"));
        GameDAO gameDAO = new GameDAO();
        PlayerDAO playerDAO = new PlayerDAO();
        List<Game> gameList = gameDAO.getAllGame();
        request.setAttribute("gameList", gameList);
        request.setAttribute("player",playerDAO.searchPlayerById(playerId));
        List<Game> games = playerDAO.searchGameByIdPlayer(playerId);
        request.setAttribute("listGameOfPlayer",games);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SelectPlayer.jsp");
        dispatcher.forward(request, response);
    }

    private void displayProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        PlayerDAO playerDAO = new PlayerDAO();
        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.getProfileByAccountId(Integer.parseInt(request.getParameter("account_id")));
        request.setAttribute("profile",profile);

        AccountDao accountDao = new AccountDao();
        String role = accountDao.getRoleByAccountId(Integer.parseInt(request.getParameter("account_id")));

        if(role.equals("user")){


        }else{
            Player player = accountDao.getPlayerByAccountId(Integer.parseInt(request.getParameter("account_id")));
            List<Game> games = playerDAO.searchGameByIdPlayer(player.getPlayer_id());
            request.setAttribute("listGameOfPlayer",games);
            request.setAttribute("player",player);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/playerProfile.jsp");
            dispatcher.forward(request, response);

        }

    }
}
