package com.kamczi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kamczi.game.MainGame;

/**
 * Created by Kamil on 2018-04-14.
 */

public class LoadingScreen implements Screen {
    private MainGame parent;

    public LoadingScreen(MainGame mainGame){
        parent = mainGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        parent.changeScreen(MainGame.MENU);
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }
}
