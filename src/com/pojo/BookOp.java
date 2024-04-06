package com.pojo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import com.pojo.database.MyConnect;

public class BookOp {
	static Scanner sc = new Scanner(System.in);

	public static void issueBook() {

		System.out.println("Enter Borrower name:");
		String name = sc.next();
		System.out.println("Enter id :");
		int id = sc.nextInt();
		Connection co = MyConnect.getConnectionWithDb();
		int records = 0, bookcount = 0;

		try {
			Statement st = co.createStatement();
			ResultSet rst = st.executeQuery("select * from borrower ");
			boolean b = false;
			boolean checkdata = false;
			boolean access = false;
			boolean countBooks = false;
			while (rst.next()) {
				int existingId = rst.getInt("id");
				if (id == existingId) {
					// count
					PreparedStatement countps = co.prepareStatement("select count(id) from borrower where id=?");
					countps.setInt(1, existingId);
					ResultSet countrs = countps.executeQuery();
					while (countrs.next()) {
						records = countrs.getInt(1);

						if (records < 3) {
							access = true;
//							 if(bookcount==1) {
//								 countBooks=true;
//							 }

						}
					}
					if (access == true) {
						System.out.println("Enter book id");
						int bookid = sc.nextInt();
						PreparedStatement pst = co.prepareStatement("select * from books where bookid=?");
						pst.setInt(1, bookid);
						ResultSet resultset = pst.executeQuery();

						PreparedStatement verify = co
								.prepareStatement("select * from borrower where id=? AND bookid is null  ");
						verify.setInt(1, id);
						ResultSet set = verify.executeQuery();

						while (set.next()) {
							checkdata = true;

						}

						PreparedStatement countbk = co
								.prepareStatement("select count(bookid) from borrower where bookid=? and id=?");
						//countbk.setInt(1, bookid);
						countbk.setInt(1, bookid);
						countbk.setInt(2, id);

						ResultSet countbookps = countbk.executeQuery();
						while (countbookps.next()) {
							bookcount = countbookps.getInt(1);
							//System.out.println("bookcount" + bookcount);
							if (bookcount <1) {
								countBooks = true;
							}
						}
						if (countBooks == true) {

							if (checkdata == false) {

//					ResultSet resultset=st.executeQuery("select * from books where bookid="+bookid+";");
								// PreparedStatement borrower=co.prepareStatement("UPDATE BORROWER SET
								// bookId=?,bookname=?,issuedate=? where id="+existingId+";");
								PreparedStatement borrower = co.prepareStatement(
										"insert into borrower(id,borrowername,bookId,bookname,issuedate,duedate) values(?,?,?,?,?,?);");
								while (resultset.next()) {
									int ids = resultset.getInt("bookid");
									String bname = resultset.getString("bookname");
//						int id1=resultset.getInt("bookid");
//						String bname=resultset.getString("bookname");
									LocalDate date = LocalDate.now();
									Date d = Date.valueOf(date);
									borrower.setInt(1, id);
									borrower.setString(2, name);
									borrower.setInt(3, ids);
									borrower.setString(4, bname);
//					
									borrower.setDate(5, d);
									LocalDate duedate = date.plusDays(15);
									Date duedate1 = Date.valueOf(duedate);
									borrower.setDate(6, duedate1);

									borrower.executeUpdate();
									System.out.println("Book issued!");
								}
							} else {

								PreparedStatement borrower = co.prepareStatement(
										"UPDATE BORROWER SET bookId=?,bookname=?,issuedate=?,duedate=? where id=?;");
								while (resultset.next()) {
									borrower.setInt(1, bookid);

									String bname = resultset.getString("bookname");
									borrower.setString(2, bname);
									LocalDate date = LocalDate.now();
									Date d = Date.valueOf(date);
									borrower.setDate(3, d);
									LocalDate due = date.plusDays(15);
									Date du1 = Date.valueOf(due);
									borrower.setDate(4, du1);
									borrower.setInt(5, id);

									borrower.executeUpdate();
									System.out.println("book issued");
								}
							}
						} else {
							System.out.println("you alredy borrow same book");
							System.out.println("You cannot borrow same book again");
						}

					}//if (access == false)
					else {
						System.out.println("you already borrow 3 books");
						break;
					}
					
					b = true;
					break;


				}
				
			}

			if (!b) {
				System.out.println("New user found!");
				System.out.println("Enter book id:");
				int bookid = sc.nextInt();
				// id | borrowername | bookid | bookname | issuedate | submit | fine |
				PreparedStatement pst = co.prepareStatement("select * from books where bookid=?");
				pst.setInt(1, bookid);
				ResultSet resultset = pst.executeQuery();
				PreparedStatement borrower = co.prepareStatement(
						"insert into borrower(id,borrowername,bookId,bookname,issuedate,duedate) values(?,?,?,?,?,?);");
				while (resultset.next()) {
					int ids = resultset.getInt("bookid");
					String bname = resultset.getString("bookname");
					LocalDate date = LocalDate.now();
					Date d = Date.valueOf(date);
					borrower.setInt(1, id);
					borrower.setString(2, name);
					borrower.setInt(3, bookid);
					borrower.setString(4, bname);
					borrower.setDate(5, d);
					LocalDate duedate = date.plusDays(15);
					Date duedate1 = Date.valueOf(duedate);
					borrower.setDate(6, duedate1);
					borrower.executeUpdate();
					System.out.println("Book Issued");

				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void submitBook() {
		System.out.println("Enter Borrower id:");
		int boid = sc.nextInt();
		System.out.println("enter book id:");
		int bid = sc.nextInt();
		Connection co = MyConnect.getConnectionWithDb();
		try {
			PreparedStatement ps = co.prepareStatement("update borrower set submit=? where bookid =? and id=?");
			LocalDate date = LocalDate.now();
			Date d = Date.valueOf(date);
			ps.setDate(1, d);
			ps.setInt(2, bid);
			ps.setInt(3, boid);
			ps.executeUpdate();
			System.out.println("Book submited!");
			PreparedStatement pst = co
					.prepareStatement("select issuedate,submit from borrower where bookid=? and id=?");
			pst.setInt(1, bid);
			pst.setInt(2, boid);
			ResultSet rst1 = pst.executeQuery();
			while (rst1.next()) {
//			String da="2024-02-28";
//			Date d1=Date.valueOf(da);
				Date issuedate = rst1.getDate("");
				LocalDate date1 = issuedate.toLocalDate();
				Date submitdate = rst1.getDate("submit");
				LocalDate date2 = submitdate.toLocalDate();
				long days = ChronoUnit.DAYS.between(date1, date2);
				if (days > 15) {
					int day = (int) (days - 15);
					int fine = day * 10;
					PreparedStatement fines = co
							.prepareStatement("UPDATE BORROWER set fine=? where bookid=? and id=? ");
					fines.setInt(1, fine);
					fines.setInt(2, bid);
					fines.setInt(3, boid);
					fines.executeUpdate();

				} else {
					PreparedStatement fines = co
							.prepareStatement("UPDATE BORROWER set fine=? where bookid=? and id=? ");
					fines.setInt(1, 0);
					fines.setInt(2, bid);
					fines.setInt(3, boid);
					fines.executeUpdate();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
