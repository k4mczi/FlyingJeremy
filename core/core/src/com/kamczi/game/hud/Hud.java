package com.kamczi.game.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Kamil on 2018-04-17.
 */

public class Hud implements Disposable {

    public Stage hudStage;

    private static int score;
    private static int lives;
    private static int coins;

    private static Label scoreLabel;
    private static Label livesLabel;
    private static Label coinsLabel;

    public Hud(SpriteBatch sb){
        lives = 3;
        score = 0;
        coins = 0;

        hudStage = new Stage(new ScreenViewport());

        Table table = new Table();//define a table used to organize our hud's labels
        table.top();//Top-Align table
        table.setFillParent(true);//make the table fill the entire stage

        livesLabel = new Label(String.format("%d", lives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        coinsLabel = new Label(String.format("%d", coins), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(livesLabel).expandX().padTop(10);
        table.add(coinsLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        hudStage.addActor(table);
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%d",score));
    }

    public static void lessLives(){
        lives -= 1;
        livesLabel.setText(String.format("%d",lives));
    }

    public static void addLives(int value){
        lives += value;
        if(lives>3){
            lives = 3;
        }
        livesLabel.setText(String.format("%d",lives));
    }

    public static void addCoins(int value){
        coins += value;
        coinsLabel.setText(String.format("%d",coins));
    }

    @Override
    public void dispose() {
        hudStage.dispose();
    }
}
