package com.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

/**
 * Created by jim on 4/25/16.
 */
public class Agent extends Actor {

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("sky_main.jpg")));
    public Agent() {
        this.setBounds(sprite.getX() + 100, sprite.getY(), sprite.getWidth(), sprite.getHeight());
        this.setColor(0, 0.8f, 0, 1);

        this.setTouchable(Touchable.enabled);
        this.addListener(new TouchListener());
    }

    public class TouchListener extends InputListener {
        @Override
        public boolean touchDown(InputEvent e, float x, float y, int pointer, int button) {
            Agent.this.moveByXY(10f, 10f);
            System.out.println("foo");
            return true;
        }
    }

    @Override
    public boolean fire(Event event) {
        event.setListenerActor(this);
        return true;
    }

    private void moveByXY(float x, float y) {
        MoveByAction mba = new MoveByAction();
        mba.setAmount(x, y);
        mba.setDuration(5f);
        this.addAction(mba);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       sprite.draw(batch, parentAlpha);
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
        super.positionChanged();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
