package climatemonitoring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.sql.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe singleton che legge e memorizza le informazioni necessarie per
 * l'applicazione
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 *
 */
public class DatiCondivisi extends UnicastRemoteObject {

    //attributi
    private static DatiCondivisi instance = null;
    private ArrayList<MonitoringStation> monitoringStations;
    private ArrayList<User> users;
    private ArrayList<InterestingAreas> areas;
    private ArrayList<Forecast> forecasts;
    private User operatore;
    private Connection conn;
    private DBManager dBManager;

    public Connection getConn() {
        return conn;
    }
    private final String JDBC_DRIVER = "org.postgresql.Driver";

    // Costruttore
    private DatiCondivisi() throws ClassNotFoundException, SQLException, RemoteException {
        super();
        dBManager = new DBManager();
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(
                "jdbc:postgresql://bvgq1krz8lvxl3imqz0z-postgresql.services.clever-cloud.com:50013/bvgq1krz8lvxl3imqz0z",
                "uwrddkjoepyde275arvo",
                "sx9wxithD6BeqC1nCYbaUpETEHlqtJ");
        users = dBManager.readUser(conn);
        areas = dBManager.readAreas(conn, 0, 140867);
        forecasts = dBManager.readForecast(conn);
        monitoringStations = dBManager.readStation(conn);
        operatore = null;
        sortAreas();
    }

    //metodi
    /**
     * Metodo che crea l'unica instanza della classe
     *
     * @return l'istanza della classe
     * @throws ClassNotFoundException Errore nel caricamento dei driver jdbc
     * @throws SQLException Errore nella connessione al database o
     * nell'esecuzione della query
     */
    public static DatiCondivisi getInstance() throws ClassNotFoundException, SQLException {
        // Crea l'oggetto solo se NON esiste:
        if (instance == null) {
            try {
                instance = new DatiCondivisi();
            } catch (RemoteException ex) {
                Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

    /**
     * Metodo che ricrea l'unica instanza della classe
     *
     * @throws SQLException Errore nella connessione al database o
     * nell'esecuzione della query
     */
    public void refresh() throws SQLException, ClassNotFoundException {
        // Ricrea l'oggetto:       
        users = dBManager.readUser(conn);
        //areas = dBManager.readAreas(conn, 0, 10000);
        forecasts = dBManager.readForecast(conn);
        monitoringStations = dBManager.readStation(conn);
        sortAreas();
    }

    /**
     * Metodo che restituisce le stazioni di monitoraggio
     *
     * @return una <strong>List</strong> di MonitoringStation
     */
    public ArrayList<MonitoringStation> getMonitoringStations() {
        return monitoringStations;
    }

    /**
     * Metodo che restituisce gli utenti registrati
     *
     * @return una <strong>List</strong> di <strong>User</strong>
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Metodo che restituisce le aree di interesse
     *
     * @return una <strong>List</strong> di <strong>InterestingAreas</strong>
     */
    public ArrayList<InterestingAreas> getAreas() {
        return areas;
    }

    /**
     * Metodo che restituisce tutte le rilevazioni eseguite fin'ora
     *
     * @return una <strong>List</strong> di <strong>Forecast</strong>
     */
    public ArrayList<Forecast> getForecasts() {
        return forecasts;
    }

    public void insert(String s) throws SQLException {
        dBManager.write(s, conn);
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
        ArrayList<String> intAreas = new ArrayList<String>();
        if (Character.isDigit(s.charAt(0))) {
            //controllo con lat lon           
            double lat1 = Double.parseDouble(s.split(" ")[0]);
            double lon1 = Double.parseDouble(s.split(" ")[1]);
            intAreas = cercaLimitrofo(lat1, lon1);
        } else {
            //controllo con nome città
            ArrayList<InterestingAreas> l = (ArrayList<InterestingAreas>) areas.parallelStream().filter(elemento -> elemento.contains(s)).collect(Collectors.toList());
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
    public ArrayList<String> cercaLimitrofo(double lat1, double lon1) {
        ArrayList<String> intAreas = new ArrayList<String>();
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
    public boolean existForecast(String area) throws SQLException {
        for (int i = 0; i < forecasts.size(); i++) {
            if (forecasts.get(i).getIdCittà().equalsIgnoreCase(convertNameToId(area))) {
                return true;
            }
        }
        return false;
    }

    public String convertNameToId(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select id from coordinatemonitoraggio where name='" + name + "'");
        ResultSet rs = stmt.executeQuery();
        String id = null;
        while (rs.next()) {
            id = rs.getString("id");
            break;
        }
        rs.close();
        return id;
    }

    /**
     * Metodo che restituisce le rilevazioni di una determinata città
     *
     * @param name nome città
     * @return una <strong>List</strong> di <strong>Forecast</strong>
     */
    public ArrayList<Forecast> getForecasts(String name) throws SQLException {
        ArrayList<Forecast> temp = new ArrayList<Forecast>();
        name = convertNameToId(name);
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
