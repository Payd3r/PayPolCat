/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.sql.*;
import java.text.SimpleDateFormat;
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
     * Legge il database nella tabella "operatoriregistrati" e restituisce una
     * lista di oggetti User.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti User letti dal database
     * @throws SQLException se si verifica un errore di connessione al database
     * durante la query richiesta
     */
    public ArrayList<User> readUser(Connection conn) throws SQLException, ClassNotFoundException {
        ArrayList<User> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("select * from operatoreregistrato o join centromonitoraggio c on o.nome_centro = c.name ");
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
     * Legge il database dalla tabella "coordinatemonitoraggio" e restituisce
     * una lista di oggetti InterestingAreas.
     *
     * @param conn la connessione al database
     * @param offset
     * @param pageSize
     * @return una lista di oggetti InterestingAreas letti dal database
     * @throws SQLException se si verifica un errore di connessione al database
     * durante la query richiesta
     */
    public ArrayList<InterestingAreas> readAreas(Connection conn, int offset, int pageSize) {
        ArrayList<InterestingAreas> list = new ArrayList<>();
        String sql = "SELECT * FROM get_interesting_areas_with_pagination(?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new InterestingAreas(
                        rs.getInt("id"),
                        rs.getString("name_ascii"),
                        rs.getString("country_code"),
                        rs.getString("country_name"),
                        rs.getString("lat"),
                        rs.getString("lon")
                ));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Legge il database nella tabella "centromonitoraggio" e restituisce una
     * lista di oggetti MonitoringStation.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti MonitoringStation letti dal database
     * @throws SQLException se si verifica un errore di connessione al database
     * durante la query richiesta
     */
    public ArrayList<MonitoringStation> readStation(Connection conn) throws SQLException {
        ArrayList<MonitoringStation> list = new ArrayList<>();

        // Using try-with-resources for PreparedStatement and ResultSet
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM centromonitoraggio"); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new MonitoringStation(rs.getString("name"), rs.getString("address"), new ArrayList<>()));
            }
        }

        for (MonitoringStation station : list) {
            String query = "SELECT c.id, c.name, c.country_code, c.country_name, c.lat, c.lon "
                    + "FROM coordinatemonitoraggio c "
                    + "INNER JOIN lavora s ON c.id = s.id_coordinate "
                    + "INNER JOIN centromonitoraggio cm ON s.nome_centro = cm.name "
                    + "WHERE cm.name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, station.getName());
                try (ResultSet rs = stmt.executeQuery()) {
                    List<InterestingAreas> areeInteresse = new ArrayList<>();
                    while (rs.next()) {
                        InterestingAreas area = new InterestingAreas(
                                rs.getInt("id"),
                                rs.getString("name"), // Assuming the correct column name is "name"
                                rs.getString("country_code"),
                                rs.getString("country_name"),
                                rs.getString("lat"),
                                rs.getString("lon")
                        );
                        areeInteresse.add(area);
                    }
                    station.setInterestingAreas(areeInteresse);
                }
            }
        }
        return list;
    }

    /**
     * Legge il databse nella tabella "parametriclimatici" e restituisce una
     * lista di oggetti Forecast.
     *
     * @param conn la connessione al database
     * @return una lista di oggetti Forecast letti dal database
     * @throws SQLException se si verifica un errore di connessione al database
     * durante la query richiesta
     */
    public ArrayList<Forecast> readForecast(Connection conn) throws SQLException {
        ArrayList<Forecast> list = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM parametriclimatici pc JOIN centromonitoraggio c ON pc.nome_centro = c.name");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            list.add(new Forecast(
                    rs.getString("idcitta"),
                    rs.getString("nome_centro"),
                    rs.getDate("data"),
                    rs.getTimestamp("ora"),
                    rs.getString("vento").split(","),
                    rs.getString("umidita").split(","),
                    rs.getString("pressione").split(","),
                    rs.getString("temperatura").split(","),
                    rs.getString("precipitazioni").split(","),
                    rs.getString("altitudine").split(","),
                    rs.getString("massa").split(",")
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
     * @throws SQLException se si verifica un errore di connessione al database
     * durante la query richiesta
     */
    public void write(String query, Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        rs.close();
    }

    public void writeForecast(Forecast f, Connection conn) throws SQLException, ClassNotFoundException {
        String a = DatiCondivisi.getInstance().convertNameToId(f.getIdCittà());
        PreparedStatement stmt = conn.prepareStatement(f.toQuery());
        stmt.setInt(1, Integer.parseInt(a));
        stmt.setString(2, f.getNomeStazione());
        stmt.setDate(3, new Date(f.getData().getTime()));
        stmt.setTimestamp(4, new Timestamp(f.getData().getTime() + f.getOra().getTime()));
        stmt.setString(5, f.getVento()[0] + "," + f.getVento()[1]);
        stmt.setString(6, f.getUmidita()[0] + "," + f.getUmidita()[1]);
        stmt.setString(7, f.getPressione()[0] + "," + f.getPressione()[1]);
        stmt.setString(8, f.getTemperatura()[0] + "," + f.getTemperatura()[1]);
        stmt.setString(9, f.getPrecipitazioni()[0] + "," + f.getPrecipitazioni()[1]);
        stmt.setString(10, f.getAltitudine()[0] + "," + f.getAltitudine()[1]);
        stmt.setString(11, f.getMassa()[0] + "," + f.getMassa()[1]);
        stmt.executeUpdate();
    }

}
