package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Hashtable;

public class GameBoard {

	private GameBoardData data = new GameBoardData(new ArrayList<Cell>(), new ArrayList<Card>(), new Hashtable<String, Integer>(), new ArrayList<Card>());
	public GameBoard() {
		Cell go = new GoCell();
		addCell(go);
	}

    public void addCard(Card card) {
        if(card.getCardType() == Card.TYPE_CC) {
            data.communityChestCards.add(card);
        } else {
            data.chanceCards.add(card);
        }
    }
	
	public void addCell(Cell cell) {
		data.cells.add(cell);
	}
	
	public void addCell(PropertyCell cell) {
		String colorGroup = cell.getColorGroup();
		int propertyNumber = getPropertyNumberForColor(colorGroup);
		data.colorGroups.put(colorGroup, new Integer(propertyNumber + 1));
        data.cells.add(cell);
	}

    public Card drawCCCard() {
        Card card = (Card)data.communityChestCards.get(0);
        data.communityChestCards.remove(0);
        addCard(card);
        return card;
    }

    public Card drawChanceCard() {
        Card card = (Card)data.chanceCards.get(0);
        data.chanceCards.remove(0);
        addCard(card);
        return card;
    }

	public Cell getCell(int newIndex) {
		return (Cell)data.cells.get(newIndex);
	}
	
	public int getCellNumber() {
		return data.cells.size();
	}
	
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
	
	public int getPropertyNumberForColor(String name) {
		Integer number = (Integer)data.colorGroups.get(name);
		if(number != null) {
			return number.intValue();
		}
		return 0;
	}

	public Cell queryCell(String string) {
		for(int i = 0; i < data.cells.size(); i++){
			Cell temp = (Cell)data.cells.get(i); 
			if(temp.getName().equals(string)) {
				return temp;
			}
		}
		return null;
	}
	
	public int queryCellIndex(String string){
		for(int i = 0; i < data.cells.size(); i++){
			Cell temp = (Cell)data.cells.get(i); 
			if(temp.getName().equals(string)) {
				return i;
			}
		}
		return -1;
	}

    public void removeCards() {
        data.communityChestCards.clear();
    }
}
