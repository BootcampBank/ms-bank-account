package com.bc48.msaccount.repository;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import com.bc48.msaccount.entity.Account;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;


@EnableReactiveMongoRepositories
public interface PersonalAccountRepository extends RxJava3CrudRepository<Account, String> {

    Maybe<Account> findByDocumentNumber(String documentNumber);

    Flowable<Account> findByDocumentNumberAndRegistrationStatus(String documentNumber, short registrationStatus);

}