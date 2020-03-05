package tictactoe.game;

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
	private int width = 300,height = 300,bufferH = 10, bufferW = 10;
	private int pieceXWidth = 90, pieceYWidth = 90;
	private int h1Y=125,h2Y=225,v1X,v2X;
	
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
    	//left vertical
    	bufferGraphics.drawLine(width/3, bufferH, width/3, height - bufferH);
    	v1X = width/3;
    	//right vertical
    	bufferGraphics.drawLine((width/3)*2, bufferH, (width/3)*2, height - bufferH);
    	v2X = (width/3)*2;
    	//top horizontal
    	bufferGraphics.drawLine(bufferW, height/3, width-bufferW, height/3);
    	//h1Y = height/3;
    	//bot horizontal
    	bufferGraphics.drawLine(bufferW, (height/3)*2, width-bufferW, (height/3)*2);
    	//h2Y = (height/3)*2;
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
					drawX(x,y);
				}
				if (player == -1)
				{
					drawO(x,y);
				}
			}
		}
	}
	
	private void drawX(int col, int row)
	{
		int xOffset,yOffset;
		xOffset = (width/3) * col;
		yOffset = (height/3) * row;
		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.drawLine(bufferW+xOffset, bufferH+yOffset, pieceXWidth+xOffset, pieceYWidth+yOffset);
		bufferGraphics.drawLine(bufferW+xOffset, pieceYWidth+yOffset, pieceXWidth+xOffset, bufferH+yOffset);
	}
	
	private void drawO(int col, int row)
	{
		int xOffset,yOffset;
		xOffset = (width/3) * col;
		yOffset = (height/3) * row;
		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.drawArc(bufferW+xOffset, bufferH+yOffset, pieceXWidth-10, pieceYWidth-10, 0, 360);
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
