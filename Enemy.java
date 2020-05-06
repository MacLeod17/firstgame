package com.example.fightinggame;

public class Enemy {
    private String name;
    private int damage;
    private int gold;
    private boolean despicableAct;

    public Enemy() {

    }

    public Enemy(String name, int damage, int gold, boolean despicableAct) {
        this.setName(name);
        this.setDamage(damage);
        this.setGold(gold);
        this.setDespicableAct(despicableAct);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public boolean isDespicableAct() {
        return despicableAct;
    }

    public void setDespicableAct(boolean despicableAct) {
        this.despicableAct = despicableAct;
    }
}
