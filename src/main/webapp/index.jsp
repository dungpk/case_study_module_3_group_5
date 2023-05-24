<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Slick Slider</title>
  <link rel="preconnect" href="https://fonts.gstatic.com" />
  <link
          href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap"
          rel="stylesheet"
  />
  <script
          type="module"
          src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
  ></script>
  <script
          nomodule
          src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
  ></script>
  <link rel="stylesheet" href="./css/reset.css" />
  <link
          rel="stylesheet"
          type="text/css"
          href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"
  />
  <link rel="stylesheet" href="./css/app.css" />

  <style>
    body{
      margin: 0;
      padding: 0;
    }
    .col-1{
      width: 8.33%;
    }
    .col-2{
      width: 16.66%;
    }
    .col-3{
      width: 25%;
    }
    .col-4{
      width: 33.33%;
    }
    .col-5{
      width: 41.66%;
    }
    .col-6{
      width: 50%;
    }
    .col-7{
      width: 58.33%;
    }
    .col-8{
      width: 66.66%;
    }
    .col-9{
      width: 75%;
    }
    .col-10{
      width: 83.33%;
    }
    .col-11{
      width: 91.66%;
    }
    .col-12{
      width: 100%;
    }
    .header{
      height: 64px;
      display: flex;
      flex-direction: row;
      background-color: white;
      border-bottom: 1px solid rgba(0, 0, 0.85);
      border-top: 1px solid rgba(0, 0, 0.85);
      box-shadow: rgba(0, 0, 0, 0) 0px 0px 0px 5px;
      position: fixed;
      z-index: 1;
    }
    .header1 a{
      text-decoration: none;
      font-size: 18px;
      font-family: sans-serif;
      color: black;
      margin-left: 40px;
    }
    #quat{
      color: black;
      font-weight: 900;
      letter-spacing: 2px;
      font-size: 20px;
    }
    #quat span{
      font-size: 120%;
      color: #2196f3;
    }
    .sidebar{
      height: 1000px;
      background-color: beige;
      border-right: 1px rgba(0, 0 ,0.85) ;
      float: left;
      display: flex;
      flex-direction: column;
    }
    .footer{
      clear: both;
      height: 100px;
      background-color: bisque;
    }
    .carousel{
      background-color: #2196f3;
      height: 264px;
      float: left;
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      border-radius: 5px;
      border-right: 5px solid gold;
      border-bottom: 5px solid gold;
    }
    .carousel img{
      width: 100px;
      height: 100px;
      margin-left: 40px;
      border-radius: 20%;
      border: goldenrod 5px solid;
    }
    .carousel label{
      position: relative;
      top: 114px;
      left: 2px;
      font-size: 22px;
      font-weight: 600;
      color: gold;
      background-color: #141e30;
      padding: 4px;
      border-radius: 5px;
    }
    #side1{
      width: 100%;
      height: 50%;

    }
    #side1 h2{
      text-indent: 40px;
      font-family: Arial;
      color: #2196f3;
    }
    .header1{
      width: 50%;
      display: inherit;
      height: 100%;
      align-items: center;
    }
    .header2{
      width: 50%;
      height: 100%;
      display: inherit;
      align-items: center;
      justify-content: right;
    }
    .header2 a{
      text-decoration: none;
      font-size: 18px;
      font-family: sans-serif;
      color: black;
      margin-right: 40px;
    }
  </style>
</head>
<body>
<div class="header col-12">
  <div class="header1">
    <a href="#" id="quat">QUAT<span>DUO</span></a>
    <a href="#">Trang chủ</a>
    <a href="#" id="dropdown">Tất cả dịch vụ</a>
    <a href="#">Khoảnh khắc</a>
  </div>
  <div class="header2">
    <a href="#">Trang cá nhân</a>
    <a href="#">Cài đặt</a>
    <a href="#">Đăng xuất</a>
  </div>
</div>
<div class="image-slider">
  <div class="image-item">
    <div class="image">
      <img
              src="https://images.unsplash.com/photo-1476718406336-bb5a9690ee2a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=687&q=80"
              alt=""
      />
    </div>
    <h3 class="image-title">This is demo title</h3>
  </div>
  <div class="image-item">
    <div class="image">
      <img
              src="https://images.unsplash.com/photo-1482049016688-2d3e1b311543?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=710&q=80"
              alt=""
      />
    </div>
    <h3 class="image-title">This is demo title</h3>
  </div>
  <div class="image-item">
    <div class="image">
      <img
              src="https://images.unsplash.com/photo-1484723091739-30a097e8f929?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=749&q=80"
              alt=""
      />
    </div>
    <h3 class="image-title">This is demo title</h3>
  </div>
  <div class="image-item">
    <div class="image">
      <img
              src="https://images.unsplash.com/photo-1467003909585-2f8a72700288?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=687&q=80"
              alt=""
      />
    </div>
    <h3 class="image-title">This is demo title</h3>
  </div>
  <div class="image-item">
    <div class="image">
      <img
              src="https://images.unsplash.com/photo-1511690656952-34342bb7c2f2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=928&q=80"
              alt=""
      />
    </div>
    <h3 class="image-title">This is demo title</h3>
  </div>
</div>
<script
        type="text/javascript"
        src="https://code.jquery.com/jquery-1.11.0.min.js"
></script>
<script
        type="text/javascript"
        src="https://code.jquery.com/jquery-migrate-1.2.1.min.js"
></script>
<script
        type="text/javascript"
        src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"
></script>
<script src="./app.js"></script>
</body>
</html>