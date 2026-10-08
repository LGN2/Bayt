package com.codevictims.bayt.billing.dto.response;

import java.math.BigDecimal;
import java.util.List;

// Return the deposit ledger information to the client.
public record DepositLedgerResponse(List<DepositEntryResponse> entries, BigDecimal held) {}
