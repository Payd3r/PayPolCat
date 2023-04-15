/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.util.List;

/**
 *
 * @author paolo
 */
public class MonitoringStation {
    private String name, address;
    private String[] interestingAreas;

    public MonitoringStation(String name, String address, String[] interestingAreas) {
        this.name = name;
        this.address = address;
        this.interestingAreas = interestingAreas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getInterestingAreas() {
        return interestingAreas;
    }

    public void setInterestingAreas(String[] interestingAreas) {
        this.interestingAreas = interestingAreas;
    }
}
