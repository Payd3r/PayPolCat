package climatemonitoring;

public class MonitoringStation {
    String id;
    String name;
    String countryCode;
    String countryName;
    String lat;
    String lon;

    MonitoringStation(){
         id="";
         name="";
         countryCode="";
         countryName="";
         lat="";
         lon="";
    }
    public MonitoringStation(String id, String name, String countryCode, String countryName, String lat, String lon) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.lat = lat;
        this.lon = lon;
    }
 }

