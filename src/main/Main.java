package main;

import config.ConnexionDB;
import entities.Category;
import services.ICategory;
import services.impl.CategoryImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ICategory dao = new CategoryImpl();
        Category cat = new Category();
        cat.setName("Conte");
        int ok = dao.create(cat);
        if (ok > 0) {
            System.out.println("insertion réussie !!!");

        }else{
            System.out.println("Erreur !!!");
        }

    }

}