/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package climatemonitoring;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author paolo
 */
public interface Stub extends Remote {
    List<User> readUser(Connection conn) throws SQLException, RemoteException;
    List<InterestingAreas> readAreas(Connection conn, int offset, int pageSize) throws SQLException, RemoteException;
    List<MonitoringStation> readStation(Connection conn) throws SQLException, RemoteException;
    List<Forecast> readForecast(Connection conn) throws SQLException, RemoteException;
    void write(String query, Connection conn) throws SQLException, RemoteException;
}
