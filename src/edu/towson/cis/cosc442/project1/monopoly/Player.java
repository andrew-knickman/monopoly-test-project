package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;


// TODO: Auto-generated Javadoc
/**
 * The Class Player.
 */
public class Player {
	
	/** The data. */
	private PlayerData data = new PlayerData(new Hashtable<String, Integer>(), new ArrayList<PropertyCell>(), new ArrayList<Cell>(), new ArrayList<Cell>());

	/**
	 * Instantiates a new player.
	 */
	public Player() {
		GameBoard gb = GameMaster.instance().getGameBoard();
		data.inJail = false;
		if(gb != null) {
			data.position = gb.queryCell("Go");
		}
	}

    /**
     * Buy property.
     *
     * @param property the property
     * @param amount the amount
     */
    public void buyProperty(Cell property, int amount) {
        property.setTheOwner(this);
        if(property instanceof PropertyCell) {
            PropertyCell cell = (PropertyCell)property;
            data.properties.add(cell);
            data.colorGroups.put(
                    cell.getColorGroup(), 
                    new Integer(getPropertyNumberForColor(cell.getColorGroup())+1));
        }
        if(property instanceof RailRoadCell) {
            data.railroads.add(property);
            data.colorGroups.put(
                    RailRoadCell.COLOR_GROUP, 
                    new Integer(getPropertyNumberForColor(RailRoadCell.COLOR_GROUP)+1));
        }
        if(property instanceof UtilityCell) {
            data.utilities.add(property);
            data.colorGroups.put(
                    UtilityCell.COLOR_GROUP, 
                    new Integer(getPropertyNumberForColor(UtilityCell.COLOR_GROUP)+1));
        }
        setMoney(getMoney() - amount);
    }
	
	/**
	 * Can buy house.
	 *
	 * @return true, if successful
	 */
	public boolean canBuyHouse() {
		return (getMonopolies().length != 0);
	}

