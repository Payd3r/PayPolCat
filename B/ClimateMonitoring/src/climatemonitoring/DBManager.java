/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        while(rs.next()) {
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
        rs.close();
        return list;
    }

    /**
     * Legge il database nella tabella "centromonitoraggio" e restituisce una lista di oggetti MonitoringStation.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti MonitoringStation letti dal database
     * @throws SQLException se si verifica un errore di connessione al database durante la query richiesta
     */
    public static List<MonitoringStation> readStation(Connection conn) throws SQLException  {
        List<MonitoringStation> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * "
                + "from centromonitoraggio as cm "
                + "join lavora as l on cm.id = l.id_centro "
                + "join operatoriregistrati as or on l.id_operatore = or.id "
                + "join parametriclimatici as pc on or.id = pc.id_operatore"
                + "join ");
        ResultSet rs = stmt.executeQuery();
//        while(rs.next()) {
//            list.add(new MonitoringStation(
//                    rs.getString("nome_centro"),
//                    rs.getString("indirizzo"),
//                    
//            ));
//        }
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
    public static List<Forecast> readForecast(Connection conn) throws SQLException  {
        List<Forecast> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM parametriclimatici as pc "
                + "JOIN operatoriregistrati AS opr ON pc.id_operatore = opr.id");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            list.add(new Forecast(
                    rs.getString(""),
                    rs.getString("nome"),
                    rs.getDate("data"), 
                    (jdk.jfr.Timestamp) rs.getTimestamp("ora"),
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
