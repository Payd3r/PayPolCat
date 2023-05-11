/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

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
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }

    public String[] cercaAreaGeografica(String s) {
        List<String> intAreas = new ArrayList<String>();
        if (Character.isDigit(s.charAt(0))) {
            //controllo con lat lon           
            double lat1 = Double.parseDouble(s.split(" ")[0]);
            double lon1 = Double.parseDouble(s.split(" ")[1]);
            intAreas = cercaLimitrofo(lat1, lon1);
        } else {
            //controllo con nome città
            List<InterestingAreas> l = areas.parallelStream().filter(elemento -> elemento.contains(s)).collect(Collectors.toList());
            if (l.size() == 1) {
                intAreas.add(l.get(0).getName());
                double lat1 = Double.parseDouble(l.get(0).getLat());
                double lon1 = Double.parseDouble(l.get(0).getLon());
                intAreas = cercaLimitrofo(lat1, lon1);
            } else {
                for (int i = 0; i < l.size(); i++) {
                    intAreas.add(l.get(i).getName());
                }
            }
        }
        return intAreas.toArray(String[]::new);
    }

    public List<String> cercaLimitrofo(double lat1, double lon1) {
        List<String> intAreas = new ArrayList<String>();
        double lat2, lon2;
        for (int i = 0; i < areas.size(); i++) {
            lat2 = Double.parseDouble(areas.get(i).getLat());
            lon2 = Double.parseDouble(areas.get(i).getLon());
            double dist = calcDist(lat1, lon1, lat2, lon2);
            if (dist < 5.0) {
                if (!intAreas.contains(areas.get(i).getName())) {
                    intAreas.add(areas.get(i).getName());
                }
            }
        }
        return intAreas;
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

    public void sortAreas() {
        Collections.sort(areas, new Comparator<InterestingAreas>() {
            @Override
            public int compare(InterestingAreas lhs, InterestingAreas rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getName().compareTo(rhs.getName()) > 0 ? 1 : (lhs.getName().compareTo(rhs.getName())) < 0 ? -1 : 0;
            }
        });
    }

}
