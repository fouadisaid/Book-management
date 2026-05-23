package services.impl;

import config.ConnexionDB;
import entities.Category;
import services.ICategory;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements ICategory {
    private ConnexionDB db = new ConnexionDB();
    private ResultSet rs;
    private int ok;

    @Override
    public int create(Category category) {
        String sql = "insert into category (name, state, created_at, updated_at) values (?,?,?,?)";
        try {
            //connexion et preparation de la requete
            db.initPrepar(sql);

            //Passage des valeurs
            db.getPstm().setString(1, category.getName());
            db.getPstm().setBoolean(2, true);
            db.getPstm().setObject(3, LocalDateTime.now());
            db.getPstm().setObject(4, null);
            ok = db.executeMaj();
            db.closeConnection();

        }catch (Exception e) {
            e.printStackTrace();//pour voir la trace d'erreur
        }
        return ok;
    }

    @Override
    public int update(Category category) {
        String sql = "update category set name=?, state=?, updated_at=? where id=?";
        try {
            //connexion et preparation de la requete
            db.initPrepar(sql);

            //Passage des valeurs
            db.getPstm().setString(1, category.getName());
            db.getPstm().setBoolean(2, category.isState());
            db.getPstm().setObject(3, LocalDateTime.now());
            db.getPstm().setInt(4, category.getId());
            ok = db.executeMaj();
            db.closeConnection();

        }catch (Exception e) {
            e.printStackTrace();//pour voir la trace d'erreur
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "delete from category where id=?";
        try {
            //connexion et preparation de la requete
            db.initPrepar(sql);

            //Passage des valeurs

            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
            db.closeConnection();

        }catch (Exception e) {
            e.printStackTrace();//pour voir la trace d'erreur
        }
        return ok;
    }

    @Override
    public List<Category> getAll() {
        String sql = "select * from category order by name asc";
        List <Category> categories = new ArrayList<Category>();

        try{
            db.initPrepar(sql);
            rs = db.executeSelect();
            while (rs.next()) {
                //Premiere methode : passage par setters
                /*Category category = new Category();
                category.setId(rs.getInt("id"));//recupere la valeur
                category.setName(rs.getString("name"));
                category.setState(rs.getBoolean("state"));
                category.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                category.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));*/

                //Deuxième methode : passage par constructeur.
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("name"),
                        rs.getBoolean("state")
                );

                //ajout
                categories.add(category);
            }
            //fermeture
            db.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        //retouner les categories
        return categories;
    }

    @Override
    public Category get(int id) {
        String sql = "select * from category where id=?";
        Category category = null;
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            rs = db.executeSelect();
            if (rs.next()) {

                category = new Category(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("name"),
                        rs.getBoolean("state")
                );
            }
            //fermeture
            db.closeConnection();
        }catch (Exception e) {
            e.printStackTrace();
        }
        //retouner les categories
        return category;
    }
}
