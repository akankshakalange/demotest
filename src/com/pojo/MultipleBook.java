package com.pojo;

import java.util.ArrayList;

import com.pojo.database.MyDatabase;

public class MultipleBook {
	private ArrayList<Book> list = new ArrayList<Book>();

	public void setToList(Book b) {
		list.add(b);
	}

	public void SaveData() {
		MyDatabase.Save(list);
	}
}
