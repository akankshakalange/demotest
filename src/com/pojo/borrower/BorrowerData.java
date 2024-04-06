package com.pojo.borrower;

import java.util.Scanner;

public class BorrowerData {
	static Scanner sc=new Scanner(System.in);
	public static void startborrower(){
		System.out.println("Choose option:" );
		System.out.println("1.add borrower\n 2.delete borrower\n3.update borrower\n4.display borrower");
		int ch=sc.nextInt();
		switch(ch) {
		case 1:
			BorrowerOp.addBorrower();
			break;
		case 2:
			BorrowerOp.deleteBorrower();
			break;
		case 3:
			BorrowerOp.updateBorrower();
			break;
		case 4:
			BorrowerOp.displayBorrower();
		}
	}
	
}
