/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rmiServer;

import edu.ssn.rmi.rmiImplementation;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Arunsankar
 */
public class RMIServer {

     private void startServer(){
        try {
            // create on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // create a new service named myMessage
            registry.rebind("rmiService", new rmiImplementation());
        } catch (Exception e) {
        }      
        System.out.println("system is ready");
    }
    
    public static void main(String[] args) {
        String filepath = "/Users/Arunsankar/NetBeansProjects/RMIServer/policy.all";
        System.setProperty("java.security.policy", filepath);
        RMIServer main = new RMIServer();
        main.startServer();
    }
}
