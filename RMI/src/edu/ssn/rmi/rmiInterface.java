/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ssn.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Arun
 */
public interface rmiInterface extends Remote {
    void sayHello(String name) throws RemoteException;
    public boolean signUp(String userName ,String password ,String email ,String mobile ,String address) throws RemoteException;
    public boolean validateLogin(String userName, String password) throws RemoteException;
    public boolean validateAdmin(String uName, String password) throws RemoteException;
    /////bbbbbb

}
