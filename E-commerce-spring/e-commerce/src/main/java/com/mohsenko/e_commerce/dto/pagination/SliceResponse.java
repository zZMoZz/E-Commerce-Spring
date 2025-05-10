package com.mohsenko.e_commerce.dto.pagination;

import lombok.Data;

import java.util.List;

// TODO: I don't understand this class probably.

@Data
public class SliceResponse<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private boolean hasNext;
}
