package Member_pro;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BookDAO {
	
	//search
	public Vector searchBook(String bname) {
		Vector items = new Vector(); 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select bookno,bname,publisher,pdate,author,classification from book where bname like ?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1,"%"+bname+"%");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("bookno"));
				row.add(rs.getString("bname"));
				row.add(rs.getString("publisher"));
				row.add(rs.getDate("pdate"));
				row.add(rs.getString("author"));
				row.add(rs.getString("classification"));
				items.add(row);
			
			}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				try {
					if(pstmt!=null)pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				try {
					if(conn!=null)conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}return items;
				
			}
	
	//list
	public Vector listBook() {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select bookno,bname,publisher,pdate,author,classification from book order by bookno");
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Vector row = new Vector();
				String bookno = rs.getString("bookno");
				String bname = rs.getString("bname");
				String publisher = rs.getString("publisher");
				Date pdate = rs.getDate("pdate");
		        String author = rs.getString("author");
				String classification = rs.getString("classification");
				row.add(bookno);
				row.add(bname);
				row.add(publisher);
				row.add(pdate);
				row.add(author);
				row.add(classification);
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}return items;
	} 
	//view
	public BookDTO viewBook(String bookno) {
		BookDTO dto = new BookDTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select bookno,bname,publisher,pdate,author,classification from book where bookno=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1,bookno); //여기에 getString써서 dto값을 받아와서 화면에 출력이 안되었던 것!!
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setBookno(rs.getString("bookno"));
				dto.setBname(rs.getString("publisher"));
				dto.setPublisher(rs.getString("publisher"));
				dto.setPdate(rs.getDate("pdate"));
				dto.setAuthor(rs.getString("author"));
				dto.setClassification(rs.getString("classification"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}return dto;
	
	}
	//delete
	
	public int deleteBook(String bookno) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn=DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from book where bookno=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, bookno);
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}return result;
	}
	//insert
		public int insertBook(BookDTO dto) {
			int result = 0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn=DB.oraConn();
				StringBuilder sb = new StringBuilder();
				sb.append("insert into book (bookno,bname,publisher,pdate,author,classification) values (?,?,?,?,?,?)");
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setString(1, dto.getBookno());
				pstmt.setString(2, dto.getBname());
				pstmt.setString(3, dto.getPublisher());
				pstmt.setDate(4, dto.getPdate());
				pstmt.setString(5, dto.getAuthor());
				pstmt.setString(6, dto.getClassification());
				result = pstmt.executeUpdate();				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				
				try {
					if(pstmt!=null)pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				try {
					if(conn!=null)conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}return result;
		
		}
		
	//update
		public int updateBook(BookDTO dto) {
			int result =0;
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			try {
				conn=DB.oraConn();
				StringBuilder sb = new StringBuilder();
				sb.append("update book set bname=?,publisher=?,pdate=?,author=?,classification=? where bookno=?");
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setString(1, dto.getBname());
				pstmt.setString(2, dto.getPublisher());
				pstmt.setDate(3, dto.getPdate());
				pstmt.setString(4, dto.getAuthor());
				pstmt.setString(5, dto.getClassification());
				pstmt.setString(6, dto.getBookno());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(pstmt!=null)pstmt.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				try {
					if(conn!=null)conn.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}return result;
		}

		
	
	
	}
