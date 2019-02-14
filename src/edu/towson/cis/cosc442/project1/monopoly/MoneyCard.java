package edu.towson.cis.cosc442.project1.monopoly;

// TODO: Auto-generated Javadoc
/**
 * The Class MoneyCard.
 */
public class MoneyCard extends Card {
    
    /** The amount. */
    private int amount;
    
    /** The card type. */
    private int cardType;
    
    /** The label. */
    private String label;
    
    /**
     * Instantiates a new money card.
     *
     * @param label the label
     * @param amount the amount
     * @param cardType the card type
     */
    public MoneyCard(String label, int amount, int cardType){
        this.label = label;
        this.amount = amount;
        this.cardType = cardType;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.Card#applyAction()
     */
    public void applyAction() {
        Player currentPlayer = GameMaster.instance().getCurrentPlayer();
		currentPlayer.setMoney(currentPlayer.getMoney() + amount);
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.Card#getCardType()
     */
    public int getCardType() {
        return cardType;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.Card#getLabel()
     */
    public String getLabel() {
        return label;
    }
}
