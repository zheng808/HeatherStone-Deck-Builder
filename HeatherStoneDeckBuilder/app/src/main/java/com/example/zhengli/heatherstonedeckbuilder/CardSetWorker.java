package com.example.zhengli.heatherstonedeckbuilder;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by zhengli on 2016-03-13.
 */
public class CardSetWorker extends AsyncTask<Void,Void,Bitmap[]>{

    
    private Bitmap[] setGallery ;
    private Bitmap myBitmap;
    ImageView cardSetView;

    public CardSetWorker(ImageView cardSetView ){
        this.cardSetView = cardSetView;
    }

    private Bitmap[] getSetGallery(){
        return setGallery;
    }

    private  void setCardGallery(Bitmap[] setGallery){
        this.setGallery = setGallery;
    }

    @Override
    protected Bitmap[] doInBackground(Void... params) {

        try {
            CardSet cardSet = new CardSet();
            String[] cardURLSet = cardSet.getCardURLSet();
            setGallery = new Bitmap[cardURLSet.length];
            for(int  i =0; i<cardURLSet.length%5; i++){
                String url = cardURLSet[i];

                //print out each url for card set
                System.out.println(url);

                URL CardConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) CardConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream CardImage = connection.getInputStream();
                myBitmap = decodeSampledBitmapFromInputStream(CardImage,100, 100);
                setGallery[i] = myBitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  setGallery;
    }

    @Override
    protected void onPostExecute(Bitmap[] result) {
        int count =0;
        super.onPostExecute(result);
        cardSetView.setImageBitmap(result[0]);

    }

    public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromInputStream(InputStream is ,int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(is, null, options);
      }
    }
