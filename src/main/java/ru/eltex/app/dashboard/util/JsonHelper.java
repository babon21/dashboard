package ru.eltex.app.dashboard.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Класс помощник для работы с запросами JSON
 * @author darzhain
 */
public class JsonHelper {

    public static String jsonToString(String url) throws Exception {
        URL obj = new URL( url );
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod( "GET" );

        int status = conn.getResponseCode();
        if( status != HttpURLConnection.HTTP_OK )
            throw new Exception( "Wrong HTTP status: " + status );

        BufferedReader in = new BufferedReader( new InputStreamReader( conn.getInputStream()));
        String inputLine;
        StringBuffer resp = new StringBuffer();

        while(( inputLine = in.readLine()) != null )
            resp.append( inputLine );

        in.close();
	    return resp.toString();
    }

}
