package main;

import config.ConnexionDB;
import entities.Book;
import entities.Category;
import services.IBook;
import services.impl.BookImpl;
import services.IBook;

import java.util.List;
import services.ICategory;
import services.impl.CategoryImpl;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IBook dao = new BookImpl();

        // CREATE
        Category category = new Category();
        category.setId(1);

        Book book = new Book();

        book.setTitle("Java JDBC");
        book.setAuthor("FOUADI Said");
        book.setIsbn("ISBN1000");
        book.setCountPages(100);
        book.setPublicationYear(2025);
        book.setCategory(category);

        int createResult = dao.create(book);

        if (createResult != 0) {

            System.out.println("Livre ajouté avec succès !");

        } else {

            System.out.println("Erreur lors de l'ajout !");
        }

        // Liste de tout les livres
        /*List<Book> books = dao.getAll();

        for (Book b : books) {

            System.out.println(b);
        }*/

        // Affichage par category
        /*List<Book> booksByCategory = dao.getAllByCategory("Roman");

        for (Book b : booksByCategory) {

            System.out.println(b);
        }*/

        //Affichage par annee de publication
        /*List<Book> booksByYear = dao.getAllByPublicationYear(2025);

        for (Book b : booksByYear) {

            System.out.println(b);
        }*/

        //UPDATE
        /*Scanner scan = new Scanner(System.in);

        // On récupère le livre ayant l'id 1
        Book bookToUpdate = dao.get(9);

        if (bookToUpdate != null) {

            System.out.println("Veuillez saisir un nouveau titre : ");
            bookToUpdate.setTitle(scan.nextLine());

            System.out.println("Veuillez saisir un nouvel auteur : ");
            bookToUpdate.setAuthor(scan.nextLine());

            System.out.println("Veuillez saisir un nouvel ISBN : ");
            bookToUpdate.setIsbn(scan.nextLine());

            System.out.println("Veuillez saisir le nombre de pages : ");
            bookToUpdate.setCountPages(scan.nextInt());

            System.out.println("Veuillez saisir l'année de publication : ");
            bookToUpdate.setPublicationYear(scan.nextInt());

            int updateResult = dao.update(bookToUpdate);

            if (updateResult != 0) {

                System.out.println("Modification effectuée !");

            } else {

                System.out.println("Erreur lors de la modification !");
            }

        } else {

            System.out.println("Livre introuvable !");
        }*/

        //DELETE
        /*int deleteResult = dao.delete(2);

        if (deleteResult != 0) {

            System.out.println("Suppression effectuée !");

        } else {

            System.out.println("Erreur lors de la suppression !");
        }*/

        //scan.close();
    }

}