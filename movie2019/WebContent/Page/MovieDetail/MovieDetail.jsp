<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery CDN -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<!--    <script type="text/javascript"
      src="http://code.jquery.com/jquery-2.1.4.js"></script>-->
<!-- Bootstrap Js CDN -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
	

<title>Insert title here</title>

<% 
String id = request.getParameter("id");

boolean open = false;
if(request.getParameter("open") != null){
	open = Boolean.parseBoolean(request.getParameter("open"));
}
String apikey = application.getInitParameter("APIKEY");

%>
<style>
.all {

margin-top:22vh;
	color: white;
}

.col-xs-12 {
	text-align: center;
	background: #000;
}

.col-xs-4, .col-xs-8 {
	text-align: center;
	background: #ccc;
	height: 500px;
}

.col-xs-8 {
	background: #bbb;
}

.movie_info {

 background-image: linear-gradient(to bottom, rgba(0,0,0,0), rgba(0,0,0,1), rgba(0,0,0,1), rgba(0,0,0,1), rgba(0,0,0,1), rgba(0,0,0,1), rgba(0,0,0,1), rgba(0,0,0,1), rgba(0,0,0,1));
}

.pickgradient {
	position: relative;
	display: inline-block;
}

.pickgradient:after {
	content: '';
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	display: inline-block;
	background: linear-gradient(to left, #000, rgba(0, 0, 0, 0.4),
		rgba(0, 0, 0, 0.1), rgba(0, 0, 0, 0.4), #000); /* W3C */
}


img {
	display: block;
}

.user_mv {
	
	background: #333;
}

.top_margin {
	margin-top: 5em;
}

.col-xs-4 {
	height: auto;
	background: inherit;
}

.review {
	padding: 3em;
	background: #333;
}
.detail_content{
color:white;}

.review_id {
	float: left;
}

.review_Star {
	float: right;
}


.col-xs-2:hover > .centered{
display:block;}

.col-xs-3:hover > .centered{
display:block;}
.centered p{
color:white;}
/* Centered text */
.centered {
display:none;
color:white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}


@keyframes leaves {
    0% {
        transform: scale(1.0);
        opacity: 1.0;
    }
    100% {
        transform: scale(1.05);
          opacity: 0.4;
    }
}


.col-xs-3:hover img{
  animation: leaves 0.5s;
  animation-fill-mode: forwards;

}


.col-xs-2:hover img{
  animation: leaves 0.5s;
  animation-fill-mode: forwards;

}



/*������ ��׶���*/
.video-background {
	background: #000;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	z-index: -99;
}

/*������ ��׶���*/
.video-foreground, .video-background iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	pointer-events: none;
}

/*������ ��׶���*/
@media ( min-aspect-ratio : 16/9) {
	.video-foreground {
		height: 100vh;
		top: -10vh;
		/*height: 300%;
		top: -100%; *//*��� ������ ���� �Ⱥ��̰��Ϸ���*/
	}
}

@media ( max-aspect-ratio : 16/9) {
	.video-foreground {
		width: 100vh;
		left: -10vh;
	}
}

/*������ ��׶���*/
@media ( min-aspect-ratio : 1/1) {
	.video-foreground {
		height: 100vh;
		top: -10vh;
		/*height: 300%;
		top: -100%; /*��� ������ ���� �Ⱥ��̰��Ϸ���*/
	}
}

@media ( max-aspect-ratio : 1/1) {
	.video-foreground {
		width: 100vh;
		left: -10vh;
	}
}

/*������ ��׶��� ������*/
iframe {
	opacity: 0.6;
}
.detail_similar{
padding-bottom: 3em;
}
</style>
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

