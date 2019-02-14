package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Iterator;


// TODO: Auto-generated Javadoc
/**
 * The Class GameMaster.
 */
public class GameMaster {

	/** The game master. */
	private static GameMaster gameMaster;
	
	/** The Constant MAX_PLAYER. */
	static final public int MAX_PLAYER = 8;	
	
	/** The data. */
	private GameMasterData data = new GameMasterData(new ArrayList<Player>(), 0);

	/**
	 * Instance.
	 *
	 * @return the game master
	 */
	public static GameMaster instance() {
		if(gameMaster == null) {
			gameMaster = new GameMaster();
		}
		return gameMaster;
	}

	/**
	 * Instantiates a new game master.
	 */
	public GameMaster() {
		data.initAmountOfMoney = 1500;
		data.dice = new Die[]{new Die(), new Die()};
	}

    /**
     * Btn buy house clicked.
     */
    public void btnBuyHouseClicked() {
        data.gui.showBuyHouseDialog(getCurrentPlayer());
    }

    /**
     * Btn draw card clicked.
     *
     * @return the card
     */
    public Card btnDrawCardClicked() {
        data.gui.setDrawCardEnabled(false);
        CardCell cell = (CardCell)getCurrentPlayer().getPosition();
        Card card = null;
        if(cell.getType() == Card.TYPE_CC) {
            card = getGameBoard().drawCCCard();
            card.applyAction();
        } else {
            card = getGameBoard().drawChanceCard();
            card.applyAction();
        }
        data.gui.setEndTurnEnabled(true);
        return card;
    }

    /**
     * Btn end turn clicked.
     */
    public void btnEndTurnClicked() {
		setAllButtonEnabled(false);
		getCurrentPlayer().getPosition().playAction(null);
		if(getCurrentPlayer().isBankrupt()) {
			data.gui.setBuyHouseEnabled(false);
			data.gui.setDrawCardEnabled(false);
			data.gui.setEndTurnEnabled(false);
			data.gui.setGetOutOfJailEnabled(false);
			data.gui.setPurchasePropertyEnabled(false);
			data.gui.setRollDiceEnabled(false);
			data.gui.setTradeEnabled(getCurrentPlayerIndex(),false);
			updateGUI();
		}
		else {
			switchTurn();
			updateGUI();
		}
    }

    /**
     * Btn get out of jail clicked.
     */
    public void btnGetOutOfJailClicked() {
		getCurrentPlayer().getOutOfJail();
		if(getCurrentPlayer().isBankrupt()) {
			data.gui.setBuyHouseEnabled(false);
			data.gui.setDrawCardEnabled(false);
			data.gui.setEndTurnEnabled(false);
			data.gui.setGetOutOfJailEnabled(false);
			data.gui.setPurchasePropertyEnabled(false);
			data.gui.setRollDiceEnabled(false);
			data.gui.setTradeEnabled(getCurrentPlayerIndex(),false);
		}
		else {
			data.gui.setRollDiceEnabled(true);
			data.gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
			data.gui.setGetOutOfJailEnabled(getCurrentPlayer().isInJail());
		}
    }

    /**
     * Btn purchase property clicked.
     */
    public void btnPurchasePropertyClicked() {
        Player player = getCurrentPlayer();
		player.purchase();
		data.gui.setPurchasePropertyEnabled(false);
		updateGUI();
    }
    
    /**
     * Btn roll dice clicked.
     */
    public void btnRollDiceClicked() {
		int[] rolls = rollDice();
		if((rolls[0]+rolls[1]) > 0) {
			Player player = getCurrentPlayer();
			data.gui.setRollDiceEnabled(false);
			StringBuffer msg = new StringBuffer();
			msg.append(player.getName())
					.append(", you rolled ")
					.append(rolls[0])
					.append(" and ")
					.append(rolls[1]);
			data.gui.showMessage(msg.toString());
			movePlayer(player, rolls[0] + rolls[1]);
			data.gui.setBuyHouseEnabled(false);
		}
    }

    /**
     * Btn trade clicked.
     */
    public void btnTradeClicked() {
        TradeDialog dialog = data.gui.openTradeDialog();
        TradeDeal deal = dialog.getTradeDeal();
        if(deal != null) {
            RespondDialog rDialog = data.gui.openRespondDialog(deal);
            if(rDialog.getResponse()) {
                completeTrade(deal);
                updateGUI();
            }
        }
    }

