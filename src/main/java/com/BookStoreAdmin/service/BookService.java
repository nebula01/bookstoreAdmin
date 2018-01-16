package com.BookStoreAdmin.service;

import java.util.List;

import com.BookStoreAdmin.domain.Book;

public interface BookService {

	// 책 객체 DB에 등록
	Book save(Book book);

	// 모든 책 보기
	List<Book> findAll();
}
