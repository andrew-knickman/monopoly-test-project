package edu.towson.cis.cosc442.project1.monopoly;

// TODO: Auto-generated Javadoc
/**
 * The Class MockGUI.
 */
public class MockGUI implements MonopolyGUI {
    
    /** The btn get out of jail state. */
    private boolean btnDrawCardState, btnEndTurnState, btnGetOutOfJailState;
    
    /** The btn trade state. */
    private boolean[] btnTradeState = new boolean[2];

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#enableEndTurnBtn(int)
     */
    public void enableEndTurnBtn(int playerIndex) {
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#enablePlayerTurn(int)
     */
    public void enablePlayerTurn(int playerIndex) {
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#enablePurchaseBtn(int)
     */
    public void enablePurchaseBtn(int playerIndex) {
    }
	
	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#getDiceRoll()
	 */
	public int[] getDiceRoll() {
		int roll[] = new int[2];
		roll[0] = 2;
		roll[1] = 3;
		return roll;
	}

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#isDrawCardButtonEnabled()
     */
    public boolean isDrawCardButtonEnabled() {
        return btnDrawCardState;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#isEndTurnButtonEnabled()
     */
    public boolean isEndTurnButtonEnabled() {
        return btnEndTurnState;
    }
	
	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#isGetOutOfJailButtonEnabled()
	 */
	public boolean isGetOutOfJailButtonEnabled() {
		return btnGetOutOfJailState;
	}

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#isTradeButtonEnabled(int)
     */
    public boolean isTradeButtonEnabled(int i) {
        return btnTradeState[i];
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#movePlayer(int, int, int)
     */
    public void movePlayer(int index, int from, int to) {
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#openRespondDialog(edu.towson.cis.cosc442.project1.monopoly.TradeDeal)
     */
    public RespondDialog openRespondDialog(TradeDeal deal) {
        RespondDialog dialog = new MockRespondDialog(deal);
        return dialog;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#openTradeDialog()
     */
    public TradeDialog openTradeDialog() {
        TradeDialog dialog = new MockTradeDialog();
        return dialog;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#setBuyHouseEnabled(boolean)
     */
    public void setBuyHouseEnabled(boolean b) {
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#setDrawCardEnabled(boolean)
     */
    public void setDrawCardEnabled(boolean b) {
        btnDrawCardState = b;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#setEndTurnEnabled(boolean)
     */
    public void setEndTurnEnabled(boolean enabled) {
        btnEndTurnState = enabled;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#setGetOutOfJailEnabled(boolean)
     */
    public void setGetOutOfJailEnabled(boolean b) {
    	this.btnGetOutOfJailState = b;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#setPurchasePropertyEnabled(boolean)
     */
    public void setPurchasePropertyEnabled(boolean enabled) {
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#setRollDiceEnabled(boolean)
     */
    public void setRollDiceEnabled(boolean b) {
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#setTradeEnabled(int, boolean)
     */
    public void setTradeEnabled(int index, boolean b) {
        this.btnTradeState[index] = b;
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#showBuyHouseDialog(edu.towson.cis.cosc442.project1.monopoly.Player)
     */
    public void showBuyHouseDialog(Player currentPlayer) {
    }

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#showMessage(java.lang.String)
     */
    public void showMessage(String string) {
    }

	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#showUtilDiceRoll()
	 */
	public int showUtilDiceRoll() {
//		int[] diceValues = GameMaster.instance().rollDice();
//		return diceValues[0] + diceValues[1];
		return 10;
	}

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#startGame()
     */
    public void startGame() {
    }

	/* (non-Javadoc)
	 * @see edu.towson.cis.cosc442.project1.monopoly.MonopolyGUI#update()
	 */
	public void update() {
	}
}
