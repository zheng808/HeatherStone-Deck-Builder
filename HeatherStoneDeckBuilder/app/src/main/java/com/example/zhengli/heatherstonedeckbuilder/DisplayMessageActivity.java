package com.example.zhengli.heatherstonedeckbuilder;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class DisplayMessageActivity extends AppCompatActivity {

    private String cardUrl;
    private String userInput;
    private String cardEffect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String userInput = intent.getStringExtra(DeckHomePage.EXTRA_MESSAGE);

        try {
            ImageView cardImage = (ImageView) findViewById(R.id.cardImage);
            TextView cardEffectView = (TextView) findViewById(R.id.cardEffect);
            CardAsync cardAsync = new CardAsync(userInput, cardImage,cardEffectView);
            cardAsync.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setCardEffect(DisplayMessageActivity view, String cardEffect){
        TextView cardEffectView = (TextView) findViewById(R.id.cardEffect);
        cardEffectView.setText(cardEffect);
    }



}
