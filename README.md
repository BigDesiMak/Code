
## Classes

### 1. `Trade.java`

This class represents a "trade" with the following attributes:

- `tradeId`: Unique identifier for the trade.
- `version`: Version of the trade.
- `counterPartyId`: ID of the counterparty.
- `bookId`: ID of the book.
- `maturityDate`: The date on which the trade matures.
- `createdDate`: The date on which the trade is created.
- `expired`: A flag indicating whether the trade is expired.

### 2. `TradeStore.java`

This class handles the storage and validation of trades. It provides functionalities to:

- Add a trade to the store.
- Validate that trades with a maturity date before today are not allowed.
- Reject trades with a lower version.
- Override trades with the same version.
- Automatically expire trades if they cross the maturity date. This function can be called using autosys or cron job functionality to execute at start/end of the day.

### 3. `TradeStoreTest.java`

This class contains JUnit test cases to test the functionality of `TradeStore`:

- `testAddTrade()`: Tests adding a valid trade.
- `testRejectOlderVersionTrade()`: Tests rejecting a trade with a lower version.
- `testReplaceTradeWithSameVersion()`: Tests replacing a trade with the same version.
- `testRejectTradeWithPastMaturityDate()`: Tests rejecting a trade with a past maturity date.
- `testExpireTradesAutomatically()`: Tests automatic expiration of trades that cross the maturity date.
