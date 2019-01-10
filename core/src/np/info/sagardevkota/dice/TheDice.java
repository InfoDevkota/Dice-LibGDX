package np.info.sagardevkota.dice;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

public class TheDice extends InputAdapter {

    private static final float MAX_SPEED = 400.0f;
    private static final float SIZE = 30;

    private Vector2 position;
    private Vector2 velocity;
    private Viewport viewport;

    private int value;
    private Random random;
    private Color color;

    private boolean touched;
    private Vector2 dragStartPosition;


    public TheDice(Viewport viewport){
        this.viewport = viewport;
        init();
    }

    private void init(){
        position = new Vector2(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2);
        velocity = new Vector2();
        value = 1;
        color = new Color(1,0,0,1);
        random = new Random();
    }

    public void roll(){
        value = random.nextInt(6) + 1;
    }

    public int getValue(){
        return value;
    }

    public void update(float delta){
        velocity.clamp(0, MAX_SPEED);

        velocity.x -= delta * 1 * velocity.x;
        velocity.y -= delta * 1 * velocity.y;

        position.x += delta * velocity.x;
        position.y += delta * velocity.y;

        collideWithWalls(viewport.getWorldWidth(), viewport.getWorldHeight());
    }

    private void collideWithWalls(float viewportWidth, float viewportHeight){
        if(position.x < 0){
            velocity.x = -velocity.x;
        }
        if(position.x + SIZE > viewportWidth){
            position.x = viewportWidth - SIZE;
            velocity.x = -velocity.x;
        }
        if(position.y < 0){
            velocity.y = -velocity.y;
        }
        if(position.y + SIZE > viewportHeight){
            position.y = viewportHeight - SIZE;
            velocity.y = -velocity.y;
        }
    }

    public void render(ShapeRenderer renderer){
        renderer.setColor(color);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(position.x,position.y,30,30);
        renderer.setColor(0,0,0,1);
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.rect(position.x, position.y,30,30);

        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(1,1,1,1);
        if(value == 1){
            renderer.circle(position.x + 15, position.y + 15,4);
        }
        if(value == 2){
            renderer.circle(position.x + 8, position.y + 5,4);
            renderer.circle(position.x + 22, position.y + 25,4);
        }
        if(value == 3){
            renderer.circle(position.x + 8, position.y + 5,4);
            renderer.circle(position.x + 15, position.y + 15,4);
            renderer.circle(position.x + 22, position.y + 25,4);

        }
        if(value == 4){
            renderer.circle(position.x + 8, position.y + 5,4);
            renderer.circle(position.x + 8, position.y + 25,4);
            renderer.circle(position.x + 22, position.y + 5,4);
            renderer.circle(position.x + 22, position.y + 25,4);

        }
        if(value == 5){
            renderer.circle(position.x + 8, position.y + 5,4);
            renderer.circle(position.x + 8, position.y + 25,4);
            renderer.circle(position.x + 15, position.y + 15,4);
            renderer.circle(position.x + 22, position.y + 5,4);
            renderer.circle(position.x + 22, position.y + 25,4);

        }
        if(value == 6){
            renderer.circle(position.x + 8, position.y + 5,4);
            renderer.circle(position.x + 8, position.y + 15,4);
            renderer.circle(position.x + 8, position.y + 25,4);
            renderer.circle(position.x + 22, position.y + 5,4);
            renderer.circle(position.x + 22, position.y + 15,4);
            renderer.circle(position.x + 22, position.y + 25,4);
        }

        renderer.setColor(0,0,0,1);
        renderer.set(ShapeRenderer.ShapeType.Line);
        renderer.rect(position.x,position.y,30,30);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button){
        Vector2 worldTouch = viewport.unproject(new Vector2(screenX, screenY));

        if(worldTouch.x > position.x && worldTouch.x < position.x + SIZE){
            if(worldTouch.y > position.y && worldTouch.y < position.y + SIZE){
                touched = true;
                dragStartPosition = worldTouch;

                //TODO HELP Start dice rolling Animation
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (touched) {
            touched = false;
            Vector2 dragEnd = viewport.unproject(new Vector2(screenX, screenY));

            velocity.x += 3 * (dragEnd.x - dragStartPosition.x);
            velocity.y += 3 * (dragEnd.y - dragStartPosition.y);
            roll();

            //TODO HELP Stop dice rolling Animation
        }
        return true;
    }

}
