package hu.tokingame.dontore.HighScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Collections;
import java.util.Vector;

import hu.tokingame.dontore.Global.Globals;
import hu.tokingame.dontore.MenuScreen.BGStage;
import hu.tokingame.dontore.MyBaseClasses.BackgroundTextButton;
import hu.tokingame.dontore.MyBaseClasses.MyLabel;
import hu.tokingame.dontore.MyGdxGame;

import static hu.tokingame.dontore.Global.Globals.MaxScores;

/**
 * Created by Zoli szereti ha Windows XP de akkor is ha rondán néz ki és mindenki szerint olyan ocsmány hogy pfhuj on 1889.05.68..
 */

public class HighStage extends BGStage {
    String s;

    Vector<MyLabel> hsV;

    public HighStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
            game.setScreenBackByStackPop();
        }
        return false;
    }


    @Override
    public void init() {
        super.init();

        addActor(new BackgroundTextButton("Back", 2) {
            @Override
            protected void init() {
                super.init();
                this.setPosition(getViewport().getWorldWidth() - this.getWidth()-10, 10);
                addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreenBackByStackPop();
                    }
                });
            }
        });

        addActor(new MyLabel("High Scores", MyLabel.style2) {
            @Override
            public void init() {
                super.init();
                setPosition(Globals.WORLD_WIDTH / 2 - this.getWidth() / 2, 600);
            }
        });

        if (Globals.MaxScores.size() > 0) {
            hsV = new Vector();
            for (int i = 0; i < Globals.MaxScores.size(); i++) {
                float k = Globals.MaxScores.get(i);
                float b = Math.round(k * 100) / 100.0f;
                final int finalI = i;
                hsV.add(new MyLabel(i + 1 + ". " + b + "", MyLabel.style2) {
                    @Override
                    public void init() {
                        super.init();
                        setPosition(Globals.WORLD_WIDTH / 2 - this.getWidth() / 2, finalI > 0 ? hsV.get(finalI - 1).getY() - 75 : 500);
                    }
                });
                addActor(hsV.get(i));
            } // Egy megérett a meggy. Kettő megérett a meggy. Három megérett a meggy...
            hsV.clear();
        } else {
            addActor(new MyLabel("There are no scores yet.", MyLabel.style2) {
                @Override
                public void init() {
                    super.init();
                    setPosition(Globals.WORLD_WIDTH / 2 - this.getWidth() / 2, 500);
                }
            });
        }


    }

    public static void highscore(float score) {
        if (MaxScores.size() >= 5) {
            if (MaxScores.get(MaxScores.size() - 1) < score) {
                Collections.sort(MaxScores);
                Collections.reverse(MaxScores);
                MaxScores.set(MaxScores.size() - 1, score);
            }
        } else {
            MaxScores.add(score);
            Collections.sort(MaxScores);
            Collections.reverse(MaxScores);
        }
        Collections.sort(MaxScores);
        Collections.reverse(MaxScores);
        System.out.println(MaxScores);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
