package com.example.tradestore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class TradeStoreTest {
    private TradeStore tradeStore;

    @BeforeEach
    public void setUp() {
        tradeStore = new TradeStore();
    }

    @Test
    public void testAddTrade() {
        Trade trade1 = new Trade("T1", 1, "CP-1", "B1", LocalDate.of(2024, 9, 30));
        assertTrue(tradeStore.addTrade(trade1));
    }

    @Test
    public void testRejectOlderVersionTrade() {
        Trade trade1 = new Trade("T1", 2, "CP-1", "B1", LocalDate.of(2024, 9, 30));
        Trade trade2 = new Trade("T1", 1, "CP-1", "B1", LocalDate.of(2024, 9, 30));
        tradeStore.addTrade(trade1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tradeStore.addTrade(trade2));
        assertEquals("Cannot add trade with lower version: 1", exception.getMessage());
    }

    @Test
    public void testReplaceTradeWithSameVersion() {
        Trade trade1 = new Trade("T1", 1, "CP-1", "B1", LocalDate.of(2024, 9, 30));
        Trade trade2 = new Trade("T1", 1, "CP-1", "B1", LocalDate.of(2024, 9, 30));
        tradeStore.addTrade(trade1);
        assertTrue(tradeStore.addTrade(trade2));
        assertEquals(1, tradeStore.getTrade("T1").getVersion());
    }

    @Test
    public void testRejectTradeWithPastMaturityDate() {
        Trade trade1 = new Trade("T1", 1, "CP-1", "B1", LocalDate.now().minusDays(1));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> tradeStore.addTrade(trade1));
        assertEquals("Trade maturity date " + trade1.getMaturityDate() + " is before today's date.", exception.getMessage());
    }

    @Test
    public void testExpireTradesAutomatically() {
        Trade trade1 = new Trade("T1", 1, "CP-1", "B1", LocalDate.now().plusDays(1));
        tradeStore.addTrade(trade1);
        tradeStore.expireTrades();
        assertFalse(tradeStore.getTrade("T1").isExpired());
    }
}
