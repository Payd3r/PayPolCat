/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
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
    private List<Forecast> forecasts;

    // Costruttore invisibile
    private DatiCondivisi() throws IOException, ParseException {
        monitoringStations = FileManager.readStation(Paths.get("Dati/CentroMonitoraggio.txt"));
        users = FileManager.readUser(Paths.get("Dati/OperatoriRegistrati.txt"));
        areas = FileManager.readAreas(Paths.get("Dati/CoordinateMonitoraggio.csv"));
        forecasts = FileManager.readForecast(Paths.get("Dati/ParametriClimatici.txt"));
    }

    public static DatiCondivisi getInstance() throws IOException, ParseException {
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

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public List<InterestingAreas> cercaAreaGeografica(String s) {
        List<InterestingAreas> intAreas = new ArrayList<InterestingAreas>();
        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i).getName().contains(s)) {
                intAreas.add(areas.get(i));
            }
        }
        return intAreas;
    }
}
