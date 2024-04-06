package com.pojo.borrower;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import com.pojo.database.MyConnect;

public class BorrowerOp {
	static Scanner sc=new Scanner(System.in);
	static MultipleBorrower mb=new MultipleBorrower();
	public static void addBorrower() {
		int ch=1;
		while(ch==1) {
		System.out.println("enter borrower id:");
		int id=sc.nextInt();
		System.out.println("Enter borrower name:");
		String name=sc.next();
		Connection co=MyConnect.getConnectionWithDb();
		Borrower bo=new Borrower();
		bo.setId(id);
		bo.setBorrowername(name);
		
		mb.setToList(bo);
		System.out.println(bo);
		System.out.println("press 1 for add more borrower \n press 2 for save borrower");
		ch=sc.nextInt();
		if(ch==2) {
			mb.SaveData();
		}
		}
		
		
	}
	public static void deleteBorrower() {
		System.out.println("enter borrowerid to delete:");
		int bid=sc.nextInt();
		Connection co=MyConnect.getConnectionWithDb();
		PreparedStatement st;
		try {
			st = co.prepareStatement("delete from borrower where id=?");
			st.setInt(1, bid);
			st.executeUpdate();
			System.out.println("borrower deleted successfully!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void updateBorrower() {
		Connection co=MyConnect.getConnectionWithDb();
		System.out.println("Enter borrowerid to update :");
		int bid=sc.nextInt();
		System.out.println("enter borrower name :");
		String name=sc.next();
		PreparedStatement st;
		try {
			
			st = co.prepareStatement("update borrower set borrowername=? where id=?");
			st.setString(1, name);
			st.setInt(2, bid);
			st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void displayBorrower() {
		Connection co=MyConnect.getConnectionWithDb();
		Statement st;
		try {
			st = co.createStatement();
			ResultSet rst=st.executeQuery("Select * from borrower");
			while(rst.next()) {
				int id=rst.getInt("id");
				String bname=rst.getString("borrowername");
				int bookid=rst.getInt("bookid");
				String bookname=rst.getString("bookname");
				Date isd=rst.getDate("issuedate");
				Date sd=rst.getDate("submit");
				int fine=rst.getInt("fine");
				
				
				System.out.println(id+"\t"+ bname+"\t"+bookid+"\t"+bookname+"\t"+isd+"\t"+sd+"\t"+fine);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void issueBook() {
		
		System.out.println("Enter Borrower name:");
		String name=sc.next();
		System.out.println("Enter id :");
		int id=sc.nextInt();
		Connection co=MyConnect.getConnectionWithDb();
		
		try {
			Statement st=co.createStatement();
			ResultSet rst=st.executeQuery("select * from borrower ");
			boolean b=false;
			while(rst.next()) {
				int existingId=rst.getInt("id");
				//System.out.println(existingId);
				if(id==existingId) {
					
					System.out.println("Enter book id");
					int bookid=sc.nextInt();
					PreparedStatement pst=co.prepareStatement("select * from books where bookid=?");
					pst.setInt(1, bookid);
					ResultSet resultset=pst.executeQuery();
//					ResultSet resultset=st.executeQuery("select * from books where bookid="+bookid+";");
					//PreparedStatement borrower=co.prepareStatement("UPDATE BORROWER SET bookId=?,bookname=?,issuedate=? where id="+existingId+";");
					PreparedStatement borrower=co.prepareStatement("insert into borrower(id,borrowername,bookId,bookname,issuedate) values(?,?,?,?,?);");
					while(resultset.next()) {
						int ids=resultset.getInt("bookid");
						String bname=resultset.getString("bookname");
//						int id1=resultset.getInt("bookid");
//						String bname=resultset.getString("bookname");
						LocalDate date=LocalDate.now();
						Date d=Date.valueOf(date);
						borrower.setInt(1, id);
						borrower.setString(2, name);
						borrower.setInt(3, ids);
						borrower.setString(4, bname);
//					
						borrower.setDate(5, d);
						
						borrower.executeUpdate();
						System.out.println("Book issued!");
					}
					b=true;
					break;
					
					
				}
			}
				 if(!b) {
					System.out.println("New user found!");
					System.out.println("Enter book id:");
					int bookid=sc.nextInt();
					// id   | borrowername | bookid | bookname | issuedate  | submit | fine |
					PreparedStatement pst=co.prepareStatement("select * from books where bookid=?");
					pst.setInt(1, bookid);
					ResultSet resultset=pst.executeQuery();
					PreparedStatement borrower=co.prepareStatement("insert into borrower(id,borrowername,bookId,bookname,issuedate) values(?,?,?,?,?);");
					while(resultset.next()) {
						int ids=resultset.getInt("bookid");
						String bname =resultset.getString("bookname");
						LocalDate date=LocalDate.now();
						Date d=Date.valueOf(date);
						borrower.setInt(1, id);
						borrower.setString(2, name);
						borrower.setInt(3, bookid);
						borrower.setString(4, bname);
						borrower.setDate(5, d);
						borrower.executeUpdate();
						System.out.println("Book Issued");
						
					
				

					
				}
				
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
