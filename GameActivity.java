package com.example.fightinggame;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView nameViewText, classViewText, healthViewText, levelViewText, genderViewText, daysActiveText, difficultyText;
    private TextView battlesWonText, goldViewText, armorViewText, potionsViewText, battleLogText, ringActiveText;
    private Button healButton, battleButton, potionButton, armorButton, waitButton, ringButton;
    private Game games = new Game();

    private int waitCounter;

    private final int ARMOR_COST = 40;
    private final int POTION_COST = 20;
    private final int RING_COST = 100;
    private final int MAX_WAIT_COUNTER = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initGuiComponents();
        battleLogText.setEnabled(false);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String gender = intent.getStringExtra("Gender");
        String charClass = intent.getStringExtra("Class");
        String difficulty = intent.getStringExtra("Difficulty");
        games.setName(name);
        games.setGender(gender);
        games.setCharClass(charClass);
        games.setDifficulty(difficulty);
        nameViewText.setText("Name: " + games.getName());
        genderViewText.setText("Gender: " + games.getGender());
        classViewText.setText("Class: " + games.getCharClass());
        difficultyText.setText("Difficulty: " + games.getDifficulty());
    }

    public void onBattleClick(View v) {
        waitCounter = 0;
        games.setEnemyStats();
        games.takeDamage();
        if(games.checkForLose()) {
            onMessage("You lose", games.getName() + ", the " + games.getCharClass() + ", has died");
            healButton.setEnabled(false);
            battleButton.setEnabled(false);
            waitButton.setEnabled(false);
            enableShopButtons(false);
            setText(healthViewText);
            battleLogText.append(games.getName() + " was defeated by the " + games.getCreature());
            return;
        }
        if (games.getDespicableAct()) {
            onMessage("Oh, the horror!", games.getName() + " has committed a despicable act.");
            enableShopButtons(false);
        }
        games.addGold();
        if (!games.getDespicableAct()) {
            games.battleWin();
        }
        setText(battlesWonText);
        setText(goldViewText);
        setText(battleLogText);
        if(games.getBattlesWon() == games.getWinsNeeded()) {
            games.raiseLevel();
            enableShopButtons(true);
            onMessage("Congratulations!", games.getName() + " has reached level " + games.getLevel() + "!");
            setText(levelViewText);
            battleLogText.append(games.getName() + " has reached level " + games.getLevel() + "!" + "\r\n");
        }
        games.onPassDay();
        setText(healthViewText);
        setText(daysActiveText);
    }

    public void onHealClick(View v) {
        try {
            games.healDamage();
            setText(healthViewText);
            setText(potionsViewText);
        }
        catch(RuntimeException ex) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No potions left");
            builder.setMessage("You do not have any potions left");
            builder.show();
        }
    }

    public void onWaitClick(View v) {
        if (waitCounter >= MAX_WAIT_COUNTER) {
            onMessage("Return to battle", games.getName() + " grows restless, and refuses to wait any longer");
        }
        else {
            if(games.onTheft()) {
                if(games.getDifficulty().equals("Easy")) {
                    games.removeGold(games.getGold() / 3);
                }
                else {
                    games.removeGold(games.getGold() / 2);
                }
                onMessage("Oh no!", "A stranger stole " + games.getName() + "'s gold while they were asleep!");
                setText(goldViewText);
            }
            games.onWait();
            waitCounter += 1;
            setText(healthViewText);
            setText(daysActiveText);
        }
    }

    public void onBuyPotionClick(View v) {
        try {
            games.addPotion(POTION_COST);
            setText(potionsViewText);
            setText(goldViewText);
        }
        catch(IllegalArgumentException ex) {
            onMessage("Not enough gold", games.getName() + " does not have enough gold to buy this potion");
        }
    }

    public void onBuyArmorClick(View v) {
        try {
            games.raiseArmorLevel(ARMOR_COST);
            setText(armorViewText);
            setText(goldViewText);
        }
        catch(IllegalArgumentException ex) {
            onMessage("Not enough gold", games.getName() + " does not have enough gold to buy this armor");
        }
        catch(ArithmeticException ex) {
            onMessage("Armor level", games.getName() + " cannot equip anymore armor");
        }
    }

    public void onBuyRingClick(View v) {
        try {
            games.onBuyRing(RING_COST);
            setText(ringActiveText);
            setText(goldViewText);
        }
        catch(IllegalArgumentException ex) {
            onMessage("Not enough gold", games.getName() + " does not have enough gold to buy this ring");
        }
        catch (RuntimeException ex) {
            onMessage("Ring active", games.getName() + " already has a Ring of Regeneration");
        }
    }

    public void onResetGame(View v) {
        games.resetAll();
        Intent intent = gotoMainScreen();
        startActivity(intent);
    }

    public void setText(TextView text) {
        if(text == healthViewText) {
            healthViewText.setText("Health: " + games.getCurrentHealth() + "/" + games.getTotalHealth());
        }
        else if (text == armorViewText) {
            armorViewText.setText("Armor Class: " + games.getArmorLevel());
        }
        else if (text == goldViewText) {
            goldViewText.setText("Gold: " + games.getGold());
        }
        else if (text == ringActiveText) {
            ringActiveText.setText("Ring Active: Yes");
        }
        else if (text == daysActiveText) {
            daysActiveText.setText("Days Active: " + games.getDaysActive());
        }
        else if(text == battlesWonText) {
            battlesWonText.setText("Battles Won: " + games.getBattlesWon());
        }
        else if (text == battleLogText) {
            battleLogText.append(games.getBattle() + "\r\n");
        }
        else if (text == potionsViewText) {
            potionsViewText.setText("Potions Left: " + games.getPotionsLeft());
        }
        else {
            levelViewText.setText("Level: " + games.getLevel());
        }

    }

    public void onMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void enableShopButtons(Boolean b) {
        armorButton.setEnabled(b);
        potionButton.setEnabled(b);
        ringButton.setEnabled(b);
    }

    protected Intent gotoMainScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        return  intent;
    }

    private void initGuiComponents() {
        nameViewText = findViewById(R.id.nameViewText);
        classViewText = findViewById(R.id.classViewText);
        healthViewText = findViewById(R.id.healthViewText);
        levelViewText = findViewById(R.id.levelViewText);
        battlesWonText = findViewById(R.id.battlesViewText);
        goldViewText = findViewById(R.id.goldViewText);
        armorViewText = findViewById(R.id.armorViewText);
        potionsViewText = findViewById(R.id.potionsViewText);
        genderViewText = findViewById(R.id.genderViewText);
        battleLogText = findViewById(R.id.battleLogText);
        daysActiveText = findViewById(R.id.daysActiveText);
        ringActiveText = findViewById(R.id.ringActiveText);
        healButton = findViewById(R.id.healButton);
        battleButton = findViewById(R.id.battleButton);
        potionButton = findViewById(R.id.buyPotionButton);
        armorButton = findViewById(R.id.buyArmorButton);
        waitButton = findViewById(R.id.waitButton);
        ringButton = findViewById(R.id.buyRingButton);
        difficultyText = findViewById(R.id.difficultyText);
    }
}