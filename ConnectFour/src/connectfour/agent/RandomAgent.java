package connectfour.agent;

import java.util.List;
import java.util.Random;

import connectfour.game.Game;
import connectfour.game.Position;

public class RandomAgent extends Agent {


	@Override
	public Position getMove(Game game, long timeDue) {
		List<Position> validMoves = game.getValidMoves(game.getBoard(), game.getTurn());
		Random r = new Random();
		int chosen = r.nextInt(validMoves.size());
		return validMoves.get(chosen);
	}

}
