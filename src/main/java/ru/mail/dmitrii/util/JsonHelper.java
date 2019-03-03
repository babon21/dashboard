package ru.mail.dmitrii.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class JsonHelper {
	/**
	 * Возвращает JSON объект полученный из url
	 * @param url для загрузки
	 * @return JSON объект
	 * @throws Exception ошибка получения
	 */
	public static JsonObject getJsonObject( String url ) throws Exception {
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
			
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse( resp.toString()).getAsJsonObject();
		
		return json;
	}

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
