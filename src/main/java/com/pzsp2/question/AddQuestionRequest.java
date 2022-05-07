package com.pzsp2.question;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AddQuestionRequest {

    @NotNull
    private String courseCode;

    @NotNull
    private String type;

    @NotNull
    private String content;

    @NotNull
    private Long teacherId;

    private List<String> answers;

    private List<Boolean> areCorrect;

}
