package com.sapient.card.app.mapper;

import com.sapient.card.app.dto.Card;
import com.sapient.card.app.repository.entity.CardEntity;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {

    @Mappings({@Mapping(target = "cardLimit", source = "limit") })
    CardEntity toCardEntity(Card card);

    @Mappings({@Mapping(source = "cardLimit", target = "limit") })
    Card toCardResource(CardEntity cardEntity);


    public default List<Card> toCardListFromCardEntity(
            List<CardEntity> cardEntities) {

        return cardEntities.stream().map( e->toCardResource(e)).collect(Collectors.toList());
    }
}
