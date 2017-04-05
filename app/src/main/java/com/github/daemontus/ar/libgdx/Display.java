package com.github.daemontus.ar.libgdx;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.github.daemontus.ar.vuforia.VuforiaRenderer;

/**
 * Screen implementation responsible for model loading and calling renderer properly.
 */
public class Display implements Screen {

    private SpriteBatchRenderer mRenderer;

    public Display(VuforiaRenderer vuforiaRenderer) {

        mRenderer = new SpriteBatchRenderer(vuforiaRenderer);


    }

    @Override
    public void render(float delta) {
        mRenderer.render(this, delta);
    }

    @Override
    public void dispose() {
        mRenderer.dispose();
    }


    @Override
    public void resize(int i, int i2) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }
}
