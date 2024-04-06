package com.pojo.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import com.pojo.Book;
import com.pojo.MultipleBook;
import com.pojo.database.MyConnect;
import com.pojo.database.MyDatabase;

public class Operations {
	static Scanner sc=new Scanner(System.in);
	static MultipleBook obj=new MultipleBook();
	
	public static void setBook() {
		int ch=1;
		while(ch==1) {
		Book bk=new Book();
		LocalDate date= LocalDate.now();
		System.out.println("enter the bookId");
		int bookid=sc.nextInt();
		bk.setBookid(bookid);
		System.out.println("enter book name:");
		bk.setBookname(sc.next());
		System.out.println("enter book author:");
		bk.setAuthor(sc.next());
		
		bk.setCurrentDate(date);
		System.out.println("Enter quantity ");
		bk.setQuantity(sc.nextInt());
		obj.setToList(bk);
		
		System.out.println("press 1 for add more book \n press 2 for save book");
		ch=sc.nextInt();
		if(ch==2) {
			obj.SaveData();
		}
		}
	}
//	public static void getBook() {
//		System.out.println("BookId\t BookName\t Author");
//		System.out.println(bk.getBookid()+"\t"+bk.getBookname()+"\t"+bk.getAuthor());
//	}
	public static void updateBook() {
		System.out.println("press 1 for update name\n press 2 for update author");
		int ch=sc.nextInt();
		System.out.println("enter bookid");
		int bid=sc.nextInt();
		
		Connection co=MyConnect.getConnectionWithDb();
		switch(ch) {
		case 1:
			System.out.println("enter book name:");
			String name=sc.next();
			try {
				PreparedStatement st=co.prepareStatement("update books set bookname=? where bookid=?");
				st.setString(1, name);
				st.setInt(2, bid);
				st.executeUpdate();
				System.out.println("book name updated successfully!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			break;
		case 2:
			System.out.println("enter author name:");
			String author=sc.next();
			try {
				PreparedStatement st=co.prepareStatement("update books set author =? where bookid=?");
				st.setString(1, author);
				st.setInt(2, bid);
				st.executeUpdate();
				System.out.println("Author updated successfully!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		}
	}
	public static void deleteBook() {
		System.out.println("enter bookid to delete:");
		int bid=sc.nextInt();
		Connection co=MyConnect.getConnectionWithDb();
		try {
			PreparedStatement st=co.prepareStatement("delete from books where bookid=?");
			st.setInt(1, bid);
			st.executeUpdate();
			System.out.println("book deleted successfully!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void displayBook() {
		Connection co=MyConnect.getConnectionWithDb();
		 try {
			Statement st=co.createStatement();
			ResultSet rst=st.executeQuery("Select * from books");
//			 bookid | bookname | author      | quantity |
			System.out.printf("%-10s| %-20s| %10s| %10s|\n","bookid","bookname","author","quantity");
			System.out.println("------------------------------------------------------------------------");
			while(rst.next()) {
//				int bid=rst.getInt("bookid");
//				String bookname=rst.getString("bookname");
//				String author=rst.getString("author");
//				int quantity=rst.getInt("quantity");
		
				System.out.printf("%-10d %-20s %10s %10d\n",rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
