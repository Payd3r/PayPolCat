/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

/**
 *
 * @author Catta
 */
public class User {
    String name;
    String surname;
    String cf;
    String mail;
    String nick;
    String password;
    String station;

    User() {
        name = "";
        surname = "";
        cf = "";
        mail = "";
        nick = "";
        password = "";
        station = "";
    }
    User(String name,String surname,String cf,String mail,String nick,String password,String station) {
        this.name = name;
        this.surname =surname;
        this.cf = cf;
        this.mail = mail;
        this.nick = nick;
        this.password = password;
        this.station = station;
    }
}
