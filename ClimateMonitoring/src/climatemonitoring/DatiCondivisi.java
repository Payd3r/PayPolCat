/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Catta
 */
public class DatiCondivisi {

    private static DatiCondivisi instance = null;
    private List<MonitoringStation> monitoringStations;
    private List<User> users;
    private List<InterestingAreas> areas;

    // Costruttore invisibile
    private DatiCondivisi() throws IOException {
        monitoringStations = FileManager.readStation(Paths.get("Dati/CentroMonitoraggio.txt"));
        users = FileManager.readUser(Paths.get("Dati/OperatoriRegistrati.txt"));
        areas = FileManager.readAreas(Paths.get("Dati/CoordinateMonitoraggio.csv"));
    }

    public static DatiCondivisi getInstance() throws IOException {
        // Crea l'oggetto solo se NON esiste:
        if (instance == null) {
            instance = new DatiCondivisi();
        }
        return instance;
    }

    public List<MonitoringStation> getMonitoringStations() {
        return monitoringStations;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<InterestingAreas> getAreas() {
        return areas;
    }
    
    
}
