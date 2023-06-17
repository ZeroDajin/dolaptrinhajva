package com.exampleM.Minh.services;


import com.exampleM.Minh.entity.Book;
import com.exampleM.Minh.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        Optional<Book> optional = bookRepository.findById(id);
        return optional.orElse(null);
    }
        
        public void addBook(Book book){
            bookRepository.save(book);
        }
        public void updateBook(Book book){
            bookRepository.save(book);
        }
        public void deleteBook(Long id){
            bookRepository.deleteById(id);
        }
	public List<Book> getAllBookByCategoryId(long id) {
        return bookRepository.findAllByCategory_Id(id);
    }
}

