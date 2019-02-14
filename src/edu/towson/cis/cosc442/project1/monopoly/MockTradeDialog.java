package edu.towson.cis.cosc442.project1.monopoly;

// TODO: Auto-generated Javadoc
/**
 * The Class MockTradeDialog.
 */
public class MockTradeDialog implements TradeDialog {

    /* (non-Javadoc)
     * @see edu.towson.cis.cosc442.project1.monopoly.TradeDialog#getTradeDeal()
     */
    public TradeDeal getTradeDeal() {
        TradeDeal deal = new TradeDeal();
        deal.setAmount(200);
        deal.setSellerIndex(0);
        deal.setPropertyName(GameMaster.instance().getGameBoard().getCell(1).toString());
        return deal;
    }
}
