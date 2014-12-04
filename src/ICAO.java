/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lauren
 */
public class ICAO {
    String stationName;
    int temperature;
    double latitude;
    double longitude;
    String dateTime;
    int humidity;
    String clouds;
    
    public ICAO(String stationName, int temperature, double latitude, double longitude, String dateTime, int humidity, String clouds) {
        this.stationName = stationName;
        this.temperature = temperature;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateTime = dateTime;
        this.humidity = humidity;
        this.clouds = clouds;
    }
    
    public String toString() {
        return ("Station Name: " + stationName + "\n" + "Temperature: " + temperature + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude + "\n" + "Date/Time: " + dateTime + "\n" + "Humidity: " + humidity + "\n" + "Clouds: " + clouds + "\n\n");
    }
}
