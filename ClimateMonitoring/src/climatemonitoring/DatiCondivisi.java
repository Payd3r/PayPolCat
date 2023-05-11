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
 * @author Ficara Paolo, Mauri Andrea, Luca Cattaneo
 */
public class DatiCondivisi {

    private static DatiCondivisi instance = null;
    private List<MonitoringStation> monitoringStations;
    private List<User> users;
    private List<InterestingAreas> areas;
    private List<Forecast> forecasts;
    private User operatore;

    // Costruttore invisibile
    private DatiCondivisi() throws IOException, ParseException {
        monitoringStations = FileManager.readStation(Paths.get("Dati/CentroMonitoraggio.txt"));
        users = FileManager.readUser(Paths.get("Dati/OperatoriRegistrati.txt"));
        areas = FileManager.readAreas(Paths.get("Dati/CoordinateMonitoraggio.csv"));
        forecasts = FileManager.readForecast(Paths.get("Dati/ParametriClimatici.txt"));
        operatore = null;
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

    private static double calcDist(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = 6371 * c;
        return distance;
    }

    public String[] cercaAreaGeografica(String s) {
        List<String> intAreas = new ArrayList<String>();
        if (Character.isDigit(s.charAt(0))) {
            //controllo con lat lon           
            double lat1 = Double.parseDouble(s.split(" ")[0].replaceAll("\\.", ""));
            double lon1 = Double.parseDouble(s.split(" ")[1].replaceAll("\\.", ""));
            for (int i = 0; i < areas.size(); i++) {
                double lat2 = Double.parseDouble(areas.get(i).getLat().replaceAll("\\.", ""));
                double lon2 = Double.parseDouble(areas.get(i).getLon().replaceAll("\\.", ""));
                if (calcDist(lat1, lon1, lat2, lon2) < 10.0) {
                    if (!intAreas.contains(areas.get(i).getName())) {
                        intAreas.add(areas.get(i).getName());
                    }
                }
            }
        } else {
            //controllo con nome città
            for (int i = 0; i < areas.size(); i++) {
                if (areas.get(i).getName().toLowerCase().contains(s)) {
                    if (!intAreas.contains(areas.get(i).getName())) {
                        intAreas.add(areas.get(i).getName());
                    }
                }
            }
        }
//        String[] temp = new String[intAreas.size()];
//        for (int i = 0; i < intAreas.size(); i++) {
//            temp[i] = intAreas.get(i);
//        }
        return intAreas.toArray(String[]::new);
    }

    public boolean existForecast(String area) {
        for (int i = 0; i < forecasts.size(); i++) {
            if (forecasts.get(i).getIdCittà().equalsIgnoreCase(area)) {
                return true;
            }
        }
        return false;
    }

    public List<Forecast> getForecasts(String name) {
        List<Forecast> temp = new ArrayList<Forecast>();
        for (int i = 0; i < forecasts.size(); i++) {
            if (forecasts.get(i).getIdCittà().equalsIgnoreCase(name)) {
                temp.add(forecasts.get(i));
            }
        }
        return temp;
    }

    public User getOperatore() {
        return operatore;
    }

    public void setOperatore(User operatore) {
        this.operatore = operatore;
    }
}
