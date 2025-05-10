package com.mohsenko.e_commerce.mapper;

import com.mohsenko.e_commerce.dto.pagination.PageResponse;
import com.mohsenko.e_commerce.dto.pagination.SliceResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

@Mapper(componentModel = "spring")
public abstract class PageMapper {
    // TODO: I don't understand generic syntax in this method
    public <T> SliceResponse<T> toSliceResponse(Slice<T> slice) {
        SliceResponse<T> response = new SliceResponse<>();
        response.setContent(slice.getContent());
        response.setPageNumber(slice.getNumber());
        response.setPageSize(slice.getSize());
        response.setHasNext(slice.hasNext());
        return response;
    }

    // TODO: I don't understand generic syntax in this method
    public <T> PageResponse<T> toPageResponse(Page<T> page) {
        PageResponse<T> response = new PageResponse<>();
        // Set base pagination info
        response.setContent(page.getContent());
        response.setPageNumber(page.getNumber());
        response.setPageSize(page.getSize());
        response.setHasNext(page.hasNext());

        // Set additional totals information
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());

        return response;
    }
}