<div class="video-background">
		<div class="video-foreground">
				<iframe id="detailVideo"
				src="video/aurora.mp4"
				frameborder="0"
				allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
				allowfullscreen> </iframe>
		</div>


	</div>


	<div class="all">
	
		<div class="row">
			
		</div>

		<div class="row movie_info">
		<div class="container">
			<div class="col-xs-2">
				<img class="img-responsive" id="detail_poster"
					src="https://image.tmdb.org/t/p/w500/tcmj4YQ6p79OD8ECpRAbV8Yjk0c.jpg">
			</div>
			<div class="col-xs-10">
				<h2>
					<b id="detail_title"></b>
				</h2>
				<h5 id="detail_genre_and_date"></h5>
				<p id="detail_content"></p>

				<div class="user_mv">
					<table>
						<tr>
							<td>�̹� �� ��ǰ�ΰ���?</td>
							<td>�ڡڡڡڡ�</td>
							<td>���� ���ƿ� �Ⱦ��</td>
						</tr>
					</table>
				</div>


				<h4 class="top_margin">
					<b>�⿬��</b>
				</h4>
				<div class="mv_guest row">

			

				</div>



				<h4 class="top_margin">
					<b>�� ����</b>
				</h4>
				<div class="row">
					<div class="col-xs-6">

						<canvas id="myChart" height="60vh" width="80vw"></canvas>

					</div>

					<div class="col-xs-6">

						<canvas id="myChart2" height="60vh" width="80vw"></canvas>

					</div>

				</div>


				<h4 class="top_margin">
					<b>����</b>
				</h4>

				<div class="row">
					<div class="col-xs-4">
						<div class="review">
							<div>
								<span class="review_id">duswl13</span> <span class="review_star">��3.5</span>
							</div>
							<hr>
							<h4>���ο����</h4>
							<p>���ο���� ���ο���亰�ο���亰�ο���亰�ο���亰�ο���亰�ο����</p>
						</div>
					</div>

					<div class="col-xs-4">
						<div class="review">
							duswl13 ��3.5
							<hr>
							<h4>���ο����</h4>
							<p>���ο���� ���ο���亰�ο���亰�ο���亰�ο���亰�ο���亰�ο����</p>
						</div>
					</div>

					<div class="col-xs-4">
						<div class="review">
							duswl13 ��3.5
							<hr>
							<h4>���ο����</h4>
							<p>���ο���� ���ο���亰�ο���亰�ο���亰�ο���亰�ο���亰�ο����</p>
						</div>
					</div>

				</div>



				<h4 class="top_margin">
					<b>����� ��ȭ</b>
				</h4>

				<div class="detail_similar row">
					
				</div>







				<div></div>

			</div>
		</div>

</div>

</div>
	</div>
</body>

<script>


