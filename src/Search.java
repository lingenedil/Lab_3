
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import net.sf.json.JSONObject;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lauren
 */
public class Search {
    
    public ArrayList<ICAO> search(ArrayList<String> toSearch) throws Exception {
        ArrayList<ICAO> icaoList = new ArrayList<>();
        
        Iterator <String> iterate = toSearch.iterator();
        
        while (iterate.hasNext()) {
        String icaoCode = iterate.next();
        String JSonString = readURL("http://api.geonames.org/weatherIcaoJSON?ICAO=" + icaoCode + "&formatted=true&username=lingenedil");
        JSONObject weather = JSONObject.fromObject(JSonString);
        JSONObject weatherData =(JSONObject)(weather.get("weatherObservation")); 
        String stationName = (String) weatherData.get("stationName");
        int temperature = weatherData.getInt("temperature");
        double latitude = Math.round(((Double) weatherData.get("lat")) * 100);
        double longitude = Math.round(((Double) weatherData.get("lng")) * 100);
        String dateTime = weatherData.getString("datetime");
        int humidity = weatherData.getInt("humidity");
        String clouds = weatherData.getString("clouds");
        
        icaoList.add(new ICAO(stationName, temperature, latitude, longitude, dateTime, humidity, clouds));
        }

        return icaoList;
    }
    
    public static String readURL(String webservice) throws Exception {
        URL oracle = new URL(webservice);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(
            oracle.openStream()));

        String inputLine;
        String result = "";

        while ((inputLine = in.readLine()) != null)
        result = result + inputLine;

        in.close();
        return result;
    }
    
    public ArrayList<String> load() throws FileNotFoundException {
        Scanner scan = new Scanner(new FileReader("input.txt"));
        
        ArrayList<String> list = new ArrayList<>();
        
        while (scan.hasNext()) {
            list.add(scan.next());
        }
        return list;
    }
    
    public void save(ArrayList<ICAO> icaoList) throws IOException {
        Iterator <ICAO> iterate = icaoList.iterator();
        String result = "";
        
        while (iterate.hasNext()) {
            result += iterate.next().toString();
        }
        
        FileWriter outfile = new FileWriter("output.txt");
	BufferedWriter out = new BufferedWriter(outfile);
        out.write(result);
        out.close();
    }
}
