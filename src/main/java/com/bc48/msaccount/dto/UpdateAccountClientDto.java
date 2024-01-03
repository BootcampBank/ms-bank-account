package com.bc48.msaccount.dto;

import lombok.Data;

@Data
public class UpdateAccountClientDto {

    private String id;
    private String documentNumber;
    private String accountNumber;
    private String idAccountType;
}