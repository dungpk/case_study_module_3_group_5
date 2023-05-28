<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/select.css">
</head>
<body>
<div class="header col-12">
    <div class="header1 col-6">
        <a href="/quat?action=goHomePage&accoun_id=${requestScope['id']}">QUAT<span>DUO</span></a>
    </div>
    <div class="header2 col-6">
        <a href="/quat?action=deposit&account_id=${requestScope['id']}"><p class="coin">Coin: <span>${requestScope['coin']}</span></p></a>
        <a href="/quat?action=goHomePage&account_id=${requestScope['id']}"><button>Trang chủ</button></a>
        <a href="/quat?action=deposit&account_id=${requestScope['id']}"><button>Nạp tiền</button></a>
        <a href="/quat?action=logout"><button>Đăng xuất</button></a>
    </div>
</div>
<div class="main col-12">
    <div class="headmain col-12"></div>
    <div class="sidebar col-2">
        <c:forEach items="${requestScope['gameList']}" var="game">
            <div class="imgside">
                <a href="/quat?action=search_player_by_game&id=${game.getId()}&account_id=${requestScope['id']}" ><img src="${game.getImageSource()}" alt=""></a>
                <a href="/quat?action=search_player_by_game&id=${game.getId()}&account_id=${requestScope['id']}" >${game.getName()}</a>
            </div>
        </c:forEach>
    </div>
    <div class="content col-10">
        <div class="main-content col-10">
            <div class="avatar col-5">
                <img src="${requestScope['player'].getImg()}" alt="">
            </div>
            <div class="dish col-7">
                <div id="head-dish" class="col-10">
                    <p>Xin chao minh la chao day</p>
                </div>
                <div id="game-dish" class="col-10">
                    <c:forEach items="${requestScope['listGameOfPlayer']}" var="game">
                        <img src="${game.getImageSource()}" alt="">
                    </c:forEach>
                </div>
                <div id="bottom-dish" class="col-12">
                    <p>ID: ${requestScope['player'].getPlayer_id()}</p>
                    <p>Tên: ${requestScope['player'].getName()}</p>
                    <p>Giá: ${requestScope['player'].getPrice()} coin/trận</p>
                    <a href="/quat?action=rent&account_id=${requestScope['id']}&player_id=${requestScope['player'].getPlayer_id()}" ><button>Thuê</button></a>
                    <button>Nhắn tin</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="comment-section col-12">
    <div class="comment1 col-10">
        <div class="com col-5">
            <form action="#">
                <h2 style="text-indent: 20px">Comment</h2>
                <textarea name="" id="myTextarea" maxlength="50"></textarea>
                <input type="submit" value="Gửi">
            </form>
            <div class="section col-12"></div>
        </div>
    </div>
    <div class="comment2 col-2">

    </div>
</div>
<div class="footer"></div>

</body>
</html>
<!--Gửi list game của player, list game, list comment, player-->