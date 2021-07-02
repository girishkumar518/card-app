package com.sapient.card.app.service;
/*
 *  Created by Girish Kumar CH on 7/2/21
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

import java.util.List;

@Service
@Slf4j
@Transactional
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;


    public Mono<Card> createCard(Card card){
        Mono<CardEntity> cardEntityMono =  cardRepository.save(cardMapper.toCardEntity(card));

        return cardEntityMono.map(c->cardMapper.toCardResource(c));
    }

    public Flux<Card> getAllCards(){
        Flux<CardEntity> cardEntityFlux = cardRepository.findAll();

        return cardEntityFlux.map(c->cardMapper.toCardResource(c));

    }



}
