package com.bc48.msaccount.repository;

import io.reactivex.rxjava3.core.Maybe;
import com.bc48.msaccount.entity.AccountType;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.reactive.RxJava3CrudRepository;

@EnableReactiveMongoRepositories
public interface PersonalAccountTypeRepository extends RxJava3CrudRepository<AccountType, String> {

    Maybe<AccountType> findById(String id);

    Maybe<AccountType> findByAbbreviation(String abbreviation);
}