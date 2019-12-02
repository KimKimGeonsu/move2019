package movie2019.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import movie2019.board.action.Action;
import movie2019.board.action.ActionForward;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
	  HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		List<BoardVO> boardlist = 
				new ArrayList<BoardVO>();
		
		//�⺻ �� ������
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("�Ѿ�� ������="+ page);
		
		//�˻��� ����Ʈ�� limit���� �Ѿ����...?
		if(request.getParameter("limit")!=null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		System.out.println("�Ѿ�� limit="+limit);
		
		//�� ����Ʈ �޾ƿ´�.
		int listcount = boarddao.getListCount();
		
		//����Ʈ �޾ƿ´�.
		boardlist = boarddao.getBoardList(page,limit);
		
		/*
		 * �� ������ �� =
		 * (DB�� ����� �� ����Ʈ�� �� + �� ���������� �����ִ� ����Ʈ�� �� - 1)/ �� ���������� �����ִ� ����Ʈ�� ��
		 * 
		 * ���� ��� �� ���������� �����ִ� ����Ʈ�� ���� 10���� ���
		 * ��1) DB�� ����� �� ����Ʈ�� ���� 0�̸� �� ������ ���� 0������
		 * ��2) DB�� ����� �� ����Ʈ�� ����(1~10)�̸� �� ���������� 1������
		 * ��3) DB�� ����� �� ����Ʈ�� ����(11~20)�̸� �� ���������� 2������
		 * ��4) DB�� ����� �� ����Ʈ�� ����(21~30)�̸� �� ���������� 3������
		 */
		int maxpage = (listcount+limit-1)/limit;
		System.out.println("�� ������ ��="+maxpage);
		
		/*
		 * startpage: ���� ������ �׷쿡�� �� ó���� ǥ�õ� ������ ���� �ǹ��Ѵ�.
		 *           ([1], [11], [21] �� ...)
		 * ������ �������� 30���� ��� [1][2][3]...[30]���� �� ǥ���ϱ⿡�� �ʹ� 
		 * ���� ������ ���� �� ���������� 10������ �������� �̵��� �� �ְ� ǥ���Ѵ�.
		 * ��) ������ �׷��� �Ʒ��� ���� ���
		 *   [1][2][3][4][5][6][7][8][9][10]
		 *   ������ �׷��� ���� �������� startpage�� 
		 *   ������ �������� endpage�� ���Ѵ�.
		 *   
		 *   ���� 1~10�������� ������ ��Ÿ�� ���� ������ �׷���
		 *   [1][2][3]...[10]�� ǥ�õǰ�
		 *   11~20�������� ������ ��Ÿ�� ���� ������ �׷���
		 *   [11][12][13]...[20]���� ǥ�õȴ�.
		 */   
		int startpage = ((page-1)/10)*10+1;
		System.out.println("���� �������� ������ ���� ������ ��="+startpage);
		
		//endpage: ���� ������ �׷쿡�� ������ ������ ������ ��([10],[20],[30] ��)
		int endpage = startpage + 10 -1;
		System.out.println("���� �������� ������ ������ ������ ��="+endpage);
		
		/*
		 * ������ �׷��� ������ ������ ���� �ִ� ������ ���̴�.
		 * ���� ������ ������ �׷��� [21]~[30]�� ���
		 * ����������(startpage=21)�� ������ ������(endpage=30)������
		 * �ִ� ������(maxpage)�� 25��� [21]~[25]������ ǥ�õǵ��� �Ѵ�.
		 */
		if(endpage>maxpage) endpage = maxpage;
		
		String state = request.getParameter("state");
		
		if(state==null) {
			System.out.println("state=null");
		
		request.setAttribute("page", page); //���� ������ ��
        request.setAttribute("maxpage", maxpage); //�ִ� ������ ��
        
        //���� �������� ǥ���� ù ������ ��
        request.setAttribute("startpage", startpage);
        
        //���� �������� ǥ���� �� ������ ��
        request.setAttribute("endpage", endpage);
        request.setAttribute("listcount", listcount); //�� ���� ��
        
        //�ش� �������� �� ����� ���� �ִ� ����Ʈ
        request.setAttribute("boardlist", boardlist);
        
        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        
        //�� ��� �������� �̵��ϱ� ���� ��θ� �����Ѵ�.
        forward.setPath("Page/Board/board/board_list.jsp");
        return forward; //BoardFrontController.java�� ���ϵȴ�.
	
	}else {
        System.out.println("state=ajax");
        JsonObject object = new JsonObject();
        object.addProperty("page", page);
        object.addProperty("maxpage", maxpage);
        object.addProperty("startpage", startpage);
        object.addProperty("endpage", endpage);
        object.addProperty("listcount", listcount);
        object.addProperty("limit", limit);
        //List => JsonArray
         JsonArray je =
        		 new Gson().toJsonTree(boardlist)
        		           .getAsJsonArray();
	
         //List => JsonElement
         //JsonElement je = new Gson().toJsonTree(boardlist);
         System.out.println("je="+je);
         object.add("boardlist", je);
         
         Gson gson = new Gson();
         String json = gson.toJson(object);
         
         response.setContentType("text/html;charset=utf-8");
         response.getWriter().append(json);
         System.out.println(json);
         return null;
	}
	
	}

}
