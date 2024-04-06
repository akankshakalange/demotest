package com.pojo;

import java.util.Scanner;

import com.pojo.app.Operations;

public class BookData {
	static Scanner sc = new Scanner(System.in);

	public static void startBook() {
		System.out.println("choose option:");
		System.out.println("1.book catalogue\n2.issue book\n3.submit book");
		int ch = sc.nextInt();
		switch (ch) {
		case 1:

			System.out.println("choose option");
			System.out.println("1.add book\n2.update book\n3.delete book\n4.display book");
			int ch1 = sc.nextInt();
			switch (ch1) {
			case 1:
				Operations.setBook();
				break;
			case 2:
				Operations.updateBook();
				break;
			case 3:
				Operations.deleteBook();
				break;
			case 4:
				Operations.displayBook();
				break;
			case 5:
				BookOp.submitBook();
				break;
			}
			break;
		case 2:
			BookOp.issueBook();
			break;
		case 3:
			BookOp.submitBook();
			break;

		}
	}
}
