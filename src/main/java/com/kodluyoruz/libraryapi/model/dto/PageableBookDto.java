package com.kodluyoruz.libraryapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageableBookDto {
    private List<BookDto> bookDtoList;
    private int totalPages;
    private long totalElements;
}
