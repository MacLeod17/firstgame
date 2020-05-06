package com.example.fightinggame;

import java.util.Random;

public class Game {
    private Player player = new Player();
    private Enemies enemies = new Enemies();

    private Random damage = new Random();
    private Random heal = new Random();
    private Random gold = new Random();
    private Random theft = new Random();

    private String creature;
    private String battle;
    private String difficulty;

    private Boolean despicableAct;

    private int creatureDamage;
    private int goldEarned;

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void resetDifficulty() {
        this.difficulty = "Normal";
    }

    public void setIndex() {
        enemies.setIndex();
        if (difficulty.equals("Easy") && ((enemies.getIndexCounter() <= 3) || (enemies.getIndexCounter() >= 34))) {
            setIndex();
        }
        else if (difficulty.equals("Hard") && ((enemies.getIndexCounter() <= 13) && (enemies.getIndexCounter() >= 4))) {
            setIndex();
        }
    }

    public String getCreature() {
        return this.creature;
    }

    public void setCreature() {
        this.creature = enemies.getCreature();
    }

    public int getCreatureDamage() {
        return creatureDamage;
    }

    public void setCreatureDamage() {
        if (difficulty.equals("Hard")) {
            this.creatureDamage = enemies.getCreatureDamage() + 5;
        }
        else {
            this.creatureDamage = enemies.getCreatureDamage();
        }
    }

    public String getBattle() {
        return this.battle;
    }

    public void setBattle() {
        if (despicableAct) {
            battle = getName() + " mugged the " + creature + " and has looted " + goldEarned + " gold. "
                    + player.getPronoun() + " should be ashamed of " + player.getOtherPronoun() + ".";
        }
        else {
            battle = getName() + " defeated the " + creature + " and has looted " + goldEarned + " gold";
        }
    }

    public Boolean getDespicableAct() {
        return despicableAct;
    }

    public void setDespicableAct() {
        this.despicableAct = enemies.getDespicableAct();
    }

    public void setEnemyStats() {
        setIndex();
        setCreature();
        setCreatureDamage();
        setDespicableAct();
        generateGold();
        setBattle();
    }

    public String getName() {
        return player.getName();
    }

    public void setName(String name) {
        player.setName(name);
    }

    public String getCharClass() {
        return player.getCharClass();
    }

    public void setCharClass(String charClass) {
        player.setCharClass(charClass);
    }

    public String getGender() {
        return player.getGender();
    }

    public void setGender(String g) {
        player.setGender(g);
    }

    public int getTotalHealth() {
        return player.getTotalHealth();
    }

    public int getCurrentHealth() {
        return player.getCurrentHealth();
    }

    public void takeDamage() {
        int takeDamage = damage.nextInt(getCreatureDamage() + (getLevel() ^ 2)) - getArmorLevel();
        if(takeDamage >= 1) {
            player.takeDamage(takeDamage);
        }
        else {
            player.takeDamage(1);
        }
    }

    public void healDamage() {
        player.healDamage(heal.nextInt(16) + 15);
    }

    public int getLevel() {
        return player.getLevel();
    }

    public void raiseLevel() {
        player.raiseLevel();
    }

    public int getBattlesWon() {
        return player.getBattlesWon();
    }

    public void battleWin() {
        player.battleWin();
    }

    public int getWinsNeeded() {
        return player.getWinsNeeded();
    }

    public int getGold() {
        return player.getGold();
    }

    public void addGold() {
        player.addGold(goldEarned);
    }

    public void generateGold() {
        goldEarned = gold.nextInt(enemies.getCreatureGold() + 1);
    }

    public void removeGold(int gold) {
        player.removeGold(gold);
    }

    public int getPotionsLeft() {
        return player.getPotionsLeft();
    }

    public void addPotion(int cost) {
        player.addPotion(cost);
    }

    public int getArmorLevel() {
        return player.getArmorLevel();
    }

    public void raiseArmorLevel(int cost) {
        player.raiseArmorLevel(cost);
    }

    public int getDaysActive() {
        return player.getDaysActive();
    }

    public void onPassDay() {
        player.onPassDay();
    }

    public void onWait() {
        player.onWait();
    }

    public Boolean onTheft() {
        int thief = theft.nextInt(16);
        if(thief == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void onBuyRing(int cost) {
        player.onBuyRing(cost);
    }

    public void resetAll() {
        player.resetAll();
        resetDifficulty();
    }

    public Boolean checkForLose() {
        return player.checkForLose();
    }
}
