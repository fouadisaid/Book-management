package services.impl;

import config.ConnexionDB;
import entities.Book;
import entities.Category;
import services.IBook;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookImpl implements IBook {

    private ConnexionDB db = new ConnexionDB();
    private ResultSet rs;
    private int ok;

    @Override
    public int create(Book book) {

        String sql = "INSERT INTO book (title, author, isbn, count_pages, publication_year, category_id, created_at, updated_at) " +
                "VALUES (?,?,?,?,?,?,?,?)";

        try {

            db.initPrepar(sql);

            db.getPstm().setString(1, book.getTitle());
            db.getPstm().setString(2, book.getAuthor());
            db.getPstm().setString(3, book.getIsbn());
            db.getPstm().setInt(4, book.getCountPages());
            db.getPstm().setInt(5, book.getPublicationYear());

            // Category
            if (book.getCategory() != null && book.getCategory().getId() != 0) {
                db.getPstm().setInt(6, book.getCategory().getId());
            } else {
                db.getPstm().setObject(6, null);
            }

            db.getPstm().setObject(7, LocalDateTime.now());
            db.getPstm().setObject(8, null);

            ok = db.executeMaj();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            db.closeConnection();
        }

        return ok;
    }

    @Override
    public int update(Book book) {

        String sql = "UPDATE book set title=?, author=?, isbn=?, count_pages=?, publication_year=?, category_id=?, updated_at=? WHERE id=?";

        try {

            db.initPrepar(sql);

            db.getPstm().setString(1, book.getTitle());
            db.getPstm().setString(2, book.getAuthor());
            db.getPstm().setString(3, book.getIsbn());
            db.getPstm().setInt(4, book.getCountPages());
            db.getPstm().setInt(5, book.getPublicationYear());

            // Category
            if (book.getCategory() != null && book.getCategory().getId() != 0) {
                db.getPstm().setInt(6, book.getCategory().getId());
            } else {
                db.getPstm().setObject(6, null);
            }

            db.getPstm().setObject(7, LocalDateTime.now());
            db.getPstm().setInt(8, book.getId());

            ok = db.executeMaj();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

        return ok;
    }

    @Override
    public int delete(int id) {

        String sql = "DELETE FROM book WHERE id=?";

        try {

            db.initPrepar(sql);
            db.getPstm().setInt(1, id);

            ok = db.executeMaj();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

        return ok;
    }

    @Override
    public List<Book> getAll() {

        String sql = "SELECT b.*, " +
                "c.id as category_id, " +
                "c.name as category_name, " +
                "c.state as category_state, " +
                "c.created_at as category_created_at, " +
                "c.updated_at as category_updated_at " +
                "FROM book b LEFT JOIN category c ON b.category_id = c.id " +
                "ORDER BY b.title ASC";

        List<Book> books = new ArrayList<>();

        try {

            db.initPrepar(sql);
            rs = db.executeSelect();

            while (rs.next()) {

                // CATEGORY
                Category category = null;

                if (rs.getObject("category_id") != null) {

                    category = new Category(
                            rs.getInt("category_id"),
                            rs.getObject("category_created_at", LocalDateTime.class),
                            rs.getObject("category_updated_at", LocalDateTime.class),
                            rs.getString("category_name"),
                            rs.getBoolean("category_state")
                    );
                }

                // BOOK
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("count_pages"),
                        rs.getInt("publication_year"),
                        category
                );

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

        return books;
    }

    @Override
    public Book get(int id) {

        String sql = "SELECT b.*, " +
                "c.id as category_id, " +
                "c.name as category_name, " +
                "c.state as category_state, " +
                "c.created_at as category_created_at, " +
                "c.updated_at as category_updated_at " +
                "FROM book b LEFT JOIN category c ON b.category_id = c.id " +
                "WHERE b.id=?";

        Book book = null;

        try {

            db.initPrepar(sql);
            db.getPstm().setInt(1, id);

            rs = db.executeSelect();

            if (rs.next()) {

                Category category = null;

                if (rs.getObject("category_id") != null) {

                    category = new Category(
                            rs.getInt("category_id"),
                            rs.getObject("category_created_at", LocalDateTime.class),
                            rs.getObject("category_updated_at", LocalDateTime.class),
                            rs.getString("category_name"),
                            rs.getBoolean("category_state")
                    );
                }

                book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("count_pages"),
                        rs.getInt("publication_year"),
                        category
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

        return book;
    }

    @Override
    public List<Book> getAllByAuthor(String author) {

        String sql = "SELECT b.*, " +
                "c.id as category_id, " +
                "c.name as category_name, " +
                "c.state as category_state, " +
                "c.created_at as category_created_at, " +
                "c.updated_at as category_updated_at " +
                "FROM book b LEFT JOIN category c ON b.category_id = c.id " +
                "WHERE lower(b.author)=lower(?) " +
                "ORDER BY b.title ASC";

        List<Book> books = new ArrayList<>();

        try {

            db.initPrepar(sql);
            db.getPstm().setString(1, author);

            rs = db.executeSelect();

            while (rs.next()) {

                Category category = null;

                if (rs.getObject("category_id") != null) {

                    category = new Category(
                            rs.getInt("category_id"),
                            rs.getObject("category_created_at", LocalDateTime.class),
                            rs.getObject("category_updated_at", LocalDateTime.class),
                            rs.getString("category_name"),
                            rs.getBoolean("category_state")
                    );
                }

                Book book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("count_pages"),
                        rs.getInt("publication_year"),
                        category
                );

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

        return books;
    }

    @Override
    public List<Book> getAllByCategory(String categoryName) {

        String sql = "SELECT b.*, " +
                "c.id as category_id, " +
                "c.name as category_name, " +
                "c.state as category_state, " +
                "c.created_at as category_created_at, " +
                "c.updated_at as category_updated_at " +
                "FROM book b left join category c ON b.category_id = c.id " +
                "WHERE lower(c.name)=lower(?) " +
                "ORDER BY b.title ASC";

        List<Book> books = new ArrayList<>();

        try {

            db.initPrepar(sql);
            db.getPstm().setString(1, categoryName);

            rs = db.executeSelect();

            while (rs.next()) {

                Category category = null;

                if (rs.getObject("category_id") != null) {

                    category = new Category(
                            rs.getInt("category_id"),
                            rs.getObject("category_created_at", LocalDateTime.class),
                            rs.getObject("category_updated_at", LocalDateTime.class),
                            rs.getString("category_name"),
                            rs.getBoolean("category_state")
                    );
                }

                Book book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("count_pages"),
                        rs.getInt("publication_year"),
                        category
                );

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

        return books;
    }

    @Override
    public List<Book> getAllByPublicationYear(int year) {

        String sql = "SELECT b.*, " +
                "c.id as category_id, " +
                "c.name as category_name, " +
                "c.state as category_state, " +
                "c.created_at as category_created_at, " +
                "c.updated_at as category_updated_at " +
                "FROM book b LEFT JOIN category c on b.category_id = c.id " +
                "WHERE b.publication_year=? " +
                "ORDER BY b.title ASC";

        List<Book> books = new ArrayList<>();

        try {

            db.initPrepar(sql);
            db.getPstm().setInt(1, year);

            rs = db.executeSelect();

            while (rs.next()) {

                Category category = null;

                if (rs.getObject("category_id") != null) {

                    category = new Category(
                            rs.getInt("category_id"),
                            rs.getObject("category_created_at", LocalDateTime.class),
                            rs.getObject("category_updated_at", LocalDateTime.class),
                            rs.getString("category_name"),
                            rs.getBoolean("category_state")
                    );
                }

                Book book = new Book(
                        rs.getInt("id"),
                        rs.getObject("created_at", LocalDateTime.class),
                        rs.getObject("updated_at", LocalDateTime.class),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("count_pages"),
                        rs.getInt("publication_year"),
                        category
                );

                books.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeConnection();
        }

        return books;
    }
}