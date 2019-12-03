package movie2019.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
Ư�� �����Ͻ� ��û���� �����ϰ� ��� ���� ActionForwardŸ������ ��ȯ�ϴ� �޼ҵ尡 ���ǵ� �ִ�.
Action : �������̽���
ActionForward : ��ȯ��
- ����� interface
*/

public interface Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
