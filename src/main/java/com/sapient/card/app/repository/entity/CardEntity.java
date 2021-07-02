package com.sapient.card.app.repository.entity;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("cards")
public class CardEntity {

    @Id
    private Integer id;
    private String name;
    private Integer cardNumber;
    private Double limit;

}
