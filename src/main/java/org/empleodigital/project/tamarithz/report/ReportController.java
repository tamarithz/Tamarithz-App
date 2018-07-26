package org.empleodigital.project.tamarithz.report;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class ReportController {

    private final ReportRepository reports;

    public ReportController(ReportRepository reportService) {
        this.reports = reportService;
    }

    @GetMapping("/report.html")
    public String showReportList(Map<String, Object> model) {
        Reports reports = new Reports();
        reports.getReportList().addAll(this.reports.findAll());
        model.put("reports", reports);
        return "reports/reportList";
    }

    @GetMapping({ "/reports" })
    public @ResponseBody Reports showResourcesReportList() {
        // Con esto se devuelve un objeto de tipo 'Reports' mas que una coleccion de 'Report'
        // para que sea mas simple el mapeo de objetos JSon
        Reports reports = new Reports();
        reports.getReportList().addAll(this.reports.findAll());
        return reports;
    }

}
