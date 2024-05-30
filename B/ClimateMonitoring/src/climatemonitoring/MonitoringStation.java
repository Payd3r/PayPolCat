/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.util.List;

/**
 * Classe che rappresenta una stazione di monitoraggio.
 *
 * @author Ficara Paolo
 * @author Mauri Andrea
 * @author Luca Cattaneo
 */
public class MonitoringStation {

 
    private String name;
    private String address;
    private List<InterestingAreas> interestingAreas;

    /**
     * Crea una nuova istanza di MonitoringStation con il nome, l'indirizzo e le
     * aree di interesse specificate.
     *
     * @param name il nome della stazione di monitoraggio
     * @param address l'indirizzo della stazione di monitoraggio
     * @param interestingAreas le aree di interesse della stazione di
     * monitoraggio
     */
    public MonitoringStation(String name, String address, List<InterestingAreas> interestingAreas) {

        this.name = name;
        this.address = address;
        this.interestingAreas = interestingAreas;
    }

    /**
     * Restituisce il nome della stazione di monitoraggio.
     *
     * @return il nome della stazione di monitoraggio
     */
    public String getName() {
        return name;
    }

    /**
     * Imposta il nome della stazione di monitoraggio.
     *
     * @param name il nome da impostare
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Restituisce l'indirizzo della stazione di monitoraggio.
     *
     * @return l'indirizzo della stazione di monitoraggio
     */
    public String getAddress() {
        return address;
    }

    /**
     * Imposta l'indirizzo della stazione di monitoraggio.
     *
     * @param address l'indirizzo da impostare
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Restituisce le aree di interesse della stazione di monitoraggio.
     *
     * @return le aree di interesse della stazione di monitoraggio
     */
    public List<InterestingAreas> getInterestingAreas() {
        return interestingAreas;
    }

    /**
     * Imposta le aree di interesse della stazione di monitoraggio.
     *
     * @param interestingAreas le aree di interesse da impostare
     */
    public void setInterestingAreas(List<InterestingAreas> interestingAreas) {
        this.interestingAreas = interestingAreas;
    }


}
