/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author shah rumit
 */
public class view_servlet extends HttpServlet {

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
         ResultSet rs;
         Statement st;
         Connection cn;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("com.mysql.jdbc.Driver"); 
               cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookinfo","root","");
                st=cn.createStatement();
                String q="select * from author";
                rs = st.executeQuery(q);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>view data</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> AUTHOR DATA</h1>");
            out.println("<table border=1>");
            out.println("<tr><th> ID </th>"
                    + "<th>Full Name</th>"
                    + "<th>City</th>"
                    + "<th>phone</th></tr>");
            
            while(rs.next())
            {
                String id=rs.getString(1);
                String name=rs.getString(2);
                String ct=rs.getString(3);
                String phone=rs.getString(4);
                
                out.println("<tr><td> "+id+" </td>"
                    + "<td> "+name+" </td>"
                    + "<td> "+ct+" </td>"
                    + "<td> "+phone+" </td>"
                    + "<td><a href=update?id="+id+">update</a> </td>"
                    + "<td><a href=delete?id="+id+">delete</a> </td></tr>");
               
            }
            out.println("</table>");
            out.println("<br>  <a href=index.jsp>logout</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(view_servlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
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
