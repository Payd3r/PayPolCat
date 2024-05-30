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

/**
 *
 * @author paolo
 */
public interface Stub extends Remote {
    ArrayList<User> readUser() throws SQLException, RemoteException;
    ArrayList<InterestingAreas> readAreas() throws SQLException, RemoteException;
    ArrayList<MonitoringStation> readStation() throws SQLException, RemoteException;
    ArrayList<Forecast> readForecast() throws SQLException, RemoteException;
    void write(String query) throws SQLException, RemoteException;
    void sortAreas() throws RemoteException;
    String[] cercaAreaGeografica(String a) throws RemoteException;
    boolean existForecast(String a) throws RemoteException;
    void setOperatore(User a) throws RemoteException;
    User getOperatore() throws RemoteException;
    void refresh() throws RemoteException;
}
