package com.pojo.borrower;

public class Borrower {
	private int id;
	private String borrowername;
	private int bookid;
	private String bookname;
	private int fine;
	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Borrower(int id, String borrowername, int bookid, String bookname, int fine) {
		super();
		this.id = id;
		this.borrowername = borrowername;
		this.bookid = bookid;
		this.bookname = bookname;
		this.fine = fine;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBorrowername() {
		return borrowername;
	}
	public void setBorrowername(String borrowername) {
		this.borrowername = borrowername;
	}
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	@Override
	public String toString() {
		return "Borrower [id=" + id + ", borrowername=" + borrowername + ", bookid=" + bookid + ", bookname=" + bookname
				+ ", fine=" + fine + "]";
	}
	
}
