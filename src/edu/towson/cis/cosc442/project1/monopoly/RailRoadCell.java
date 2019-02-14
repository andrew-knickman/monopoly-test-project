package edu.towson.cis.cosc442.project1.monopoly;

// TODO: Auto-generated Javadoc
/**
 * The Class RailRoadCell.
 */
public class RailRoadCell extends Cell {
	
	/** The base rent. */
	static private int baseRent;
	
	/** The color group. */
	static public String COLOR_GROUP = "RAILROAD";
	
	/** The price. */
	static private int price;

	/**
	 * Sets the base rent.
	 *
	 * @param baseRent the new base rent
	 */
	public static void setBaseRent(int baseRent) {
		RailRoadCell.baseRent = baseRent;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public static void setPrice(int price) {
		RailRoadCell.price = price;
	}

	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project1.monopoly.Cell#getPrice()
	 */
	public int getPrice() {
		return RailRoadCell.price;
	}

	/**
	 * Gets the rent.
	 *
	 * @return the rent
	 */
	public int getRent() {
		return RailRoadCell.baseRent * (int)Math.pow(2, theOwner.numberOfRR() - 1);
	}
	
	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project1.monopoly.Cell#playAction(java.lang.String)
	 */
	public boolean playAction(String msg) {
		Player currentPlayer = null;
		if(!isAvailable()) {
			currentPlayer = GameMaster.instance().getCurrentPlayer();
			if(theOwner != currentPlayer) {
				currentPlayer.payRentTo(theOwner, getRent());
			}
		}
		return false;
	}
}
