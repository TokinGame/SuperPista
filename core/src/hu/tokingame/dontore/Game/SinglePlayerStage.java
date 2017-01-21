package hu.tokingame.dontore.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.tokingame.dontore.Bodies.Character;
import hu.tokingame.dontore.Bodies.CrateActor;
import hu.tokingame.dontore.Bodies.GrassActor;
import hu.tokingame.dontore.Bodies.SpikeActor;
import hu.tokingame.dontore.Global.Globals;
import hu.tokingame.dontore.MyBaseClasses.MyLabel;
import hu.tokingame.dontore.MyBaseClasses.MyStage;
import hu.tokingame.dontore.MyBaseClasses.ShapeType;
import hu.tokingame.dontore.MyBaseClasses.WorldBodyEditorLoader;
import hu.tokingame.dontore.MyGdxGame;

/**
 * Created by davimatyi on 2017. 01. 18..
 */

public class SinglePlayerStage extends MyStage {

    World world;
    Box2DDebugRenderer box2DDebugRenderer;
    WorldBodyEditorLoader loader;
    ControlStage controlStage;
    PauseStage pauseStage;

    public Character character;



    public SinglePlayerStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);

    }
    @Override
    public void init() {
        world = new World(new Vector2(0, -5), false);
        box2DDebugRenderer = new Box2DDebugRenderer();
        loader = new WorldBodyEditorLoader(Gdx.files.internal("phys.json"));
        controlStage = new ControlStage(new ExtendViewport(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT,new OrthographicCamera(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT)),new SpriteBatch(),game);

        //pauseStage = new PauseStage(new ExtendViewport(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT,new OrthographicCamera(Globals.WORLD_WIDTH,Globals.WORLD_HEIGHT)),new SpriteBatch(),game);

        character = new Character(world, 1, 1);

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(controlStage);
        //inputMultiplexer.addProcessor(pauseStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        //addActor(new CrateActor(world, loader, 0, 0));
        //addActor(new SpikeActor(world, loader, 2, 0));


        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                character.jump();
            }
        });
        setCameraMoveToXY(1, 4, 0.01f, 10000);

        addActor(character);
        addActor(new GrassActor(world, loader, 0, 0));
        addActor(new GrassActor(world, loader, -8, 0));
        addActor(new GrassActor(world, loader, 8, 0));
        addActor(new CrateActor(world, loader, 3, 1));
        addActor(new SpikeActor(world, loader, 8, 1));

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureA().getUserData() instanceof SpikeActor && contact.getFixtureB().getUserData() instanceof Character ||
                        contact.getFixtureA().getUserData() instanceof Character && contact.getFixtureB().getUserData() instanceof SpikeActor) {
                    System.out.println("collision");
                    character.die();
                    controlStage.addActor(new MyLabel("DED", MyLabel.style1){
                        @Override
                        public void init() {
                            super.init();
                            setPosition(Globals.WORLD_WIDTH/2-this.getWidth()/2, Globals.WORLD_HEIGHT/2-this.getHeight()/2);
                        }
                    });
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
        });
    }

    @Override
    public void dispose() {
        super.dispose();
        controlStage.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        world.step(delta, 1, 1);
        controlStage.act(delta);
        setCameraMoveToXY(character.getX(), 4, 0.01f, 10000);
    }

    @Override
    public void draw() {
        updateFrustum(1.25f);
        super.draw();
        controlStage.draw();
        box2DDebugRenderer.render(world, getCamera().combined);
    }


    @Override
    public void resize(int screenWidth, int screenHeight) {
        super.resize(screenWidth, screenHeight);
        controlStage.resize(screenWidth, screenHeight);
    }
}
