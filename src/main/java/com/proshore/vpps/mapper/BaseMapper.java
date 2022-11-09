package com.proshore.vpps.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<E,D> {

    E toEntity(D d);

    D toDTO(E e);

    default List<D> toDtos(List<E> e){
        return e.stream().map(data -> toDTO(data)).collect(Collectors.toList());
    }

    default List<E> toEntities(List<D> d){
        return d.stream().map(data -> toEntity(data)).collect(Collectors.toList());
    }
}
