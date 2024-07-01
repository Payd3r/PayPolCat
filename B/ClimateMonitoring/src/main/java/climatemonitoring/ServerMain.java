/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import java.net.URL;

import org.apache.maven.cli.MavenCli;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.InvocationResult;

import java.util.Collections;

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
     *                         creazione del server
     */
    public ServerMain() throws RemoteException {

    }

    /**
     * Metodo principale per avviare il programma. Controlla la configurazione
     * dell'ambiente e avvia il server.
     *
     * @param args argomenti della riga di comando (non utilizzati in questo
     *             contesto).
     * @throws RemoteException se si verifica un errore durante la creazione del
     *                         registro RMI.
     */
    public static void main(String[] args) throws RemoteException {
        LocateRegistry.createRegistry(PORT).rebind("Stub", new ServerMain());
        System.out.println("Server ready");
        try {
            // Check if JAVA_HOME is set correctly
            String javaHome = System.getenv("JAVA_HOME");
            if (javaHome == null || javaHome.isEmpty()) {
                throw new IllegalStateException("JAVA_HOME is not set. Please set JAVA_HOME to a JDK directory.");
            }

            // Check if Maven is installed
            if (!isMavenInstalled()) {
                System.out.println("Maven is not installed. Installing Maven...");
                installMaven();

            } else {
                System.out.println("Maven is already installed.");
            }

            // Set the environment variable for Maven home
            String mavenHome = System.getProperty("user.home") + "/maven/apache-maven-3.9.8";
            System.setProperty("maven.home", mavenHome);

            // Add Maven to the PATH
            String path = System.getenv("PATH");
            String newPath = mavenHome + "/bin" + File.pathSeparator + path;
            System.setProperty("java.library.path", newPath);

            // Execute Maven build
            String pomFilePath = "pom.xml"; // Update this path
            if (pomFilePath == null || pomFilePath.isEmpty()) {
                throw new IllegalArgumentException("The path to pom.xml is null or empty.");
            }
            executeMavenBuild(pomFilePath);
            System.out.println("Installing library...");
            installLib();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
     * @throws SQLException    se si verifica un errore SQL durante l'interazione
     *                         con il database
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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
     * @throws SQLException    se si verifica un errore SQL durante l'interazione
     *                         con il database
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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
     * @throws SQLException    se si verifica un errore SQL durante l'interazione
     *                         con il database
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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
     * @throws SQLException    se si verifica un errore SQL durante l'interazione
     *                         con il database
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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
     *                         comunicazione con il server
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
     *                         comunicazione con il server
     */
    @Override
    public synchronized String[] cercaAreaGeografica(String a, int b) throws RemoteException {
        try {
            return DatiCondivisi.getInstance().cercaAreaGeografica(a, b);
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
     *          della previsione
     * @return {@code true} se esiste una previsione per l'area specificata,
     * altrimenti {@code false}
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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
     *                         comunicazione con il server
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
     *                         comunicazione con il server
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
     *                         comunicazione con il server
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
     * @throws SQLException    se si verifica un errore durante l'operazione di
     *                         scrittura nel database
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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
     * @throws SQLException    se si verifica un errore durante l'operazione di
     *                         scrittura nel database
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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
     * @param ms    l'oggetto MonitoringStation che rappresenta la stazione di
     *              monitoraggio da scrivere
     * @param areas la lista di stringhe che rappresenta le aree di interesse
     *              della stazione
     * @throws SQLException    se si verifica un errore durante l'operazione di
     *                         scrittura nel database
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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
     * @throws SQLException    se si verifica un errore durante l'operazione di
     *                         conversione nel database
     * @throws RemoteException se si verifica un errore remoto durante la
     *                         comunicazione con il server
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

    /**
     * Controlla se Maven è installato eseguendo il comando 'mvn -v'.
     *
     * @return true se Maven è installato e può essere eseguito con successo,
     * false altrimenti.
     * @throws IOException          se si verifica un errore di I/O.
     * @throws InterruptedException se il thread corrente viene interrotto
     *                              durante l'attesa della terminazione del processo.
     */
    private static boolean isMavenInstalled() throws IOException, InterruptedException {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("mvn", "-v");
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Installa Maven scaricando la versione specificata dall'URL fornito.
     *
     * @throws IOException se si verifica un errore di I/O durante il download o
     *                     l'estrazione.
     */
    private static void installMaven() throws IOException {
        // Define Maven version and download URL
        String mavenVersion = "3.9.8";
        String downloadUrl = "https://dlcdn.apache.org/maven/maven-3/3.9.8/binaries/apache-maven-3.9.8-bin.zip";
        String installDir = System.getProperty("user.home") + "/maven";

        // Download Maven
        File installDirFile = new File(installDir);
        if (!installDirFile.exists()) {
            installDirFile.mkdirs();
        }

        // Download Maven
        File zipFile = new File(installDir + "/apache-maven-" + mavenVersion + "-bin.zip");
        try (BufferedInputStream in = new BufferedInputStream(new URL(downloadUrl).openStream()); FileOutputStream fileOutputStream = new FileOutputStream(zipFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        }

        // Extract Maven
        unzip(zipFile, new File(installDir));

        System.out.println("Maven installed successfully.");
    }

    /**
     * Estrae i file da un archivio ZIP nella directory di destinazione
     * specificata.
     *
     * @param zipFile il file archivio ZIP da cui estrarre.
     * @param destDir la directory di destinazione in cui salvare i file
     *                estratti.
     * @throws IOException se si verifica un errore di I/O durante l'estrazione.
     */
    private static void unzip(File zipFile, File destDir) throws IOException {
        try (java.util.zip.ZipInputStream zipIn = new java.util.zip.ZipInputStream(new java.io.FileInputStream(zipFile))) {
            java.util.zip.ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                File filePath = new File(destDir, entry.getName());
                if (!entry.isDirectory()) {
                    filePath.getParentFile().mkdirs();
                    try (java.io.BufferedOutputStream bos = new java.io.BufferedOutputStream(new java.io.FileOutputStream(filePath))) {
                        byte[] bytesIn = new byte[4096];
                        int read;
                        while ((read = zipIn.read(bytesIn)) != -1) {
                            bos.write(bytesIn, 0, read);
                        }
                    }
                } else {
                    filePath.mkdirs();
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * Esegue la build di Maven utilizzando il file POM specificato.
     *
     * @param pomFilePath il percorso del file POM da utilizzare per la build.
     */
    private static void executeMavenBuild(String pomFilePath) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(pomFilePath));
        request.setGoals(Collections.singletonList("clean install"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(System.getProperty("user.home") + "/maven/apache-maven-3.9.8"));

        try {
            InvocationResult result = invoker.execute(request);

            if (result.getExitCode() != 0) {
                System.err.println("Build failed.");
            } else {
                System.out.println("Build successful.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void installLib() {
        String mavenHome = System.getProperty("user.home") + "/maven/apache-maven-3.9.8";
        String mvnCommand = mavenHome + "/bin/mvn"; // Percorso completo al comando mvn

        String[] command = {
                "cmd.exe", "/c", mvnCommand, "install:install-file",
                "-Dfile=lib\\codice-fiscale-java-master.jar",
                "-DgroupId=climatemonitoring",
                "-DartifactId=codice-fiscale-java-master",
                "-Dversion=1.0.0",
                "-Dpackaging=jar"
        };

        try {
            // Crea il processo
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Imposta la variabile di ambiente MAVEN_HOME nel processo Maven
            Map<String, String> env = processBuilder.environment();
            env.put("MAVEN_HOME", mavenHome);

            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Leggi l'output del processo
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Attendi il completamento del processo
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Installazione completata con successo.");
            } else {
                System.err.println("Errore durante l'installazione. Codice di uscita: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



}
