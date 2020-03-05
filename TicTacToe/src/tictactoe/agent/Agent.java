package tictactoe.agent;

import tictactoe.game.Game;
import tictactoe.game.Position;

public abstract class Agent {
	public boolean responded = true;
	public abstract Position getMove(Game game, long timeDue);
}
