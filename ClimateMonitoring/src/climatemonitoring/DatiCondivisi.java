package climatemonitoring;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe singleton che legge e memorizza le informazioni necessarie per
 * l'applicazione
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 *
 */
public class DatiCondivisi {

    //attributi
    private static DatiCondivisi instance = null;
    private List<MonitoringStation> monitoringStations;
    private List<User> users;
    private List<InterestingAreas> areas;
    private List<Forecast> forecasts;
    private User operatore;

    // Costruttore
    private DatiCondivisi() throws IOException, ParseException {
        monitoringStations = FileManager.readStation(Paths.get("Dati/CentroMonitoraggio.txt"));
        users = FileManager.readUser(Paths.get("Dati/OperatoriRegistrati.txt"));
        areas = FileManager.readAreas(Paths.get("Dati/CoordinateMonitoraggio.csv"));
        forecasts = FileManager.readForecast(Paths.get("Dati/ParametriClimatici.txt"));
        operatore = null;
        sortAreas();
    }

    //metodi
    /**
     * Metodo che crea l'unica instanza della classe
     *
     * @return l'istanza della classe
     * @throws IOException
     * @throws ParseException
     */
    public static DatiCondivisi getInstance() throws IOException, ParseException {
        // Crea l'oggetto solo se NON esiste:
        if (instance == null) {
            instance = new DatiCondivisi();
        }
        return instance;
    }

    /**
     * Metodo che ricrea l'unica instanza della classe
     *     
     * @throws IOException
     * @throws ParseException
     */
    public void refresh() throws IOException, ParseException {
        // Ricrea l'oggetto:       
        monitoringStations = FileManager.readStation(Paths.get("Dati/CentroMonitoraggio.txt"));
        users = FileManager.readUser(Paths.get("Dati/OperatoriRegistrati.txt"));
        areas = FileManager.readAreas(Paths.get("Dati/CoordinateMonitoraggio.csv"));
        forecasts = FileManager.readForecast(Paths.get("Dati/ParametriClimatici.txt"));
        sortAreas();
    }

    /**
     * Metodo che restituisce le stazioni di monitoraggio
     *
     * @return una <strong>List</strong> di MonitoringStation
     */
    public List<MonitoringStation> getMonitoringStations() {
        return monitoringStations;
    }

    /**
     * Metodo che restituisce gli utenti registrati
     *
     * @return una <strong>List</strong> di <strong>User</strong>
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Metodo che restituisce le aree di interesse
     *
     * @return una <strong>List</strong> di <strong>InterestingAreas</strong>
     */
    public List<InterestingAreas> getAreas() {
        return areas;
    }

    /**
     * Metodo che restituisce tutte le rilevazioni eseguite fin'ora
     *
     * @return una <strong>List</strong> di <strong>Forecast</strong>
     */
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

    /**
     * Metodo che cerca l'area geografica interessata dal cittadino
     *
     * @param s l'area da cercare
     * @return un <strong>array</strong> di <strong>String</strong> che
     * memorizza le città nell'area d'interesse
     */
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

    /**
     * Metodo che cerca le città aree di interesse vicine ad un determinato
     * punto
     *
     * @param lat1 latitudine
     * @param lon1 longitudine
     * @return una <strong>List</strong> di <strong>String</strong> contenente
     * le aree di interesse vicine
     */
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

    /**
     * Metodo che controlla se una determinata area di interesse ha delle
     * rilevazioni
     *
     * @param area area di interesse
     * @return <strong>true</strong> se l'area ha rilevazioni,
     * <strong>falso</strong> se
     */
    public boolean existForecast(String area) {
        for (int i = 0; i < forecasts.size(); i++) {
            if (forecasts.get(i).getIdCittà().equalsIgnoreCase(area)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo che restituisce le rilevazioni di una determinata città
     *
     * @param name nome città
     * @return una <strong>List</strong> di <strong>Forecast</strong>
     */
    public List<Forecast> getForecasts(String name) {
        List<Forecast> temp = new ArrayList<Forecast>();
        for (int i = 0; i < forecasts.size(); i++) {
            if (forecasts.get(i).getIdCittà().equalsIgnoreCase(name)) {
                temp.add(forecasts.get(i));
            }
        }
        return temp;
    }

    /**
     * Metodo che restituisce l'operatore attualmente in sessione
     *
     * @return un oggetto <strong>User</strong> che descrive l'operatore
     */
    public User getOperatore() {
        return operatore;
    }

    /**
     * Metodo che modifica il valore dell'oggetto operatore attuale
     *
     * @param operatore nuovo valore operatore
     */
    public void setOperatore(User operatore) {
        this.operatore = operatore;
    }

    /**
     * Metodo che ordina in modo crescente le aree di interesse
     */
    public void sortAreas() {
        Collections.sort(areas, new Comparator<InterestingAreas>() {
            @Override
            public int compare(InterestingAreas lhs, InterestingAreas rhs) {
                return lhs.getName().compareTo(rhs.getName()) > 0 ? 1 : (lhs.getName().compareTo(rhs.getName())) < 0 ? -1 : 0;
            }
        });
    }

}
