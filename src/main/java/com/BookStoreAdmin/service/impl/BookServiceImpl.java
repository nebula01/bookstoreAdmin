package com.BookStoreAdmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BookStoreAdmin.domain.Book;
import com.BookStoreAdmin.repository.BookRepository;
import com.BookStoreAdmin.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book book) {
		// 새 책 등록
		return bookRepository.save(book);
	}

	// 모든 책 보기
	@Override
	public List<Book> findAll() {
		return (List<Book>)bookRepository.findAll();
	}
	
	// 책 상세 보기
	@Override
	public Book findOne(Long id) {
		// TODO Auto-generated method stub
		return bookRepository.findOne(id);
	}
}
