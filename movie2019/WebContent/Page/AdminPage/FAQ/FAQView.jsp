<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style>
.center-block{
	display:flex;
	justify-content:center;	/* ��� ���� */
}
span{
	background-color:grey;
}
</style>
<title>FAQ �Խ���</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<% 
boolean open = false;
if(request.getParameter("open") != null){
   open = Boolean.parseBoolean(request.getParameter("open"));
}
%>
<script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	if(<%=open%>)
		document.getElementById("main").style.marginLeft = "250px";

});

</script>
</head>
<body>
<% if(!open) {%>
<jsp:include page="/Page/Navi/Navi.jsp" />
<%} else{ %>
<jsp:include page="/Page/Navi/Navi3.jsp" />
<%} %>

<div id="main">
      <span style="font-size: 30px; cursor: pointer; color: white;"
         onclick="openNav()">&#9776;</span>
<div class="container">
	<table class="table table-striped">
		<tr><th colspan="2"><strong>FAQ �Խ���-view������</strong></th></tr>
		<tr><td>�۾���</td>
			<td>${faqdata.FAQ_NAME }</td></tr>
		<tr><td>����</td>
			<td>${faqdata.FAQ_SUBJECT }</td></tr>
		<tr><td>����</td>
			<td><textarea  class="form-control" rows="5" readOnly style="width:102%">
			${faqdata.FAQ_CONTENT }</textarea></td></tr>	
		<tr>
			<td colspan="2" class="center">
				<a href="FAQModifyView.fa?num=${faqdata.FAQ_NUMBER}">
					<button class="btn btn-info">����</button>
				</a>
				<a href="FAQDeleteAction.fa?num=${faqdata.FAQ_NUMBER}">
					<button class="btn btn-danger">����</button>
				</a>
				<a href="FAQList.fa?">
					<button class="btn btn-primary">���</button>
				</a>
			</td>
		</tr>
	</table>
</div>
</div>
</body>
</html>