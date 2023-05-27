<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<!--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>-->
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
        <a href="#">QUAT<span>DUO</span></a>
    </div>
    <div class="header2 col-6">
        <button>Trang chủ</button>
        <button>Nạp tiền</button>
        <button>Đăng xuất</button>
    </div>
</div>
<div class="main col-12">
    <div class="headmain col-12"></div>
    <div class="sidebar col-2">

    </div>
    <div class="content col-10">
        <div class="main-content col-10">
            <div class="avatar col-5">
                <img src="../image/player/default.jpg" alt="">
            </div>
            <div class="dish col-7">
                <div id="head-dish" class="col-10">
                    <p>Xin chao minh la chao day</p>
                </div>
                <div id="game-dish" class="col-10">
                    <img src="../image/game/cod.jpg" alt="">
                    <img src="../image/game/free_fire.jpg" alt="">
                    <img src="../image/game/cs.png" alt="">
                    <img src="../image/game/pubg.jpg" alt="">
                </div>
                <div id="bottom-dish" class="col-12">
                    <p>ID: </p>
                    <p>Tên:</p>
                    <p>Giá: </p>
                    <button>Thuê</button>
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