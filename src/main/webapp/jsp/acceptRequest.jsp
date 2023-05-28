<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
  <style>
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
    .header a{
      text-decoration: none;
      font-size: 20px;
      font-family: Arial;
      font-weight: bold;
      color: #ffff;
      letter-spacing: 2px;
    }
    .header a span{
      font-size: 120%;
      color: #03e9f4;
      animation: flexing 2s infinite;
    }
    @keyframes flexing {
      from{text-shadow: 0 0 5px #03e9f4,
      0 0 25px #03e9f4}
      to{text-shadow: 0 0 50px #03e9f4,
      0 0 100px #03e9f4;}
    }
    body{
      background-color: #243b55;
      padding: 0;
      margin: 0;
    }
    button{
      padding: 8px 10px;
      border-radius: 5px;
      background-color: #03e9f4;
      color: #fff;
      box-shadow: 0 0 0 1px #03e9f4;
      border: none;
      cursor: pointer;
      font-family: sans-serif Arial;
      font-weight: 600;
    }
    button:hover{
      color: #03e9f4;
      background: #fff;
    }
    ::-webkit-scrollbar-track{
      background: #141e30;
    }
    ::-webkit-scrollbar-thumb{
      background: #03e9f4;
      border-radius: 2px;
    }
    .header2{
      display: flex;
      flex-direction: row;
      justify-content: right;
      padding-right: 10px;
    }
    .header{
      height: 64px;
      background-color: #141e30;
      position: fixed;
      display: flex;
      flex-direction: row;
      align-items: center;
      text-indent: 40px;
      z-index: 1;
    }
    .header2 button{
      margin-right: 10px;
    }
    .content{
      margin: auto;
      height: 700px;
      background-color: #1f1f22;
      border-radius: 10px;
      display: flex;
      color: #03e9f4;
      font-family: Tahoma;
      font-size: 16px;
      justify-content: center;
    }
    .content img{
      border-radius: 50%;
      width: 200px;

    }
    .content fieldset{
      margin-top: 80px;
      border: 1px solid #03e9f4;
      width: 700px;
      height: 500px;
    }
    fieldset input{
      width: 200px;
      height: 30px;
      border-radius: 10px;
      border: none;
      margin-top: 20px;
    }
    fieldset label{
    }
    textarea{
      resize: none;
      margin-top: 20px;
      width: 400px;
      height: 100px;
    }
    #submit{
      width: 90px;
      color: white;
      background-color: #03e9f4;
      font-family: Tahoma;
      font-size: 14px;
      cursor: pointer;
    }
    #submit:hover{
      color: #03e9f4;
      background-color: white;
    }
    p{
      margin-top: 20px;
    }
    .coin{
      color: #03e9f4;
      font-size: 17px;
      margin-left: 20px;
    }
    .coin span{
      color: yellow;
      font-size: 120%;
    }
  </style>
</head>
<body>
<div class="header col-12">
  <div class="header1 col-6">
    <a href="/quat?action=goHomePage&account_id=${requestScope['id']}&coin=${requestScope['coin']}">QUAT<span>DUO</span></a>
  </div>
  <div class="header2 col-6">
    <a href="/quat?action=deposit&account_id=${requestScope['id']}&coin=${requestScope['coin']}"><p class="coin">Coin: <span>${requestScope['coin']}</span></p></a>
    <a href="/quat?action=goHomePage&account_id=${requestScope['id']}&coin=${requestScope['coin']}"><button>Trang chủ</button></a>
    <a href="/quat?action=deposit&account_id=${requestScope['id']}&coin=${requestScope['coin']}"><button>Nạp tiền</button></a>
    <a href="/quat?action=logout"><button>Đăng xuất</button></a>
  </div>
</div>
<div class="content col-10">
  <form action="/quat" method="post">
    <input type="hidden" name="action" value="accept_request">
    <input type="hidden" name="account_id" value= ${requestScope['id']}>
    <input type="hidden" name="request_id" value=${request.getId()}>
    <input type="hidden" name="hours" value=${request.getHours()}>

    <fieldset>
      <legend>
        <img src="../image/player/default.jpg" alt="">
      </legend>
      <p>Tên người chơi: ${request.getUserName()}</p><br>
      <p>Giờ Thuê:${request.getHours()}</p><br>
      <p>Tin Nhắn:${request.getDescription()}</p><br>
      <input type="submit" value="Đồng ý" id="submit">
    </fieldset>
  </form>
</div>
</body>
</html>