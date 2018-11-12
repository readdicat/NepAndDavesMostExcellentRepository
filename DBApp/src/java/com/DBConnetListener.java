/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import db.DBBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.ServletContext;

/**
 * Web application lifecycle listener.
 *
 * @author me-aydin
 */
@WebListener()
public class DBConnetListener implements ServletContextListener {
    Connection conn = null;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        ServletContext sc = sce.getServletContext();
        String db = sc.getInitParameter("Database");
      //  String vendor = sc.getInitParameter("vendor");
        String table = sc.getInitParameter("table");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("jdbc:mysql://localhost:3306/"+ db.trim() + "root");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "user=root", "");
            System.out.println("conn = " + conn);
        }
        catch(ClassNotFoundException | SQLException e) {
            System.out.println("couldn't connect = " + e);
            sc.setAttribute("error", e);
        }
        // DBBean dbBean = new DBBean();  
        // dbBean.setTable(table);
        // sc.setAttribute("db", dbBean);
        sc.setAttribute("connection", conn);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       try { 
           System.out.println("closing...");
           conn.close(); } catch(SQLException e) {
           System.out.println("error = " + e);
           }
    }
}
