package com.pzsp2.question;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModifyQuestionRequest extends QuestionRequest {

  @NotNull private Long questionId;

  public ModifyQuestionRequest() {
    super();
  }

  public ModifyQuestionRequest(
      String courseCode,
      String type,
      String content,
      Long teacherId,
      List<String> answers,
      List<Boolean> areCorrect,
      Long questionId) {
    super(courseCode, type, content, teacherId, answers, areCorrect);
    this.questionId = questionId;
  }
}
