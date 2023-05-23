/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.util.stream.Stream;

/**
 * Classe che rappresenta un'area interessante.
 * @author Ficara Paolo, Mauri Andrea, Luca Cattaneo
 */
public class InterestingAreas {

    private String id;
    private String name;
    private String countryCode;
    private String countryName;
    private String lat;
    private String lon;

    /**
     * Costruttore predefinito della classe `InterestingAreas`.
     * Inizializza gli attributi con valori vuoti.
     */
    public InterestingAreas() {
        id = "";
        name = "";
        countryCode = "";
        countryName = "";
        lat = "";
        lon = "";
    }

    /**
     * Costruttore della classe `InterestingAreas`.
     *
     * @param id           l'ID dell'area
     * @param name         il nome dell'area
     * @param countryCode il codice del paese dell'area
     * @param countryName il nome del paese dell'area
     * @param lat          la latitudine dell'area
     * @param lon          la longitudine dell'area
     */
    public InterestingAreas(String id, String name, String countryCode, String countryName, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Restituisce l'ID dell'area.
     *
     * @return l'ID dell'area
     */
    public String getId() {
        return id;
    }

    /**
     * Restituisce il nome dell'area.
     *
     * @return il nome dell'area
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce il codice del paese dell'area.
     *
     * @return il codice del paese dell'area
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Restituisce il nome del paese dell'area.
     *
     * @return il nome del paese dell'area
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Restituisce la latitudine dell'area.
     *
     * @return la latitudine dell'area
     */
    public String getLat() {
        return lat;
    }

    /**
     * Restituisce la longitudine dell'area.
     *
     * @return la longitudine dell'area
     */
    public String getLon() {
        return lon;
    }

    /**
     * Imposta l'ID dell'area.
     *
     * @param id l'ID dell'area
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Imposta il nome dell'area.
     *
     * @param name il nome dell'area
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Imposta il codice del paese dell'area.
     *
     * @param countryCode il codice del paese dell'area
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Imposta il nome del paese dell'area.
     *
     * @param countryName il nome del paese dell'area
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Imposta la latitudine dell'area.
     *
     * @param lat la latitudine dell'area
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * Imposta la longitudine dell'area.
     *
     * @param lon la longitudine dell'area
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     * Verifica se il nome dell'area contiene una determinata stringa.
     *
     * @param s la stringa da cercare
     * @return `true` se il nome dell'area contiene la stringa (ignorando maiuscole/minuscole), altrimenti `false`
     */
    boolean contains(String s) {
        return this.name.toLowerCase().contains(s.toLowerCase());
    }
}
