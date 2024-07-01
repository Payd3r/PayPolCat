package climatemonitoring;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Classe singleton che legge e memorizza le informazioni necessarie per
 * l'applicazione
 *
 * @author Ficara Paolo 755155 CO
 * @author Mauri Andrea 755140 CO
 * @author Luca Cattaneo 755083 CO
 */
public class DatiCondivisi extends UnicastRemoteObject {

    /**
     * Istanza singleton della classe <code>DatiCondivisi</code>.
     * <p>
     * Questo attributo tiene traccia dell'unica istanza della classe
     * <code>DatiCondivisi</code>, assicurando che venga utilizzato il pattern
     * singleton per la gestione dei dati condivisi.
     */
    private static DatiCondivisi instance = null;
    /**
     * Lista delle stazioni di monitoraggio.
     * <p>
     * Questo attributo contiene un elenco delle stazioni di monitoraggio
     * disponibili nel sistema.
     */
    private ArrayList<MonitoringStation> monitoringStations;
    /**
     * Lista degli utenti registrati nel sistema.
     * <p>
     * Questo attributo contiene un elenco degli utenti registrati e autorizzati
     * ad accedere al sistema.
     */
    private ArrayList<User> users;
    /**
     * Lista delle aree di interesse.
     * <p>
     * Questo attributo contiene un elenco delle aree di interesse configurate
     * nel sistema.
     */
    private ArrayList<InterestingAreas> areas;
    /**
     * Lista delle previsioni meteorologiche.
     * <p>
     * Questo attributo contiene un elenco delle previsioni meteorologiche
     * disponibili nel sistema.
     */
    private ArrayList<Forecast> forecasts;
    /**
     * L'operatore attualmente autenticato nel sistema.
     * <p>
     * Questo attributo rappresenta l'utente operatore attualmente autenticato e
     * attivo nel sistema.
     */
    private User operatore;
    /**
     * Connessione al database.
     * <p>
     * Questo attributo rappresenta la connessione attiva al database utilizzata
     * per eseguire le operazioni di accesso e di aggiornamento dei dati.
     */
    private Connection conn;
    /**
     * Gestore del database (<code>DBManager</code>).
     * <p>
     * Questo attributo rappresenta l'istanza del <code>DBManager</code>
     * utilizzata per interagire con il database. Gestisce le operazioni di
     * accesso al database, come connessioni, query e aggiornamenti.
     */
    private DBManager dBManager;
    /**
     * Driver JDBC per il database PostgreSQL.
     * <p>
     * Questo attributo contiene il nome completo della classe del driver JDBC
     * utilizzato per stabilire la connessione al database PostgreSQL.
     */
    private final String JDBC_DRIVER = "org.postgresql.Driver";


    /**
     * Costruttore privato per la classe <code>DatiCondivisi</code>.
     * <p>
     * Questo costruttore inizializza l'istanza della classe
     * <code>DatiCondivisi</code> gestendo le eccezioni e stabilisce la
     * connessione al database. Legge quindi gli utenti, le aree, le previsioni
     * meteorologiche e le stazioni di monitoraggio dal database e ordina le
     * aree. Infine, imposta l'operatore su <code>null</code>.
     *
     * @throws ClassNotFoundException se la classe del driver JDBC non viene
     *                                trovata
     * @throws SQLException           se si verifica un errore SQL durante l'accesso al
     *                                database
     * @throws RemoteException        se si verifica un errore remoto durante l'accesso
     *                                al database
     */
    private DatiCondivisi() throws ClassNotFoundException, SQLException, RemoteException {
        super();
        dBManager = new DBManager();
        //Class.forName(JDBC_DRIVER);
        System.out.println("Connecting to database..."); // Stampa di debug
        conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/dbcm",
                "postgres",
                "postgres");
        System.out.println("Connected to database"); // Stampa di debug
        conn.setAutoCommit(false); // Assicurati che le transazioni siano gestite correttamente

        try {
            users = dBManager.readUser(conn);
            areas = dBManager.readAreas(conn);
            forecasts = dBManager.readForecast(conn);
            monitoringStations = dBManager.readStation(conn);
            conn.commit(); // Commit delle transazioni
        } catch (SQLException e) {
            conn.rollback(); // Rollback in caso di errore
            throw new RuntimeException(e); // Stampa di debug per gli errori
        } finally {
            conn.setAutoCommit(true); // Ripristina il comportamento di autocommit
        }



