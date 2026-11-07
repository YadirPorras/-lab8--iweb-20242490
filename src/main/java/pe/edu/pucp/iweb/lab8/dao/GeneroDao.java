package pe.edu.pucp.iweb.lab8.dao;

import java.util.ArrayList;
import java.util.*;
import java.sql.*;

import pe.edu.pucp.iweb.lab8.beans.Genero;

public class GeneroDao extends BaseDao{ // TODO: heredar de BaseDao

    /**
     * Ejercicio 2: todos los géneros ordenados por nombre.
     */
    public ArrayList<Genero> listar() {
        // TODO
        ArrayList<Genero> lista = new ArrayList<>();
        String sql = "SELECT * FROM genero ORDER BY nombre";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Genero ls = new Genero();
                ls.setIdGenero(rs.getInt("idgenero"));
                ls.setNombre(rs.getString("nombre"));
                lista.add(ls);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
