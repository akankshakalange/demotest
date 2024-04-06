package com.pojo.borrower;

import java.util.ArrayList;

import com.pojo.Book;
import com.pojo.database.MyDatabase;

public class MultipleBorrower {

	
		private ArrayList<Borrower> list = new ArrayList<Borrower>();

		public void setToList(Borrower b) {
			list.add(b);
		}
		public void SaveData() {
			MyDatabase.SaveBorrower(list);
		}
	

}
