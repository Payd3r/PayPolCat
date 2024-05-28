/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Classe che rappresenta una previsione meteorologica.
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 */
public class Forecast {

    private int id_Coordinate;
    private int id_Operatore;
    private String nomeStazione;
    private Date data;
    private Timestamp ora;
    private int vento;
    private String nota_vento;
    private int umidita;
    private String nota_umidita;
    private int pressione;
    private String nota_pressione;
    private int temperatura;
    private String nota_temperatura;
    private int precipitazioni;
    private String nota_precipitazioni;
    private int altitudine;
    private String nota_altitudine;
    private int massa;
    private String nota_massa;
    private int id_Stazione;

    public Forecast(int id_Coordinate, int id_Operatore, String nomeStazione, Date data, Timestamp ora, int vento, String nota_vento, int umidita, String nota_umidita, int pressione, String nota_pressione, int temperatura, String nota_temperatura, int precipitazioni, String nota_precipitazioni, int altitudine, String nota_altitudine, int massa, String nota_massa, int id_Stazione) {
        this.id_Coordinate = id_Coordinate;
        this.id_Operatore = id_Operatore;
        this.nomeStazione = nomeStazione;
        this.data = data;
        this.ora = ora;
        this.vento = vento;
        this.nota_vento = nota_vento;
        this.umidita = umidita;
        this.nota_umidita = nota_umidita;
        this.pressione = pressione;
        this.nota_pressione = nota_pressione;
        this.temperatura = temperatura;
        this.nota_temperatura = nota_temperatura;
        this.precipitazioni = precipitazioni;
        this.nota_precipitazioni = nota_precipitazioni;
        this.altitudine = altitudine;
        this.nota_altitudine = nota_altitudine;
        this.massa = massa;
        this.nota_massa = nota_massa;
        this.id_Stazione = id_Stazione;
    }

    public int getId_Coordinata() {
        return id_Coordinate;
    }

    public void setId_Coordinate(int id_Coordinate) {
        this.id_Coordinate = id_Coordinate;
    }

    public int getId_Operatore() {
        return id_Operatore;
    }

    public void setId_Operatore(int id_Operatore) {
        this.id_Operatore = id_Operatore;
    }

    public String getNomeStazione() {
        return nomeStazione;
    }

    public void setNomeStazione(String nomeStazione) {
        this.nomeStazione = nomeStazione;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Timestamp getOra() {
        return ora;
    }

    public void setOra(Timestamp ora) {
        this.ora = ora;
    }

    public int getVento() {
        return vento;
    }

    public void setVento(int vento) {
        this.vento = vento;
    }

    public String getNota_vento() {
        return nota_vento;
    }

    public void setNota_vento(String nota_vento) {
        this.nota_vento = nota_vento;
    }

    public int getUmidita() {
        return umidita;
    }

    public void setUmidita(int umidita) {
        this.umidita = umidita;
    }

    public String getNota_umidita() {
        return nota_umidita;
    }

    public void setNota_umidita(String nota_umidita) {
        this.nota_umidita = nota_umidita;
    }

    public int getPressione() {
        return pressione;
    }

    public void setPressione(int pressione) {
        this.pressione = pressione;
    }

    public String getNota_pressione() {
        return nota_pressione;
    }

    public void setNota_pressione(String nota_pressione) {
        this.nota_pressione = nota_pressione;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public String getNota_temperatura() {
        return nota_temperatura;
    }

    public void setNota_temperatura(String nota_temperatura) {
        this.nota_temperatura = nota_temperatura;
    }

    public int getPrecipitazioni() {
        return precipitazioni;
    }

    public void setPrecipitazioni(int precipitazioni) {
        this.precipitazioni = precipitazioni;
    }

    public String getNota_precipitazioni() {
        return nota_precipitazioni;
    }

    public void setNota_precipitazioni(String nota_precipitazioni) {
        this.nota_precipitazioni = nota_precipitazioni;
    }

    public int getAltitudine() {
        return altitudine;
    }

    public void setAltitudine(int altitudine) {
        this.altitudine = altitudine;
    }

    public String getNota_altitudine() {
        return nota_altitudine;
    }

    public void setNota_altitudine(String nota_altitudine) {
        this.nota_altitudine = nota_altitudine;
    }

    public int getMassa() {
        return massa;
    }

    public void setMassa(int massa) {
        this.massa = massa;
    }

    public String getNota_massa() {
        return nota_massa;
    }

    public void setNota_massa(String nota_massa) {
        this.nota_massa = nota_massa;
    }

    public int getId_Stazione() {
        return id_Stazione;
    }

    public void setId_Stazione(int id_Stazione) {
        this.id_Stazione = id_Stazione;
    }

    /**
     * Restituisce una rappresentazione in formato CSV della previsione
     * meteorologica.
     *
     */
    public String toCSV() {
        return "INSERT INTO parametriclimatici(id_coordinate,id_operatore,data,ora,vento,nota_vento,umidita,nota_umidita,pressione,nota_pressione,temperatura,nota_temperatura,precipitazioni,nota_precipitazioni,altitudine,nota_altitudine,massa,nota_massa)" + "     VALUES (" + id_Coordinate + "," + id_Operatore + "," + data + "," + ora + "," + vento + "," + nota_vento + "," + umidita + "," + nota_umidita + "," + pressione + "," + nota_pressione + "," + temperatura + "," + nota_temperatura + "," + precipitazioni + "," + nota_precipitazioni + "," + altitudine + "," + nota_altitudine + "," + massa + "," + nota_massa + ")";
    }

    public int getIdStazione() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
