<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����������:�� ���� ����</title>
<style>
*{margin:0; box-sizing: border-box; text-decoration: none;}
html,body{/*height:100%;*/
			height:400px;}
header {
	background-color: yellow;
	height: 100px;
	width: 100%;
	line-height: 100px;
	text-align: center;
}
</style>
</head>
<body>
 <div class="container">

    <form name="joinform">
    
       <div>
          <b>���̵�</b>
          <input type="text" placeholder="Enter id" id="id" name="id" maxLength="12" required>
          <span id="message"></span>
       </div>
       <div>
           <b>��й�ȣ</b>
           <input type="password" placeholder="Enter password" name="pass" required>
       </div>
       <div>
          <b>�̸�</b>
          <input type="text" placeholder="Enter name"  name="name" maxLength="15" required>
       </div>
       <div>
          <b>����</b>
          <input type="text" placeholder="Enter age"  name="age" maxLength=2 required>
       </div>
       <div>
          <b>����</b>
          <input type="radio" name="gender" value="��" checked><span>����</span>
          <input type="radio" name="gender" value="��"><span>����</span>
       </div>
       <div>
          <b>�̸��� �ּ�</b>
          <input type="text" placeholder="Enter email"  name="email" required>
          <span id="email_message"></span>
       </div>
       <div class="clearfix">
          <button type="submit" class="submitbtn">�α���</button>
          <button type="reset" class="cancelbtn">�ٽ��ۼ�</button>
        </div>
     </form>
  </div>
</body>
</html>