package com.example.zhengli.heatherstonedeckbuilder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashape.unirest.http.JsonNode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.content.res.Resources;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by zhengli on 2016-03-05.
 */
public class CardAsync extends AsyncTask<Void, Void, Bitmap> {
    private static String URL = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/search/";
    private  String cardName;
    private  String cardURL;
    private  String cardEffect;


    ImageView cardImage;
    TextView cardEffectView;

    public CardAsync(String cardName, ImageView cardImage, TextView cardEffectView){
        this.cardName = cardName;
        this.cardImage = cardImage;
        this.cardEffectView = cardEffectView;
    }

    public void setCardURL(String cardURL) {
        this.cardURL = cardURL;
    }

    public String getCardURL() {
        return this.cardURL;
    }

    public String getCardEffect() {
        return cardEffect;
    }

    public void setCardEffect(String cardEffect) {
        this.cardEffect = cardEffect;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {

        HttpsURLConnection urlConnection = null;
        try {
            Card card = new Card(cardName);
            cardURL = card.getCardURL();
            cardEffect = card.getCardEffect();
            URL CardConnection = new URL(cardURL);
            HttpURLConnection connection = (HttpURLConnection) CardConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream CardImage = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(CardImage);

            return myBitmap;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {

        super.onPostExecute(result);
        cardImage.setImageBitmap(result);
        cardEffectView.setText(cardEffect);

    }



 }
