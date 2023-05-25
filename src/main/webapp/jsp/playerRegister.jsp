<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 5/21/2023
  Time: 4:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Register</title>
  <style>
    html{
      height: 100%;
    }
    body{
      margin: 0;
      padding: 0;
      font-family: sans-serif;
      background: linear-gradient(
              #141e30, #243b55);
    }
    .register-box{
      position: absolute;
      top: 50%;
      left: 50%;
      width: 400px;
      padding: 40px;
      transform: translate(-50%, -50%);
      background: rgba(0,0,0,.5);
      box-sizing: border-box;
      box-shadow: 0 15px 25px rgba(0,0,0,.6);
    }
    .register-box h2{
      margin: 0 0 30px;
      padding: 0;
      color: #fff;
      text-align: center;
    }
    .register-box .user-box{
      position: relative;
    }
    .register-box .user-box input{
      width: 100%;
      padding: 10px 0;
      font-size: 16px;
      color: #fff;
      margin-bottom: 30px;
      border: none;
      border-bottom: 1px solid #fff;
      outline: none;
      background: transparent;
    }
    .register-box .user-box label{
      position: absolute;
      top: 0;
      left: 0;
      padding: 10px 0;
      font-size: 16px;
      color: #fff;
      pointer-events: none;
      transition: .5s;
    }
    .register-box .user-box input:focus~label,
    .register-box .user-box input:valid~label{
      top: -20px;
      left: 0;
      color: #03e9f4;
      font-size: 12px;
    }
    .register-box form #submit{
      position: relative;
      display: inline-block;
      padding: 10px 20px;
      color: #03e9f4;
      font-size: 16px;
      text-transform: uppercase;
      overflow: hidden;
      transition: .5s;
      margin-top: 40px;
      letter-spacing: 4px;
    }
    .register-box #submit:hover{
      cursor: pointer;
      background: #03e9f4;
      color: #fff;
      border-radius: 5px;
      box-shadow: 0 0 5px #03e9f4,
      0 0 25px #03e9f4,
      0 0 50px #03e9f4,
      0 0 100px #03e9f4;
    }
    input span{
      position: absolute;
      display: block;
    }
    input span:nth-child(1){
      top: 0;
      left: -100%;
      width: 100%;
      height: 2px;
      background: linear-gradient(90deg, transparent, #2196f3);
    }
    input:hover span:nth-child(1){
      left: 100%;
      transition: 1s;
    }

  </style>
</head>
<body>
<div class="register-box">
  <h2>Register</h2>
  <form action="/Admin" method="post">
    <div class="user-box">
      <input type="text" name="name" required="">
      <label>Enter your name</label>
    </div>
    <div class="user-box">
      <input type="text" name="age" required="">
      <label>Enter your age</label>
    </div>
    <div class="user-box">
      <input type="text" name="username" required="">
      <label>Username</label>
    </div>
    <div class="user-box">
      <input type="password" name="password" required="">
      <label>Password</label>
    </div>
    <div class="user-box">
      <input type="password" name="confirm" required="">
      <label>Confirm password</label>
    </div>
    <div class="user-box">
      <input type="text" name="games" required="">
      <label>Games</label>
    </div>
    <input hidden="hidden" name="action" value="createPlayer">
    <input type="submit" value="Submit" id="submit"/>
  </form>
</div>
</body>
</html>
