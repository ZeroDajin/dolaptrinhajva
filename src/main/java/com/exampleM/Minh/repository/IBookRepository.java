package com.exampleM.Minh.repository;

import com.exampleM.Minh.entity.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByCategory_Id(long id);
}
