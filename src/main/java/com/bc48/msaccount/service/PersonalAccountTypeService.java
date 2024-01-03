package com.bc48.msaccount.service;

import com.bc48.msaccount.dto.CreateAccountTypeClientDto;
import com.bc48.msaccount.dto.DeleteAccountTypeClientDto;
import com.bc48.msaccount.dto.UpdateAccountTypeClientDto;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import com.bc48.msaccount.dto.*;
import com.bc48.msaccount.entity.AccountType;

public interface PersonalAccountTypeService {
    public Single<AccountType> create(CreateAccountTypeClientDto createAccountTypeClientDto);

    public Flowable<AccountType> findAll();

    public Single<AccountType> findById(String id);

    public Single<AccountType> findByAbbreviation(String abbreviation);

    public Single<AccountType> update(UpdateAccountTypeClientDto updateAccountTypeClientDto);

    public Single<AccountType> delete(DeleteAccountTypeClientDto deleteAccountTypeClientDto);


}