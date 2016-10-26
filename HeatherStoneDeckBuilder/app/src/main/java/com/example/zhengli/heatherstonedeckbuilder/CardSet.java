package com.example.zhengli.heatherstonedeckbuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by zhengli on 2016-03-15.
 */
public class CardSet {

    private String [] cardURLSet;
    private static ArrayList<String> cardSetURL = new ArrayList<>();
    private static String URLCardSet = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/sets/";

    public void setCardURLSet(String[] cardURLSet) {
        this.cardURLSet = cardURLSet;
    }

    public String[] getCardURLSet() {
        return cardURLSet;
    }

    public CardSet() throws Exception {
        getCardSetURL(getCardSet("Classic"));

    }
    public JSONArray getCardSet(String set) throws IOException, JSONException {
        URL url = new URL(URLCardSet + set);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.addRequestProperty("X-Mashape-Key", "YhED4Nf6s8mshKOZNcygkgVJ70zwp1KeIbWjsn69Jax9aGmhOb");
        urlConnection.connect();
        InputStream input = urlConnection.getInputStream();
        BufferedReader Reader = new BufferedReader(new InputStreamReader(input));

        String temp = "";
        String response = "";

        while ((temp = Reader.readLine()) != null)
            response += temp;

        Reader.close();
        input.close();
        urlConnection.disconnect();
        JSONArray cardSetResponse = new JSONArray(response);
        System.out.println(cardSetResponse);
        return  cardSetResponse;

    }

    public String[] storeCardSetURL(ArrayList<String> cardSetURL) throws Exception {
        String url;
        cardURLSet = new String[cardSetURL.size()];
        for(int i=0; i< cardSetURL.size(); i++){
            url = cardSetURL.get(i);
            cardURLSet[i] = url;
        }

        return cardURLSet;
    }

    public ArrayList<String> getCardSetURL(JSONArray cardResponse) throws Exception{
        for(int i=0; i< cardResponse.length(); i++){
            if(cardResponse.getJSONObject(i).toString().contains("img")) {
                cardSetURL.add(cardResponse.getJSONObject(i).getString("img"));
            }

        }
        storeCardSetURL(cardSetURL);
        return  cardSetURL;
    }

}
