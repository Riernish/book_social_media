package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Author;

import ru.iliya.entities.Book;
import ru.iliya.entities.Comments;
import ru.iliya.repositories.BaseRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class BookSearchService {

    @Autowired
    BaseRepository baseRepository;

    public Book findBookById(String bookId) {
        return baseRepository.findBookByBookID(Integer.parseInt(bookId));
    }

    public Book findBookByTitle(String title) {
        return baseRepository.getByTitle(title);
    }

    public List<Book> findBooksByAuthor(String firstName, String lastName) {
        return baseRepository.findByAuthor(firstName, lastName);
    }

    public List <Book> findBooksByGenre(String genre) {
        return baseRepository.findByGenre(genre);
    }

    public List <Book> findBooksByTitleAuthorGenre(String title, String authorName, String genre) {
        boolean titleIsEmpty = title.isEmpty();
        boolean authorIsEmpty = authorName.isEmpty();
        boolean genreIsEmpty = genre.isEmpty();
        String[] names = authorName.split(" ", 2);

        if (titleIsEmpty && authorIsEmpty && !genreIsEmpty) {
            return baseRepository.findByGenre(genre);
        }
        if (titleIsEmpty && !authorIsEmpty && genreIsEmpty) {
            return baseRepository.findByAuthor(names[0], names[1]);
        }
        if (!titleIsEmpty && authorIsEmpty && genreIsEmpty ) {
            return baseRepository.findBooksByTitle(title);
        }
        if (titleIsEmpty && !authorIsEmpty && !genreIsEmpty) {
            List <Author> authors = new ArrayList<>();
            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);
            authors.add(author);
            return baseRepository.findBooksByAuthorsAndGenre(authors, genre);
        }
        if (!titleIsEmpty && authorIsEmpty && !genreIsEmpty) {
            return baseRepository.findBooksByTitleAndGenre(title, genre);
        }
        if (!titleIsEmpty && !authorIsEmpty ) {
            List <Author> authors = new ArrayList<>();
            Author author = new Author();
            author.setFirstName(names[0]);
            author.setLastName(names[1]);
            authors.add(author);
            if (genreIsEmpty) {
                return baseRepository.findBooksByTitleAndAuthors(title, authors);
            }
            else { return baseRepository.findBooksByTitleAndAuthorsAndGenre(title, authors, genre);}
        }
        return new ArrayList<>();
    }

    public List<Comments> getComments(String bookId) {
        return baseRepository.findCommentsByBookId(Integer.parseInt(bookId));
    }

    public void addComment(String bookId, String comment) {
        baseRepository.setCommentByBookId(Integer.parseInt(bookId), comment);
    }
}