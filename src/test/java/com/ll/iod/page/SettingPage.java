package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SettingPage {
    WebDriver driver;
   private ReportGenerator reportGenerator=null;
    @FindBy(xpath = "//div[@data-automation='tenant-approval-submenu-item']")
    private WebElement tenantApproval;
    @FindBy(xpath = "//div[@data-automation='audit-trail-submenu-item']")
    private WebElement auditTrail;
    @FindBy(xpath = "//div[@data-automation='confidence-score-submenu-item']")
    private WebElement confidenceScore;
    @FindBy(xpath = "//div[@data-automation='data-extraction-submenu-item']")
    private WebElement dataExtraction;
    @FindBy(xpath = "//div[@data-automation='notification-webhooks-submenu-item']")
    private WebElement notiWebhook;
    @FindBy(xpath = "//div[@data-automation='rate-limits-submenu-item']")
    private WebElement rateLimit;
    @FindBy(xpath = "//div[@data-automation='stacking-order-submenu-item']")
    private WebElement stackingOrder;
    @FindBy(xpath = "//div[@data-automation='taxonomy-submenu-item']")
    private WebElement taxonomy;
    @FindBy(xpath = "//div[@data-automation='tentants-submenu-item']")
    private WebElement tenants;
    @FindBy(xpath = "//div[@data-automation='automated-rules-submenu-item']")
    private WebElement atmdRls;
    @FindBy(xpath = "//core-icon[@data-automation='user-management-icon']")
    private WebElement usrmgmnt;
    @FindBy(xpath = "//core-icon[@data-automation='pipeline-clarifi-icon']")
    public WebElement pipeline;
    public AccountPage stngToUsrPg()
    {
        SeleniumUtils.doClick(driver,usrmgmnt);
        return new AccountPage(driver);
    }


    public SettingPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    public SettingPage(WebDriver driver,ReportGenerator reportGenerator) {

        this.driver = driver;
        this.reportGenerator = reportGenerator;
        PageFactory.initElements(driver, this);

    }
    public HomePage clickPipeline(){
        reportGenerator.logAndCaptureScreen("navigating to IoD pipeline page","clickPipeline",SeleniumUtils.doClick(driver,pipeline),driver);
        return new HomePage(driver);
    }
    public AuditTrailPage clkAdtTrl(){
        reportGenerator.logAndCaptureScreen("clicked on Audit trail","clkAdtTrl",SeleniumUtils.doClick(driver, auditTrail),driver);

         return new AuditTrailPage(driver,reportGenerator);
    }
    public ConfidenceScorePage clkConfScr(){
        reportGenerator.logAndCaptureScreen("clicked on confidence score","clkConfScr",SeleniumUtils.doClick(driver, confidenceScore),driver);
        return new ConfidenceScorePage(driver,reportGenerator);
    }
    public DataExtractionPage clkDePg(){
        reportGenerator.logAndCaptureScreen("clicked on data ext page","clkDePg",SeleniumUtils.doClick(driver, dataExtraction),driver);
        return new DataExtractionPage(driver,reportGenerator);
    }
    public NotificationWbhkPage clkNtfWbhkPg(){
        SeleniumUtils.doClick(driver,notiWebhook);

        return new NotificationWbhkPage(driver,reportGenerator);
    }
    public TaxonomyPage clktxnmy(){
        SeleniumUtils.doClick(driver,taxonomy);
        return new TaxonomyPage(driver,reportGenerator);

    }
    public TenantApprovalPage clktntAprl(){
        SeleniumUtils.doClick(driver,tenantApproval);
        return new TenantApprovalPage(driver,reportGenerator);


    }
    public RateLimitPage clkrteLmt(){
        SeleniumUtils.doClick(driver,rateLimit);
        return new RateLimitPage(driver,reportGenerator);


    }
    public TenantsPage clktnts(){
        SeleniumUtils.doClick(driver,tenants);
        return new TenantsPage(driver,reportGenerator);


    }
    public StackingOrderPage clkStkOrdPg(){
        SeleniumUtils.doClick(driver,stackingOrder);
        return new StackingOrderPage(driver,reportGenerator);
    }
    public AtmtdRlsPage clkAtmdRls(){
        SeleniumUtils.doClick(driver,atmdRls);
        return new AtmtdRlsPage(driver,reportGenerator);
    }
    public boolean vrfystngsopt(String setOpt) {


        boolean flag = true;
        List<String> setoptlst = new ArrayList<String>(Arrays.asList(setOpt.split(",")));
        Collections.sort(setoptlst);
        WebElement SettingsOption;
        List<WebElement> opt = driver.findElements(By.xpath("//div[@data-automation='body-menu']/div"));

        int j = 0;
        for (int i = 1; i <= opt.size(); i++) {
            String path = "(//div[@data-automation='body-menu']/div)[" + i + "]";
            SettingsOption= driver.findElement(By.xpath(path)) ;
            System.out.println(SettingsOption.getText()+" "+setoptlst.get(j));
            if (setoptlst.get(j).equals(SettingsOption.getText())) {
                flag &= true;
            } else {
                flag = false;
            }
            j++;

        }
        return flag;

    }
    public boolean vrfySupportSettings(String tenantType,String opt)
    {
        boolean flag= true;
        if(tenantType.equalsIgnoreCase("admin"))
        {

            flag&=SeleniumUtils.isDisplayed(driver,tenantApproval,true);
        }
        List<String> optList = new ArrayList<String>(Arrays.asList(opt.split(",")));
        Collections.sort(optList);
        WebElement SettingsOption;
        List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@data-automation='body-menu']/div"));
        int j= listOfElements.size()-1;
        for (int i = listOfElements.size(); i > 0; i--)
        {
            try
            {
                String path = "//div[@data-automation='body-menu']/div" + "[" + (i) + "]";
                SettingsOption = driver.findElement(By.xpath(path));

                System.out.println(SettingsOption.getText()+" "+optList.get(j));
                if(SettingsOption.getText().equalsIgnoreCase(optList.get(j)))
                {
                    //System.out.println(SettingsOption.getText()+" "+optList.get(i));
                    flag&= true;
                }
                else
                {
                    flag&= false;
                }
                j--;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

}
