package com.pzsp2.question;

import com.sun.istack.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class QuestionRequest {

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
