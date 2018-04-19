package com.kamczi.game.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Kamil on 2018-04-18.
 */

public class Animation {
    Array<TextureRegion> frames;
    float maxFrameTime;
    float currentFrameTime;
    int frameCount;
    int frame;
    boolean looping;
    float cycleTime;

    public Animation(TextureRegion region, int frameCount, float cycleTime, boolean isLopping){
        frames = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
        looping = isLopping;
        this.cycleTime = cycleTime;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;

    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }

    public float getCycleTime(){
        return cycleTime;
    }
}
