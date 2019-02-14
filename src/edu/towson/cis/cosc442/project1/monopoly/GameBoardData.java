package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

// TODO: Auto-generated Javadoc
/**
 * The Class GameBoardData.
 */
public class GameBoardData {
	
	/** The cells. */
	public ArrayList<Cell> cells;
	
	/** The chance cards. */
	public ArrayList<Card> chanceCards;
	
	/** The color groups. */
	public Hashtable<String, Integer> colorGroups;
	
	/** The community chest cards. */
	public ArrayList<Card> communityChestCards;

	/**
	 * Instantiates a new game board data.
	 *
	 * @param cells the cells
	 * @param chanceCards the chance cards
	 * @param colorGroups the color groups
	 * @param communityChestCards the community chest cards
	 */
	public GameBoardData(ArrayList<Cell> cells, ArrayList<Card> chanceCards, Hashtable<String, Integer> colorGroups,
			ArrayList<Card> communityChestCards) {
		this.cells = cells;
		this.chanceCards = chanceCards;
		this.colorGroups = colorGroups;
		this.communityChestCards = communityChestCards;
	}
}