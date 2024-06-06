package com.azqore.tasks.mapper;

import org.springframework.stereotype.Component;


public interface Mapper<E, DTO> {
    
    DTO toDto(E c);

    E fromDto(DTO d);
}
