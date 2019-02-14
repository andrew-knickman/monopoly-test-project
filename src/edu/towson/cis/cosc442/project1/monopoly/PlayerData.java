package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerData.
 */
public class PlayerData {
	
	/** The color groups. */
	public Hashtable<String, Integer> colorGroups;
	
	/** The in jail. */
	public boolean inJail;
	
	/** The money. */
	public int money;
	
	/** The name. */
	public String name;
	
	/** The position. */
	public Cell position;
	
	/** The properties. */
	public ArrayList<PropertyCell> properties;
	
	/** The railroads. */
	public ArrayList<Cell> railroads;
	
	/** The utilities. */
	public ArrayList<Cell> utilities;

	/**
	 * Instantiates a new player data.
	 *
	 * @param colorGroups the color groups
	 * @param properties the properties
	 * @param railroads the railroads
	 * @param utilities the utilities
	 */
	public PlayerData(Hashtable<String, Integer> colorGroups, ArrayList<PropertyCell> properties,
			ArrayList<Cell> railroads, ArrayList<Cell> utilities) {
		this.colorGroups = colorGroups;
		this.properties = properties;
		this.railroads = railroads;
		this.utilities = utilities;
	}
}