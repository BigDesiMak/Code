package com.example.tradestore;

import java.time.LocalDate;

public class Trade {
    private String tradeId;
    private int version;
    private String counterPartyId;
    private String bookId;
    private LocalDate maturityDate;
    private LocalDate createdDate;
    private boolean expired;

    public Trade(String tradeId, int version, String counterPartyId, String bookId, LocalDate maturityDate) {
        this.tradeId = tradeId;
        this.version = version;
        this.counterPartyId = counterPartyId;
        this.bookId = bookId;
        this.maturityDate = maturityDate;
        this.createdDate = LocalDate.now();
        this.expired = false;
    }

    public String getTradeId() { return tradeId; }
    public int getVersion() { return version; }
    public String getCounterPartyId() { return counterPartyId; }
    public String getBookId() { return bookId; }
    public LocalDate getMaturityDate() { return maturityDate; }
    public LocalDate getCreatedDate() { return createdDate; }
    public boolean isExpired() { return expired; }
    public void setExpired(boolean expired) { this.expired = expired; }
}
