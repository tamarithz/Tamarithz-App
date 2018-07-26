package org.empleodigital.project.tamarithz.report;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reports {

    private List<Report> reports;

    @XmlElement
    public List<Report> getReportList() {
        if (reports == null) {
            reports = new ArrayList<>();
        }
        return reports;
    }

}
