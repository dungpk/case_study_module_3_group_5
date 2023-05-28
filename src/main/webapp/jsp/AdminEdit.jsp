<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    body{
      padding: 0;
      margin: 0;
      background-color: #243b55;
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
    ::-webkit-scrollbar{
      border-radius: 10px;
      background: #8e8fb5;
      height: 5px;
      width: 5px;
    }
    .header2{
      display: flex;
      flex-direction: row;
      justify-content: right;
      padding-right: 10px;
    }
    .header2 button{
      margin-right: 10px;
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
    .main{
        height: 1000px;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    form{
        width: 800px;
        height: 500px;
        font-family: Tahoma;
        font-size: 17px;
        color: #03e9f4;
        padding-left: 20px;
    }
    form input{
        margin-top: 30px;
        height: 30px;
        border-radius: 5px;
        border: none;
        width: 300px;
    }
    fieldset{
        width: 100%;
        height: 100%;
    }
    fieldset img{
        border-radius: 50%;
        border: 3px solid #03e9f4;
        width: 200px;
    }
    #submit{
        width: 100px;
        cursor: pointer;
        color: white;
        background-color: #03e9f4;
        font-family: Tahoma;
        font-size: 17px;
    }
    #submit:hover{
        color: #03e9f4;
        background-color: white;
    }
  </style>
</head>
<body>
<div class="header col-12">
  <div class="header1 col-6">
    <a href="#">QUAT<span>DUO</span></a>
  </div>
  <div class="header2 col-6">
      <a href="/admin"><button>Trang chủ</button></a>
  </div>
</div>
<div class="main">
    <form action="/admin" method="post">
       <fieldset>
           <legend>
               <c:choose>
                    <c:when test="${requestScope['role'] == 'player'}">
                        <img src="${requestScope['account'].getImg()}" alt="">
                    </c:when>
                   <c:otherwise>
                       <img src="${requestScope['account'].getImage_source()}" alt="">
                   </c:otherwise>
               </c:choose>
           </legend>
            <label for="username">Username:</label>
           <input type="text" placeholder="Enter" name="username" id="username"><br>
           <label for="password">Password:</label>
           <input type="text" placeholder="Enter" name="password" id="password" style="margin-left: 5px"><br>
           <input type="submit" id="submit" value="Nắn con lợn này" id="submit">
       </fieldset>     
    </form>
</div>
</body>
</html>