package com.sapient.card.app.repository;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */

import com.sapient.card.app.repository.entity.CardEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends ReactiveCrudRepository<CardEntity,Long> {
}
