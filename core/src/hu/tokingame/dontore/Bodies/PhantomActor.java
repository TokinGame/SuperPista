package hu.tokingame.dontore.Bodies;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.tokingame.dontore.Global.Assets;
import hu.tokingame.dontore.MyBaseClasses.OneSpriteStaticActor;
import hu.tokingame.dontore.MyBaseClasses.WorldActorGroup;
import hu.tokingame.dontore.MyBaseClasses.WorldBodyEditorLoader;

/**
 * Created by davimatyi on 2017. 01. 21..
 */

public class PhantomActor extends WorldActorGroup {


    OneSpriteStaticActor actor;
    public int maxSpeed = 5;
    float currentSpeed = 0;

    public PhantomActor(World world, WorldBodyEditorLoader loader, float x, float y) {
        super(world, loader, "bg.png", BodyDef.BodyType.KinematicBody, 0, 0.1f, 0, false);
        setSize(1, 1);
        actor = new OneSpriteStaticActor(Assets.manager.get(Assets.NEM));
        actor.setSize(1, 1);
        addActor(actor);
        addToWorld();
        setPosition(x, y);
        getBody().setLinearVelocity(currentSpeed, 0);
    }

    @Override
    public void init() {
        super.init();

    }

    public void setSpeed(float speed) {
        getBody().setLinearVelocity(speed, 0);
        currentSpeed = speed;
    }

    public float getSpeed() {
        return currentSpeed;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
