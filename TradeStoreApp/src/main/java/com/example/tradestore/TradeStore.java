package com.example.tradestore;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TradeStore {
    private Map<String, Trade> tradeStore;

    public TradeStore() {
        this.tradeStore = new HashMap<>();
    }

    public boolean addTrade(Trade trade) throws IllegalArgumentException {
        validateTrade(trade);

        String tradeId = trade.getTradeId();

        if (tradeStore.containsKey(tradeId)) {
            Trade existingTrade = tradeStore.get(tradeId);
            if (existingTrade.getVersion() > trade.getVersion()) {
                throw new IllegalArgumentException("Cannot add trade with lower version: " + trade.getVersion());
            }
        }

        tradeStore.put(tradeId, trade);
        return true;
    }

    private void validateTrade(Trade trade) throws IllegalArgumentException {
        if (trade.getMaturityDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Trade maturity date " + trade.getMaturityDate() + " is before today's date.");
        }
    }

    public Trade getTrade(String tradeId) {
        return tradeStore.get(tradeId);
    }

    public void expireTrades() {
        LocalDate today = LocalDate.now();
        for (Trade trade : tradeStore.values()) {
            if (trade.getMaturityDate().isBefore(today)) {
                trade.setExpired(true);
            }
        }
    }
}
