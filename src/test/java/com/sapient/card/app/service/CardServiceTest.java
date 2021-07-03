package com.sapient.card.app.service;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import com.sapient.card.app.dto.Card;
import com.sapient.card.app.mapper.CardMapper;
import com.sapient.card.app.mapper.CardMapperImpl;
import com.sapient.card.app.repository.CardRepository;
import com.sapient.card.app.repository.entity.CardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Mock
    private CardMapper cardMapper;


    static Stream<Arguments> cardsEntityData(){

        CardEntity c1 = new CardEntity();
        c1.setId(1);
        c1.setCardLimit(0.0);
        c1.setName("Test Card 1");
        c1.setNumber("12345678");

        CardEntity c2 = new CardEntity();
        c2.setId(2);
        c2.setCardLimit(0.0);
        c2.setName("Test Card 2");
        c2.setNumber("12345678");

        CardEntity c3 = new CardEntity();
        c3.setId(3);
        c3.setCardLimit(0.0);
        c3.setName("Test Card 3");
        c3.setNumber("12345678");

        return Stream.of(
                Arguments.of((Object) Arrays.asList(c1,c2,c3))
        );

    }

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

    }

    @ParameterizedTest
    @MethodSource(value = "cardsEntityData")
    public void testSuccessGetAllCards(List<CardEntity> cardList){

        Flux<CardEntity> source =  Flux.fromIterable(cardList);

        when(cardRepository.findAll()).thenReturn(source);

        cardList.stream().forEach(c->{
            when(cardMapper.toCardResource(c)).thenReturn((new CardMapperImpl()).toCardResource(c));
        });

        StepVerifier.create(cardService.getAllCards().log())
                .expectNextMatches(c->c.getId().equals(1))
                .expectNextCount(2)
                .verifyComplete();

    }

    @Test
    public void testSuccessCreateCard(){

        Card c1 = new Card();
        c1.setLimit(0.0);
        c1.setName("Test Card 1");
        c1.setNumber("12345678");

        CardEntity expectedCard = new CardEntity();
        expectedCard.setId(1);
        expectedCard.setCardLimit(0.0);
        expectedCard.setName("Test Card 1");
        expectedCard.setNumber("12345678");


        Card returnCard = (new CardMapperImpl()).toCardResource(expectedCard);

        when(cardRepository.save(any())).thenReturn(Mono.just(expectedCard));
        when(cardMapper.toCardResource(any())).thenReturn(returnCard);

        StepVerifier.create(cardService.createCard(c1))
                .expectNextMatches(c->c.getId().equals(1))
                .verifyComplete();

    }




}
