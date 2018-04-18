package com.kamczi.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.kamczi.game.controller.KeyboardController;
import com.kamczi.game.entity.Bullet;
import com.kamczi.game.entity.Coin;
import com.kamczi.game.entity.Plane;
import com.kamczi.game.loader.B2dAssetManager;

/**
 * Created by Kamil on 2018-04-14.
 */

public class Box2DModel {
    public World world;
    private Body bodyd;
    private Body bodys;
    private Body bodyk;
    private KeyboardController controller;
    private B2dAssetManager assMan;

    public Plane player;
    public Coin coin;
    public Bullet bullet;

    private OrthographicCamera camera;
    private Sound ping;
    private Sound boing;

    public static final int BOING_SOUND = 0;
    public static final int PING_SOUND = 1;


    public Box2DModel(KeyboardController cont, OrthographicCamera cam, B2dAssetManager assetManager){
        this.assMan = assetManager;
        camera = cam;
        controller = cont;
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new B2dContactListener(this));



        assMan.queueAddSounds();
        assMan.manager.finishLoading();
        ping = assMan.manager.get("sounds/ping.wav");
        boing = assMan.manager.get("sounds/boing.wav");


        player = new Plane(world,0,0,5,3, BodyDef.BodyType.DynamicBody);
        coin = new Coin(world,4,4);
        bullet = new Bullet(world,0,4,1.5f,0.5f);
        createFloor(0,-12);
        createFloor(0,12);


    }

    private void createFloor(float posx,float posy) {


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posx, posy);

        bodyd = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(24, 0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        bodyd.createFixture(fixtureDef);

        shape.dispose();
    }


    public void logicStep(float delta){
        if(controller.up){
            player.flyUp();
        }
        if(controller.right){
            player.shoot();
        }
        else
            player.stopShooting();
        world.step(delta , 3, 3);
    }

    public void playSound(int sound){
        switch(sound){
            case BOING_SOUND:
                boing.play();
                break;
            case PING_SOUND:
                ping.play();
                break;
        }
    }
}
