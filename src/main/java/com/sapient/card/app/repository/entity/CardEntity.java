package com.sapient.card.app.repository.entity;
/*
 *  Created by Girish Kumar CH on 7/2/21
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("cards")
public class CardEntity {

    @Id
    private Integer id;
    private String name;
    private String number;
    private Double cardLimit;

}
