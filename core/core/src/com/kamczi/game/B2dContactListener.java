package com.kamczi.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.kamczi.game.entity.Coin;
import com.kamczi.game.entity.Plane;
import com.kamczi.game.hud.Hud;

/**
 * Created by Kamil on 2018-04-14.
 */

public class B2dContactListener implements ContactListener {
    private Box2DModel parent;



    public B2dContactListener(Box2DModel parent){
        this.parent = parent;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        Body a = fa.getBody();
        Body b = fb.getBody();

        if(fa.getBody().getUserData() instanceof Coin && fb.getBody().getUserData() instanceof Plane){
            Hud.addCoins(1);
            ((Coin) fa.getBody().getUserData()).isTouched = true;
            parent.playSound(1);
        }else if(fa.getBody().getUserData() instanceof Plane && fb.getBody().getUserData() instanceof Coin){
            Hud.addCoins(1);
            ((Coin) fb.getBody().getUserData()).isTouched = true;
            parent.playSound(1);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }
}
