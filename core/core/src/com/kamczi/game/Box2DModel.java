package com.kamczi.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.kamczi.game.controller.KeyboardController;
import com.kamczi.game.entity.Bullet;
import com.kamczi.game.entity.Coin;
import com.kamczi.game.entity.Plane;
import com.kamczi.game.loader.B2dAssetManager;

import java.util.Random;


/**
 * Created by Kamil on 2018-04-14.
 */

public class Box2DModel {

    public static final float vWidth = 40f;
    public static final float vHeight = 24f;

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

    private float stateTime=0;
    public static final int BOING_SOUND = 0;
    public static final int PING_SOUND = 1;

    private Array<Coin> coins;

    private Random rand;
    private int randomInt;

    public Box2DModel(KeyboardController cont, OrthographicCamera cam, B2dAssetManager assetManager){
        this.assMan = assetManager;
        rand = new Random();
        camera = cam;
        controller = cont;
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new B2dContactListener(this));



        assMan.queueAddSounds();
        assMan.manager.finishLoading();
        ping = assMan.manager.get("sounds/ping.wav");
        boing = assMan.manager.get("sounds/boing.wav");


        player = new Plane(world,0,0,5,3, BodyDef.BodyType.DynamicBody,assMan);
        bullet = new Bullet(world,0,4,1.5f,0.5f);
        createFloor(0,-12);
        createFloor(0,12);

        coins = new Array<Coin>();

    }

    private void createFloor(float posx,float posy) {


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(posx, posy);

        bodyd = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(vWidth, 0.1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        bodyd.createFixture(fixtureDef);

        shape.dispose();
    }


    public void logicStep(float delta){
        spawning(delta);
        if(controller.up){
            player.flyUp();
        }
        if(controller.down){
            player.flyDown();
        }
        if(controller.right){
            player.shoot(delta);
        }
        else
            player.stopShooting();
        world.step(delta , 3, 3);
    }

    public void draw(SpriteBatch sb, float dt){
        for(Coin coin : coins) {
            if(coin.getPosX()<-vWidth/2 || coin.isTouched) {
                coin.destroy();
                coins.removeValue(coin, true);
            }
            coin.update(dt);
            coin.draw(sb);
        }
    }
    public void spawning(float delta) {

        randomInt = rand.nextInt(30);
        if(randomInt == 0)
            coins.add(new Coin(world, vWidth, rand.nextInt(22)-11));

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
