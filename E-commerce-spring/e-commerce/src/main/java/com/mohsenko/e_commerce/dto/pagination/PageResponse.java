package com.mohsenko.e_commerce.dto.pagination;

import lombok.Data;

// TODO: I don't understand this class probably.


@Data
public class PageResponse<T> extends SliceResponse<T>  {
    private long totalElements;
    private int totalPages;
}
