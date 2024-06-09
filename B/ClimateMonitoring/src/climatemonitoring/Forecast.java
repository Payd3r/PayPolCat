/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

/**
 * Classe che rappresenta una previsione meteorologica.
 *
 * @author Ficara Paolo 755155 CO
 * @author Mauri Andrea 755140 CO
 * @author Luca Cattaneo 755083 CO
 */
public class Forecast implements Serializable {

    /**
     * Identificatore della città associata alla previsione.
     * <p>
     * Questo attributo contiene l'identificatore univoco della città a cui la
     * previsione meteorologica si riferisce. Viene utilizzato per collegare la
     * previsione alla sua città di riferimento nel database.
     */
    private String idCitta;
    /**
     * Nome della stazione meteorologica.
     * <p>
     * Questo attributo rappresenta il nome della stazione meteorologica da cui
     * proviene la previsione. Identifica la fonte da cui sono stati raccolti i
     * dati meteorologici per la previsione.
     */
    private String NomeStazione;
    /**
     * Data della previsione.
     * <p>
     * Questo attributo indica la data alla quale si riferisce la previsione
     * meteorologica. Indica il momento in cui è stata effettuata la previsione.
     */
    private Date data;
    /**
     * Ora della previsione.
     * <p>
     * Questo attributo rappresenta l'ora esatta in cui è stata effettuata la
     * previsione meteorologica. Indica il momento temporale al quale si
     * riferisce la previsione.
     */
    private Timestamp ora;
    /**
     * Array contenente i dati relativi al vento (direzione e velocità).
     * <p>
     * Questo attributo è un array che contiene i dati relativi al vento
     * previsti nella previsione meteorologica. Gli elementi dell'array
     * rappresentano la direzione e la velocità del vento.
     */
    private String[] vento;
    /**
     * Array contenente i dati relativi all'umidità (percentuale e unità di
     * misura).
     * <p>
     * Questo attributo è un array che contiene i dati relativi all'umidità
     * previsti nella previsione meteorologica. Gli elementi dell'array
     * rappresentano la percentuale di umidità e la sua unità di misura.
     */
    private String[] umidita;
    /**
     * Array contenente i dati relativi alla pressione atmosferica (valore e
     * unità di misura).
     * <p>
     * Questo attributo è un array che contiene i dati relativi alla pressione
     * atmosferica previsti nella previsione meteorologica. Gli elementi
     * dell'array rappresentano il valore della pressione e la sua unità di
     * misura.
     */
    private String[] pressione;
    /**
     * Array contenente i dati relativi alla temperatura (valore e unità di
     * misura).
     * <p>
     * Questo attributo è un array che contiene i dati relativi alla temperatura
     * previsti nella previsione meteorologica. Gli elementi dell'array
     * rappresentano il valore della temperatura e la sua unità di misura.
     */
    private String[] temperatura;
    /**
     * Array contenente i dati relativi alle precipitazioni (quantità e unità di
     * misura).
     * <p>
     * Questo attributo è un array che contiene i dati relativi alle
     * precipitazioni previste nella previsione meteorologica. Gli elementi
     * dell'array rappresentano la quantità di precipitazioni e la sua unità di
     * misura.
     */
    private String[] precipitazioni;
    /**
     * Array contenente i dati relativi all'altitudine (valore e unità di
     * misura).
     * <p>
     * Questo attributo è un array che contiene i dati relativi all'altitudine
     * previsti nella previsione meteorologica. Gli elementi dell'array
     * rappresentano il valore dell'altitudine e la sua unità di misura.
     */
    private String[] altitudine;
    /**
     * Array contenente i dati relativi alla massa (valore e unità di misura).
     * <p>
     * Questo attributo è un array che contiene i dati relativi alla massa
     * previsti nella previsione meteorologica. Gli elementi dell'array
     * rappresentano il valore della massa e la sua unità di misura.
     */
    private String[] massa;

