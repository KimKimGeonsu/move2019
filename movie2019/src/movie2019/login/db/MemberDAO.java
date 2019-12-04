package movie2019.login.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	DataSource ds;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	int result;

	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		} catch (Exception ex) {
			System.out.println("DB ���� ���� : " + ex);
		}
	}

	public int isId(String id) {
		try {
			con = ds.getConnection();
			System.out.println("getConnection");

			String sql = "select USER_ID from USERS where USER_ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = 0; // DB�� �ش� ID�� �ֽ��ϴ�.
			} else {
				result = -1; // DB�� �ش� ID�� �ֽ��ϴ�.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	public int isId(String id, String pass) {
		try {
			con = ds.getConnection();
			System.out.println("getConnection");

			String sql = "select USER_ID, USER_PASS from USERS where USER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(2).equals(pass)) {
					result = 1; // ���̵�, ��� �� �� ��ġ�ϴ� ���
				} else {
					result = 0; // ��й�ȣ ��ġ���� �ʴ� ���
				}
			} else {
				result = -1; // ���̵� ����X
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

}
