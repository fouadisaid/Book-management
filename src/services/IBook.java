package services;

import entities.Book;

import java.util.List;

public interface IBook extends IGeneric<Book> {
    List<Book> getAllByAuthor(String author);
    List<Book> getAllByCategory(String Category);
    List<Book> getAllByPublicationYear(int year);



}
