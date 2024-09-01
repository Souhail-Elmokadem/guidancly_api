package com.guidancly.guidancly_api.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi<T>{
    Collection<T> items;
    Integer totalItems;
}
