package com.BookStoreAdmin.repository;

import org.springframework.data.repository.CrudRepository;

import com.BookStoreAdmin.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
	
}
