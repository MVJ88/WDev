/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ssn.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.ssn.BusinessObject.signUpObject;

/**
 *
 * @author Arunsankar
 */
public class rmiImplementation extends UnicastRemoteObject implements rmiInterface {

    public rmiImplementation() throws RemoteException {
    }

    @Override
    public void sayHello(String name) throws RemoteException {
        System.out.println("hello " + name);
    }

    @Override
    public boolean signUp(String userName, String password, String email, String mobile, String address) throws RemoteException {
        signUpObject so = new signUpObject();
        so.setUserName(userName);
        so.setPassword(password);
        so.setAddress(address);
        so.setEmail(email);
        so.setMobile(mobile);
        String connectionURL;
        connectionURL = "jdbc:mysql://127.0.0.1:3306/textile";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root", "root");
            System.out.println("connection establsihed");
            //Statement st = connection.createStatement();
            PreparedStatement pStatement;
            ResultSet rs = null;
            pStatement = connection.prepareStatement("INSERT INTO userDetails(username,password,address,email,mobile) VALUES (?,?,?,?,?);");
            pStatement.setString(1, so.getUserName());
            pStatement.setString(2, so.getPassword());
            pStatement.setString(3, so.getAddress());
            pStatement.setString(4, so.getEmail());
            pStatement.setString(5, so.getMobile());
            int row = pStatement.executeUpdate();
            System.out.println(row);
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean validateLogin(String userName, String password) {
        signUpObject so = new signUpObject();
        so.setUserName(userName);
        so.setPassword(password);
        String connectionURL;
        connectionURL = "jdbc:mysql://127.0.0.1:3306/textile";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root", "root");
            System.out.println("connection establsihed");
            PreparedStatement pStatement;
                ResultSet rs = null;
                pStatement = connection.prepareStatement("SELECT * FROM userDetails WHERE username =? and password =?;");
                pStatement.setString(1, so.getUserName());
                pStatement.setString(2, so.getPassword());
                rs = pStatement.executeQuery();
                while(rs.next())
                {
                    return true;
                }
                
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean validateAdmin(String userName, String password) throws RemoteException {
        signUpObject so = new signUpObject();
        so.setUserName(userName);
        so.setPassword(password);
        String connectionURL;
        connectionURL = "jdbc:mysql://127.0.0.1:3306/textile";
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root", "root");
            System.out.println("connection establsihed");
            PreparedStatement pStatement;
                ResultSet rs = null;
                pStatement = connection.prepareStatement("SELECT * FROM adminDetails WHERE username =? and password =?;");
                pStatement.setString(1, so.getUserName());
                pStatement.setString(2, so.getPassword());
                rs = pStatement.executeQuery();
                while(rs.next())
                {
                    return true;
                }
                
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
