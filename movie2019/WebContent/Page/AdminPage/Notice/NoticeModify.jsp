<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<script src="notice/writeform.js" charset="euc-kr"></script>
<style>
tr.center-block{text-align:center}
h1{font-size:1.5rem;text-align:center;color:#1a92b9}
.container{width:60%}
label{font-weight:bold}
#upfile{display:none}
img{width:20px}
span{
	background-color:grey;
}
</style>
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
	<form action="NoticeModifyAction.bo" method="post" 
	name="modifyform">
	<%--�亯�� �߰��ϱ� ���ؼ��� �亯�� �����ۿ� ���� NOTICE_RE_REF, NOTICE_RE_LEV, NOTICE_RE_SEQ ������ �ʿ��մϴ�. --%>
	<input type="hidden" name="NOTICE_NUM" value="${notice.NOTICE_NUM }">
		<h1>MVC �Խ���-Modify</h1>
		<div class="form-group">
			<label for="notice_name">�۾���</label>
			<input name="NOTICE_NAME" id="notice_name" value="${notice.NOTICE_NAME }" readOnly type="text"
					class="form-control">
		</div>
		<div class="form-group">
			<label for="notice_subject">����</label>
			<input name="NOTICE_SUBJECT" id="notice_subject" type="text" 
			value="Re: ${notice.NOTICE_SUBJECT }" class="form-control" maxlength="100" value="${notice.NOTICE_SUBJECT }">
		</div>
		<div class="form-group">
			<label for="notice_content">����</label>
			<textarea name="NOTICE_CONTENT" id="notice_content" rows="10" class="form-control">${notice.NOTICE_CONTENT }</textarea>
		</div>
		
		<div class="form-group">
			<button type=submit class="btn btn-primary">����</button>
			<button type=reset class="btn btn-danger"onClick="history.go(-1)">���</button>
		</div>
	</form>
</div>
</div>
</body>
</html>