	/**
	 * Check property.
	 *
	 * @param property the property
	 * @return true, if successful
	 */
	public boolean checkProperty(String property) {
		for(int i=0;i<data.properties.size();i++) {
			Cell cell = (Cell)data.properties.get(i);
			if(cell.getName().equals(property)) {
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * Exchange property.
	 *
	 * @param player the player
	 */
	public void exchangeProperty(Player player) {
		for(int i = 0; i < getPropertyNumber(); i++ ) {
			PropertyCell cell = getProperty(i);
			cell.setTheOwner(player);
			if(player == null) {
				cell.setAvailable(true);
				cell.setNumHouses(0);
			}
			else {
				player.data.properties.add(cell);
				data.colorGroups.put(
						cell.getColorGroup(), 
						new Integer(getPropertyNumberForColor(cell.getColorGroup())+1));
			}
		}
		data.properties.clear();
	}
    
    /**
     * Gets the all properties.
     *
     * @return the all properties
     */
    public IOwnable[] getAllProperties() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        list.addAll(data.properties);
        list.addAll(data.utilities);
        list.addAll(data.railroads);
        return (IOwnable[])list.toArray(new Cell[list.size()]);
    }

	/**
	 * Gets the money.
	 *
	 * @return the money
	 */
	public int getMoney() {
		return this.data.money;
	}
	
	/**
	 * Gets the monopolies.
	 *
	 * @return the monopolies
	 */
	public String[] getMonopolies() {
		ArrayList<String> monopolies = new ArrayList<String>();
		Enumeration<String> colors = data.colorGroups.keys();
		while(colors.hasMoreElements()) {
			String color = (String)colors.nextElement();
            if(!(color.equals(RailRoadCell.COLOR_GROUP)) && !(color.equals(UtilityCell.COLOR_GROUP))) {
    			Integer num = (Integer)data.colorGroups.get(color);
    			GameBoard gameBoard = GameMaster.instance().getGameBoard();
    			if(num.intValue() == gameBoard.getPropertyNumberForColor(color)) {
    				monopolies.add(color);
    			}
            }
		}
		return (String[])monopolies.toArray(new String[monopolies.size()]);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return data.name;
	}

	/**
	 * Gets the out of jail.
	 *
	 * @return the out of jail
	 */
	public void getOutOfJail() {
		data.money -= JailCell.BAIL;
		if(isBankrupt()) {
			data.money = 0;
			exchangeProperty(null);
		}
		data.inJail = false;
		GameMaster.instance().updateGUI();
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Cell getPosition() {
		return this.data.position;
	}
	
	/**
	 * Gets the property.
	 *
	 * @param index the index
	 * @return the property
	 */
	public PropertyCell getProperty(int index) {
		return (PropertyCell)data.properties.get(index);
	}
	
	/**
	 * Gets the property number.
	 *
	 * @return the property number
	 */
	public int getPropertyNumber() {
		return data.properties.size();
	}

	/**
	 * Gets the property number for color.
	 *
	 * @param name the name
	 * @return the property number for color
	 */
	private int getPropertyNumberForColor(String name) {
		Integer number = (Integer)data.colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Checks if is bankrupt.
	 *
	 * @return true, if is bankrupt
	 */
	public boolean isBankrupt() {
		return data.money <= 0;
	}

	/**
	 * Checks if is in jail.
	 *
	 * @return true, if is in jail
	 */
	public boolean isInJail() {
		return data.inJail;
	}

	/**
	 * Number of RR.
	 *
	 * @return the int
	 */
	public int numberOfRR() {
		return getPropertyNumberForColor(RailRoadCell.COLOR_GROUP);
	}

	/**
	 * Number of util.
	 *
	 * @return the int
	 */
	public int numberOfUtil() {
		return getPropertyNumberForColor(UtilityCell.COLOR_GROUP);
	}
	
	/**
	 * Pay rent to.
	 *
	 * @param owner the owner
	 * @param rentValue the rent value
	 */
	public void payRentTo(Player owner, int rentValue) {
		if(data.money < rentValue) {
			owner.data.money += data.money;
			data.money -= rentValue;
		}
		else {
			data.money -= rentValue;
			owner.data.money +=rentValue;
		}
		if(isBankrupt()) {
			data.money = 0;
			exchangeProperty(owner);
		}
	}
	
	/**
	 * Purchase.
	 */
	public void purchase() {
		if(getPosition().isAvailable()) {
			Cell c = getPosition();
			c.setAvailable(false);
			if(c instanceof PropertyCell) {
				PropertyCell cell = (PropertyCell)c;
				purchaseProperty(cell);
			}
			if(c instanceof RailRoadCell) {
				RailRoadCell cell = (RailRoadCell)c;
				purchaseRailRoad(cell);
			}
			if(c instanceof UtilityCell) {
				UtilityCell cell = (UtilityCell)c;
				purchaseUtility(cell);
			}
		}
	}
	
	/**
	 * Purchase house.
	 *
	 * @param selectedMonopoly the selected monopoly
	 * @param houses the houses
	 */
	public void purchaseHouse(String selectedMonopoly, int houses) {
		GameBoard gb = GameMaster.instance().getGameBoard();
		PropertyCell[] cells = gb.getPropertiesInMonopoly(selectedMonopoly);
		if((data.money >= (cells.length * (cells[0].getHousePrice() * houses)))) {
			for(int i = 0; i < cells.length; i++) {
				int newNumber = cells[i].getNumHouses() + houses;
				if (newNumber <= 5) {
					cells[i].setNumHouses(newNumber);
					this.setMoney(data.money - (cells[i].getHousePrice() * houses));
					GameMaster.instance().updateGUI();
				}
			}
		}
	}
	
	/**
	 * Purchase property.
	 *
	 * @param cell the cell
	 */
	private void purchaseProperty(PropertyCell cell) {
        buyProperty(cell, cell.getPrice());
	}

	/**
	 * Purchase rail road.
	 *
	 * @param cell the cell
	 */
	private void purchaseRailRoad(RailRoadCell cell) {
	    buyProperty(cell, cell.getPrice());
	}

	/**
	 * Purchase utility.
	 *
	 * @param cell the cell
	 */
	private void purchaseUtility(UtilityCell cell) {
	    buyProperty(cell, cell.getPrice());
	}

    /**
     * Sell property.
     *
     * @param property the property
     * @param amount the amount
     */
    public void sellProperty(IOwnable property, int amount) {
        property.setTheOwner(null);
        if(property instanceof PropertyCell) {
            data.properties.remove(property);
        }
        if(property instanceof RailRoadCell) {
            data.railroads.remove(property);
        }
        if(property instanceof UtilityCell) {
            data.utilities.remove(property);
        }
        setMoney(getMoney() + amount);
    }

	/**
	 * Sets the in jail.
	 *
	 * @param inJail the new in jail
	 */
	public void setInJail(boolean inJail) {
		this.data.inJail = inJail;
	}

	/**
	 * Sets the money.
	 *
	 * @param money the new money
	 */
	public void setMoney(int money) {
		this.data.money = money;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.data.name = name;
	}

	/**
	 * Sets the position.
	 *
	 * @param newPosition the new position
	 */
	public void setPosition(Cell newPosition) {
		this.data.position = newPosition;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return data.name;
    }
    
    /**
     * Reset property.
     */
    public void resetProperty() {
    	data.properties = new ArrayList<PropertyCell>();
    	data.railroads = new ArrayList<Cell>();
    	data.utilities = new ArrayList<Cell>();
	}
}
