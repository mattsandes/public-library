package io.github.library.services;

import io.github.library.exceptions.ResourceNotFoundException;
import io.github.library.model.Book;
import io.github.library.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(UUID id) {
        if(id == null) {
            throw new ResourceNotFoundException("Informe um id válido");
        }

        return bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Nenhum livro foi encontrado com o id informado"));
    }

    public Book create(Book book) {
        if(book == null) {
            throw new IllegalArgumentException("Por favor, não passar informações vazias!");
        }

        return bookRepository.save(book);
    }

    public Book update(Book toUpdateBook) {
        if(toUpdateBook == null) {
            throw new IllegalArgumentException(
                    "O livro para ser atualizado não pode ser vazio!");
        }

        var foundBook = bookRepository.findById(toUpdateBook.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Não foi possivel encontra o livro para atualizar!"));

        foundBook.setTitle(toUpdateBook.getTitle());
        foundBook.setSubtitle(toUpdateBook.getSubtitle());
        foundBook.setAuthors(toUpdateBook.getAuthors());
        foundBook.setPublisher(toUpdateBook.getPublisher());
        foundBook.setPublishDate(toUpdateBook.getPublishDate());

        return bookRepository.save(foundBook);
    }

    public void deleteById(UUID id) {
        if(id == null) {
            throw new ResourceNotFoundException(
                    "Não foi possivel encontra o livro!");
        }

        bookRepository.deleteById(id);
    }
}
