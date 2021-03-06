package com.sapient.card.app.controller;
/*
 *  Created by Girish Kumar CH on 7/1/21
 */

import com.sapient.card.app.dto.Card;
import com.sapient.card.app.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@Slf4j
public class CardController {

    @Autowired
    private CardService cardService;


    @PostMapping(value="/v1/cards", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Card> createCard( @RequestBody @Validated final Card card){

        log.info("creating the new card");
        return cardService.createCard(card);
    }


    @GetMapping(value="/v1/cards",produces = {MediaType.APPLICATION_JSON_VALUE}  )
    public Flux<Card> getAllCards(){

        log.info("Starting getAllCards");

        return cardService.getAllCards() ;
    }

}
