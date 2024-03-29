package com.bc48.msaccount.service.impl;

import com.bc48.msaccount.repository.PersonalAccountRepository;
import io.reactivex.rxjava3.core.*;
import lombok.RequiredArgsConstructor;
import com.bc48.msaccount.dto.CreateAccountClientDto;
import com.bc48.msaccount.dto.DeleteAccountClientDto;
import com.bc48.msaccount.dto.UpdateAccountClientDto;
import com.bc48.msaccount.entity.Account;
import com.bc48.msaccount.service.PersonalAccountService;
import com.bc48.msaccount.util.handler.exceptions.BadRequestException;
import com.bc48.msaccount.util.mapper.PersonalAccountModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class PersonalAccountServiceImpl implements PersonalAccountService {

    static PersonalAccountModelMapper modelMapper = PersonalAccountModelMapper.singleInstance();
    public final PersonalAccountRepository repository;

    @Override
    public Flowable<Account> findAll() {
        return repository.findAll()
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The customer haven accounts.",
                        getClass(),
                        "findAll.switchIfEmpty"
                )));
    }

    @Override
    public Single<Account> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Maybe.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with id number " + id + " does not exists.",
                        getClass(),
                        "findById.switchIfEmpty"
                )))
                .cast(Account.class).toSingle();
    }

    @Override
    public Flowable<Account> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus) {
        return repository.findByDocumentNumberAndRegistrationStatus(documentNumber, registrationStatus)
                .switchIfEmpty(Flowable.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to get an item.",
                        "The personal client with document number " + documentNumber + " does not exists.",
                        getClass(),
                        "findByIdClient.switchIfEmpty"
                )));

    }

    @Override
    public Single<Account> create(CreateAccountClientDto createAccountClientDto) {
        return repository.findByDocumentNumberAndRegistrationStatus(createAccountClientDto.getDocumentNumber(), createAccountClientDto.getRegistrationStatus())
                .filter(account -> !account.getIdAccountType().equals("6585b0efa4b49232d73023ec")
                        && account.getIdAccountType().equals(createAccountClientDto.getIdAccountType()))
                .firstElement()
                .map(account -> {
                    throw new BadRequestException(
                            "ClientId",
                            "[save] The client" + createAccountClientDto.getDocumentNumber() + " have other accounts.",
                            "An error occurred while trying to create an item.",
                            getClass(),
                            "save"
                    );
                })
                .switchIfEmpty(repository.save(modelMapper.reverseMapCreateWithDate(createAccountClientDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "ERROR",
                        "An error occurred while trying to create an item.",
                        e.getMessage(),
                        getClass(),
                        "save.onErrorResume"
                ))).cast(Account.class);
    }

    @Override
    public Single<Account> update(UpdateAccountClientDto updateAccountClientDto) {

        return repository.findById(updateAccountClientDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the document number " + updateAccountClientDto.getDocumentNumber() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapUpdate(p, updateAccountClientDto)))
                .doOnError(e -> Single.error(new BadRequestException(
                        "idClient",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }

    @Override
    public Single<Account> delete(DeleteAccountClientDto deleteAccountClientDto) {

        return repository.findById(deleteAccountClientDto.getId())
                .switchIfEmpty(Single.error(new Exception("An item with the document number " + deleteAccountClientDto.getDocumentNumber() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(modelMapper.reverseMapDelete(p, deleteAccountClientDto)))
                .doOnError(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        getClass(),
                        "update.onErrorResume"
                )));
    }
}