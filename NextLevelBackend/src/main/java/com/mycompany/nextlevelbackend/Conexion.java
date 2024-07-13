/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nextlevelbackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author salvador
 */
public class Conexion {
    public String driver="com.mysql.cj.jdbc.Driver";
	
    public Connection getConnection() throws ClassNotFoundException{
        Connection conexion=null;
	try{
            Class.forName(driver);
            conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/Juegos","root","");
			
	}catch(SQLException e){
            System.out.println("hay un error: "+e);
        }		
	return conexion;
    }	
	
	
	
	

    public static void main(String[] args) throws ClassNotFoundException ,SQLException{
        Connection conexion = null;
	Conexion con = new Conexion();
	conexion=con.getConnection();
		
	PreparedStatement ps;
	ResultSet rs;
		
	ps=conexion.prepareStatement("select * from juego");		
	rs=ps.executeQuery();

	while(rs.next()){
            String titulo=rs.getString("titulo");
            System.out.println(titulo);			
	}
		
    }
}
