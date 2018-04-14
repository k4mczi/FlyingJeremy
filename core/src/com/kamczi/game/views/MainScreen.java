package com.kamczi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.kamczi.game.Box2DModel;
import com.kamczi.game.MainGame;
import com.kamczi.game.controller.KeyboardController;

/**
 * Created by Kamil on 2018-04-14.
 */

public class MainScreen implements Screen {
    private MainGame parent;
    private Box2DModel model;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera cam;
    private KeyboardController controller;
    private Texture playerTex;
    private Texture backgroundTex1;
    private Texture backgroundTex2;
    private SpriteBatch sb;

    private float b1x,b2x;


    public MainScreen(MainGame mainGame) {
        parent = mainGame;
        cam = new OrthographicCamera(40,24);
        controller = new KeyboardController();
        model = new Box2DModel(controller,cam,parent.assMan);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

        sb = new SpriteBatch();
        sb.setProjectionMatrix(cam.combined);


        // tells our asset manger that we want to load the images set in loadImages method
        parent.assMan.queueAddImages();
        // tells teh asset manager to load the images and wait until finsihed loading.
        parent.assMan.manager.finishLoading();
        // gets the images as a texture
        playerTex = parent.assMan.manager.get("images/player.png");
        backgroundTex1 = parent.assMan.manager.get("images/background.png");
        backgroundTex2 = parent.assMan.manager.get("images/background.png");
        b1x=-20;
        b2x=20;



    }
    public void update(float dt){
        b1x-=0.5f;
        b2x-=0.5f;
        if(b1x <= -60){
            b1x = b2x+40;
        }
        if(b2x <= -60){
            b2x = b1x+40;
        }
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {
        update(delta);
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        sb.draw(backgroundTex1,b1x,-12,40,24);
        sb.draw(backgroundTex2,b2x,-12,40,24);
        sb.draw(playerTex,model.player.getPosition().x -2,model.player.getPosition().y -1.25f,4,2.5f);
        sb.end();


        debugRenderer.render(model.world, cam.combined);

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
