package connectfour.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class GameView extends JComponent {
	private Game game;
	private GameFrame frame;
	private Graphics bufferGraphics;
	private int width = 700,height = 600,bufferH = 10, bufferW = 10;
	private int pieceXWidth = 90, pieceYWidth = 90;
	private int h1Y=125,h2Y=225,v1X,v2X;
	private int titleBarPixels = 25;
	
	public GameView(Game game)
	{
		this.game = game;
	}
	
	public GameView showGame()
	{
		if (frame == null)
			this.frame = new GameFrame(this);
		return this;
	}
	
	public void paintComponent(Graphics g) 
    {
    	bufferGraphics = g;
    	
        drawBoard();
        drawPieces();
    }
	
	private void drawBoard()
	{
		bufferGraphics.setColor(Color.BLACK);
    	bufferGraphics.fillRect(0,0,width,height);
    	bufferGraphics.setColor(Color.WHITE);
    	// vertical lines
    	for (int i = 1; i < 7; i++)
    	{
    		bufferGraphics.drawLine((width/7)*i, bufferH, (width/7)*i, height - bufferH);
    	}
    	// horizontal lines
    	for (int i = 1; i < 6; i++)
    	{
    		bufferGraphics.drawLine(bufferW, (height/6)*i, width-bufferW, (height/6)*i);
    	}
	}
	
	private void drawPieces()
	{
		List<Position> board = game.getBoard();
		for (int x = 0; x < game.getBoardWidth(); x++)
		{
			for (int y = 0; y < game.getBoardHeight(); y++)
			{
				int player = board.get((x*game.getBoardHeight())+y).getPiece().getOwner();
				if (player == 1)
				{
					drawRed(x,y);
				}
				if (player == -1)
				{
					drawBlue(x,y);
				}
			}
		}
	}
	
	private void drawRed(int col, int row)
	{
		int xOffset,yOffset;
		xOffset = (width/7) * col;
		yOffset = (height/6) * row;
		bufferGraphics.setColor(Color.RED);
		bufferGraphics.fillArc(bufferW+xOffset, bufferH+yOffset, pieceXWidth-10, pieceYWidth-10, 0, 360);
	}
	
	private void drawBlue(int col, int row)
	{
		int xOffset,yOffset;
		xOffset = (width/7) * col;
		yOffset = (height/6) * row;
		bufferGraphics.setColor(Color.BLUE);
		bufferGraphics.fillArc(bufferW+xOffset, bufferH+yOffset, pieceXWidth-10, pieceYWidth-10, 0, 360);
	}
	
	/**
     * The Class GameFrame.
     */
    @SuppressWarnings("serial")
	public class GameFrame extends JFrame
    {        
        /**
         * Instantiates a new game frame.
         *
         * @param comp the comp
         */
        public GameFrame(JComponent comp)
        {
            getContentPane().add(BorderLayout.CENTER,comp);
            getContentPane().setPreferredSize(new Dimension(width,height));
            pack();
            Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation((int)(screen.getWidth()*3/8),(int)(screen.getHeight()*3/8));            
            this.setVisible(true);
            this.setResizable(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            repaint();            
        }
    }
}
