package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AtmtdRlsPage {
    WebDriver driver;
    ReportGenerator reportGenerator;
    public AtmtdRlsPage(WebDriver driver,ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);
    }
}
