package pe.edu.pucp.iweb.lab8.dao;

import java.util.ArrayList;
import java.sql.*;
import java.util.*;

import pe.edu.pucp.iweb.lab8.beans.Cancion;
import pe.edu.pucp.iweb.lab8.beans.Genero;

public class CancionDao extends BaseDao{ // TODO: heredar de BaseDao

    /**
     * Ejercicio 2: todas las canciones (con el nombre de su género, JOIN),
     * ordenadas por título. Use PreparedStatement.
     */
    public ArrayList<Cancion> listar() {
        // TODO
        ArrayList<Cancion> lista = new ArrayList<>();
        String sql = "SELECT * FROM cancion JOIN genero ON cancion.idGenero = genero.idGenero ORDER BY titulo";
        try (Connection conn = this.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()) {
                Cancion ls = new Cancion();
                ls.setIdCancion(rs.getInt("idcancion"));
                ls.setTitulo(rs.getString("titulo"));
                ls.setArtista(rs.getString("artista"));
                ls.setDuracionSeg(rs.getInt("duracionSeg"));
                ls.setAnio(rs.getInt("anio"));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getInt("genero.idgenero"));
                genero.setNombre(rs.getString("nombre"));
                ls.setGenero(genero);

                lista.add(ls);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Ejercicio 2: la canción con ese id, o null si no existe.
     */
    public Cancion buscarPorId(int idCancion) {
        Cancion cancion = null;
        String sql = "SELECT * FROM cancion WHERE idcancion = ?";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setInt(1, idCancion);
             try (ResultSet rs = pstmt.executeQuery()){
                 if (rs.next()) {
                     cancion = new Cancion();
                     cancion.setIdCancion(rs.getInt("idcancion"));
                     cancion.setTitulo(rs.getString("titulo"));
                     cancion.setArtista(rs.getString("artista"));
                     cancion.setDuracionSeg(rs.getInt("duracionSeg"));
                     cancion.setAnio(rs.getInt("anio"));

                     Genero genero = new Genero();
                     genero.setIdGenero(rs.getInt("genero.idgenero"));
                     genero.setNombre(rs.getString("nombre"));
                     cancion.setGenero(genero);

                 }
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cancion;
    }

    /**
     * Ejercicio 2: INSERT de una canción nueva (PreparedStatement).
     */
    public void crear(Cancion cancion) {
        // TODO
        String sql = "INSERT INTO cancion (titulo, artista, duracionSeg, anio, idGenero) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cancion.getTitulo());
            pstmt.setString(2, cancion.getArtista());
            pstmt.setInt(3, cancion.getDuracionSeg());
            pstmt.setInt(4, cancion.getAnio());
            pstmt.setInt(5, cancion.getGenero().getIdGenero());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ejercicio 2: UPDATE de una canción existente (PreparedStatement).
     */
    public void actualizar(int idCancion, String titulo, String artista, String ciudad, int idLandingStation) {
        // TODO
        String sql = "UPDATE cancion SET titulo = ?, artista = ?, duracionSeg = ?, anio = ?, genero = ? WHERE idcancion = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            pstmt.setString(2, artista);
            pstmt.setString(3, ciudad);
            pstmt.setInt(4, idLandingStation);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ejercicio 3: DELETE por id (PreparedStatement).
     */
    public void eliminar(int idCancion) {
        // TODO
    }
}
