package movie2019.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie2019.board.action.Action;
import movie2019.board.action.ActionForward;

public class BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
	      BoardDAO boarddao = new BoardDAO();
	      BoardVO boarddata = new BoardVO();
	      int result = 0;
	      
	      
	      //�Ķ���ͷ� �Ѿ�� ������ boarddata��ü�� ����
	      boarddata.setBOARD_NAME(request.getParameter("BOARD_NAME"));
	      boarddata.setBOARD_PASS(request.getParameter("BOARD_PASS"));
	      boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
	      boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
	      boarddata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
	      boarddata.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
	      boarddata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
	      
	      //�亯�� DB�� �����ϱ� ���� boarddata��ü�� �Ķ���ͷ� 
	      //DAO�� �ִ� boardReply�޼ҵ� ȣ��
	      result = boarddao.boardReply(boarddata);
	      
	      //�亯 ���� ����
	      if (result == 0) {
	         System.out.println("�亯 ����");
	         forward.setRedirect(false);
	         request.setAttribute("message", "�亯 �ۼ� ����");
	         forward.setPath("error/error.jsp");
	         return forward;
	      }
	      //�亯 ������ ����� �� ���
	      System.out.println("�亯 �Ϸ�");
	      forward.setRedirect(true);
	      //�亯 �� ���� Ȯ���� ���� �� ���뺸�� �������� ��η� ���� 
	      forward.setPath("BoardDetailAction.bd?num="+result);

	      return forward;
	   }
}