    /**
     * Complete trade.
     *
     * @param deal the deal
     */
    public void completeTrade(TradeDeal deal) {
        Player seller = getPlayer(deal.getPlayerIndex());
        Cell property = data.gameBoard.queryCell(deal.getPropertyName());
        seller.sellProperty(property, deal.getAmount());
        getCurrentPlayer().buyProperty(property, deal.getAmount());
    }

    /**
     * Draw CC card.
     *
     * @return the card
     */
    public Card drawCCCard() {
        return data.gameBoard.drawCCCard();
    }

    /**
     * Draw chance card.
     *
     * @return the card
     */
    public Card drawChanceCard() {
        return data.gameBoard.drawChanceCard();
    }

	
	/**
	 * Gets the current player.
	 *
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return getPlayer(data.turn);
	}
    
    /**
     * Gets the current player index.
     *
     * @return the current player index
     */
    public int getCurrentPlayerIndex() {
        return data.turn;
    }

	/**
	 * Gets the game board.
	 *
	 * @return the game board
	 */
	public GameBoard getGameBoard() {
		return data.gameBoard;
	}

    /**
     * Gets the gui.
     *
     * @return the gui
     */
    public MonopolyGUI getGUI() {
        return data.gui;
    }

	/**
	 * Gets the inits the amount of money.
	 *
	 * @return the inits the amount of money
	 */
	public int getInitAmountOfMoney() {
		return data.initAmountOfMoney;
	}
	
	/**
	 * Gets the number of players.
	 *
	 * @return the number of players
	 */
	public int getNumberOfPlayers() {
		return data.players.size();
	}

    /**
     * Gets the number of sellers.
     *
     * @return the number of sellers
     */
    public int getNumberOfSellers() {
        return data.players.size() - 1;
    }

	/**
	 * Gets the player.
	 *
	 * @param index the index
	 * @return the player
	 */
	public Player getPlayer(int index) {
		return (Player)data.players.get(index);
	}
	
	/**
	 * Gets the player index.
	 *
	 * @param player the player
	 * @return the player index
	 */
	public int getPlayerIndex(Player player) {
		return data.players.indexOf(player);
	}

    /**
     * Gets the seller list.
     *
     * @return the seller list
     */
    public ArrayList<Player> getSellerList() {
        ArrayList<Player> sellers = new ArrayList<Player>();
        for (Iterator<Player> iter = data.players.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            if(player != getCurrentPlayer()) sellers.add(player);
        }
        return sellers;
    }

	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public int getTurn() {
		return data.turn;
	}

	/**
	 * Gets the util dice roll.
	 *
	 * @return the util dice roll
	 */
	public int getUtilDiceRoll() {
		return this.data.utilDiceRoll;
	}

	/**
	 * Move player.
	 *
	 * @param playerIndex the player index
	 * @param diceValue the dice value
	 */
	public void movePlayer(int playerIndex, int diceValue) {
		Player player = (Player)data.players.get(playerIndex);
		movePlayer(player, diceValue);
	}
	
	/**
	 * Move player.
	 *
	 * @param player the player
	 * @param diceValue the dice value
	 */
	public void movePlayer(Player player, int diceValue) {
		Cell currentPosition = player.getPosition();
		int positionIndex = data.gameBoard.queryCellIndex(currentPosition.getName());
		int newIndex = (positionIndex+diceValue)%data.gameBoard.getCellNumber();
		if(newIndex <= positionIndex || diceValue > data.gameBoard.getCellNumber()) {
			player.setMoney(player.getMoney() + 200);
		}
		player.setPosition(data.gameBoard.getCell(newIndex));
		data.gui.movePlayer(getPlayerIndex(player), positionIndex, newIndex);
		playerMoved(player);
		updateGUI();
	}

	/**
	 * Player moved.
	 *
	 * @param player the player
	 */
	public void playerMoved(Player player) {
		Cell cell = player.getPosition();
		int playerIndex = getPlayerIndex(player);
		if(cell instanceof CardCell) {
		    data.gui.setDrawCardEnabled(true);
		} else{
			if(cell.isAvailable()) {
				int price = cell.getPrice();
				if(price <= player.getMoney() && price > 0) {
					data.gui.enablePurchaseBtn(playerIndex);
				}
			}	
			data.gui.enableEndTurnBtn(playerIndex);
		}
        data.gui.setTradeEnabled(data.turn, false);
	}

