/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

/**
 * La classe User rappresenta un utente con diverse informazioni personali.
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 */
public class User {

    private int id;
    private String name;
    private String surname;
    private String cf;
    private String mail;
    private String nick;
    private String password;
    private int station;

    /**
     * Costruisce un oggetto User con le informazioni personali specificate.
     *
     * @param name il nome dell'utente
     * @param surname il cognome dell'utente
     * @param cf il codice fiscale dell'utente
     * @param mail l'indirizzo email dell'utente
     * @param nick il nickname dell'utente
     * @param password la password dell'utente
     * @param station la stazione dell'utente
     */
    public User(int id, String name, String surname, String cf, String mail, String nick, String password, int station) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.mail = mail;
        this.nick = nick;
        this.password = password;
        this.station = station;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Restituisce il nome dell'utente.
     *
     * @return il nome dell'utente
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return il cognome dell'utente
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Restituisce il codice fiscale dell'utente.
     *
     * @return il codice fiscale dell'utente
     */
    public String getCf() {
        return cf;
    }

    /**
     * Restituisce l'indirizzo email dell'utente.
     *
     * @return l'indirizzo email dell'utente
     */
    public String getMail() {
        return mail;
    }

    /**
     * Restituisce il nickname dell'utente.
     *
     * @return il nickname dell'utente
     */
    public String getNick() {
        return nick;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return la password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Restituisce la stazione dell'utente.
     *
     * @return la stazione dell'utente
     */
    public int getStation() {
        return station;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param name il nome dell'utente da impostare
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param surname il cognome dell'utente da impostare
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Imposta il codice fiscale dell'utente.
     *
     * @param cf il codice fiscale dell'utente da impostare
     */
    public void setCf(String cf) {
        this.cf = cf;
    }

    /**
     * Imposta l'indirizzo email dell'utente.
     *
     * @param mail l'indirizzo email dell'utente da impostare
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Imposta il nickname dell'utente.
     *
     * @param nick il nickname dell'utente da impostare
     */
    public void setNick(String nick) {
        this.nick = nick;
    }

    /**
     * Imposta la password dell'utente.
     *
     * @param password la password dell'utente da impostare
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Imposta la stazione dell'utente.
     *
     * @param station la stazione dell'utente da impostare
     */
    public void setStation(int station) {
        this.station = station;
    }
}
