package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;

public class GameMasterData {
	public Die[] dice;
	public GameBoard gameBoard;
	public MonopolyGUI gui;
	public int initAmountOfMoney;
	public ArrayList<Player> players;
	public int turn;
	public int utilDiceRoll;
	public boolean testMode;

	public GameMasterData(ArrayList<Player> players, int turn) {
		this.players = players;
		this.turn = turn;
	}
}