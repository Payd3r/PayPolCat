/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Classe principale del server.
 * <p>
 * Questa classe implementa l'interfaccia remota {@link ServerInterface} e
 * fornisce l'implementazione dei metodi per interagire con il server e gestire
 * i dati.
 *
 * @author Ficara Paolo 755155 CO
 * @author Mauri Andrea 755140 CO
 * @author Luca Cattaneo 755083 CO
 */
public class ServerMain extends UnicastRemoteObject implements ServerInterface {

    static final int PORT = 1234;

    /**
     * Costruttore della classe ServerMain.
     *
     * @throws RemoteException se si verifica un errore remoto durante la
     * creazione del server
     */
    public ServerMain() throws RemoteException {

    }

    /**
     * Metodo principale per avviare il server.
     *
     * @param args gli argomenti della riga di comando
     * @throws RemoteException se si verifica un errore remoto durante la
     * creazione del server
     */
    public static void main(String[] args) throws RemoteException {
        LocateRegistry.createRegistry(PORT).rebind("Stub", new ServerMain());
        System.out.println("Server ready");
    }

    /**
     * Legge gli utenti dal database.
     * <p>
     * Questo metodo sincronizzato restituisce una lista degli utenti presenti
     * nel database. Se si verifica un'eccezione SQL durante l'interazione con
     * il database o se si verifica un errore durante il caricamento della
     * classe, viene registrato un errore di livello SEVERE e viene restituito
     * un valore nullo.
     *
     * @return la lista degli utenti letti dal database, o {@code null} se si
     * verifica un errore
     * @throws SQLException se si verifica un errore SQL durante l'interazione
     * con il database
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized ArrayList<User> readUser() throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().getUsers();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Legge le aree di interesse dal database.
     * <p>
     * Questo metodo sincronizzato restituisce una lista delle aree di interesse
     * presenti nel database. Se si verifica un'eccezione SQL durante
     * l'interazione con il database o se si verifica un errore durante il
     * caricamento della classe, viene registrato un errore di livello SEVERE e
     * viene restituito un valore nullo.
     *
     * @return la lista delle aree di interesse lette dal database, o
     * {@code null} se si verifica un errore
     * @throws SQLException se si verifica un errore SQL durante l'interazione
     * con il database
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized ArrayList<InterestingAreas> readAreas() throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().getAreas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Legge le stazioni di monitoraggio dal database.
     * <p>
     * Questo metodo sincronizzato restituisce una lista delle stazioni di
     * monitoraggio presenti nel database. Se si verifica un'eccezione SQL
     * durante l'interazione con il database o se si verifica un errore durante
     * il caricamento della classe, viene registrato un errore di livello SEVERE
     * e viene restituito un valore nullo.
     *
     * @return la lista delle stazioni di monitoraggio lette dal database, o
     * {@code null} se si verifica un errore
     * @throws SQLException se si verifica un errore SQL durante l'interazione
     * con il database
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized ArrayList<MonitoringStation> readStation() throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().getMonitoringStations();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Legge le previsioni dal database.
     * <p>
     * Questo metodo sincronizzato restituisce una lista delle previsioni
     * presenti nel database. Se si verifica un'eccezione SQL durante
     * l'interazione con il database o se si verifica un errore durante il
     * caricamento della classe, viene registrato un errore di livello SEVERE e
     * viene restituito un valore nullo.
     *
     * @return la lista delle previsioni lette dal database, o {@code null} se
     * si verifica un errore
     * @throws SQLException se si verifica un errore SQL durante l'interazione
     * con il database
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized ArrayList<Forecast> readForecast() throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().getForecasts();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Ordina le aree di interesse.
     * <p>
     * Questo metodo sincronizzato ordina le aree di interesse presenti nel
     * sistema. Se si verifica un'eccezione durante l'ordinamento delle aree,
     * viene registrato un errore di livello SEVERE.
     *
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized void sortAreas() throws RemoteException {
        try {
            DatiCondivisi.getInstance().sortAreas();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cerca un'area geografica.
     * <p>
     * Questo metodo sincronizzato cerca un'area geografica nel sistema in base
     * al nome specificato. Restituisce un array di stringhe che contiene
     * informazioni sull'area geografica trovata, o {@code null} se l'area non
     * viene trovata. Se si verifica un'eccezione durante la ricerca dell'area,
     * viene registrato un errore di livello SEVERE.
     *
     * @param a il nome dell'area geografica da cercare
     * @return un array di stringhe che contiene informazioni sull'area
     * geografica trovata, o {@code null} se l'area non viene trovata
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized String[] cercaAreaGeografica(String a, int b) throws RemoteException {
        try {
            return DatiCondivisi.getInstance().cercaAreaGeografica(a,b);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Verifica l'esistenza di una previsione.
     * <p>
     * Questo metodo sincronizzato verifica se esiste una previsione per l'area
     * geografica specificata. Restituisce {@code true} se esiste una previsione
     * per l'area specificata, altrimenti restituisce {@code false}. Se si
     * verifica un'eccezione durante la verifica dell'esistenza della
     * previsione, viene registrato un errore di livello SEVERE.
     *
     * @param a il nome dell'area geografica per cui verificare l'esistenza
     * della previsione
     * @return {@code true} se esiste una previsione per l'area specificata,
     * altrimenti {@code false}
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized boolean existForecast(String a) throws RemoteException {
        try {
            return DatiCondivisi.getInstance().existForecast(a);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Imposta l'operatore corrente.
     * <p>
     * Questo metodo sincronizzato imposta l'operatore corrente nel sistema.
     * Accetta un oggetto User che rappresenta l'operatore da impostare. Se si
     * verifica un'eccezione durante l'impostazione dell'operatore, viene
     * registrato un errore di livello SEVERE.
     *
     * @param a l'oggetto User che rappresenta l'operatore da impostare
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized void setOperatore(User a) throws RemoteException {
        try {
            DatiCondivisi.getInstance().setOperatore(a);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ottiene l'operatore corrente.
     * <p>
     * Questo metodo sincronizzato restituisce l'operatore corrente nel sistema.
     * Se si verifica un'eccezione durante il recupero dell'operatore, viene
     * registrato un errore di livello SEVERE e viene restituito {@code null}.
     *
     * @return l'oggetto User che rappresenta l'operatore corrente, o
     * {@code null} se si verifica un errore
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized User getOperatore() throws RemoteException {
        try {
            return DatiCondivisi.getInstance().getOperatore();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Aggiorna i dati nel sistema.
     * <p>
     * Questo metodo sincronizzato aggiorna i dati nel sistema. Se si verifica
     * un'eccezione durante l'aggiornamento dei dati, viene registrato un errore
     * di livello SEVERE.
     *
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized void refresh() throws RemoteException {
        try {
            DatiCondivisi.getInstance().refresh();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Scrive una previsione nel sistema.
     * <p>
     * Questo metodo sincronizzato scrive una previsione nel sistema. Accetta un
     * oggetto Forecast che rappresenta la previsione da scrivere. Se si
     * verifica un'eccezione di tipo SQLException durante l'operazione di
     * scrittura, viene registrato un errore di livello SEVERE. Se si verifica
     * un'eccezione di tipo ClassNotFoundException, viene registrato un errore
     * di livello SEVERE.
     *
     * @param f l'oggetto Forecast che rappresenta la previsione da scrivere
     * @throws SQLException se si verifica un errore durante l'operazione di
     * scrittura nel database
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized void writeForecast(Forecast f) throws SQLException, RemoteException {
        try {
            DatiCondivisi.getInstance().writeForecast(f);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Scrive un utente nel sistema.
     * <p>
     * Questo metodo sincronizzato scrive un utente nel sistema. Accetta un
     * oggetto User che rappresenta l'utente da scrivere. Se si verifica
     * un'eccezione di tipo SQLException durante l'operazione di scrittura,
     * viene registrato un errore di livello SEVERE. Se si verifica un'eccezione
     * di tipo ClassNotFoundException, viene registrato un errore di livello
     * SEVERE.
     *
     * @param u l'oggetto User che rappresenta l'utente da scrivere
     * @throws SQLException se si verifica un errore durante l'operazione di
     * scrittura nel database
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized void writeUser(User u) throws SQLException, RemoteException {
        try {
            DatiCondivisi.getInstance().writeUser(u);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Scrive una stazione di monitoraggio nel sistema.
     * <p>
     * Questo metodo sincronizzato scrive una stazione di monitoraggio nel
     * sistema. Accetta un oggetto MonitoringStation che rappresenta la stazione
     * da scrivere e una lista di stringhe che rappresenta le aree di interesse
     * della stazione. Se si verifica un'eccezione di tipo SQLException durante
     * l'operazione di scrittura, viene registrato un errore di livello SEVERE.
     * Se si verifica un'eccezione di tipo ClassNotFoundException, viene
     * registrato un errore di livello SEVERE.
     *
     * @param ms l'oggetto MonitoringStation che rappresenta la stazione di
     * monitoraggio da scrivere
     * @param areas la lista di stringhe che rappresenta le aree di interesse
     * della stazione
     * @throws SQLException se si verifica un errore durante l'operazione di
     * scrittura nel database
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized void writeStation(MonitoringStation ms, List<String> areas) throws SQLException, RemoteException {
        try {
            DatiCondivisi.getInstance().writeStation(ms, areas);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Converte il nome di un'area geografica in un identificatore.
     * <p>
     * Questo metodo sincronizzato converte il nome di un'area geografica nel
     * suo identificatore corrispondente. Accetta una stringa che rappresenta il
     * nome dell'area geografica da convertire. Restituisce una stringa che
     * rappresenta l'identificatore corrispondente all'area geografica. Se si
     * verifica un'eccezione di tipo SQLException durante l'operazione di
     * conversione, viene registrato un errore di livello SEVERE. Se si verifica
     * un'eccezione di tipo ClassNotFoundException, viene registrato un errore
     * di livello SEVERE.
     *
     * @param name il nome dell'area geografica da convertire in identificatore
     * @return una stringa che rappresenta l'identificatore corrispondente
     * all'area geografica
     * @throws SQLException se si verifica un errore durante l'operazione di
     * conversione nel database
     * @throws RemoteException se si verifica un errore remoto durante la
     * comunicazione con il server
     */
    @Override
    public synchronized String convertNameToId(String name) throws SQLException, RemoteException {
        try {
            return DatiCondivisi.getInstance().convertNameToId(name);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    /**
     * Normalizza una stringa rimuovendo eventuali caratteri diacritici.
     * <p>
     * Questo metodo sincronizzato normalizza una stringa rimuovendo eventuali
     * caratteri diacritici (accenti, tilde, ecc.). Accetta una stringa in input
     * e restituisce la stringa normalizzata. Se l'ultimo carattere della
     * stringa in input è una lettera accentata, viene rimosso l'accento.
     *
     * @param s la stringa da normalizzare
     * @return la stringa normalizzata senza caratteri diacritici
     */
    @Override
    public synchronized String normalizeStrings(String s) {
        if ("ÀÁÂÃÄÅÈÉÊËÌÍÎÏÒÓÔÕÖÙÚÛÜÝ".indexOf(s.charAt(s.length() - 1)) != -1) {
            String normalized = Normalizer.normalize(s, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            s = pattern.matcher(normalized).replaceAll("") + "'";
        }
        return s;
    }

}
