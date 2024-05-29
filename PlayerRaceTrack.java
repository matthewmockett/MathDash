/**
 * The PlayerRaceTrack class represents a race track where a player can move forward.
 */
public class PlayerRaceTrack{
	private int playerPosition;
	private int trackLength;
	private Player player;
	private String section;
	private SaveGameManager saveFile;
    /**
     * Constructs a new PlayerRaceTrack object with the specified track length.
     *
     * @param trackLength The length of the race track.
     */
	public PlayerRaceTrack(int trackLength, Player player, String section){
		this.playerPosition = 0;
		this.trackLength = trackLength;
		this.player = player;
		this.section = section;
		saveFile = new SaveGameManager("players.csv");
	}

    /**
     * Moves the player forward by the specified distance.
     *
     * @param distance The distance by which the player should move forward.
     */
	public void movePlayer(int distance){
		playerPosition += distance;
	}

    /**
     * Retrieves the current position of the player on the race track.
     *
     * @return The position of the player.
     */
	public int getPlayerPosition(){
		return playerPosition;
	}

    /**
     * Retrieves the length of the race track.
     *
     * @return The length of the race track.
     */
	public int getTrackLength(){
		return trackLength;
	}
	
	public void addPoints() {
		player.setTotalPlayerPoints(player.getTotalPlayerPoints()+10, saveFile);
	}
	
	public void addTotalCorrectAnswer() {
		player.setTotalIncorrectAnswers(player.getTotalIncorrectAnswers()+1, saveFile);
	}
	
	public void addTotalIncorrectAnswer() {
		player.setTotalCorrectAnswers(player.getTotalCorrectAnswers()+1, saveFile);
	}
	
	
	public boolean playerWon() {//also handle level update
		if (playerPosition == trackLength){
			SaveGameManager saveFile = new SaveGameManager("players.csv");
			switch(section) {
			  case "Addition":
			    player.setAdditionLevel(player.getAdditionLevel()+1, saveFile);
			    break;
			  case "Subtraction":
				  player.setSubtractionLevel(player.getSubtractionLevel()+1, saveFile);
				  break;
			  case "Multiplication":
				  player.setMultiplicationLevel(player.getMultiplicationLevel()+1, saveFile);
				  break;
			  case "Division":
				  player.setDivisionLevel(player.getDivisionLevel()+1, saveFile);
				  break;
			}
			return true;
		}
		else {
			return false;
		}
	}
}