package ru.erik182.utils;

import org.json.simple.parser.JSONParser;
import ru.erik182.models.City;

import javax.json.JsonObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class Geocoder {

    public static JsonObject getCoordinatesOfCity(City city) throws Exception {
        URLConnection connection = new URL("https://geocode-maps.yandex.ru/1.x/?format=json&apikey=d77468b0-a7d7-40d5-bce9-38caf9607315&geocode=" + city.getName()).openConnection();

        InputStream is = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(is);
        char[] buffer = new char[256];
        int rc;

        StringBuilder sb = new StringBuilder();

        while ((rc = reader.read(buffer)) != -1)
            sb.append(buffer, 0, rc);

        reader.close();
        JSONParser parser = new JSONParser();
        JsonObject jsonObject = parser.parse(sb.toString());
        return jsonObject;
    }
}
