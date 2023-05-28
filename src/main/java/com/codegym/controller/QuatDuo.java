package com.codegym.controller;

import com.codegym.dao.*;
import com.codegym.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "QuatDuo", urlPatterns = "/quat")
public class QuatDuo extends HttpServlet {
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
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    searchPlayer(request, response);
                    break;
                case "createUser":
                    createUser(request, response);
                    break;
                case "createPlayer":
                    createPlayer(request, response);
                    break;
                case "createGame":
                    createGame(request, response);
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
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    searchPlayerByGame(request, response);
                    break;
                case "register":
                    register(request, response);
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "goHomePage":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    goHomePage(request, response);
                    break;
                case "display_player":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    displayPlayer(request, response);
                case "profile":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    displayProfile(request, response);
                    break;

                case "playerRegister":
                    playerRegister(request, response);
                    break;

                case "accept_request":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    showFormAccept(request, response);
                    break;
                case "accept_refuse":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    showFormRefuse(request, response);
                    break;
                case "deposit":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    showDepositForm(request, response);
                    break;
                case "coin_deposit":
                    request.setAttribute("id", Integer.parseInt(request.getParameter("account_id")));
                    deposit(request, response);
                    break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void createPlayer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        AccountDao accountDao = new AccountDao();
        PlayerDAO playerDAO = new PlayerDAO();
        ProfileDao profileDao = new ProfileDao();
        GameDAO gameDAO = new GameDAO();

        boolean checkAccount = accountDao.checkAccountExist(userName);

        if (checkAccount || !password.equals(confirm)) {
            response.sendRedirect("jsp/playerRegister.html");
        } else {
            accountDao.createAccount(userName, password, "player");
            int idForeign = accountDao.getIdByUserName(userName);
            playerDAO.createPlayer(name, 0, 0, 0, idForeign, "", 0);
            int idPlayer = playerDAO.getIdByIdForegin(idForeign);
            profileDao.createProfile(age, address, email, idForeign);
//
            request.setAttribute("idPlayer", idPlayer);

            //query db de get list game
            List<Game> gameList = gameDAO.getAllGame();
            request.setAttribute("gameList", gameList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Game.jsp?idPlayer=" + idPlayer);
            dispatcher.forward(request, response);
        }

    }

    private void showFormLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/login.jsp");
        dispatcher.forward(request, response);
    }

    private void confirmLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        AccountDao accountDao = new AccountDao();
        PlayerDAO playerDAO = new PlayerDAO();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountDao.confirmLogin(name, password);
        if (name.equals("admin") && password.equals("admin")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Admin.jsp");
            request.setAttribute("list", accountDao.listAccount());
            dispatcher.forward(request, response);
        } else if (account == null) {
            response.sendRedirect("jsp/login.jsp");
        } else {
            request.setAttribute("id", account.getId());
            goHomePage(request, response);
        }
    }

    private void goHomePage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
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

    private void searchPlayerByGame(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Register.jsp");
        dispatcher.forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        AccountDao accountDao = new AccountDao();
        UserDAO userDao = new UserDAO();
        ProfileDao profileDao = new ProfileDao();

        boolean checkAccount = accountDao.checkAccountExist(userName);
        if (checkAccount || !password.equals(confirm)) {
            response.sendRedirect("jsp/Register.jsp");
        } else {
            accountDao.createAccount(userName, password, "user");
            int idForeign = accountDao.getIdByUserName(userName);
            userDao.createUser(name, 0, idForeign);
            profileDao.createProfile(age, address, email, idForeign);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/successfulRegistration.html");
            dispatcher.forward(request, response);
        }
    }

    private void playerRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/playerRegister.html");
        dispatcher.forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("jsp/welcome.jsp");
    }

    private void displayPlayer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int playerId = Integer.parseInt(request.getParameter("id"));
        GameDAO gameDAO = new GameDAO();
        PlayerDAO playerDAO = new PlayerDAO();
        List<Game> gameList = gameDAO.getAllGame();
        request.setAttribute("gameList", gameList);
        request.setAttribute("player", playerDAO.searchPlayerById(playerId));
        List<Game> games = playerDAO.searchGameByIdPlayer(playerId);
        request.setAttribute("listGameOfPlayer", games);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/SelectPlayer.jsp");
        dispatcher.forward(request, response);
    }

    private void displayProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PlayerDAO playerDAO = new PlayerDAO();
        ProfileDao profileDao = new ProfileDao();
        Profile profile = profileDao.getProfileByAccountId(Integer.parseInt(request.getParameter("account_id")));
        request.setAttribute("profile", profile);

        AccountDao accountDao = new AccountDao();
        String role = accountDao.getRoleByAccountId(Integer.parseInt(request.getParameter("account_id")));

        if (role.equals("user")) {


        } else {
            Player player = accountDao.getPlayerByAccountId(Integer.parseInt(request.getParameter("account_id")));
            request.setAttribute("player", player);

            List<Game> games = playerDAO.searchGameByIdPlayer(player.getPlayer_id());
            request.setAttribute("listGameOfPlayer", games);

            RequestDAO requestDAO = new RequestDAO();
            List<Request> requests = requestDAO.getRequestByIdPlayer(player.getPlayer_id());
            request.setAttribute("requests", requests);


            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/playerProfile.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void createGame(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SQLException, IOException {
        int idInt = Integer.parseInt(request.getParameter("idPlayer"));

        GameDAO gameDAO = new GameDAO();
        List<Game> gameList = gameDAO.getAllGame();
        List<String> options = new ArrayList<>();
        List<Integer> intOptions = new ArrayList<>();
        for (Game g : gameList) {
            String game = request.getParameter("" + g.getId());
            if (game != null) {
                options.add(game);
            }

        }
        for (String option : options) {
            int intOption = Integer.parseInt(option);
            intOptions.add(intOption);
        }
        for (int option : intOptions) {
            // Thực hiện các xử lý tương ứng với giá trị của option ở đây
            gameDAO.createGamePlayer(option, idInt, 100);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/successfulRegistration.html");
        dispatcher.forward(request, response);
    }

    private void showDepositForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Deposit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFormAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/acceptRequest.jsp");
        dispatcher.forward(request, response);
    }

    private void showFormRefuse(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/refuseRequest.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deposit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("account_id"));
        int coin = Integer.parseInt(request.getParameter("coin"));
        User user = userDAO.getUserByAccountId(id);
        if (user.getName() != null) {
            int currentCoin = user.getCoin();
            int afterDeposit = currentCoin + coin;
            userDAO.deposit(afterDeposit , id);
            request.setAttribute("id", id);
            request.setAttribute("coin", afterDeposit);
            RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/DepositSuccess.jsp");
            dispatcher.forward(request, response);
        }
    }
}

