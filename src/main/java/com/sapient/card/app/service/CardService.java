package com.sapient.card.app.service;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import com.sapient.card.app.repository.CardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class CardService {

    @Autowired
    private CardRepository cardRepository;


}
