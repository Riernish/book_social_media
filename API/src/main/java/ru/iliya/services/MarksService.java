package ru.iliya.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.iliya.entities.Marks;
import ru.iliya.repositories.BaseRepository;

import java.util.List;

@Service
public class MarksService {
    @Autowired
    BaseRepository baseRepository;
    public Marks findByBookIdAndUserId(Integer bookId, Integer userId) {
        return baseRepository.findMarksByBookIdAndUserId(bookId, userId);
    }
    public List<Marks> findAllMarks(Integer bookId) {
        return baseRepository.findMarksByBookId(bookId);
    }
    public void setMarksByBookIdAndUserId(Integer bookId, Integer userId, Integer mark) {
        baseRepository.setMarksByBookIdAndUserId(bookId, userId, mark);
    }
    public void deleteMarkByBookIdAndUserId(Integer bookId, Integer userId) {
        baseRepository.deleteMarkByBookIdAndUserId(bookId, userId);
    };
}
