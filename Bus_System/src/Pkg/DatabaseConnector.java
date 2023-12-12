package Pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    // Informasi koneksi database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/busman"; // Sesuaikan dengan URL database Anda
    private static final String USER = "username"; // Ganti dengan username database Anda
    private static final String PASSWORD = "password"; // Ganti dengan password database Anda

    private static Connection connection;

    // Fungsi untuk membuat koneksi dengan database
    public static Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                // Membuat koneksi
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Berhasil terhubung ke database");
            }
        } catch (SQLException e) {
            System.err.println("Error koneksi database: " + e.getMessage());
        }
        return connection;
    }

    // Fungsi untuk menutup koneksi dengan database
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi database ditutup");
            }
        } catch (SQLException e) {
            System.err.println("Error menutup koneksi database: " + e.getMessage());
        }
    }
}