package hu.tokingame.dontore.Game;

import hu.tokingame.dontore.MyGdxGame;

/**
 * Created by tuskeb on 2017. 01. 30..
 */

public class SingleJumpGameStage extends JumpGameStage {
    //   AdderStage adderStage;

    public SingleJumpGameStage(MyGdxGame game) {
        super(game);
//        adderStage = new AdderStage(game, this);
//        inputMultiplexer.addProcessor(0,adderStage);
    }


    void generateMap() {
        //int ref = (int)grassV.get(2).getX();
        float ref = phantomActor.getX() + 8;
        int nr = rdm(1, 2);
        switch (nr) {
            case 1: {
                if (rdm(1, 5) != 1) {
                    switch (rdm(1, 3)) {
                        case 1:
                            addCrate(ref + 4, 1);
                            break;
                        case 2:
                            addCrate(ref + 4, 1);
                            addCrate(ref + 4, 2);
                            break;
                        case 3:
                            addCrate(ref + 4, 1);
                            addCrate(ref + 4, 2);
                            addCrate(ref + 4, 3);
                            break;
                    }
                } else addSpike(ref + 4, 1);
                break;
            }
            case 2: {
                if (rdm(1, 5) != 1) {
                    switch (rdm(1, 3)) {
                        case 1:
                            addCrate(ref + 2, 1);
                            break;
                        case 2:
                            addCrate(ref + 2, 1);
                            addCrate(ref + 2, 2);
                            break;
                        case 3:
                            addCrate(ref + 2, 1);
                            addCrate(ref + 2, 2);
                            addCrate(ref + 2, 3);
                            break;
                    }
                    switch (rdm(1, 3)) {
                        case 1:
                            addCrate(ref + 5, 1);
                            break;
                        case 2:
                            addCrate(ref + 5, 1);
                            addCrate(ref + 5, 2);
                            break;
                        case 3:
                            addCrate(ref + 5, 1);
                            addCrate(ref + 5, 2);
                            addCrate(ref + 5, 3);
                            break;
                    }
                } else {
                    addSpike(ref + 2, 1);
                    addSpike(ref + 5, 1);
                }
                break;
            }
        }
    }

    float generateInterval = 2;
    float lastGenerated = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        //     adderStage.act(delta);
        //world.step(delta, 1, 1);
        elapsedtime += delta;
        if (character.alive) {
            if (lastGenerated + generateInterval < elapsedtime) {
                lastGenerated = elapsedtime;
                generateMap();
            }
        }

    }

    @Override
    public void draw() {
        super.draw();
        //      adderStage.draw();
    }

    @Override
    public void dispose() {
        //    adderStage.dispose();
        super.dispose();
    }

    public void generate() {
        generateMap();
    }
}
