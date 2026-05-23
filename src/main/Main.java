package main;

import config.ConnexionDB;
import entities.Category;
import services.ICategory;
import services.impl.CategoryImpl;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ICategory dao = new CategoryImpl();
       /* Category cat = dao.get(4);
        Scanner scan = new Scanner(System.in);
        System.out.println("Veuillez choisir un nom de categorie");
        cat.setName(scan.nextLine()); //saisir chaine de caractere
        cat.setState(false);
        dao.update(cat);*/
        dao.delete(6);

        for (Category category : dao.getAll()) {
            System.out.println(category);
        }



    }

}