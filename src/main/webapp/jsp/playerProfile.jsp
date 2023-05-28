<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%
    String message = (String) session.getAttribute("message");
%>

<% if (message != null && !message.isEmpty()) { %>
<script>
    alert("<%= message %>");
</script>
<%
        session.removeAttribute("message");
    } %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/profile.css">
</head>
<body>
<div class="header col-12">
    <div class="header1 col-6">
        <a href="/quat?action=goHomePage&account_id=${requestScope['id']}">QUAT<span>DUO</span></a>
    </div>
    <div class="header2 col-6">
        <a href="/quat?action=deposit&account_id=${requestScope['id']}"><p class="coin">Coin: <span>${requestScope['coin']}</span></p></a>
        <a href="/quat?action=goHomePage&account_id=${requestScope['id']}"><button>Trang chủ</button></a>
        <a href="/quat?action=logout"><button>Đăng xuất</button></a>
    </div>
</div>
<div class="content col-12">
  <div class="headmain col-12"></div>
  <div class="main col-9">
      <div class="name col-10">
        <div class="name1 col-6">
            <div class="imgname col-6">
                <img src="${requestScope['player'].getImg()}" alt="" width="80px">
            </div>
            <div class="infoname col-6">
                <p>ID: ${requestScope['player'].getPlayer_id()}</p>
                <p>Name: ${requestScope['player'].getName()}</p>
                <p>Rank: ${requestScope['player'].getRank()}</p>
            </div>
        </div>
        <div class="name2 col-6">

        </div>
      </div>
        <div class="imgscr col-10">
            <div class="picture col-5">
              <img src="${requestScope['player'].getImg()}" alt="" width="350px">
            </div>
            <div class="info col-7">
                <div class="game col-10">
                    <div class="slide">
                        <p>Xin chao minh la chao day</p>
                    </div>
                </div>
                <div class="realinfo col-10">
                    <div class="infogame1 col-12">
                        <c:forEach items="${requestScope['listGameOfPlayer']}" var="game">
                            <img src="${game.getImageSource()}" alt="">
                        </c:forEach>
                    </div>
                    <div class="infogame2 col-12">
                        <button>Chỉnh sửa</button>
                    </div>
                </div>
            </div>
        </div>
      </div>
  </div>
  <div class="sidebar col-3">
      <c:forEach items="${requestScope['requests']}" var="request">
      <div class="request">
          <div id="imageRequest" class="col-6">
              <img src="../image/player/default.jpg" alt="">
          </div>
          <div id="mainRequest" class="col-6">
              <p>Tên: ${request.getUserName()}</p>
              <p>Giờ thuê: ${request.getHours()}</p>
              <p>Tin nhắn: ${request.getDescription()}</p>
              <span>
                    <a href="/quat?action=accept_request&account_id=${requestScope['id']}&request_id=${request.getId()}&name_user=${request.getUserName()}&hours=${request.getHours()}&des=${request.getDescription()}"><button>Đồng ý</button></a>
                    <a href="/quat?action=refuse_request&account_id=${requestScope['id']}&request_id=${request.getId()}&name_user=${request.getUserName()}&hours=${request.getHours()}&des=${request.getDescription()}"><button>Huỷ</button></a>
              </span>
          </div>
      </div>
      </c:forEach>
  </div>
<div class="rate col-12">
    <div class="rateinfo col-5">
        <div class="form col-12">
            <form action="">
                <h2 style="text-indent: 30px">Comment</h2>
                <textarea name="type"></textarea>
                <input type="submit" value="Submit" id="submit">
            </form>
        </div>
        <div class="bluan col-12">
            <div class="comment col-12">
                <a href="" ><img src="../image/player/default.jpg" alt=""><span>Hà Linh</span></a>
                <p class="comsection">sdafafafadsf</p>
            </div>
            <div class="comment col-12">
                <a href="" ><img src="../image/player/default.jpg" alt=""><span>Hà Linh</span></a>
                <p class="comsection">sdafafafadsf</p>
            </div>
            <div class="comment col-12">
                <a href="" ><img src="../image/player/default.jpg" alt=""><span>Hà Linh</span></a>
                <p class="comsection">sdafafafadsf</p>
            </div>
            <div class="comment col-12">
                <a href="" ><img src="../image/player/default.jpg" alt=""><span>Hà Linh</span></a>
                <p class="comsection">sdafafafadsf</p>
            </div>
            <div class="comment col-12">
                <a href="" ><img src="../image/player/default.jpg" alt=""><span>Hà Linh</span></a>
                <p class="comsection">sdafafafadsf</p>
            </div>
            <div class="comment col-12">
                <a href="" ><img src="../image/player/default.jpg" alt=""><span>Hà Linh</span></a>
                <p class="comsection">sdafafafadsf</p>
            </div>
            <div class="comment col-12">
                <a href="" ><img src="../image/player/default.jpg" alt=""><span>Hà Linh</span></a>
                <p class="comsection">sdafafafadsf</p>
            </div>
        </div>
    </div>
    <div class="dexuat col-3">
        <h2>Đề xuất</h2>
        <div class="xx">
            <img src="../image/player/default.jpg" alt="" width="80px">
        </div>
        <div class="xx"></div>
        <div class="xx"></div>
        <div class="xx"></div>
    </div>
</div>
</div>
<div class="footer col-12">
</div>

</body>
</html>