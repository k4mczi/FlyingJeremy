package com.kamczi.game.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kamczi.game.BodyFactory;

/**
 * Created by Kamil on 2018-04-14.
 */

public class Plane {
    private World world;
    public Plane(World world){
        this.world = world;
    }


}
