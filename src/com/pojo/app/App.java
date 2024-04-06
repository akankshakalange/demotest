package com.pojo.app;

import java.util.Scanner;

import com.pojo.BookData;
import com.pojo.BookOp;
import com.pojo.borrower.BorrowerData;
import com.pojo.borrower.BorrowerOp;

public class App {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
		System.out.println("press 1 for book\npress 2 for borower\npress 3 for issue book\n press 4 for submit book");
		int ch=sc.nextInt();
		switch(ch) {
		case 1:
			BookData.startBook();
			break;
		case 2:
//			
			BorrowerData.startborrower();
			break;
		case 3:
			BookOp.issueBook();
			break;
		case 4:
			BookOp.submitBook();
			break;
		}

	}

}
}
