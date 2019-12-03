package movie2019.review.action;

/* ActionForwardŬ����
Action�������̽����� ����� �����ϰ� �� ��� ���� ������ ������ �� �� ���Ǵ� Ŭ����.
�� Ŭ������ Redirect ���� ���� �������� �������� ��ġ�� ������ �ִ�.
�� ������ FrontController���� ActionForward Ŭ���� Ÿ������ ��ȯ���� ��������
�� ���� Ȯ���� �ش��ϴ� ��û �������� ������ ó����.
- ��, ������ �̵��� ���� ������ ��� Ŭ���� **
*/
public class ActionForward {
	private boolean redirect = false;
	private String path = null;

	// property redirect�� is�޼ҵ�
	public boolean isRedirect() {
		// ������Ƽ Ÿ���� boolean�� ��� get ��� is�� �տ� ���� �� �ִ�.
		return redirect;
	}

	// property redirect�� set�޼ҵ�
	public void setRedirect(boolean b) {
		redirect = b;
	}

	// property path�� get�޼ҵ�
	public String getPath() {
		return path;
	}

	// property path�� set�޼ҵ�
	public void setPath(String string) {
		path = string;
	}
}