if(<%=open%>)
document.getElementById("main").style.marginLeft = "250px";
	
	
	
	$.ajax({
	
		url : 'http://api.themoviedb.org/3/movie/'
		+<%=id%>+'/videos?api_key=<%=apikey%>',
		dataType : 'json',
		cache : false,
		success : function(data) {

			console.log('���� ���� ����');
			var list = data.results;

			if(list[0].key != null)
			printVideo(list[0].key);
			
		},
		error : function(request, status, error) {
			console.log('���� error');
		},
		complete : function() {
			console.log('���� �Ϸ�');

		}
	});
	
	
	
	
	$.ajax({
				url : 'https://api.themoviedb.org/3/movie/<%=id%>?api_key=<%=apikey%>&language=ko-KO', //��û ���� url
				dataType : 'json',
				cache : false,
				success : function(data) {
					console.log('�������� ����');
					var list = data;

					printDetail(list);
					

				}, //HTTP ��û�� ������ ��� ����
				error : function(request, status, error) {
					console.log('�������� error');

				},
				complete : function() {
					console.log('�������� �Ϸ�');

				} 
			});
	
	
	
	
	$.ajax({
		url : 'https://api.themoviedb.org/3/movie/<%=id%>/credits?api_key=<%=apikey%>', //��û ���� url
		dataType : 'json',
		cache : false,
		success : function(data) {
			console.log('ũ���� ����');
			var list = data;

			printCredits(list.cast);
			

		}, //HTTP ��û�� ������ ��� ����
		error : function(request, status, error) {
			console.log('ũ���� error');

		},
		complete : function() {
			console.log('ũ���� �Ϸ�');

		} 
	});

	
	
	$.ajax({
		url : 'https://api.themoviedb.org/3/movie/<%=id%>/similar?api_key=<%=apikey%>&language=ko-KO&page=1', //��û ���� url
		dataType : 'json',
		cache : false,
		success : function(data) {
			
			var list = data.results;
			console.log('����� ��ȭ ���� , ���� :' + list.length);
			printSimilar(list);
			

		}, //HTTP ��û�� ������ ��� ����
		error : function(request, status, error) {
			console.log('ũ���� error');

		},
		complete : function() {
			console.log('ũ���� �Ϸ�');

		} 
	});

	
	
	
	
	
	function printVideo(videokey) {

		$('#detailVideo')
				.prop(
						'src',
						'https://www.youtube.com/embed/'
								+ videokey
								+ '?version=3&mute=1&loop=1&autoplay=1&rel=0&controls=0&showinfo=0&playlist='
								+ videokey);
	}
	
	
	function printDetail(list){
		
		if(list.original_language == "ko")
			console.log(list.title);
		$('#detail_title').text(list.title);
		
		$("#detail_content").text(list.overview);
		
		var genre = list.genres;
		var genre_text = '';
		for(var i =0; i< genre.length; i++)
			genre_text +=genre[i].name+" ";
		
		
		$('#detail_genre_and_date').text(genre_text + " " + list.release_date);
		$("#detail_poster").prop('src','https://image.tmdb.org/t/p/w500'+list.poster_path);
	}

	
	function printCredits(list){
		
		
		
		var tag = '';
		console.log("list:"+list.length);
	
		var check = 0;
		for(var i = 0; i < 6; i++){
			if(i < list.length){
				
				if(list[check].profile_path == null){
					check ++;
					i--;
					continue;
				}
					
				
			console.log("�̸�:"+list[check].name);
			console.log("������:"+list[check].profile_path);
			tag += '<div class="col-xs-2">';
			tag += '<a href="PersonDetail.ml?id=' + list[check].id + '&name='+list[check].name+'"><img class="img-responsive"';
			tag += '	src="https://image.tmdb.org/t/p/w500'+list[check].profile_path+'"></a>';
			
			tag += '<div class="centered">\n';
			tag += '<h5>' + list[check].name + '</h5></div>\n';
			
			tag += '<p>'+list[check].name+'</p>';
			tag += '</div>';
			check ++;
			}
		}
		
		console.log("tag : "+ tag);
		$('.mv_guest').html(tag);
		
	}
	
	function printSimilar(list){
		var tag = '';
		
	
		var check = 0;
		
		for(var i = 0; i < 4; i++){
			if(check < list.length){
				
				if (list[check].poster_path == null
						|| list[check].overview == null
						|| list[check].genre_ids == null
						|| list[check].overview == ""
						|| list[check].genre_ids == ""
						|| list[check].overview.includes('����')){
					
					i--;
					check++;
					console.log('�̻��� �Ŵϱ� �ѱ�' + check);

					continue;
					
				}
				
				
				tag+= '<div class="col-xs-3">';
				tag += '<a href="moviedetail.ml?open=false&id=' + list[check].id + '">\n';
				tag+= '<img class="img-responsive"';
				tag+= '	src="https://image.tmdb.org/t/p/w500'+list[check].poster_path+'">';
				tag+= '</a>';
				
				tag += '<div class="centered">\n';
				tag += '<h5>' + list[check].original_title + '</h5>\n';
				tag += '<p>' + list[check].overview.substring(0,50) + '...</p></div>\n';
				tag+= '<p>'+list[check].original_title+'</p>';
				
				tag+= '</div>';
				check++;
			}
		}
	
		$('.detail_similar').html(tag);
	}





	// �켱 ���ؽ�Ʈ�� �����ɴϴ�. 
	var ctx = document.getElementById("myChart").getContext('2d');
	var ctx2 = document.getElementById("myChart2").getContext('2d');
	/*
	 - Chart�� �����ϸ鼭, 
	 - ctx�� ù��° argument�� �Ѱ��ְ�, 
	 - �ι�° argument�� �׸��� �׸��� �ʿ��� ��ҵ��� ��� �Ѱ��ݴϴ�. 
	 */

	Chart.defaults.global.defaultFontColor = 'white';

	var myPieChart = new Chart(ctx,
			{
				type : 'doughnut',

				data : {

					labels : [ "1", "2", "3", "4", "5" ],
					datasets : [ {
						label : '# of Votes',
						data : [ 5, 10, 30, 15, 40 ],
						backgroundColor : [ 'rgba(255, 99, 132, 0.8)',
								'rgba(54, 162, 235, 0.8)',
								'rgba(255, 206, 86, 0.8)',
								'rgba(75, 192, 192, 0.8)',
								'rgba(153, 102, 255, 0.8)' ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)',
								'rgba(255, 206, 86, 1)',
								'rgba(75, 192, 192, 1)',
								'rgba(153, 102, 255, 1)' ],
						borderWidth : 1
					} ]
				},
				options : {
					maintainAspectRatio : true, // default value. false�� ��� ���Ե� div�� ũ�⿡ ���缭 �׷���.
					scales : {
						yAxes : [ {
							ticks : {
								display : false
							}
						} ]
					}
				}
			});

	var myPieChart2 = new Chart(ctx2,
			{
				type : 'doughnut',
				data : {
					labels : [ "���ƿ�", "�Ⱦ��" ],
					datasets : [ {
						label : '# of Votes',
						data : [ 75, 25 ],
						backgroundColor : [ 'rgba(255, 99, 132, 0.8)',
								'rgba(54, 162, 235, 0.8)' ],
						borderColor : [ 'rgba(255,99,132,1)',
								'rgba(54, 162, 235, 1)' ],
						borderWidth : 1
					} ]
				},
				options : {
					maintainAspectRatio : true, // default value. false�� ��� ���Ե� div�� ũ�⿡ ���缭 �׷���.
					scales : {
						yAxes : [ {
							ticks : {
								display : false
							}
						} ]
					}
				}
			});
</script>
</html>