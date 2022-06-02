package com.pzsp2.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CsvExportController {
    private final CsvExportService csvExportService;
    private final TestService testService;

    public CsvExportController(CsvExportService csvExportService, TestService testService) {
        this.csvExportService = csvExportService;
        this.testService = testService;
    }
    @RequestMapping(path = "/api/tests/downloadCsv")
    public void getTestSolutionsCsv(
            @RequestParam(value = "id") Long id,
            HttpServletResponse servletResponse) throws IOException {
        String testName = testService.getTestById(id).getName();
        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition","attachment; filename=\""+id+"_"+testName+".csv\"");
        csvExportService.writeSolutionsToCSV(servletResponse.getWriter(), id);
    }
}
