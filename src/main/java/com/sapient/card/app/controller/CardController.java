package com.sapient.card.app.controller;
/*
 *  Created by Girish Kumar CH on 7/1/21
 */

import com.sapient.card.app.dto.Card;
import com.sapient.card.app.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class CardController {

    @Autowired
    private CardService cardService;


    @PostMapping(value="/v1/cards", produces = {MediaType.APPLICATION_JSON_VALUE} , consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Card> createCard(@RequestBody final Card card){
        return null;
    }


    @GetMapping(value="/v1/cards",produces = {MediaType.APPLICATION_JSON_VALUE}  )
    public Flux<Card> getAllCards(){
        return null;
    }



}
