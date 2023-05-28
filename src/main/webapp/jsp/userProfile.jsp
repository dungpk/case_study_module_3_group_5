<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
  <link rel="stylesheet" href="../css/user.css">
</head>
<body>
    <div class="header col-12">
        <div class="header1 col-6">
            <a href="#">QUAT<span>DUO</span></a>
        </div>
        <div class="header2 col-6">
            <a href="/quat?action=deposit&account_id=${requestScope['id']}"><p class="coin">Coin: <span>${requestScope['coin']}</span></p></a>
            <a href="/quat?action=goHomePage&account_id=${requestScope['id']}"></a><button>Trang chủ</button>
            <a href="/quat?action=deposit&account_id=${requestScope['id']}"></a><button>Nạp tiền</button>
            <a href="/quat?action=logout"></a><button>Đăng xuất</button>
        </div>
    </div>
    <div class="main col-12">
      <div class="content col-10">
          <div class="infomation col-10">
              <div class="avatar col-6">
                  <div class="imageA col-12">
                      <img src="../image/player/default.jpg" alt="">
                  </div>
                  <div class="nickname">
                        
                  </div>
              </div>
              <div class="realinfo col-6">
                  <div class="box col-11">
                      <h1>Thông tin</h1>
                      <div class="header-box col-12">
                            <p>Tên: ${requestScope['user'].getName()}</p>
                            <p>Tuổi: ${requestScope['user'].getAge()}</p>
                            <p>Email: ${requestScope['user'].getEmail()}</p>
                            <p>Địa chỉ: ${requestScope['user'].getAddress()}</p>
                      </div>
                      <div class="footer-box col-12">
                          <a href="/quat?action=user_edit&account_id=${requestScope['id']}"><button>Chỉnh sửa</button></a>
                      </div>
                  </div>
                  <div class=""></div>
              </div>
          </div>
      </div>
        <div class="sidebar col-2"></div>
    </div>
    <div class="footer col-12"></div>
</body>
</html>