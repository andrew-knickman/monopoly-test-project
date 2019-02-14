package edu.towson.cis.cosc442.project1.monopoly;


// TODO: Auto-generated Javadoc
/**
 * The Class JailCard.
 */
public class JailCard extends Card {
    
    /** The type. */
    int type;
    
    /**
     * Instantiates a new jail card.
     *
     * @param cardType the card type
     */
    public JailCard(int cardType) {
        type = cardType;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.Card#applyAction()
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		GameMaster.instance().getGameBoard().queryCell("Jail");
		GameMaster.instance().sendToJail(currentPlayer);
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
        return "Go to Jail immediately without collecting" +
        		" $200 when passing the GO cell";
    }
}
