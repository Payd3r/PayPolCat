/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

/**
 *
 * @author Ficara Paolo, Mauri Andrea, Luca Cattaneo
 */
public class User {

    private String name;
    private String surname;
    private String cf;
    private String mail;
    private String nick;
    private String password;
    private String station;

    User() {
        name = "";
        surname = "";
        cf = "";
        mail = "";
        nick = "";
        password = "";
        station = "";
    }

    User(String name, String surname, String cf, String mail, String nick, String password, String station) {
        this.name = name;
        this.surname = surname;
        this.cf = cf;
        this.mail = mail;
        this.nick = nick;
        this.password = password;
        this.station = station;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getCf() {
        return cf;
    }

    public String getMail() {
        return mail;
    }

    public String getNick() {
        return nick;
    }

    public String getPassword() {
        return password;
    }

    public String getStation() {
        return station;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public static User formCSV(String info) {
        String[] splittedInfo = info.split(";");
        return new User(splittedInfo[0], splittedInfo[1], splittedInfo[2], splittedInfo[3], splittedInfo[4], splittedInfo[5], splittedInfo[6]);
    }
}