    /**
     * Crea una nuova istanza di Forecast.
     *
     * @param idCitta l'ID della città
     * @param NomeStazione il nome della stazione
     * @param data la data della previsione
     * @param ora l'ora della previsione
     * @param vento i dati relativi al vento
     * @param umidita i dati relativi all'umidità
     * @param pressione i dati relativi alla pressione
     * @param temperatura i dati relativi alla temperatura
     * @param precipitazioni i dati relativi alle precipitazioni
     * @param altitudine i dati relativi all'altitudine
     * @param massa i dati relativi alla massa
     */
    public Forecast(String idCitta, String NomeStazione, Date data, Timestamp ora, String[] vento, String[] umidita, String[] pressione, String[] temperatura, String[] precipitazioni, String[] altitudine, String[] massa) {
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

    /**
     * Imposta l'ID della città.
     *
     * @param idCittà l'ID della città da impostare
     */
    public void setIdCittà(String idCittà) {
        this.idCitta = idCittà;
    }

    /**
     * Imposta il nome della stazione.
     *
     * @param NomeStazione il nome della stazione da impostare
     */
    public void setNomeStazione(String NomeStazione) {
        this.NomeStazione = NomeStazione;
    }

    /**
     * Imposta la data della previsione.
     *
     * @param data la data della previsione da impostare
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Imposta l'ora della previsione.
     *
     * @param ora l'ora della previsione da impostare
     */
    public void setOra(Timestamp ora) {
        this.ora = ora;
    }

    /**
     * Imposta i dati relativi al vento.
     *
     * @param vento i dati relativi al vento da impostare
     */
    public void setVento(String[] vento) {
        this.vento = vento;
    }

    /**
     * Imposta i dati relativi all'umidità.
     *
     * @param umidita i dati relativi all'umidità da impostare
     */
    public void setUmidita(String[] umidita) {
        this.umidita = umidita;
    }

    /**
     * Imposta i dati relativi alla pressione.
     *
     * @param pressione i dati relativi alla pressione da impostare
     */
    public void setPressione(String[] pressione) {
        this.pressione = pressione;
    }

    /**
     * Imposta i dati relativi alla temperatura.
     *
     * @param temperatura i dati relativi alla temperatura da impostare
     */
    public void setTemperatura(String[] temperatura) {
        this.temperatura = temperatura;
    }

    /**
     * Imposta i dati relativi alle precipitazioni.
     *
     * @param precipitazioni i dati relativi alle precipitazioni da impostare
     */
    public void setPrecipitazioni(String[] precipitazioni) {
        this.precipitazioni = precipitazioni;
    }

    /**
     * Imposta i dati relativi all'altitudine.
     *
     * @param altitudine i dati relativi all'altitudine da impostare
     */
    public void setAltitudine(String[] altitudine) {
        this.altitudine = altitudine;
    }

    /**
     * Imposta i dati relativi alla massa.
     *
     * @param massa i dati relativi alla massa da impostare
     */
    public void setMassa(String[] massa) {
        this.massa = massa;
    }

    /**
     * Ottiene l'ID della città.
     *
     * @return l'ID della città
     */
    public String getIdCittà() {
        return idCitta;
    }

    /**
     * Ottiene il nome della stazione.
     *
     * @return il nome della stazione
     */
    public String getNomeStazione() {
        return NomeStazione;
    }

    /**
     * Ottiene la data della previsione.
     *
     * @return la data della previsione
     */
    public Date getData() {
        return data;
    }

    /**
     * Ottiene l'ora della previsione.
     *
     * @return l'ora della previsione
     */
    public Timestamp getOra() {
        return ora;
    }

    /**
     * Ottiene i dati relativi al vento.
     *
     * @return i dati relativi al vento
     */
    public String[] getVento() {
        return vento;
    }

    /**
     * Ottiene i dati relativi all'umidità.
     *
     * @return i dati relativi all'umidità
     */
    public String[] getUmidita() {
        return umidita;
    }

    /**
     * Ottiene i dati relativi alla pressione.
     *
     * @return i dati relativi alla pressione
     */
    public String[] getPressione() {
        return pressione;
    }

    /**
     * Ottiene i dati relativi alla temperatura.
     *
     * @return i dati relativi alla temperatura
     */
    public String[] getTemperatura() {
        return temperatura;
    }

    /**
     * Ottiene i dati relativi alle precipitazioni.
     *
     * @return i dati relativi alle precipitazioni
     */
    public String[] getPrecipitazioni() {
        return precipitazioni;
    }

    /**
     * Ottiene i dati relativi all'altitudine.
     *
     * @return i dati relativi all'altitudine
     */
    public String[] getAltitudine() {
        return altitudine;
    }

    /**
     * Ottiene i dati relativi alla massa.
     *
     * @return i dati relativi alla massa
     */
    public String[] getMassa() {
        return massa;
    }

    /**
     * Restituisce una stringa contentente la query da inviare al db per
     * inserire una nuova previsione
     *
     * @return una stringa contentente la query da inviare al db per inserire
     * una nuova previsione
     */
    public String toQuery() {
        return "INSERT INTO parametriclimatici (idCitta, nome_centro, data, ora, vento, umidita, pressione, temperatura, precipitazioni, altitudine, massa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }
}
