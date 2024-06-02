/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Payd3r
 */
public class ServerMain extends UnicastRemoteObject implements Stub {

    static final int PORT = 1234;

    public ServerMain() throws RemoteException {

    }

    public static void main(String[] args) throws RemoteException {
        LocateRegistry.createRegistry(PORT).rebind("Stub", new ServerMain());
        System.out.println("Server ready");
    }

    @Override
    public ArrayList<User> readUser() throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().getUsers();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<InterestingAreas> readAreas() throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().getAreas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<MonitoringStation> readStation() throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().getMonitoringStations();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Forecast> readForecast() throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().getForecasts();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void sortAreas() throws RemoteException {
        try {
            DatiCondivisi.getInstance().sortAreas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String[] cercaAreaGeografica(String a) throws RemoteException {
        try {
            return DatiCondivisi.getInstance().cercaAreaGeografica(a);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean existForecast(String a) throws RemoteException {
        try {
            return DatiCondivisi.getInstance().existForecast(a);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void setOperatore(User a) throws RemoteException {
        try {
            DatiCondivisi.getInstance().setOperatore(a);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User getOperatore() throws RemoteException {
        try {
            return DatiCondivisi.getInstance().getOperatore();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void refresh() throws RemoteException {
        try {
            DatiCondivisi.getInstance().refresh();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeForecast(Forecast f) throws SQLException, RemoteException {
        try {
            DatiCondivisi.getInstance().writeForecast(f);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeUser(User u) throws SQLException, RemoteException {
        try {
            DatiCondivisi.getInstance().writeUser(u);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeStation(MonitoringStation ms, List<String> areas) throws SQLException, RemoteException {
        try {
            DatiCondivisi.getInstance().writeStation(ms, areas);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String convertNameToId(String name) throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().convertNameToId(name);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
