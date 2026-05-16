package config;

public class Constants {

    public static final String HOST = "localhost";
    public static final String PORT = "5432";
    public static final String DATABASE = "book-db2";

    public static final String USER = "postgres";
    public static final String PASSWORD = "PostgreSQL";

    public static final String URL =
            "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE;

    public static final String DRIVER = "org.postgresql.Driver";
}