        operatore = null;
        sortAreas();
    }

    /**
     * Restituisce l'oggetto di connessione al database.
     * <p>
     * Questo metodo restituisce l'oggetto di connessione attualmente utilizzato
     * per interagire con il database.
     *
     * @return l'oggetto di connessione al database
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Metodo che crea l'unica instanza della classe
     *
     * @return l'istanza della classe
     * @throws ClassNotFoundException Errore nel caricamento dei driver jdbc
     * @throws SQLException           Errore nella connessione al database o
     *                                nell'esecuzione della query
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
     *                      nell'esecuzione della query
     */
    public void refresh() throws SQLException, ClassNotFoundException {
        // Ricrea l'oggetto:       
        users = dBManager.readUser(conn);
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

    /**
     * Calcola la distanza in chilometri tra due punti geografici utilizzando la
     * formula di Haversine.
     * <p>
     * Questo metodo prende in input le coordinate (latitudine e longitudine) di
     * due punti geografici e restituisce la distanza approssimativa in
     * chilometri tra di essi utilizzando la formula di Haversine.
     *
     * @param lat1 la latitudine del primo punto in gradi
     * @param lon1 la longitudine del primo punto in gradi
     * @param lat2 la latitudine del secondo punto in gradi
     * @param lon2 la longitudine del secondo punto in gradi
     * @return la distanza approssimativa in chilometri tra i due punti
     * geografici
     */
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
    public String[] cercaAreaGeografica(String s, int a) {
        ArrayList<String> intAreas = new ArrayList<>();
        if (a == 0) {
            //controllo con nome città
            ArrayList<InterestingAreas> l = (ArrayList<InterestingAreas>) areas.parallelStream().filter(elemento -> elemento.contains(s)).collect(Collectors.toList());
            if (l.size() == 1) {
                intAreas.add(l.get(0).getCountryCode() + "-" + l.get(0).getName());
                double lat1 = Double.parseDouble(l.get(0).getLat());
                double lon1 = Double.parseDouble(l.get(0).getLon());
                intAreas = cercaLimitrofo(lat1, lon1);
            } else {
                for (int i = 0; i < l.size(); i++) {
                    intAreas.add(l.get(i).getCountryCode() + "-" + l.get(i).getName());
                }
            }
        } else if (a == 1) {
            if (Character.isDigit(s.charAt(0)) || s.charAt(0) == '-') {
                //controllo con lat lon           
                double lat1 = Double.parseDouble(s.split(" ")[0]);
                double lon1 = Double.parseDouble(s.split(" ")[1]);
                intAreas = cercaLimitrofo(lat1, lon1);
            }
        } else {
            for (int i = 0; i < areas.size() - 1; i++) {
                String countryCode = areas.get(i).getCountryCode();
                String countryName = areas.get(i).getCountryName();
                String name = areas.get(i).getName();
                if ((countryCode != null && countryCode.equalsIgnoreCase(s))
                        || (countryName != null && countryName.equalsIgnoreCase(s))) {
                    if (name != null) {
                        intAreas.add(countryCode + "-" + name);
                    }
                }
            }
        }
        return intAreas.toArray(new String[0]);
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
                    intAreas.add(areas.get(i).getCountryCode() + "-" + areas.get(i).getName());
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
     * @throws java.sql.SQLException
     */
    public boolean existForecast(String area) throws SQLException {
        for (int i = 0; i < forecasts.size(); i++) {
            String s = area.split("-")[1];
            if (forecasts.get(i).getIdCittà().equalsIgnoreCase(convertNameToId(s))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converte il nome di una località nel suo identificatore univoco nel
     * database.
     * <p>
     * Questo metodo prende in input il nome di una località e restituisce il
     * suo identificatore univoco nel database delle coordinate di monitoraggio.
     * Se il nome della località è identificato con successo, restituisce il
     * relativo identificatore; altrimenti, restituisce null.
     *
     * @param name il nome della località da convertire in identificatore
     * @return l'identificatore univoco della località nel database
     * @throws SQLException se si verifica un errore SQL durante l'esecuzione
     *                      della query
     */
    public String convertNameToId(String name) throws SQLException {
        if (name.charAt(0) == ' ') {
            name = name.substring(1);
        }
        PreparedStatement stmt = conn.prepareStatement("select id from coordinatemonitoraggio where name=? OR name_ascii=?");
        stmt.setString(1, name);
        stmt.setString(2, name);
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
     * @throws java.sql.SQLException
     */
    public ArrayList<Forecast> getForecasts(String name) throws SQLException {
        ArrayList<Forecast> temp = new ArrayList<Forecast>();
        String s = name.split("-")[1];
        name = convertNameToId(s);
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

    /**
     * Scrive una nuova previsione nel database.
     * <p>
     * Questo metodo delega l'operazione di scrittura di una nuova previsione
     * nel database al DBManager, utilizzando l'istanza attuale della
     * connessione al database.
     *
     * @param f la previsione da scrivere nel database
     * @throws SQLException           se si verifica un errore SQL durante l'esecuzione
     *                                della query
     * @throws ClassNotFoundException se la classe specificata non è trovata
     */
    public void writeForecast(Forecast f) throws SQLException, ClassNotFoundException {
        dBManager.writeForecast(f, conn);
    }

    /**
     * Scrive un nuovo utente nel database.
     * <p>
     * Questo metodo delega l'operazione di scrittura di un nuovo utente nel
     * database al DBManager, utilizzando l'istanza attuale della connessione al
     * database.
     *
     * @param u l'utente da scrivere nel database
     * @throws SQLException    se si verifica un errore SQL durante l'esecuzione
     *                         della query
     * @throws RemoteException se si verifica un errore remoto durante
     *                         l'esecuzione della query
     */
    public void writeUser(User u) throws SQLException, RemoteException {
        dBManager.writeUser(u, conn);
    }

    /**
     * Scrive una nuova stazione di monitoraggio nel database.
     * <p>
     * Questo metodo delega l'operazione di scrittura di una nuova stazione di
     * monitoraggio nel database al DBManager, utilizzando l'istanza attuale
     * della connessione al database e l'elenco delle aree di interesse
     * specificate.
     *
     * @param ms    la stazione di monitoraggio da scrivere nel database
     * @param areas l'elenco delle aree di interesse associate alla stazione di
     *              monitoraggio
     * @throws SQLException           se si verifica un errore SQL durante l'esecuzione
     *                                della query
     * @throws RemoteException        se si verifica un errore remoto durante
     *                                l'esecuzione della query
     * @throws ClassNotFoundException se la classe specificata non è trovata
     */
    public void writeStation(MonitoringStation ms, List<String> areas) throws SQLException, RemoteException, ClassNotFoundException {
        dBManager.writeStation(ms, conn, areas);
    }
}
