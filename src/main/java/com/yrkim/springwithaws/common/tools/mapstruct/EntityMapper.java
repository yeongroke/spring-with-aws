package com.yrkim.springwithaws.common.tools.mapstruct;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

/**
* 역할 : MapStruct 공통 정의
* @param <D> Dto
* @param <E> Entity
* */
public interface EntityMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);
    List<D> toDto(List<E> entityList);

    /*
    * @BeanMapping NullValuePropertyMappingStrategy정책에 따라 null을 무시
    * @MappingTarget으로 매핑할 목표 설정
    * Dto의 값중 mull 값들은 Entity에 적용하지 않음
    * */
    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget E entity, D dto);
}
