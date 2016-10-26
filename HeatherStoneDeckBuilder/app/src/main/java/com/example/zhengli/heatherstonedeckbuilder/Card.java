package com.example.zhengli.heatherstonedeckbuilder;

/**
 * Created by zhengli on 2016-02-28.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;

import javax.net.ssl.HttpsURLConnection;

public class Card {


    private static String URL = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/search/";
    private static String URLCardSet = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/sets/";
    private static ArrayList<String> cardSetURL = new ArrayList<>();
    private String[] cardURLSet;
    private String cardName;
    private String cardEffect;
    private String cardURL;

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return this.cardName;
    }

    public String getCardURL() {
        return this.cardURL;
    }

    public void setCardURL(String cardURL) {
        this.cardURL = cardURL;
    }

    public String getCardEffect() {
        return cardEffect;
    }

    public void setCardEffect(String cardEffect) {
        this.cardEffect = cardEffect;
    }

    public Card(String cardName) throws Exception {

        if(!cardName.equals("")) {
            JSONArray cardArray = netWorkOperationForCardInfo(cardName);

            //return single card url
            cardURL = getSingleCardURL(cardArray);
            cardEffect = getCardEffect(cardArray);
        }
    }

    private JSONArray netWorkOperationForCardInfo(String cardName) throws Exception {
        URL url = new URL(URL + cardName);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.addRequestProperty("X-Mashape-Key", "YhED4Nf6s8mshKOZNcygkgVJ70zwp1KeIbWjsn69Jax9aGmhOb");
        urlConnection.connect();
        InputStream input = urlConnection.getInputStream();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(input));

        String temp = "";
        String response = "";

        while ((temp = bReader.readLine()) != null)
            response += temp;

        bReader.close();
        input.close();
        urlConnection.disconnect();
        JSONArray cardResponse = new JSONArray(response);
        return cardResponse;
    }

    public ArrayList<String> getCardSetURL(JSONArray cardResponse) throws Exception{
        if(cardResponse.length() == 1){
            cardSetURL.add(cardResponse.getJSONObject(0).getString("img"));
            return cardSetURL;
        }

        //store the card url into String array
        storeCardSetURL(cardSetURL);


        return cardSetURL;
    }

    private String getSingleCardURL(JSONArray cardResponse) throws JSONException {
         if(cardResponse.getJSONObject(0).toString().contains("img")){
              cardURL =  cardResponse.getJSONObject(0).getString("img");
             return  cardURL;
         }else {
             System.out.println("The card does not have the image");
         }

        return  "";


    }

    private String getCardEffect(JSONArray cardResponse) throws Exception{
        List<String> list = new ArrayList<String>();
        //mechanics
        if(!cardResponse.getJSONObject(0).toString().contains("mechanics")){
            return cardResponse.getJSONObject(0).getString("text");
        }else{
            JSONArray effectArray = cardResponse.getJSONObject(0).getJSONArray("mechanics");
            for(int i = 0 ; i < effectArray.length() ; i++){
                list.add(effectArray.getJSONObject(i).getString("name"));
            }
            cardEffect = list.get(0);
            return cardEffect;
        }


    }

    private String[] storeCardSetURL(ArrayList<String> cardSetURL) throws Exception {
        String url;
        cardURLSet = new String[cardSetURL.size()];
        for(int i=0; i< cardSetURL.size(); i++){
            url = cardSetURL.get(i);
            cardURLSet[i] = url;
        }

        System.out.println("cardSet" + cardURLSet[2]);
        return cardURLSet;
    }


}


