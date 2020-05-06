package com.example.fightinggame;

public class Help {
    private String basic;
    private String shops;
    private String heal;
    private String wait;
    private String difficulty;

    Help() {
        basic = "In Fighting Game, your goal is to have your character survive as many days as possible " +
                "while also collecting gold, winning battles, and increasing in level. In Fighting Game, your " +
                "character will fight in over 30 unique battle scenarios, including (but not limited to) dragons, assorted undead, " +
                "assorted arachnid-types, and more! But beware, your character has a mind of their own! If they " +
                "are greedy and commit despicable acts, the shopkeepers will refuse to serve them until time has " +
                "passed, so make sure to stock up on Healing Potions (see Shops Info)! You lose and the game ends once your " +
                "character has died.";

        shops = "In Fighting Game, your character can use the gold they've earned from battles to buy three " +
                "types of equipment from the shops: Armor, Healing Potions, and a Ring of Regeneration. " +
                "Healing Potions restore your character's health points lost in battle. Armor reduces the amount of " +
                "damage taken in each battle by up to 30 points. The Ring of Regeneration increases the healing capabilities " +
                "of your character while waiting (see Wait Info).";

        heal = "In Fighting Game, once your character has bought Healing Potions, they may consume one to restore 15-30 " +
                "health points per Potion. Time your Potion consumption: if your character is one battle away from increasing " +
                "in level, it may be worthwhile to save your Potions, since your character's health points reset to maximum " +
                "on level up!";

        wait = "In Fighting Game, if your character has low health points and is out of Healing Potions, then they may wait to " +
                "recover 1 health points per day to a maximum of 25 health points (4 per day to a maximum of 40 health points " +
                "with the Ring of Regeneration (see Shops Info)). However, your character can grow restless and refuse to wait " +
                "any longer. But beware, sometimes when your character is waiting, a Thief comes along and steals some of their gold!";

        difficulty = "In Fighting Game, you get to choose the difficulty of the game as you make your character. On Easy difficulty, " +
                "your character will never commit a despicable act (see Basic Info), won't encounter some of the hardest monsters, and " +
                "the Thief (see Wait Info) won't steal as much gold. On Normal difficulty, your character can encounter all of the " +
                "possible creatures in the game. On Hard difficulty, your character won't encounter some of the easiest monsters, " +
                "and can take more damage from each monster they encounter. ";
    }

    public String getBasic() {
        return basic;
    }

    public String getShops() {
        return shops;
    }

    public String getHeal() {
        return heal;
    }

    public String getWait() {
        return wait;
    }

    public String getDifficulty() {
        return difficulty;
    }
}
