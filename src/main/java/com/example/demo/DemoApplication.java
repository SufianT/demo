package com.example.demo;

import com.example.demo.model.BookHandler;
import com.example.demo.model.Library;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {


			// Initialize the library
		Library library = new Library();
		BookHandler bookHandler = new BookHandler(library);

		bookHandler.loadBooksFromDatabase("src/main/resources/JavaScript/Books.json");
			// Print the books to verify
			library.getBookList().forEach(book -> {
				System.out.println("Title: " + book.getTitle() +
						", ISBN: " + book.getISBN() +
						", Authors: " + book.getAuthor() +
						", Is Borrowed: " + book.getIsBarrowed());
			});

	}


}
