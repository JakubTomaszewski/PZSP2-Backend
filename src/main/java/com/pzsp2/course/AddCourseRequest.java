package com.pzsp2.course;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddCourseRequest {
    @NotNull
    private String code;

    @NotNull
    private String name;
}
