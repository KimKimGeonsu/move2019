
/* DataBean �����ͺ� Ŭ���� 
 
�Խ��ǿ��� ���Ǵ� �������� �����ͺ��̶�� �ϳ��� ��ü�� �����ϰ�,
�� ��ü�� �����ϸ� ������ �ϳ��� ������ �ʿ� ���� �Ѳ����� ��� ������ ���޵ȴ�.

�̷� Ŭ������ DTO(Data Transfer Object), VO(Value Object)��� �Ѵ�.
DB���� ������� �÷���� �����ϰ� ������Ƽ���� �����ؾ� ��.
*/

package movie2019.login.db;

public class Member {

	private String id;
	private String pass;
	private String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return pass;
	}
	public void setPassword(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
