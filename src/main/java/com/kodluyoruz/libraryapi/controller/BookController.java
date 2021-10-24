package com.kodluyoruz.libraryapi.controller;

import com.kodluyoruz.libraryapi.model.dto.BookDto;
import com.kodluyoruz.libraryapi.model.dto.PageableBookDto;
import com.kodluyoruz.libraryapi.model.request.GetPageableRequest;
import com.kodluyoruz.libraryapi.model.request.UpdateAvailableRequest;
import com.kodluyoruz.libraryapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;

    @PutMapping("{id}/availability")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patchAvailability(@PathVariable int id,
                                  @Valid @RequestBody UpdateAvailableRequest request) {
        service.updateAvailability(id, request);
    }


    @DeleteMapping("{id}")
    public void removeBook(@PathVariable int id) {
        service.removeBook(id);
    }

    @GetMapping("{id}")
    public BookDto getBook(@PathVariable int id) {
        return service.getBookDto(id);
    }

    @GetMapping
    public PageableBookDto getBooks(GetPageableRequest request) {
        return service.getBookDtoList(request);
    }
}
