package com.example.zhengli.heatherstonedeckbuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by zhengli on 2016-03-09.
 */
public class Deck{

    public ArrayList<JSONArray> cardDeck =  new ArrayList<JSONArray>(30);
    public static ArrayList<JSONArray> storeDeck;
    private String url;

    public ArrayList<JSONArray> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(ArrayList<JSONArray> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public ArrayList<JSONArray> getStoreDeck(){
        return storeDeck;
    }

    public void setStoreDeck(ArrayList<JSONArray> storeDeck){
        this.storeDeck = storeDeck;
    }


    public Deck(JSONArray card) throws JSONException {

        storeCardToDeck(card);

    }

    private ArrayList<JSONArray> storeCardToDeck(JSONArray card) throws JSONException {
        cardDeck.add(card);

        for(int i =0; i< cardDeck.size(); i++) {
            for (JSONArray item : cardDeck) {
                System.out.println(item.getJSONObject(i).get("img"));
                return cardDeck;
            }
        }
        return null;
    }


}
