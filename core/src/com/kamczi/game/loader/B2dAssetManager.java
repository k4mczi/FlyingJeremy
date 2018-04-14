package com.kamczi.game.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by Kamil on 2018-04-14.
 */

public class B2dAssetManager {
    public final AssetManager manager = new AssetManager();

    // Textures
    public final String playerImage = "images/player.png";
    public final String backgroundImage = "images/background.png";

    // Sounds
    public final String boingSound = "sounds/boing.wav";
    public final String pingSound = "sounds/ping.wav";

    // Music
    public final String playingSong = "music/Rolemusic_-_pl4y1ng.mp3";

    // Skin
    public final String skin = "skin/glassy-ui.json";

    public void queueAddSkin(){
        SkinLoader.SkinParameter params = new SkinLoader.SkinParameter("skin/glassy-ui.atlas");
        manager.load(skin, Skin.class, params);

    }

    public void queueAddMusic(){}

    public void queueAddSounds(){
        manager.load(boingSound, Sound.class);
        manager.load(pingSound, Sound.class);
    }

    public void queueAddImages(){
        manager.load(playerImage, Texture.class);
        manager.load(backgroundImage, Texture.class);
    }

    // a small set of images used by the loading screen
    public void queueAddLoadingImages(){

    }
}
