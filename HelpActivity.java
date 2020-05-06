package com.example.fightinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    private TextView helpText;

    private Help help = new Help();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        helpText = findViewById(R.id.helpText);

        helpText.setEnabled(false);
    }

    public void onBasicInfoClick(View v) {
        String basic = help.getBasic();
        helpText.setText(basic);
    }

    public void onWaitInfoClick(View v) {
        String wait = help.getWait();
        helpText.setText(wait);
    }

    public void onHealInfoClick(View v) {
        String heal = help.getHeal();
        helpText.setText(heal);
    }

    public void onShopsInfoClick(View v) {
        String shops = help.getShops();
        helpText.setText(shops);
    }

    public void onDifficultyInfoClick(View v) {
        String difficulty = help.getDifficulty();
        helpText.setText(difficulty);
    }

    public void onGoToStartClick(View v) {
        Intent intent = gotoStartScreen();
        startActivity(intent);
    }

    protected Intent gotoStartScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        return  intent;
    }
}
