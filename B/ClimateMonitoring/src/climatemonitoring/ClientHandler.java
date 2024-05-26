/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.lang.System.Logger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.logging.Level;

/**
 *
 * @author Payd3r
 */
public class ClientHandler extends UnicastRemoteObject {

    private static int PORT = 1234;
    private Stub stub;

    private static ClientHandler instance = null;

    public ClientHandler() throws RemoteException, NotBoundException, InterruptedException {
        stub = (Stub) LocateRegistry.getRegistry("127.0.0.1", PORT).lookup("Stub");
    }

    public Stub getStub() {
        return stub;
    }

    public static ClientHandler getInstance() {
        if (instance == null) {
            try {
                instance = new ClientHandler();
            } catch (RemoteException | NotBoundException | InterruptedException ex) {
                java.util.logging.Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

}
