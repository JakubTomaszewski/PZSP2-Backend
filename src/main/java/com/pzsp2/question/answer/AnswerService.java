package com.pzsp2.question.answer;

import com.pzsp2.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer getAnswerById(Long id) {
        Optional<Answer> answerOptional = answerRepository.findById(id);
        if (answerOptional.isPresent())
            return answerOptional.get();
        else
            throw new ApiRequestException("Answer doesn't exist");
    }
}
