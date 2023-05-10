/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Ficara Paolo, Mauri Andrea, Luca Cattaneo
 */
public class Forecast {

    private String idCitta;
    private String NomeStazione;
    private Date data;
    private Date ora;
    private String[] vento;
    private String[] umidita;
    private String[] pressione;
    private String[] temperatura;
    private String[] precipitazioni;
    private String[] altitudine;
    private String[] massa;

    public Forecast(String idCitta, String NomeStazione, Date data, Date ora, String[] vento, String[] umidita, String[] pressione, String[] temperatura, String[] precipitazioni, String[] altitudine, String[] massa) {
        this.idCitta = idCitta;
        this.NomeStazione = NomeStazione;
        this.data = data;
        this.ora = ora;
        this.vento = vento;
        this.umidita = umidita;
        this.pressione = pressione;
        this.temperatura = temperatura;
        this.precipitazioni = precipitazioni;
        this.altitudine = altitudine;
        this.massa = massa;
    }

    public void setIdCittà(String idCittà) {
        this.idCitta = idCittà;
    }

    public void setNomeStazione(String NomeStazione) {
        this.NomeStazione = NomeStazione;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setOra(Date ora) {
        this.ora = ora;
    }

    public void setVento(String[] vento) {
        this.vento = vento;
    }

    public void setUmidita(String[] umidita) {
        this.umidita = umidita;
    }

    public void setPressione(String[] pressione) {
        this.pressione = pressione;
    }

    public void setTemperatura(String[] temperatura) {
        this.temperatura = temperatura;
    }

    public void setPrecipitazioni(String[] precipitazioni) {
        this.precipitazioni = precipitazioni;
    }

    public void setAltitudine(String[] altitudine) {
        this.altitudine = altitudine;
    }

    public void setMassa(String[] massa) {
        this.massa = massa;
    }

    public String getIdCittà() {
        return idCitta;
    }

    public String getNomeStazione() {
        return NomeStazione;
    }

    public Date getData() {
        return data;
    }

    public Date getOra() {
        return ora;
    }

    public String[] getVento() {
        return vento;
    }

    public String[] getUmidita() {
        return umidita;
    }

    public String[] getPressione() {
        return pressione;
    }

    public String[] getTemperatura() {
        return temperatura;
    }

    public String[] getPrecipitazioni() {
        return precipitazioni;
    }

    public String[] getAltitudine() {
        return altitudine;
    }

    public String[] getMassa() {
        return massa;
    }

    public String toCSV() {
        return idCitta + ";" + NomeStazione + ";" + new SimpleDateFormat("dd/MM/yyyy").format(data) + ";" + new SimpleDateFormat("hh:mm:ss").format(ora) + ";" + vento[0] + "," + vento[1] + "," + vento[2] + ";" + umidita[0] + "," + umidita[1] + "," + umidita[2] + ";" + pressione[0] + "," + pressione[1] + "," + pressione[2] + ";" + temperatura[0] + "," + temperatura[1] + "," + temperatura[2] + ";" + precipitazioni[0] + "," + precipitazioni[1] + "," + precipitazioni[2] + ";" + altitudine[0] + "," + altitudine[1] + "," + altitudine[2] + ";" + massa[0] + "," + massa[1] + "," + massa[2] + "\n";
    }

}
