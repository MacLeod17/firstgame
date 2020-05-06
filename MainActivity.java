package com.example.fightinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView nameInputText, classInputText, genderInputText, difficultyInputText;
    private final int MAX_NAME_SIZE = 20;
    private final int MIN_NAME_SIZE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInputText = findViewById(R.id.nameInputText);
        genderInputText = findViewById(R.id.genderInputText);
        classInputText = findViewById(R.id.classInputText);
        difficultyInputText = findViewById(R.id.difficultyInputText);
    }

    public void onStartGameClick(View v) {
        String name = nameInputText.getText().toString();
        String charClass = classInputText.getText().toString();
        String gender = genderInputText.getText().toString();
        String difficulty;
        if(difficultyInputText.getText().toString().toLowerCase().equals("easy")) {
            difficulty = "Easy";
        }
        else if (difficultyInputText.getText().toString().toLowerCase().equals("normal")) {
            difficulty = "Normal";
        }
        else if (difficultyInputText.getText().toString().toLowerCase().equals("hard")){
            difficulty = "Hard";
        }
        else if (difficultyInputText.getText().toString().equals("")) {
            difficulty = "Normal";
        }
        else {
            onMessage("Difficulty", "Difficulty settings can only be easy, normal, or hard");
            return;
        }
        if (name.length() < MIN_NAME_SIZE || name.length() > MAX_NAME_SIZE) {
            onMessage("Name", "Character name must be 2-20 characters long");
            return;
        }
        if (gender.length() > MAX_NAME_SIZE) {
            onMessage("Gender", "Gender must not exceed 20 characters long");
            return;
        }
        if (charClass.length() > MAX_NAME_SIZE) {
            onMessage("Class", "Class name must not exceed 20 characters long");
            return;
        }
        Intent intent = gotoGameScreen();
        intent.putExtra("Name", name);
        intent.putExtra("Gender", gender);
        intent.putExtra("Class", charClass);
        intent.putExtra("Difficulty", difficulty);
        startActivity(intent);
    }

    public void onGameplayClick(View v) {
        Intent intent = gotoHelpScreen();
        startActivity(intent);
    }

    protected Intent gotoGameScreen() {
        Intent intent = new Intent(this, GameActivity.class);
        return  intent;
    }

    protected Intent gotoHelpScreen() {
        Intent intent = new Intent(this, HelpActivity.class);
        return  intent;
    }

    public void onMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}