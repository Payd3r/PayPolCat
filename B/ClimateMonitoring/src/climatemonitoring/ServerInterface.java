/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package climatemonitoring;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaccia per la comunicazione remota con il server.
 * <p>
 * Questa interfaccia definisce i metodi utilizzati per interagire con il server
 * remoto per ottenere e modificare i dati.
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 */
public interface ServerInterface extends Remote {

    ArrayList<User> readUser() throws SQLException, RemoteException;

    ArrayList<InterestingAreas> readAreas() throws SQLException, RemoteException;

    ArrayList<MonitoringStation> readStation() throws SQLException, RemoteException;

    ArrayList<Forecast> readForecast() throws SQLException, RemoteException;

    public String convertNameToId(String name) throws SQLException, RemoteException;

    void writeForecast(Forecast f) throws SQLException, RemoteException;

    void writeUser(User u) throws SQLException, RemoteException;

    void writeStation(MonitoringStation ms, List<String> areas) throws SQLException, RemoteException;

    void sortAreas() throws RemoteException;

    String[] cercaAreaGeografica(String a) throws RemoteException;

    boolean existForecast(String a) throws RemoteException;

    void setOperatore(User a) throws RemoteException;

    User getOperatore() throws RemoteException;

    void refresh() throws RemoteException;

    String normalizeStrings(String s);
}
