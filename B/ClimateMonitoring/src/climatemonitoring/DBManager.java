/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe che fornisce metodi per la lettura e la scrittura su file di dati.
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 */
public class DBManager {

    /**
     * Legge il database nella tabella operatoriregistrati e restituisce una lista di oggetti User.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti User letti dal database
     * @throws SQLException se si verifica un errore di connessione al database durante la query richiesta
     */
    public static List<User> readUser(Connection conn) throws SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from operatoriregistrati");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            list.add(new User(
                    rs.getString("nome"), 
                    rs.getString("cognome"),
                    rs.getString("cf"),
                    rs.getString("mail"),
                    rs.getString("nick"),
                    rs.getString("password"),
                    rs.getInt("id_stazione")
            ));
        }
        return list;
    }

    /**
     * Legge il database dalla tabella coordinatemonitoraggio e restituisce una lista di oggetti InterestingAreas.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti InterestingAreas letti dal database
     * @throws SQLException se si verifica un errore di connessione al database durante la query richiesta
     */
    public static List<InterestingAreas> readAreas(Connection conn) throws SQLException {
        List<InterestingAreas> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from coordinatemonitoraggio");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            list.add(new InterestingAreas(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("sigla_stato"),
                    rs.getString("stato"),
                    rs.getString("latitudine"),
                    rs.getString("longitudine")
            ));
        }
        return list;
    }

    /**
     * Legge il file delle stazioni di monitoraggio e restituisce una lista di oggetti MonitoringStation.
     *
     * @param path il percorso del file da leggere
     * @return una lista di oggetti MonitoringStation letti dal file
     * @throws IOException se si verifica un errore di input/output durante la lettura del file
     */
    public static List<MonitoringStation> readStation(Path path) throws IOException {
        List<String> allLines = (ArrayList) Files.readAllLines(path, StandardCharsets.UTF_8);
        List<MonitoringStation> list = new ArrayList<MonitoringStation>();
        for (String line : allLines) {
            if (!line.equals("")) {
                String[] split = line.split(";");
                String[] areas = split[2].split(",");
                list.add(new MonitoringStation(split[0], split[1], areas));
            }
        }
        return list;
    }

    /**
     * Legge il file delle previsioni meteo e restituisce una lista di oggetti Forecast.
     *
     * @param path il percorso del file da leggere
     * @return una lista di oggetti Forecast letti dal file
     * @throws IOException se si verifica un errore di input/output durante la lettura del file
     * @throws ParseException se si verifica un errore durante il parsing delle date
     */
    public static List<Forecast> readForecast(Path path) throws IOException, ParseException {
        List<String> allLines = (ArrayList) Files.readAllLines(path, StandardCharsets.UTF_8);
        List<Forecast> list = new ArrayList<Forecast>();
        for (String line : allLines) {
            if (!line.equals("")) {
                String[] split = line.split(";");
                list.add(new Forecast(split[0], split[1], new SimpleDateFormat("dd/MM/yyyy").parse(split[2]), new SimpleDateFormat("hh:mm:ss").parse(split[3]), split[4].split(","), split[5].split(","), split[6].split(","), split[7].split(","), split[8].split(","), split[9].split(","), split[10].split(",")));
            }
        }
        return list;
    }

    /**
     * Scrive il contenuto specificato nel file specificato.
     *
     * @param content il contenuto da scrivere nel file
     * @param path il percorso del file in cui scrivere
     * @throws IOException se si verifica un errore di input/output durante la scrittura del file
     */
    public static void write(String content, Path path) throws IOException {
        Charset charset = Charset.forName("UTF-8");
        try (BufferedWriter writer = Files.newBufferedWriter(path, charset, StandardOpenOption.APPEND)) {
            writer.write(content + "\n", 0, content.length());
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
