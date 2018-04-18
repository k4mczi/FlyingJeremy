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
import com.kamczi.game.entity.Plane;
import com.kamczi.game.hud.Hud;

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
    private Texture coinTexture;
    private SpriteBatch sb;
    private float b1x,b2x;

    public final static float gameSpeed = 0.5f;

    private float stateTime = 0;

    private Hud hud;

    public MainScreen(MainGame mainGame) {
        parent = mainGame;
        cam = new OrthographicCamera(40,24);
        controller = new KeyboardController();
        model = new Box2DModel(controller,cam,parent.assMan);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);

        sb = new SpriteBatch();
        sb.setProjectionMatrix(cam.combined);

        hud = new Hud(sb);

        parent.assMan.queueAddImages();
        parent.assMan.manager.finishLoading();

        backgroundTex1 = parent.assMan.manager.get("images/background.png");
        backgroundTex2 = parent.assMan.manager.get("images/background.png");
        b1x=-20;
        b2x=20;



    }

    public void update(float dt){
        b1x-=gameSpeed;
        b2x-=gameSpeed;
        if(model.coin.getPosX()==-20){

        }
        if(b1x <= -60){
            b1x = b2x+40;
        }
        if(b2x <= -60){
            b2x = b1x+40;
        }

        model.player.update(dt);
        model.coin.update(dt);
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
        sb.draw(model.coin.getTexture(),model.coin.getPosX()-0.5f,model.coin.getPosY()-0.5f,1,1);
        sb.draw(model.player.getTexture(),model.player.getPosX()-2.5f,model.player.getPosY()-1.5f,5,3);
        sb.draw(model.bullet.getTexture(),model.bullet.getPosX()-0.75f,model.bullet.getPosY()-0.5f,1.5f,1f);
        sb.end();
        hud.hudStage.draw();


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
