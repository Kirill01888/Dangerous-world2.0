package com.mygdx.fantastickworld.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera;
    protected Vector3 vector3;
    protected GameStateManager gsm;

    public State( GameStateManager gsm) {
        camera = new OrthographicCamera();
        this.vector3 = new Vector3();
        this.gsm = gsm;
    }

    protected State() {
    }

    protected abstract void handleInput();
    public abstract void update();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();
}
