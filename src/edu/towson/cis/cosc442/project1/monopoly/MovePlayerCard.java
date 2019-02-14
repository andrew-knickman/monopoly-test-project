package edu.towson.cis.cosc442.project1.monopoly;


// TODO: Auto-generated Javadoc
/**
 * The Class MovePlayerCard.
 */
public class MovePlayerCard extends Card {
    
    /** The destination. */
    private String destination;
    
    /** The type. */
    private int type;

    /**
     * Instantiates a new move player card.
     *
     * @param destination the destination
     * @param cardType the card type
     */
    public MovePlayerCard(String destination, int cardType) {
        this.destination = destination;
        this.type = cardType;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.Card#applyAction()
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
        Cell currentPosition = currentPlayer.getPosition();
        int newCell = GameMaster.instance().getGameBoard().queryCellIndex(destination);
        int currentCell = GameMaster.instance().getGameBoard().queryCellIndex(currentPosition.getName());
        int diceValue = 0;
        if(currentCell > newCell) {
            diceValue = (GameMaster.instance().getGameBoard().getCellNumber() + 
                    (newCell - currentCell));
        }
        else if(currentCell <= newCell) {
            diceValue = newCell - currentCell;
        }
        System.out.println(diceValue);
        GameMaster.instance().movePlayer(currentPlayer, diceValue);
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.Card#getCardType()
     */
    public int getCardType() {
        return type;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.Card#getLabel()
     */
    public String getLabel() {
        return "Go to " + destination;
    }

}
