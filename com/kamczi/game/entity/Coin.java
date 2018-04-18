package com.kamczi.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Kamil on 2018-04-15.
 */

public class Coin {
    private World world;
    private Body boxBody;
    private Texture texture;
    private Animation coinAnimation;

    public Coin(World world, float posx, float posy){
        this.world = world;

        texture = new Texture("images/coin.png");
        coinAnimation = new Animation(new TextureRegion(texture), 6, 1f, true);

        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = BodyDef.BodyType.KinematicBody;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = true;

        boxBody = world.createBody(boxBodyDef);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(0.5f, 0.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = poly;
        boxBody.createFixture(fixtureDef);
        poly.dispose();

    }

    public float getPosX(){
        return boxBody.getPosition().x;
    }

    public float getPosY(){
        return boxBody.getPosition().y;
    }

    public void update(float dt){
        coinAnimation.update(dt);
    }


    public TextureRegion getTexture(){
        return coinAnimation.getFrame();
    }

}
