package services.impl;

import config.ConnexionDB;
import entities.Category;
import services.ICategory;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
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
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public List<Category> getAll() {
        return List.of();
    }

    @Override
    public Category get(int id) {
        return null;
    }
}