	/**
	 * Reset.
	 */
	public void reset() {
		for(int i = 0; i < getNumberOfPlayers(); i++){
			Player player = (Player)data.players.get(i);
			player.setPosition(data.gameBoard.getCell(0));
		}
		if(data.gameBoard != null) data.gameBoard.removeCards();
		data.turn = 0;
	}
	
	/**
	 * Roll dice.
	 *
	 * @return the int[]
	 */
	public int[] rollDice() {
		if(data.testMode) {
			return data.gui.getDiceRoll();
		}
		else {
			return new int[]{
					data.dice[0].getRoll(),
					data.dice[1].getRoll()
			};
		}
	}
	
	/**
	 * Send to jail.
	 *
	 * @param player the player
	 */
	public void sendToJail(Player player) {
	    int oldPosition = data.gameBoard.queryCellIndex(getCurrentPlayer().getPosition().getName());
		player.setPosition(data.gameBoard.queryCell("Jail"));
		player.setInJail(true);
		int jailIndex = data.gameBoard.queryCellIndex("Jail");
		data.gui.movePlayer(
		        getPlayerIndex(player),
		        oldPosition,
		        jailIndex);
	}
    
	/**
	 * Sets the all button enabled.
	 *
	 * @param enabled the new all button enabled
	 */
	private void setAllButtonEnabled(boolean enabled) {
		data.gui.setRollDiceEnabled(enabled);
		data.gui.setPurchasePropertyEnabled(enabled);
		data.gui.setEndTurnEnabled(enabled);
        data.gui.setTradeEnabled(data.turn, enabled);
        data.gui.setBuyHouseEnabled(enabled);
        data.gui.setDrawCardEnabled(enabled);
        data.gui.setGetOutOfJailEnabled(enabled);
	}

	/**
	 * Sets the game board.
	 *
	 * @param board the new game board
	 */
	public void setGameBoard(GameBoard board) {
		this.data.gameBoard = board;
	}
	
	/**
	 * Sets the gui.
	 *
	 * @param gui the new gui
	 */
	public void setGUI(MonopolyGUI gui) {
		this.data.gui = gui;
	}

	/**
	 * Sets the inits the amount of money.
	 *
	 * @param money the new inits the amount of money
	 */
	public void setInitAmountOfMoney(int money) {
		this.data.initAmountOfMoney = money;
	}

	/**
	 * Sets the number of players.
	 *
	 * @param number the new number of players
	 */
	public void setNumberOfPlayers(int number) {
		data.players.clear();
		for(int i =0;i<number;i++) {
			Player player = new Player();
			player.setMoney(data.initAmountOfMoney);
			data.players.add(player);
		}
	}

	/**
	 * Sets the util dice roll.
	 *
	 * @param diceRoll the new util dice roll
	 */
	public void setUtilDiceRoll(int diceRoll) {
		this.data.utilDiceRoll = diceRoll;
	}
	
	/**
	 * Start game.
	 */
	public void startGame() {
		data.gui.startGame();
		data.gui.enablePlayerTurn(0);
        data.gui.setTradeEnabled(0, true);
	}

	/**
	 * Switch turn.
	 */
	public void switchTurn() {
		data.turn = (data.turn + 1) % getNumberOfPlayers();
		if(!getCurrentPlayer().isInJail()) {
			data.gui.enablePlayerTurn(data.turn);
			data.gui.setBuyHouseEnabled(getCurrentPlayer().canBuyHouse());
            data.gui.setTradeEnabled(data.turn, true);
		}
		else {
			data.gui.setGetOutOfJailEnabled(true);
		}
	}
	
	/**
	 * Update GUI.
	 */
	public void updateGUI() {
		data.gui.update();
	}

	/**
	 * Util roll dice.
	 */
	public void utilRollDice() {
		this.data.utilDiceRoll = data.gui.showUtilDiceRoll();
	}

	/**
	 * Sets the test mode.
	 *
	 * @param b the new test mode
	 */
	public void setTestMode(boolean b) {
		data.testMode = b;
	}
}
