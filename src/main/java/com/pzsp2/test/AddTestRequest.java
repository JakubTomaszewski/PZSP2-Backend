package com.pzsp2.test;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class AddTestRequest {
  @NotNull private List<Long> questionsId;

  @NotNull private Long teacherId;
  @NotNull private Date endDate;

  private Date startDate;
}
