/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import db.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author me-aydin
 */
@WebServlet(name = "DBServlet", urlPatterns = {"/DBServlet.do"})
public class DBServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        System.out.println("parm = " + request.getParameter("tbl"));
        DBBean dbBean = new DBBean();
        dbBean.setCon((Connection) request.getServletContext().getAttribute("connection"));
        if (request.getParameter("tbl").equals("turnover")) {
            System.out.println("in if statement");
            dbBean.setTable("journey");
            request.setAttribute("dbbean", dbBean);
            request.getRequestDispatcher("/WEB-INF/showTurnOver.jsp").forward(request, response);
        }
        if (request.getParameter("tbl").equals("dailycust")) {
            System.out.println("in 2nd if statement");
            dbBean.setTable("journey");
            request.setAttribute("dbbean", dbBean);
            request.getRequestDispatcher("/WEB-INF/showCustomers.jsp").forward(request, response);
        }
        dbBean.setTable(request.getParameter("tbl"));

        request.setAttribute("dbbean", dbBean);

        request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
