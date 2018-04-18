package com.kamczi.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Kamil on 2018-04-17.
 */

public class Plane{


    private World world;
    public Body b2body;

    private Texture flyTexture;
    private Animation flyAnimation;
    private Texture shootTexture;
    private Animation shootAnimation;

    private boolean isFlying;
    private boolean isShooting;

    private Array<Bullet> bullets;

    public Plane(World world, float posx, float posy, float width, float height, BodyDef.BodyType bodyType){
        this.world = world;

        flyTexture = new Texture("images/player.png");
        flyAnimation = new Animation(new TextureRegion(flyTexture),2,0.1f, true);

        shootTexture = new Texture("images/shooting.png");
        shootAnimation = new Animation(new TextureRegion(shootTexture),5,0.3f, false);

        definePlane(posx,posy,width,height,bodyType);

        bullets = new Array<Bullet>();
    }
    private void definePlane(float posx, float posy, float width, float height, BodyDef.BodyType bodyType){
        BodyDef bdef = new BodyDef();
        bdef.position.set(posx,posy);
        bdef.fixedRotation = true;
        bdef.type = bodyType;

        b2body = world.createBody(bdef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2,height/2);
        fixtureDef.shape = poly;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.0f;
        b2body.createFixture(fixtureDef).setUserData(this);
        poly.dispose();

    }


    public float getPosX(){
        return b2body.getPosition().x;
    }

    public float getPosY(){
        return b2body.getPosition().y;
    }


    public void flyUp(){
        b2body.applyLinearImpulse(new Vector2(0,10f), b2body.getWorldCenter(),true);
    }

    public void shoot(){
        isShooting = true;
    }
    public void stopShooting(){
        isShooting = false;
    }

    public void update(float dt){
        if(isFlying)
        flyAnimation.update(dt);

        if(isShooting)
        shootAnimation.update(dt);
    }

    public TextureRegion getTexture(){
        if(isShooting)
            return shootAnimation.getFrame();
        else
            return flyAnimation.getFrame();
    }

}
