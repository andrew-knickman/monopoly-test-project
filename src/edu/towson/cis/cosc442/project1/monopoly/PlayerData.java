package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

public class PlayerData {
	public Hashtable<String, Integer> colorGroups;
	public boolean inJail;
	public int money;
	public String name;
	public Cell position;
	public ArrayList<PropertyCell> properties;
	public ArrayList<Cell> railroads;
	public ArrayList<Cell> utilities;

	public PlayerData(Hashtable<String, Integer> colorGroups, ArrayList<PropertyCell> properties,
			ArrayList<Cell> railroads, ArrayList<Cell> utilities) {
		this.colorGroups = colorGroups;
		this.properties = properties;
		this.railroads = railroads;
		this.utilities = utilities;
	}
}