package np.info.sagardevkota.dice;

import com.badlogic.gdx.Game;

public class Dice extends Game {
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}
}
