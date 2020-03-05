package connectfour.agent;

import connectfour.game.Game;
import connectfour.game.GamePiece;
import connectfour.game.Position;

public class Human extends Agent{
	public int xClick,yClick;
	public Human()
	{
		responded = false;
	}

	@Override
	public Position getMove(Game game, long timeDue) {
		Position p = new Position(xClick,yClick,new GamePiece("",game.getTurn()));
		xClick = -1;
		yClick = -1;
		responded = false;
		return p;
	}

}
