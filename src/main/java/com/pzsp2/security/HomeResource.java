package com.pzsp2.security;

import com.pzsp2.teacher.Teacher;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

  @GetMapping("/home")
  public String home() {
    return ("<h1> Willkommensseite für nicht eingeloggte </h1>");
  }
}
