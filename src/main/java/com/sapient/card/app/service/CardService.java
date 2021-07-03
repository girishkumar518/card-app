package com.sapient.card.app.service;
/*
 *  Created by Girish Kumar CH on 7/2/21
 *  Class CardService provides the functionality of card services like
 *  retreving card data and operations
 *
 */

import com.sapient.card.app.dto.Card;
import com.sapient.card.app.mapper.CardMapper;
import com.sapient.card.app.repository.CardRepository;
import com.sapient.card.app.repository.entity.CardEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@Transactional
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    /***
     *  Methos used to create card in the persistence layer through reactive approach
     * @param card
     * @return Created Card Object
     */
    public Mono<Card> createCard(Card card){
        log.debug("createCard method called");

        //setting initial value for all the new cards
        card.setLimit(0.0);
        Mono<CardEntity> cardEntityMono =  cardRepository.save(cardMapper.toCardEntity(card));

        return cardEntityMono.map(c->cardMapper.toCardResource(c));
    }

    /***
     *  this method returns all the cards in the System
     * @return list of the all the cards in the persistence layer
     */
    public Flux<Card> getAllCards(){
        log.debug("getAllCards method called");

        Flux<CardEntity> cardEntityFlux = cardRepository.findAll();

        return cardEntityFlux.map(c->cardMapper.toCardResource(c));

    }



}
