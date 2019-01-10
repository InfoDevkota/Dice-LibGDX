package np.info.sagardevkota.dice;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    ShapeRenderer renderer;
    FitViewport viewport;
    TheDice dice;

    @Override
    public void show() {
        renderer = new ShapeRenderer();
        viewport = new FitViewport(210,210);

        dice = new TheDice(viewport);
        Gdx.input.setInputProcessor(dice);
    }

    @Override
    public void render(float delta) {
        viewport.apply();
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.setAutoShapeType(true);
        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.setColor(0,0,0,1);
        renderer.rect(5,5,200,200);
        dice.update(delta);

        dice.render(renderer);
        renderer.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }
}
