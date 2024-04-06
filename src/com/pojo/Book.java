package com.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
// Testing
public class Book {
	private int bookid;
	private String bookname;
	private String author;
	private LocalDate currentDate;
	private int quantity;
	
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public LocalDate getCurrentDate() {
		return LocalDate.now();
	}
	public void setCurrentDate(LocalDate date) {
		//this.currentDate = date;
		this.currentDate = date;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book(int bookid, String bookname, String author, LocalDate currentDate, int quantity) {
		super();
		this.bookid = bookid;
		this.bookname = bookname;
		this.author = author;
		this.currentDate = currentDate;
		this.quantity = quantity;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", bookname=" + bookname + ", author=" + author + ", currentDate="
				+ currentDate + ", quantity=" + quantity + "]";
	}
	
	
	
	
}
