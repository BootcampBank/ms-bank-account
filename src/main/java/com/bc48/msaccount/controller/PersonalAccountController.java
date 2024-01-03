package com.bc48.msaccount.controller;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.bc48.msaccount.dto.CreateAccountClientDto;
import com.bc48.msaccount.dto.DeleteAccountClientDto;
import com.bc48.msaccount.dto.UpdateAccountClientDto;
import com.bc48.msaccount.entity.Account;
import com.bc48.msaccount.service.impl.PersonalAccountServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/accounts")
@Tag(name = "Personal Clients Account Information", description = "Manage personal clients account")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class PersonalAccountController {

    public final PersonalAccountServiceImpl service;

    @GetMapping("/{documentNumber}/{registrationStatus}")
    public ResponseEntity<Flowable<Account>> findByIdClient(@PathVariable String documentNumber, @PathVariable short registrationStatus) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findByDocumentNumberAndRegistrationStatus(documentNumber, registrationStatus));
    }

    @GetMapping("/find/{id}")
    public Single<ResponseEntity<Account>> findById(@PathVariable String id) {

        return service.findById(id).map(client -> ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(client));
    }

    @GetMapping
    public ResponseEntity<Flowable<Account>> findAll() {
        Flowable<Account> flowable = service.findAll();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flowable);
    }

    @PostMapping
    public Single<ResponseEntity<Account>> create(@RequestBody CreateAccountClientDto createAccountClientDto) {
        return service.create(createAccountClientDto).map(p -> ResponseEntity
                .created(URI.create("/client/personal/".concat(p.get_id())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }


    @PutMapping
    public Single<ResponseEntity<Account>> update(@RequestBody UpdateAccountClientDto updateAccountClientDto) {
        return service.update(updateAccountClientDto)
                .map(p -> ResponseEntity.created(URI.create("/api/accounts"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @DeleteMapping
    public Single<ResponseEntity<Account>> delete(@RequestBody DeleteAccountClientDto deleteAccountClientDto) {
        return service.delete(deleteAccountClientDto)
                .map(p -> ResponseEntity.created(URI.create("/api/accounts"
                                .concat(p.get_id())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }
}