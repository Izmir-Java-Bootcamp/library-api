package com.kodluyoruz.libraryapi.model.mapper;


import com.kodluyoruz.libraryapi.model.dto.BookDto;
import com.kodluyoruz.libraryapi.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    BookDto toBookDto(Book book);

    List<BookDto> toBookDtoList(List<Book> book);
}
