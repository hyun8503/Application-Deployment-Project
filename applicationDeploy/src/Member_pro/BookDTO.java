package Member_pro;

import java.sql.Date;

public class BookDTO {
	private String bookno;
	private String bname;
	private String publisher;
	private Date pdate;
	private String author;
	private String classification;
	public String getBookno() {
		return bookno;
	}
	public void setBookno(String bookno) {
		this.bookno = bookno;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	@Override
	public String toString() {
		return "BookDTO [bookno=" + bookno + ", bname=" + bname + ", publisher=" + publisher + ", pdate=" + pdate
				+ ", author=" + author + ", classification=" + classification + "]";
	}
	public BookDTO(String bookno, String bname, String publisher, Date pdate, String author, String classification) {
		
		this.bookno = bookno;
		this.bname = bname;
		this.publisher = publisher;
		this.pdate = pdate;
		this.author = author;
		this.classification = classification;
	}
	public BookDTO() {
		
	}
	
	

}
