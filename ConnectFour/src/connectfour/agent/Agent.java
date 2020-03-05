package connectfour.agent;

import connectfour.game.Game;
import connectfour.game.Position;

public abstract class Agent {
	public boolean responded = true;
	public abstract Position getMove(Game game, long timeDue);
}
