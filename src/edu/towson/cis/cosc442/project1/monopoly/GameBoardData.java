package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameBoardData {
	public ArrayList<Cell> cells;
	public ArrayList<Card> chanceCards;
	public Hashtable<String, Integer> colorGroups;
	public ArrayList<Card> communityChestCards;

	public GameBoardData(ArrayList<Cell> cells, ArrayList<Card> chanceCards, Hashtable<String, Integer> colorGroups,
			ArrayList<Card> communityChestCards) {
		this.cells = cells;
		this.chanceCards = chanceCards;
		this.colorGroups = colorGroups;
		this.communityChestCards = communityChestCards;
	}
}