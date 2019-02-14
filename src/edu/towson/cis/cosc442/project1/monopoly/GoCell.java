package edu.towson.cis.cosc442.project1.monopoly;

// TODO: Auto-generated Javadoc
/**
 * The Class GoCell.
 */
public class GoCell extends Cell {
	
	/**
	 * Instantiates a new go cell.
	 */
	public GoCell() {
		super.setName("Go");
		setAvailable(false);
	}

	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project1.monopoly.Cell#playAction(java.lang.String)
	 */
	public boolean playAction(String msg) {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project1.monopoly.Cell#setName(java.lang.String)
	 */
	void setName(String name) {
	}
}
