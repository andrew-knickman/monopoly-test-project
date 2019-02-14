package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

// TODO: Auto-generated Javadoc
/**
 * The Class GameBoard.
 */
public class GameBoard {

	/** The data. */
	private GameBoardData data = new GameBoardData(new ArrayList<Cell>(), new ArrayList<Card>(), new Hashtable<String, Integer>(), new ArrayList<Card>());
	
	/**
	 * Instantiates a new game board.
	 */
	public GameBoard() {
		Cell go = new GoCell();
		addCell(go);
	}

    /**
     * Adds the card.
     *
     * @param card the card
     */
    public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            data.communityChestCards.add(card);
        } else {
            data.chanceCards.add(card);
        }
    }
	
	/**
	 * Adds the cell.
	 *
	 * @param cell the cell
	 */
	public void addCell(Cell cell) {
		data.cells.add(cell);
	}
	
	/**
	 * Adds the cell.
	 *
	 * @param cell the cell
	 */
	public void addCell(PropertyCell cell) {
		String colorGroup = cell.getColorGroup();
		int propertyNumber = getPropertyNumberForColor(colorGroup);
		data.colorGroups.put(colorGroup, new Integer(propertyNumber + 1));
        data.cells.add(cell);
	}

    /**
     * Draw CC card.
     *
     * @return the card
     */
    public Card drawCCCard() {
        Card card = (Card)data.communityChestCards.get(0);
        data.communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    /**
     * Draw chance card.
     *
     * @return the card
     */
    public Card drawChanceCard() {
        Card card = (Card)data.chanceCards.get(0);
        data.chanceCards.remove(0);
        addCard(card);
        return card;
    }

	/**
	 * Gets the cell.
	 *
	 * @param newIndex the new index
	 * @return the cell
	 */
	public Cell getCell(int newIndex) {
		return (Cell)data.cells.get(newIndex);
	}
	
	/**
	 * Gets the cell number.
	 *
	 * @return the cell number
	 */
	public int getCellNumber() {
		return data.cells.size();
	}
	
	/**
	 * Gets the properties in monopoly.
	 *
	 * @param color the color
	 * @return the properties in monopoly
	 */
	public PropertyCell[] getPropertiesInMonopoly(String color) {
		PropertyCell[] monopolyCells = 
			new PropertyCell[getPropertyNumberForColor(color)];
		int counter = 0;
		for (int i = 0; i < getCellNumber(); i++) {
			IOwnable c = getCell(i);
			if(c instanceof PropertyCell) {
				PropertyCell pc = (PropertyCell)c;
				if(pc.getColorGroup().equals(color)) {
					monopolyCells[counter] = pc;
					counter++;
				}
			}
		}
		return monopolyCells;
	}
	
	/**
	 * Gets the property number for color.
	 *
	 * @param name the name
	 * @return the property number for color
	 */
	public int getPropertyNumberForColor(String name) {
		Integer number = (Integer)data.colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	/**
	 * Query cell.
	 *
	 * @param string the string
	 * @return the cell
	 */
	public Cell queryCell(String string) {
		for(int i = 0; i < data.cells.size(); i++){
			Cell temp = (Cell)data.cells.get(i); 
			if(temp.getName().equals(string)) {
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * Query cell index.
	 *
	 * @param string the string
	 * @return the int
	 */
	public int queryCellIndex(String string){
		for(int i = 0; i < data.cells.size(); i++){
			Cell temp = (Cell)data.cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

    /**
     * Removes the cards.
     */
    public void removeCards() {
        data.communityChestCards.clear();
    }
}
