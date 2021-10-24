package com.kodluyoruz.libraryapi.service;


import com.kodluyoruz.libraryapi.exception.NotFoundException;
import com.kodluyoruz.libraryapi.model.dto.BookDto;
import com.kodluyoruz.libraryapi.model.dto.PageableBookDto;
import com.kodluyoruz.libraryapi.model.entity.Book;
import com.kodluyoruz.libraryapi.model.request.GetPageableRequest;
import com.kodluyoruz.libraryapi.model.request.UpdateAvailableRequest;
import com.kodluyoruz.libraryapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.kodluyoruz.libraryapi.model.mapper.BookMapper.BOOK_MAPPER;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public BookDto getBookDto(int id) {
        Book book = getBook(id);
        return BOOK_MAPPER.toBookDto(book);
    }

    private Book getBook(int id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found!"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAvailability(int id, UpdateAvailableRequest request) {
        repository.setAvailability(id, request.getAvailability());
    }

    public void removeBook(int id) {
        repository.deleteById(id);
    }

    public PageableBookDto getBookDtoList(GetPageableRequest request) {
        Page<Book> bookPage = repository.findAll(PageRequest.of(request.getCurrentPage(), request.getPageSize()));
        return PageableBookDto.builder()
                .bookDtoList(BOOK_MAPPER.toBookDtoList(bookPage.getContent()))
                .totalElements(bookPage.getTotalElements())
                .totalPages(bookPage.getTotalPages())
                .build();
    }
}
