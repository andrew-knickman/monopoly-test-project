package edu.towson.cis.cosc442.project1.monopoly;

import java.util.ArrayList;
import java.util.Iterator;


public class GameMaster {

	private static GameMaster gameMaster;
	static final public int MAX_PLAYER = 8;	
	private GameMasterData data = new GameMasterData(new ArrayList<Player>(), 0);

	public static GameMaster instance() {
		if(gameMaster == null) {
			gameMaster = new GameMaster();
		}
		return gameMaster;
	}

	public GameMaster() {
		data.initAmountOfMoney = 1500;
		data.dice = new Die[]{new Die(), new Die()};
	}

    public void btnBuyHouseClicked() {
        data.gui.showBuyHouseDialog(getCurrentPlayer());
    }

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

    public void btnPurchasePropertyClicked() {
        Player player = getCurrentPlayer();
		player.purchase();
		data.gui.setPurchasePropertyEnabled(false);
		updateGUI();
    }
    
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

    public void completeTrade(TradeDeal deal) {
        Player seller = getPlayer(deal.getPlayerIndex());
        Cell property = data.gameBoard.queryCell(deal.getPropertyName());
        seller.sellProperty(property, deal.getAmount());
        getCurrentPlayer().buyProperty(property, deal.getAmount());
    }

    public Card drawCCCard() {
        return data.gameBoard.drawCCCard();
    }

    public Card drawChanceCard() {
        return data.gameBoard.drawChanceCard();
    }

	
	public Player getCurrentPlayer() {
		return getPlayer(data.turn);
	}
    
    public int getCurrentPlayerIndex() {
        return data.turn;
    }

	public GameBoard getGameBoard() {
		return data.gameBoard;
	}

    public MonopolyGUI getGUI() {
        return data.gui;
    }

	public int getInitAmountOfMoney() {
		return data.initAmountOfMoney;
	}
	
	public int getNumberOfPlayers() {
		return data.players.size();
	}

    public int getNumberOfSellers() {
        return data.players.size() - 1;
    }

	public Player getPlayer(int index) {
		return (Player)data.players.get(index);
	}
	
	public int getPlayerIndex(Player player) {
		return data.players.indexOf(player);
	}

    public ArrayList<Player> getSellerList() {
        ArrayList<Player> sellers = new ArrayList<Player>();
        for (Iterator<Player> iter = data.players.iterator(); iter.hasNext();) {
            Player player = (Player) iter.next();
            if(player != getCurrentPlayer()) sellers.add(player);
        }
        return sellers;
    }

	public int getTurn() {
		return data.turn;
	}

	public int getUtilDiceRoll() {
		return this.data.utilDiceRoll;
	}

	public void movePlayer(int playerIndex, int diceValue) {
		Player player = (Player)data.players.get(playerIndex);
		movePlayer(player, diceValue);
	}
	
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

	public void reset() {
		for(int i = 0; i < getNumberOfPlayers(); i++){
			Player player = (Player)data.players.get(i);
			player.setPosition(data.gameBoard.getCell(0));
		}
		if(data.gameBoard != null) data.gameBoard.removeCards();
		data.turn = 0;
	}
	
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
    
	private void setAllButtonEnabled(boolean enabled) {
		data.gui.setRollDiceEnabled(enabled);
		data.gui.setPurchasePropertyEnabled(enabled);
		data.gui.setEndTurnEnabled(enabled);
        data.gui.setTradeEnabled(data.turn, enabled);
        data.gui.setBuyHouseEnabled(enabled);
        data.gui.setDrawCardEnabled(enabled);
        data.gui.setGetOutOfJailEnabled(enabled);
	}

	public void setGameBoard(GameBoard board) {
		this.data.gameBoard = board;
	}
	
	public void setGUI(MonopolyGUI gui) {
		this.data.gui = gui;
	}

	public void setInitAmountOfMoney(int money) {
		this.data.initAmountOfMoney = money;
	}

	public void setNumberOfPlayers(int number) {
		data.players.clear();
		for(int i =0;i<number;i++) {
			Player player = new Player();
			player.setMoney(data.initAmountOfMoney);
			data.players.add(player);
		}
	}

	public void setUtilDiceRoll(int diceRoll) {
		this.data.utilDiceRoll = diceRoll;
	}
	
	public void startGame() {
		data.gui.startGame();
		data.gui.enablePlayerTurn(0);
        data.gui.setTradeEnabled(0, true);
	}

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
	
	public void updateGUI() {
		data.gui.update();
	}

	public void utilRollDice() {
		this.data.utilDiceRoll = data.gui.showUtilDiceRoll();
	}

	public void setTestMode(boolean b) {
		data.testMode = b;
	}
}
