package movie2019.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Ư�� ����Ͻ� ��û���� �����ϰ� ��� ���� ActionForward Ÿ������ ��ȯ�ϴ�
//�޼��尡 ���ǵǾ� �ִ�.
//Action: �������̽� ��
//ActionForward: ��ȯ��
public interface Action {
    public ActionForward execute(HttpServletRequest request,
    		  HttpServletResponse response)
    throws Exception;
}