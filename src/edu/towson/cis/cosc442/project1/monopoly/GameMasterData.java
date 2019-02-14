package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class GameMasterData.
 */
public class GameMasterData {
	
	/** The dice. */
	public Die[] dice;
	
	/** The game board. */
	public GameBoard gameBoard;
	
	/** The gui. */
	public MonopolyGUI gui;
	
	/** The init amount of money. */
	public int initAmountOfMoney;
	
	/** The players. */
	public ArrayList<Player> players;
	
	/** The turn. */
	public int turn;
	
	/** The util dice roll. */
	public int utilDiceRoll;
	
	/** The test mode. */
	public boolean testMode;

	/**
	 * Instantiates a new game master data.
	 *
	 * @param players the players
	 * @param turn the turn
	 */
	public GameMasterData(ArrayList<Player> players, int turn) {
		this.players = players;
		this.turn = turn;
	}
}