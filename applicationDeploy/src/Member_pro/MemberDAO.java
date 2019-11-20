package Member_pro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MemberDAO {
	
	//회원가입
	public int insertMember(MemberDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		try {
			conn=DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into member(userid,password,name,email) values (?,?,?,?)");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			result=pstmt.executeUpdate();
			if(result==1) {
				System.out.println("추가되었습니다");
			}else {System.out.println("다시 입력해 주세요");}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}return result;
	}
	
	//비밀번호 확인 (뷰)
		public boolean logintest (String userid, String password){ //애는 dto가 필요하겠군! 
			boolean flag = false;
			MemberDTO dto = new MemberDTO();
			Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs =null;
					
			try {
				conn = DB.oraConn();
				StringBuilder sb = new StringBuilder();
				sb.append("select password from member where userid=?");
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setString(1, userid);
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					String getPass = rs.getString("password");
					if(getPass.equals(password)) {
						System.out.println("받아온 비밀번호:"+getPass);
						flag = true; 
					}
				}

			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null) rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				try {
					if(pstmt!=null) pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}try {
					if(conn!=null) conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}return flag; //이렇게 해도 되나??
		}
		
	
	//회원탈퇴
	public int deleteMamber() {
		int result = 0;
		MemberDTO dto = new MemberDTO(); //다 가져올 필요는 없어 필요한 매개변수만 가져와도 됨!!!
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn= DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from member where userid, password");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getPassword());
			result = pstmt.executeUpdate();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}return result;
	}
	
	
	//회원정보수정
	public MemberDTO updateMember(MemberDTO dto) {
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			conn = DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("update member set password=?, name=?, email=? where userid=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getUserid());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String password = rs.getString("password");
				String name = rs.getString("name");
				String email = rs.getString("email");
				//원래는 여기 dto에 dao를 넣는 생성자 하나를 만들어 주는데
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}return dto; //이렇게 해도 되나??
	}
	
	//(리스트) 
     public Vector listMember(){ //애는 dto가 필요하겠군! 
		       	 
    	    Vector items = new Vector();
		    Connection conn =null;
			PreparedStatement pstmt = null;
			ResultSet rs =null;
				
			try {
				conn = DB.oraConn();
				StringBuilder sb = new StringBuilder();
				sb.append("select * from member order by userid");
				pstmt=conn.prepareStatement(sb.toString());
				rs=pstmt.executeQuery();
					
					while(rs.next()) {
						Vector row = new Vector();
						String userid = rs.getString("userid");
						String password = rs.getString("********");
						String name = rs.getString("name");
						String email = rs.getString("email");
						row.add(userid);
						row.add(password);
						row.add(name);
						row.add(email);
						items.add(row);
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(pstmt!=null) pstmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}try {
						if(conn!=null) conn.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}return items; 
			}
			
		
	}
	



