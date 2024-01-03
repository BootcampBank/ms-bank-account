package com.bc48.msaccount.service;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import com.bc48.msaccount.dto.CreateAccountClientDto;
import com.bc48.msaccount.dto.DeleteAccountClientDto;
import com.bc48.msaccount.dto.UpdateAccountClientDto;
import com.bc48.msaccount.entity.Account;

public interface PersonalAccountService {
    public Single<Account> create(CreateAccountClientDto createAccountClientDto);

    public Flowable<Account> findAll();

    public Single<Account> findById(String id);

    public Flowable<Account> findByDocumentNumberAndRegistrationStatus(String idClient, short registrationStatus);

    public Single<Account> update(UpdateAccountClientDto updateAccountClientDto);

    public Single<Account> delete(DeleteAccountClientDto deleteAccountClientDto);


}