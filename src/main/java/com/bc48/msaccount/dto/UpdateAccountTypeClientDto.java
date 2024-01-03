package com.bc48.msaccount.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UpdateAccountTypeClientDto {

    private String id;
    private String description;
    private String abbreviation;
    private BigDecimal interestRate;
    private BigDecimal transactionFee;
    private Long transactionsNumber;
    private BigDecimal maintenanceCost;
    private BigDecimal minimumBalance;
    private Date transactionPermissionDate;
    private short registrationStatus;
    private String isoCurrencyCode;
}