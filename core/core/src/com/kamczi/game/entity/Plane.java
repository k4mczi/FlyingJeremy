package com.kamczi.game.entity;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.kamczi.game.loader.B2dAssetManager;

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
    private boolean canShoot=true;

    private float stateTime = 0;
    private int attackSpeed = 100;

    private long timeStart;
    private long timeToShoot;
    private Array<Bullet> bullets;

    private Sound fire;

    public Plane(World world, float posx, float posy, float width, float height, BodyDef.BodyType bodyType, B2dAssetManager assMan){
        this.world = world;
        timeStart = System.currentTimeMillis();;
        flyTexture = new Texture("images/player.png");
        flyAnimation = new Animation(new TextureRegion(flyTexture),2,0.1f, true);

        shootTexture = new Texture("images/shooting.png");
        shootAnimation = new Animation(new TextureRegion(shootTexture),5,0.1f, false);

        definePlane(posx,posy,width,height,bodyType);

        bullets = new Array<Bullet>();

        fire = assMan.manager.get("sounds/fire.wav");
    }
    private void definePlane(float posx, float posy, float width, float height, BodyDef.BodyType bodyType){
        BodyDef bdef = new BodyDef();
        bdef.position.set(posx,posy);
        bdef.fixedRotation = true;
        bdef.type = bodyType;

        b2body = world.createBody(bdef);
        b2body.setUserData(this);
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width/2,height/2);
        fixtureDef.shape = poly;
        fixtureDef.density = 1f;
        fixtureDef.friction = 0.3f;
        fixtureDef.restitution = 0.0f;
        b2body.createFixture(fixtureDef);
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

    public void flyDown(){
        b2body.applyLinearImpulse(new Vector2(0,-10f),b2body.getWorldCenter(),true);
    }

    public void shoot(float dt){
        isShooting = true;

        if(canShoot) {
            bullets.add(new Bullet(world, getPosX(), getPosY()-1, 1.5f, 1));
            fire.play();
            timeToShoot = System.currentTimeMillis()+attackSpeed;
        }
    }
    public void stopShooting(){
        isShooting = false;
    }

    public void update(float dt){
        if(System.currentTimeMillis()>timeToShoot){
            canShoot=true;
        }else
            canShoot=false;

        if(isShooting)
            shootAnimation.update(dt);
        else if(isFlying)
            flyAnimation.update(dt);

    }

    public TextureRegion getTexture(){
        if(isShooting)
            return shootAnimation.getFrame();
        else
            return flyAnimation.getFrame();
    }

    public void draw(SpriteBatch sb){
        sb.draw(getTexture(),getPosX()-2.5f,getPosY()-1.5f,5,3);

        for(Bullet bullet : bullets) {
            if (bullet.getPosX() > 20) {
                bullet.destroy();
                bullets.removeValue(bullet, true);
            }
            bullet.draw(sb);
        }
    }

}
