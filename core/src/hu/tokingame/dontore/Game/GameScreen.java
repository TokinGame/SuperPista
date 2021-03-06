package hu.tokingame.dontore.Game;

import hu.tokingame.dontore.MyBaseClasses.MyScreen;
import hu.tokingame.dontore.MyGdxGame;

/**
 * Created by davimatyi on 2017. 01. 18..
 */

public class GameScreen extends MyScreen {

    SingleJumpGameStage singlestage;

    public GameScreen(MyGdxGame game) {
        super(game);
        singlestage = new SingleJumpGameStage(game);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        singlestage.act(delta);
        singlestage.draw();
    }


    @Override
    public void init() {
        super.init();
    }

    @Override
    public void resume() {
        super.resume();
    }

}
