package com.BookStoreAdmin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BookStoreAdmin.domain.Book;
import com.BookStoreAdmin.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String postAddBook(@ModelAttribute("book") Book book, HttpServletRequest req) {

		bookService.save(book);

		// 책 이미지 불러옴
		MultipartFile bookImage = book.getBookImage();

		try {
			byte[] bytes = bookImage.getBytes(); // 이미지 파일을 byte로
			String name = book.getId() + ".png"; // 서버에 저장될 파일 이름을 id+.png

			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close(); // 파일 쓰기가 완료되면 버퍼닫음
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:bookList";
	}

	// 책 목록 보기
	@RequestMapping("/bookList")
	public String bookList(Model model) {
		List<Book> bookList = bookService.findAll();

		model.addAttribute("bookList", bookList);
		return "bookList";
	}

	// 책 상세보기
	@RequestMapping("/bookInfo")
	public String bookInfo(@RequestParam("id") Long id, Model model) {
		// id에 따른 객체 생성
		Book book = bookService.findOne(id);

		model.addAttribute("book", book);
		return "bookInfo";
	}

	// 책 수정하기 get
	@RequestMapping("/updateBook")
	public String bookUpdate(@RequestParam("id") Long id, Model model) {
		// 책 정보를 가져옴
		Book book = bookService.findOne(id);

		model.addAttribute("book", book);

		return "updateBook";
	}

	// 책 수정하기 post
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST)
	public String bookUpdatePost(@ModelAttribute("book") Book book, HttpServletRequest req) {
		// DB에 저장
		bookService.save(book);

		// 이미지 처리는 따로
		MultipartFile bookImage = book.getBookImage();
		
		if (!bookImage.isEmpty()) {
			try {
				byte[] bytes = bookImage.getBytes(); // 이미지 파일을 byte로
				String name = book.getId() + ".png"; // 서버에 저장될 파일 이름을 id+.png
				
				// 수정이기 때문에 우선 먼저 등록된 이미지를 지워줌
				Files.delete(Paths.get("src/main/resources/static/image/book/" + name));
				
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
				stream.write(bytes);
				stream.close(); // 파일 쓰기가 완료되면 버퍼닫음
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		return "redirect:/book/bookInfo?id="+book.getId();
	}
}
