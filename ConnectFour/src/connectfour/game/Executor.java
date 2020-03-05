package connectfour.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import connectfour.agent.Agent;
import connectfour.agent.Human;
import connectfour.agent.RandomAgent;


public class Executor {
	private long runTime = 10000;
	private long timeBuffer = 1000;
	
	public static void main(String[] args) {
		Executor exec = new Executor();
		exec.runGame(new Human(), new RandomAgent());

	}
	
	private void runGame(Agent agent1, Agent agent2)
	{
		Game game = new Game();
		/*for (Position p : game.getValidMoves(game.getBoard(), game.getTurn()))
		{
			System.out.println(p.getPiece().getOwner() + ": " + p.getX() + "," + p.getY());
		}*/
		GameView gv = new GameView(game).showGame();
		Agent h = null;
		if (agent1 instanceof Human)
			h = agent1;
		else if (agent2 instanceof Human)
			h = agent2;
		final Agent humanAgent = h;
		gv.addMouseListener(new MouseAdapter() {

	        public void mouseClicked(MouseEvent e) {
	        	int mouseX, mouseY;
	            mouseX=e.getX();
	            mouseY=e.getY();
	            //System.out.println(mouseX+","+mouseY);
	            if (game.getTurn() == 1)
	            	handleMouseClick(mouseX, mouseY, humanAgent);
	        }

		});
		
		long start = System.currentTimeMillis();
		
		while (!game.gameOver())
		{
			if (game.getTurn() == 1 && agent1.responded)
			{
				game.advanceGame(agent1.getMove(game, runTime));
				if (start + runTime + timeBuffer < System.currentTimeMillis() && !(agent1 instanceof Human))
				{
					System.out.println("Agent 1 took too long to respond");
				}
				start = System.currentTimeMillis();
			}
			else if (game.getTurn() == -1 && agent2.responded)
			{
				game.advanceGame(agent2.getMove(game, runTime));
				if (start + runTime + timeBuffer < System.currentTimeMillis() && !(agent2 instanceof Human))
				{
					System.out.println("Agent 2 took too long to respond");
				}
				start = System.currentTimeMillis();
			}
			gv.repaint();
		}
		gv.repaint();
	}
	
	private void handleMouseClick(int x, int y, Agent human)
	{
		int xCell, yCell;
		xCell = x/100;
		yCell = (y-25)/100;
		//System.out.println(xCell + "::" + yCell);
		Human h = (Human) human;
		h.xClick = xCell;
		h.yClick = yCell;
		h.responded = true;
	}

}
