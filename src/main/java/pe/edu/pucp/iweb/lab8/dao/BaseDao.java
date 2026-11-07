package pe.edu.pucp.iweb.lab8.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

/**
 * Ejercicio 2: DAO base.
 *
 *  - Esta clase debe ser ABSTRACTA.
 *  - GeneroDao y CancionDao deben heredar de ella y obtener la conexión
 *    únicamente con getConnection().
 */
public abstract class BaseDao { // TODO: convertir en clase abstracta

    private static final String URL = "jdbc:mysql://localhost:3306/melodiape";
    private static final String USER = "root";
    private static final String PASS = "12345678"; // cambiar por la contraseña de su MySQL

    public Connection getConnection() throws SQLException {
        // TODO 2a: cargar el driver (com.mysql.cj.jdbc.Driver) y retornar la conexión
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }
}