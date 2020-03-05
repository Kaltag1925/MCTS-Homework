package connectfour.game;

public class GamePiece {
	private String pieceName;
	private int owner;
	
	public GamePiece(String pieceName, int owner)
	{
		this.pieceName = pieceName;
		this.owner = owner;
	}
	
	@SuppressWarnings("unused")
	private GamePiece()
	{
		
	}
	
	public String getPieceName()
	{
		return this.pieceName;
	}
	
	public int getOwner()
	{
		return this.owner;
	}
	
	protected void setOwner(int owner)
	{
		this.owner = owner;
	}
	
	protected void setPieceName(String pieceName)
	{
		this.pieceName = pieceName;
	}
}
