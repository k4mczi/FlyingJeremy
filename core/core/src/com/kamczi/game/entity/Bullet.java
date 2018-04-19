package com.kamczi.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Kamil on 2018-04-18.
 */

public class Bullet {
    private World world;
    private Texture bullet;

    Body b2body;

    public Bullet(World world, float x, float y,float width, float height){
        this.world = world;

        bullet = new Texture("images/bullet.png");

        defineBullet(x,y,width,height);
    }

    public void defineBullet(float x, float y, float width, float height){
        BodyDef bdef = new BodyDef();
        bdef.position.set(x,y);
        bdef.type = BodyDef.BodyType.KinematicBody;

        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2,height/2);
        fdef.shape = poly;
        b2body.createFixture(fdef).setUserData(this);
        poly.dispose();
        for(Fixture fix :b2body.getFixtureList()){
            fix.setSensor(true);
        }
        b2body.setLinearVelocity(100,0);
    }

    public float getPosX(){
        return b2body.getPosition().x;
    }

    public float getPosY(){
        return b2body.getPosition().y;
    }
    public Texture getTexture(){
        return bullet;
    }

    public void draw(SpriteBatch sb){
        sb.draw(getTexture(),getPosX()-0.75f,getPosY()-0.5f,1.5f,1f);
    }

    public void destroy(){
        world.destroyBody(b2body);
    }

}
