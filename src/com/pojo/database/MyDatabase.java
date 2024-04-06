package com.pojo.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.pojo.Book;
import com.pojo.borrower.Borrower;

public class MyDatabase {
	public static void Save(ArrayList<Book> list) {
		Connection co=MyConnect.getConnectionWithDb();
		try {
			PreparedStatement ps=co.prepareStatement("insert into books(bookid,bookname,author) values(?,?,?)");
			for(Book bk:list) {
			//	LocalDate date=LocalDate.now();
				int bookid=bk.getBookid();
				String name=bk.getBookname();
				String author=bk.getAuthor();
				ps.setInt(1, bookid);
				ps.setString(2, name);
				ps.setString(3, author);
			//	Date d = Date.valueOf(date);
				//ps.setDate(4, d);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void SaveBorrower(ArrayList<Borrower> list) {
		Connection co=MyConnect.getConnectionWithDb();
		try {
			PreparedStatement ps1=co.prepareStatement("insert into borrower(id,borrowername) values(?,?)");
		for(Borrower bo:list) {
			int bid=bo.getId();
			String name=bo.getBorrowername();
			ps1.setInt(1, bid);
			ps1.setString(2, name);
			ps1.executeUpdate();
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
