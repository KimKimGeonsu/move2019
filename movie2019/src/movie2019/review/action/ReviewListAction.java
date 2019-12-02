package movie2019.review.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import movie2019.login.action.ActionForward;
import movie2019.review.action.Action;
import movie2019.review.db.ReviewDAO;
import movie2019.review.db.ReviewVO;



public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {


		ReviewDAO reviewdao = new ReviewDAO();
		List<ReviewVO> reviewlist = new ArrayList<ReviewVO>();
		
		int page = 1;
		int limit = 10;
		
		if (request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������ = " + page);
		
		if (request.getParameter("limit") != null) {  //�� �κ� �߰�
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("�Ѿ�� limit = " + limit);
				
		
		//�� ����Ʈ �� �޾ƿ���.
		int listcount = reviewdao.getListCount();
		
		//����Ʈ �޾ƿ���
		reviewlist = reviewdao.getreviewList(page, limit);
		
		/* �� ������ �� = (DB�� ����� �� ����Ʈ + �� ���������� �����ִ� ����Ʈ - 1) / �� ���������� �����ִ� ����Ʈ
		 
		 * ���� ��� �� ���������� �����ִ� ����Ʈ�� ���� 10���� ���
		  DB�� ����� �� ����Ʈ�� ���� 0�̸� �� ������ ���� 0������
		  DB�� ����� �� ����Ʈ�� ���� (1~10)�̸� �� ������ ���� 1������
		  DB�� ����� �� ����Ʈ�� ���� (11~20)�̸� �� ������ ���� 2������
		  DB�� ����� �� ����Ʈ�� ���� (21~30)�̸� �� ������ ���� 3������
		  	  
		 */
		
		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("�� ������ �� = " + maxpage);
		
		/* startpage : ���� ������ �׷쿡�� �� ó���� ǥ�õ� ������ ��
		 			([1], [11], [21] ��...)
		 			
			������ �������� 30���� ��� [1][2][3]...[30]���� �� ǥ���ϱ⿣ �ʹ� �����ϱ�
			���� �� �������� 10������ ������ ǥ���Ѵ�.
			
			���� ��� ������ �׷��� �Ʒ��� ���� ���,			
			[1][2][3][4][5][6][7][8][9][10]
			������ �׷��� ���� �������� startpage��, ������ �������� endpage�� ����.
			
			���� 1~10�������� ������ ��Ÿ�� �� 
			������ �׷��� [1][2][3]...[10]�� ǥ��,
			11~10�������� ������ ��Ÿ�� �� 
			������ �׷��� [11][12][13]...[20]���� ǥ�õȴ�.
		 */
		
		int startpage = ((page-1) / 10) * 10 + 1;
		System.out.println("���� �������� ������ ���� ������ �� = " + startpage);
		
		int endpage = startpage + 10 - 1;
		System.out.println("���� �������� ������ ������ ������ �� = " + endpage);
		
		/*
		  ������ �׷��� ������ ������ ���� �ִ� ������ ��.
		  ������ ������ �׷��� [21]~[30]�� ���
		  ���������� (startpage=21)�� ������������(endpage=30)������
		  �ִ�������(maxpage=25)�� 25��� [21]~[25]������ ǥ�õǰ� �Ѵ�.
		 */
		
		if (endpage>maxpage) endpage = maxpage;
		
		String state = request.getParameter("state");
		
		if (state == null) {  // �� �κ� �߰�
			System.out.println("state=null");
			request.setAttribute("page", page); //���� ������ ��
			request.setAttribute("mexpage",maxpage);  //�ִ� ������ ��
		
		
		
		request.setAttribute("startpage", startpage);  //���� �������� ǥ���� ù ������
		request.setAttribute("endpage", endpage);  //���� �������� ǥ���� �� ������
		
		request.setAttribute("listcount", listcount); //�� ���� ��
		
		request.setAttribute("reviewlist", reviewlist); //�ش� �������� �� ����� ���� �ִ� ����Ʈ
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("review/review_list.jsp");
		return forward; //reviewFrontController.java�� ����.
		
	} else {  //�� �κ� �߰�
		System.out.println("state=ajax");
		JsonObject object = new JsonObject();
		object.addProperty("page", page);
		object.addProperty("maxpage", maxpage);
		object.addProperty("startpage", startpage);
		object.addProperty("endpage", endpage);
		object.addProperty("listcount", listcount);
		object.addProperty("limit", limit);
		//List => JsonArray
		JsonArray je = new Gson().toJsonTree(reviewlist).getAsJsonArray();
		
		//List => JsonElement
		//JsonElement je = new Gson().toJsonTree(reviewlist);
		System.out.println("je = " +je);
		object.add("reviewlist", je);
		
		Gson gson = new Gson();
		String json = gson.toJson(object);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append(json);
		System.out.println(json);
		return null;
		
		// http://localhost:8088/review_Ajax_bootstrap/reviewList.bo?page=1&state=ajax&limit=3 //���� Ȯ��
	}
	}
}
