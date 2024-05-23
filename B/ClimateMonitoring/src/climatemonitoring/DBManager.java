/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Classe che fornisce metodi per la lettura del database
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 */
public class DBManager {

    /**
     * Legge il database nella tabella "operatoriregistrati" e restituisce una lista di oggetti User.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti User letti dal database
     * @throws SQLException se si verifica un errore di connessione al database durante la query richiesta
     */
    public static List<User> readUser(Connection conn) throws SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from operatoriregistrati as opr"
                + " join lavora as l on opr.id = l.id_operatore"
                + " join centromonitoraggio as cm on l.id_centro = cm.id");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new User(
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("cf"),
                    rs.getString("mail"),
                    rs.getString("nick"),
                    rs.getString("password"),
                    rs.getString("nome_centro")
            ));
        }
        rs.close();
        return list;
    }

    /**
     * Legge il database dalla tabella "coordinatemonitoraggio" e restituisce una lista di oggetti InterestingAreas.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti InterestingAreas letti dal database
     * @throws SQLException se si verifica un errore di connessione al database durante la query richiesta
     */
    public static List<InterestingAreas> readAreas(Connection conn, int offset, int pageSize) throws SQLException {
        List<InterestingAreas> list = new ArrayList<>();
        String sql = "SELECT * FROM get_interesting_areas_with_pagination(?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new InterestingAreas(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("sigla_stato"),
                        rs.getString("stato"),
                        rs.getString("latitudine"),
                        rs.getString("longitudine")
                ));
            }
            rs.close();
        }
        return list;
    }
//    public static List<InterestingAreas> readAreas(Connection conn) throws SQLException, InterruptedException, ExecutionException {
//        int totalRecords = 140868; // Numero totale di record
//        int batchSize = 10000; // Dimensione del batch
//        int totalBatches = (totalRecords + batchSize - 1) / batchSize;
//
//        ExecutorService executor = Executors.newFixedThreadPool(10); // Pool di thread per parallelizzare
//        List<Future<List<InterestingAreas>>> futures = new ArrayList<>(); //lista che raccoglie mediante oggetto future i risultati delle query in parallelo
//
//        for (int i = 0; i < totalBatches; i++) {
//            final int batchStart = i * batchSize;
//            final int batchEnd = Math.min(batchStart + batchSize, totalRecords);
//
//            futures.add(executor.submit(() -> fetchBatch(conn, batchStart, batchEnd))); // query in parallelo
//        }
//
//        List<InterestingAreas> allAreas = new ArrayList<>();
//        for (Future<List<InterestingAreas>> future : futures) {
//            allAreas.addAll(future.get());
//        }
//
//        executor.shutdown();
//        return allAreas;
//    }
//
//    //metodo che esegue la query attuale
//    private static List<InterestingAreas> fetchBatch(Connection conn, int start, int end) throws SQLException {
//        List<InterestingAreas> list = new ArrayList<>();
//        String query = "SELECT id, nome, sigla_stato, stato, latitudine, longitudine FROM coordinatemonitoraggio WHERE id BETWEEN ? AND ?";
//        try (PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setInt(1, start);
//            stmt.setInt(2, end);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    list.add(new InterestingAreas(
//                            rs.getInt("id"),
//                            rs.getString("nome"),
//                            rs.getString("sigla_stato"),
//                            rs.getString("stato"),
//                            rs.getString("latitudine"),
//                            rs.getString("longitudine")
//                    ));
//                }
//            }
//        }
//        return list;
//    }

    /**
     * Legge il database nella tabella "centromonitoraggio" e restituisce una lista di oggetti MonitoringStation.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti MonitoringStation letti dal database
     * @throws SQLException se si verifica un errore di connessione al database durante la query richiesta
     */
    public static List<MonitoringStation> readStation(Connection conn) throws SQLException {
        List<MonitoringStation> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from centromonitoraggio");
        ResultSet rs = stmt.executeQuery();
        List<InterestingAreas> areeInteresse = new ArrayList<>();
        while (rs.next()) {
            list.add(new MonitoringStation(rs.getInt("id"), rs.getString("nome_centro"), rs.getString("indirizzo"), new ArrayList<InterestingAreas>()));
        }
        //select * from coordinatemonitoraggio c inner join stazioni s on c.id = s.id_areainteresse inner join centromonitoraggio cm on s.id_centro = cm.id where cm.id =
        for (int i = 0; i < list.size(); i++) {
            stmt = conn.prepareStatement("select * from coordinatemonitoraggio c inner join stazioni s on c.id = s.id_areainteresse inner join centromonitoraggio cm on s.id_centro = cm.id where cm.id =" + list.get(i).getId());
            rs = stmt.executeQuery();
            areeInteresse = new ArrayList<>();
            while (rs.next()) {
                areeInteresse.add(new InterestingAreas(rs.getInt("id"), rs.getString("nome_ascii"), rs.getString("sigla_stato"), rs.getString("stato"), rs.getString("latitudine"), rs.getString("longitudine")));
            }
            list.get(i).setInterestingAreas(areeInteresse);
        }
        rs.close();
        return list;
    }

    /**
     * Legge il databse nella tabella "parametriclimatici" e restituisce una lista di oggetti Forecast.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti Forecast letti dal database
     * @throws SQLException se si verifica un errore di connessione al database durante la query richiesta
     */
    public static List<Forecast> readForecast(Connection conn) throws SQLException {
        List<Forecast> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM parametriclimatici as pc "
                + "JOIN operatoriregistrati AS opr ON pc.id_operatore = opr.id");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new Forecast(
                    rs.getString(""),
                    rs.getString("nome"),
                    rs.getDate("data"),
                    rs.getTimestamp("ora"),
                    rs.getString("nota_vento").split(";"),
                    rs.getString("nota_umidita").split(";"),
                    rs.getString("nota_pressione").split(";"),
                    rs.getString("nota_temperatura").split(";"),
                    rs.getString("nota_precipitazioni").split(";"),
                    rs.getString("nota_altitudine").split(";"),
                    rs.getString("nota_massa").split(";")
            ));
        }
        rs.close();
        return list;
    }

    /**
     * Esegue la query di inserimento specificata nel database
     *
     * @param conn la connessione al database
     * @param query la insert da eseguire sul database
     * @throws SQLException se si verifica un errore di connessione al database durante la query richiesta
     */
    public static void write(String query, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.close();
    }
}