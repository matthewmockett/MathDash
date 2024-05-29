/**
 * The CpuRaceTrack class represents a race track where a CPU opponent can move forward.
 * author Mirna
 */
public class CpuRaceTrack{
	private int	cpuPosition;
	private int trackLength;

    /**
     * Constructs a new CpuRaceTrack object with the specified track length.
     *
     * @param trackLength The length of the race track.
     */
	public CpuRaceTrack(int trackLength){
		this.cpuPosition = 0;
		this.trackLength = trackLength;
	}

    /**
     * Moves the CPU opponent forward by the specified distance.
     *
     * @param distance The distance by which the CPU should move forward.
     */
	public void moveCpu(int distance){
		cpuPosition += distance;
	}

    /**
     * Retrieves the current position of the CPU on the race track.
     *
     * @return The position of the CPU.
     */
	public int getCpuPosition(){
		return cpuPosition;
	}

	/**
     * Retrieves the length of the race track.
     *
     * @return The length of the race track.
     */
	public int getTrackLength(){
		return trackLength;
	}
	
  /**
	 * Checks if the CPU has won the game by reaching the end of the track.
	 * This method assesses the CPU's position relative to the track length.
	 * If the CPU has reached the end of the track, it prints a message indicating
	 * that the CPU has won and returns true. Otherwise, it returns false.
	 *
	 * @return true if the CPU has won (reached the end of the track); false otherwise.
	 */
	public boolean CpuWon() {//also handle level update
		if (cpuPosition == trackLength){
			return true;
		}
		else {
			return false;
		}
	}

}
