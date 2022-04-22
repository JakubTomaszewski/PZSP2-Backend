package com.pzsp2.question;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class AddQuestionRequest {

    @NotNull
    public String courseCode;

    @NotNull
    public String type;

    @NotNull
    public String content;

    @NotNull
    public Long teacher_id;


}
