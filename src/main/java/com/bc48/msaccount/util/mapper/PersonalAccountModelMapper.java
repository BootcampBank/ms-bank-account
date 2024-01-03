package com.bc48.msaccount.util.mapper;

import java.util.Date;
import java.util.UUID;

import com.bc48.msaccount.dto.CreateAccountClientDto;
import com.bc48.msaccount.dto.DeleteAccountClientDto;
import com.bc48.msaccount.dto.UpdateAccountClientDto;
import com.bc48.msaccount.entity.Account;
import org.modelmapper.ModelMapper;



public class PersonalAccountModelMapper {

    private final ModelMapper mapper = new ModelMapper();

    private static PersonalAccountModelMapper instance;

    private PersonalAccountModelMapper() {
    }

    public static PersonalAccountModelMapper singleInstance() {
        if (instance == null) {
            instance = new PersonalAccountModelMapper();
        }
        return instance;
    }

    //MAPPERS BEGIN
    public Account reverseMapCreateWithDate(CreateAccountClientDto createAccountClientDto) {
        Account personalClient = mapper.map(createAccountClientDto, Account.class);
        personalClient.setDocumentNumber(createAccountClientDto.getDocumentNumber());
        personalClient.setAccountNumber(createAccountClientDto.getAccountNumber());
        personalClient.setInsertionDate(new Date());
        personalClient.setRegistrationStatus((short) 1);
        return personalClient;
    }


    public Account reverseMapUpdate(Account account, UpdateAccountClientDto updateDto) {
        account.setAccountNumber(updateDto.getAccountNumber());
        account.setIdAccountType(updateDto.getIdAccountType());
        return account;
    }

    public Account reverseMapDelete(Account personalClient, DeleteAccountClientDto deleteDto) {

        personalClient.setRegistrationStatus((short) 0);

        return personalClient;
    }

}