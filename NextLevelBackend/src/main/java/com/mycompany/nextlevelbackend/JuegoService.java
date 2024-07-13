/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nextlevelbackend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salvador
 */
public class JuegoService {
   private Conexion conexion;

    public JuegoService() {
        this.conexion = new Conexion();
    }

    public List<Juego> getAllJuegos() throws SQLException, ClassNotFoundException 
    {
        List<Juego> juegos = new ArrayList<>();
        Connection con = conexion.getConnection();
        String sql = "SELECT * FROM juego";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Juego juego = new Juego(
                rs.getInt("id"),
                rs.getString("titulo"),                
                rs.getString("imagen"),
                rs.getString("descripcion"),
                rs.getString("genero")
            );
            juegos.add(juego);
        }

        rs.close();
        ps.close();
        con.close();
        
        return juegos;
    }

    public Juego getJuegoById(int id) throws SQLException, ClassNotFoundException 
    {
        Juego juego = null;
        Connection con = conexion.getConnection();
        String sql = "SELECT * FROM juego WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            juego = new Juego(
                rs.getInt("id"),
                rs.getString("titulo"),               
                rs.getString("imagen"),
                rs.getString("descripcion"),
                rs.getString("genero")
            );
        }

        rs.close();
        ps.close();
        con.close();

        return juego;
    }

    public void addJuego(Juego juego) throws SQLException, ClassNotFoundException 
    {
        Connection con = conexion.getConnection();
        String sql = "INSERT INTO juego (titulo,  imagen, descripcion, genero) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, juego.getTitulo());       
        ps.setString(2, juego.getImagen());
        ps.setString(3, juego.getDescripcion());
        ps.setString(4, juego.getGenero());
        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public void updateJuego(Juego juego) throws SQLException, ClassNotFoundException 
    {
        Connection con = conexion.getConnection();
        String sql = "UPDATE juego SET titulo = ?,  imagen = ?, descripcion = ?, genero = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, juego.getTitulo());       
        ps.setString(2, juego.getImagen());
        ps.setString(3, juego.getDescripcion());
        ps.setString(4, juego.getGenero());
        ps.setInt(5, juego.getId());
        ps.executeUpdate();

        ps.close();
        con.close();
    }

    public void deleteJuego(int id) throws SQLException, ClassNotFoundException 
    {
        Connection con = conexion.getConnection();
        String sql = "DELETE FROM juego WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();

        ps.close();
        con.close();
    }
}
