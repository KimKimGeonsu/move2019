<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.sidenav {
	height: 100%;
	width: 0;
	position: fixed;
	z-index: 1;
	top: 0;
	left: 0;
	background-color: #000;
	overflow-x: hidden;
	transition: 0.5s;
	padding-top: 60px;
}

.sidenav a {
	padding: 8px 8px 8px 32px;
	text-decoration: none;
	font-size: 25px;
	color: #818181;
	display: block;
	transition: 0.3s;
}

.sidenav a:hover {
	color: #f1f1f1;
}

.sidenav .closebtn {
	position: absolute;
	top: 0;
	right: 25px;
	font-size: 36px;
	margin-left: 50px;
}

#main {
	transition: margin-left .5s;
	padding: 16px;
}

@media screen and (max-height: 450px) {
	.sidenav {
		padding-top: 15px;
	}
	.sidenav a {
		font-size: 18px;
	}
}

#accordian {
	background: #000;
	width: 250px;
	margin: 100px auto 0 auto;
	color: white;
}

ul {
	padding-left: 10px;
	margin-right: 10px;
}

/* �޴� ��Ÿ�� */
#accordian h2 {
	font-size: 14px;
	line-height: 34px;
	padding: 0 10px;
	cursor: pointer;
}

#accordian h2:hover {
	text-shadow: 0 0 1px rgba(255, 255, 255, 0.7);
}

/* ��������Ʈ ��Ÿ�� */
#accordian h2 span {
	font-size: 16px;
	margin-right: 10px;
}

#accordian li {
	list-style-type: none;
}

/* ����޴� ��Ÿ�� */
#accordian ul ul li a {
	color: white;
	text-decoration: none;
	font-size: 12.5px;
	line-height: 27px;
	display: block;
	padding-top: 8px;
	padding-bottom: 8px;
	-webkit-transition: all 0.15s;
	-moz-transition: all 0.15s;
	-o-transition: all 0.15s;
	-ms-transition: all 0.15s;
	transition: all 0.15s;
	-webkit-transition: all 0.15s;
}

#accordian ul ul li a:hover {
	background: #003545;
	border-left: 5px solid #09c;
}

/* active Ŭ���� �ܿ� ���� ������ �ʰ� �ϱ� */
#accordian ul ul {
	display: none;
}

#accordian li.active ul {
	display: block;
}

#search-form {
	width: 100%;
	padding: 8px;
	color: white;
	border: 0;
	background-color: #141414;
	margin-bottom: 3em;
}

#login {
	width: 100%;
	padding: 8px;
	color: white;
	border: 1 solid white;
	background-color: #141414;
	margin-top: 3em;
}
</style>
<script>

function enterkey() {
    if (window.event.keyCode == 13) {
    	location.href='Search.ml?open=true&key='+$('#search-form').val().replace(/(\s*)/g, "");
    }
}


</script>
</head>
<body>



	<div id="mySidenav" class="sidenav">
		<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>



		<div id="accordian">

			

			<ul>
				<li><input type="text" id="search-form" placeholder="Search"
					onkeyup="enterkey();"></li>
			</ul>

			<ul>
				<li>
					<h2><a href='MovieList.ml?option=0'>��ȭ</a></h2>

				</li>
				<li>
					<h2><a href="list.ch">��õ</a></h2>
				</li>
				<li>
					<h2><a href='Page/Review/r_list.jsp'>����</a></h2>
				</li>
				<li>
					<h2><a href="Page/Board/board/board_list.jsp">�Խ���</a></h2>
				</li>

				<li>
					<h2>����������</h2>
					<ul>
						<li><a href="Page/MyPage/MyInfo/MyInfo.jsp">�� ���� ����</a></li>
						<li><a href="Page/MyPage/Like/Like.jsp">���� ������</a></li>
						<li><a href="Page/MyPage/MyReview/MyReview.jsp">�� ���� ����</a></li>
						<li><a href="Page/MyPage/Hidden/Hidden.jsp">���� ������ ����</a></li>
					</ul>
				</li>
				<li>
					<h2>��������</h2>
				</li>
				<li>
					<h2>������ ������</h2>
					<ul>
						<li><a href="NoticeList.bo">�������� ����</a></li>
						<li><a href="Page/AdminPage/FAQ/FAQList.jsp">FAQ ����</a></li>
						<li><a href="Page/AdminPage/UserList/UserList.jsp">ȸ�� ����</a></li>
					</ul>
				</li>
			</ul>
			
			<ul>
				<li><button type="button" id="login">Login</button></li>
			</ul>
			
		</div>
	</div>

	<script>
	
	openNav();
	
		function openNav() {
			document.getElementById("mySidenav").style.width = "250px";
			document.getElementById("main").style.marginLeft = "250px";
		}

		function closeNav() {
			document.getElementById("mySidenav").style.width = "0";
			document.getElementById("main").style.marginLeft = "0px";
		}

		$(function() {
			$("#accordian h2").click(function() {
				$("#accordian ul ul").slideUp();
				if (!$(this).next().is(":visible")) {
					$(this).next().slideDown();
				}
			})
		})
	</script>

</body>
</html>
