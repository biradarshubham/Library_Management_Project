package edu.lib.Entity;

import java.sql.Date;

import javax.servlet.http.Part;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter

public class Library {

	private int bookId;
	private String bookname;
	private String author;
	private double price;
	private int pages;
	private Date publicationdate;
//	private Part image;
	private String image;
	
}
