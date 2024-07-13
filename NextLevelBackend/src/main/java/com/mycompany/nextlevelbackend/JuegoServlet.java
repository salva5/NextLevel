/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.nextlevelbackend;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author salvador
 */
@WebServlet("/juegos")
public class JuegoServlet extends HttpServlet {
    private JuegoService juegoService;
    private ObjectMapper objectMapper;
	
    @Override
    public void init() throws ServletException{
	juegoService = new JuegoService();
	objectMapper = new ObjectMapper();
    }
	  
	  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        configurarCORS(resp);
        String pathInfo=req.getPathInfo();
        try{
            if(pathInfo==null||pathInfo.equals("/")){
                List<Juego> juegos=juegoService.getAllJuegos();
		String json = objectMapper.writeValueAsString(juegos);
		resp.setContentType("application/json");
		resp.getWriter().write(json);
            } else{
		String[] pathParts = pathInfo.split("/");
		int id=Integer.parseInt(pathParts[1]);
		Juego juego = juegoService.getJuegoById(id);
		if(juego != null){
                    String json = objectMapper.writeValueAsString(juego);
                    resp.setContentType("application/json");
                    resp.getWriter().write(json); 
		}else{
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
            } 
			  
	} catch(SQLException|ClassNotFoundException e){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
		  
    }
	  
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        configurarCORS(resp);
        try{
            Juego juego =objectMapper.readValue(req.getReader(),Juego.class);
            juegoService.addJuego(juego);
            resp.setStatus(HttpServletResponse.SC_CREATED);  
	} catch(SQLException|ClassNotFoundException e){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
		  
    }
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        configurarCORS(resp);
        try{
            Juego juego =objectMapper.readValue(req.getReader(),Juego.class);
			  
            juegoService.updateJuego(juego);
			  
            resp.setStatus(HttpServletResponse.SC_CREATED);  
	} catch(SQLException|ClassNotFoundException e){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}		  
    }
	  
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        configurarCORS(resp);
        String pathInfo=req.getPathInfo();
	try{
            if(pathInfo!=null&&pathInfo.split("/").length>1){
		int id= Integer.parseInt(pathInfo.split("/")[1]);  
		juegoService.deleteJuego(id);
		resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else{
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST); 
            }
	}catch(SQLException|ClassNotFoundException e){
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
    }
    private void configurarCORS(HttpServletResponse resp) {
        // Configurar los encabezados de respuesta para permitir CORS
        resp.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");        
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept, authorization");
    }

}
