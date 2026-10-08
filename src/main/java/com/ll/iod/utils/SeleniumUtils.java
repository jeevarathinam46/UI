package com.ll.iod.utils;

import com.ll.iod.constants.IodConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class SeleniumUtils {

    private SeleniumUtils() {
        //prevent instantiation of this object from outside of this class
    }

    /**
     * To send the Data for the Particular field
     *
     * @param element Holds the Web element
     */
    public static boolean confScrSlider(WebDriver webDriver, WebElement element, String attValue,Integer val){
        boolean flag = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.elementToBeClickable(element));
            webWait.until(ExpectedConditions.visibilityOf(element));
            Actions s = new Actions(webDriver);
        s.dragAndDropBy(element, (val - (Integer.parseInt(element.getAttribute(attValue)))) * 22 / 10, 0);
        s.build().perform();
            flag = true;
        } catch (Exception ex) {
            Reporter.log("Exception occured while entering value into an UI component " + ex.getMessage());
            throw ex;
        }
        return flag;
    }
    public static boolean sendKeys(WebDriver webDriver, WebElement element, String keyValue) {
        boolean flag = false;
        try {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            js.executeScript(IodConstants.JS_ARG_SCROLL_VIEW, element);
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.elementToBeClickable(element));
            webWait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            Actions action = new Actions(webDriver);
            action.moveToElement(element).doubleClick().build().perform();
            element.sendKeys(keyValue);
            flag = true;
        } catch (Exception ex) {
            Reporter.log("Exception occured while entering value into an UI component " + ex.getMessage());
            throw ex;
        }
        return flag;
    }

    /**
     * This method is will performs click action on the element
     *
     * @param element Holds the Web element
     * @return return the boolean value
     */
    public static boolean doClick(WebDriver webDriver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        boolean isClicked = false;
        try {
            js.executeScript(IodConstants.JS_ARG_SCROLL_VIEW, element);
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.elementToBeClickable(element));
            webWait.until(ExpectedConditions.visibilityOf(element));

            Actions action = new Actions(webDriver);
            action.moveToElement(element).click().build().perform();
            isClicked = true;
        } catch (Exception ex) {
            Reporter.log("Exception occured while doClick event " + ex.getMessage());
            throw ex;
        }
        return isClicked;
    }

    public static boolean doClickIcon(WebDriver webDriver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        boolean isClicked = false;
        try {
            js.executeScript(IodConstants.JS_ARG_CLICK, element);
            isClicked = true;
        } catch (Exception ex) {
            Reporter.log("Exception occured while doClick event " + ex.getMessage());
            throw ex;
        }
        return isClicked;
    }

    public static boolean doClickCondn(WebDriver webDriver, WebElement element, boolean condn) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        boolean isClicked = false;
        int flag = 1;
        if (condn) {
            try {
                js.executeScript(IodConstants.JS_ARG_SCROLL_VIEW, element);
                WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
                webWait.until(ExpectedConditions.elementToBeClickable(element));
                webWait.until(ExpectedConditions.visibilityOf(element));
                Actions action = new Actions(webDriver);
                action.moveToElement(element).click().build().perform();
                isClicked = true;
                flag = Boolean.compare(isClicked, condn);
            } catch (Exception ex) {
                isClicked = false;
                Reporter.log("Exception occured while doClick event " + ex.getMessage());
                throw ex;
            }
        } else {
            flag = Boolean.compare(false, condn);
        }
        //  System.out.println(element+"-"+flag);
        if (flag == 0)
            return true;
        else return false;
    }

    public static boolean doHover(WebDriver webDriver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        boolean isClicked = false;
        try {
            js.executeScript(IodConstants.JS_ARG_SCROLL_VIEW, element);
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.elementToBeClickable(element));
            webWait.until(ExpectedConditions.visibilityOf(element));
            Actions action = new Actions(webDriver);
            action.moveToElement(element).build().perform();
            isClicked = true;
        } catch (Exception ex) {
            System.out.println("Exception occured while doClick event " + ex.getMessage());
            throw ex;
        }
        return isClicked;
    }
    public static boolean doHoverTwo(WebDriver webDriver, WebElement element)  {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        boolean isClicked = false;
        try {
            //js.executeScript(IodConstants.JS_ARG_SCROLL_VIEW, element);
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.elementToBeClickable(element));
            webWait.until(ExpectedConditions.visibilityOf(element));
            String script = "var element = arguments[0];" +"var mouseEvent = document.createEvent('MouseEvents');" +"mouseEvent.initMouseEvent('mouseover', true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +"element.dispatchEvent(mouseEvent);";
            ((JavascriptExecutor) webDriver).executeScript(script, element);
           // Actions action = new Actions(webDriver);
          //  action.moveToElement(element).clickAndHold().perform();
            isClicked = true;
        } catch (Exception ex) {
            System.out.println("Exception occured while doClick event " + ex.getMessage());
            //throw ex;
        }
        return isClicked;
    }

    /**
     * This method helps to get text from web element
     *
     * @param element Holds the web element
     * @return Returns the String
     */
    public static String getAttribute(WebDriver webDriver, WebElement element, String att) {
        String actualValue = null;
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        try {
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.elementToBeClickable(element));
            webWait.until(ExpectedConditions.visibilityOf(element));
            js.executeScript(IodConstants.JS_ARG_SCROLL_VIEW, element);
            actualValue = element.getAttribute(att);
        } catch (Exception ex) {
            Reporter.log("Exception occured while getValue event " + ex.getMessage());
            throw ex;
        }
        return actualValue;
    }

    public static String getValue(WebDriver webDriver, WebElement element) {
        String actualValue = null;
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        try {
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.elementToBeClickable(element));
            webWait.until(ExpectedConditions.visibilityOf(element));
            js.executeScript(IodConstants.JS_ARG_SCROLL_VIEW, element);
            actualValue = element.getText();
        } catch (Exception ex) {
            Reporter.log("Exception occured while getValue event " + ex.getMessage());
            throw ex;
        }
        return actualValue;
    }

    public static String getValue(WebDriver webDriver, String name, WebElement element) {
        String actualValue = null;
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        try {
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.elementToBeClickable(element));
            webWait.until(ExpectedConditions.visibilityOf(element));
            js.executeScript(IodConstants.JS_ARG_SCROLL_VIEW, element);
            actualValue = element.getAttribute(name);
        } catch (Exception ex) {
            Reporter.log("Exception occured while getValue event " + ex.getMessage());
            throw ex;
        }
        return actualValue;
    }

    /**
     * jquery load time to load the page
     *
     * @param driver current active driver
     */
    public static void waitForJQueryToLoad(WebDriver driver) {
        try {
            WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue("loadingTime"))));
            webWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
            webWait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return jQuery.active==0").equals(true));
        } catch (Exception e) {
            Reporter.log("Exceptino occured while waiting for JQuery to load " + e.getMessage());
        }
    }

    /**
     * This method helps to select value from list
     *
     * @param listOfElements Holds the list of web element
     * @param expValue       Holds the value to select
     * @return Returns boolean
     */
    public static boolean selectValueFromList(WebDriver webDriver, List<WebElement> listOfElements, String expValue) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            for (WebElement element : listOfElements) {
                if ((element.getAttribute(IodConstants.TEXTCONTENT)).trim().equalsIgnoreCase(expValue) && element.isDisplayed()) {
                    Actions action = new Actions(webDriver);
                    action.moveToElement(element);
                    action.click().build().perform();
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Reporter.log("Expected value [" + expValue + "] is not present in list");
            }
        } catch (Exception ex) {
            Reporter.log("Exception occured while doClick event " + ex.getMessage());
        }
        return flag;
    }

    /**
     * This method is used to validate the element displayed or not
     */
    public static boolean isDisplayed(WebDriver webDriver, WebElement element, String message) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            if (element.isDisplayed()) {
                Reporter.log("As expected [" + message + "] is displayed");
                flag = true;
            } else {
                Reporter.log("[" + message + "] is not displayed");
            }
        } catch (Exception e) {
            Reporter.log("Exception is occured while checking element is displayed or not " + e.getMessage());
        }
        //  System.out.println(element.isDisplayed()+"--"+message+"-"+flag);
        return flag;
    }

    public static boolean isDisplayed(WebDriver webDriver, WebElement element, boolean act) {
        waitForJQueryToLoad(webDriver);
        int flag = 0;
        try {
            element.isDisplayed();
            flag = Boolean.compare(true, act);
            Reporter.log("Expected element is displayed");
        } catch (NoSuchElementException e) {
            flag = Boolean.compare(false, act);
        }
        if (flag == 0) return true;
        else return false;
    }

    public static boolean isEnabled(WebDriver webDriver, WebElement element) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            if (element.isEnabled()) {
                Reporter.log("As expected element is enabled");
                flag = true;
            } else {
                Reporter.log("Element is not enabled");
            }
        } catch (Exception e) {
            Reporter.log("Exception is occured while checking element is displayed or not " + e.getMessage());
        }
        return flag;
    }

    public static boolean isEnabled(WebDriver webDriver, WebElement element, boolean act) {
        waitForJQueryToLoad(webDriver);
        int flag = 1;
        try {

            if (element.isEnabled()) {
                Reporter.log("As expected element is enabled");
                flag = Boolean.compare(true, act);
            } else {
                flag = Boolean.compare(false, act);
                Reporter.log("Element is not enabled");
            }
        } catch (Exception e) {
            Reporter.log("Exception is occured while checking element is displayed or not " + e.getMessage());
        }
       // System.out.println(element+"-"+flag);
        if (flag == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSelected(WebDriver webDriver, WebElement element, boolean act) {
        waitForJQueryToLoad(webDriver);
        int flag = 1;
        try {

            if (element.isSelected()) {
                Reporter.log("As expected element is selected");
                flag = Boolean.compare(true, act);
            } else {
                flag = Boolean.compare(false, act);
                Reporter.log("Element is not selected");
            }
        } catch (Exception e) {
            Reporter.log("Exception is occured while checking element is displayed or not " + e.getMessage());
        }
        // System.out.println(element+"-"+flag);
        if (flag == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyAttribute(WebDriver webDriver, WebElement element, String att, String message) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            if (getAttribute(webDriver, element, att).equalsIgnoreCase(message)) {
                Reporter.log("As expected [" + message + "] is displayed");
                flag = true;
            } else {
                Reporter.log("[" + message + "] is not displayed");
            }
        } catch (Exception e) {
            Reporter.log("Exception is occured while checking element is displayed or not " + e.getMessage());
        }
       // System.out.println(getAttribute(webDriver, element, att) + "-" + element + "-" + flag);
        return flag;
    }

    public static boolean verifyText(WebDriver webDriver, WebElement element, String message) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            if (getValue(webDriver, element).equalsIgnoreCase(message)) {
                Reporter.log("As expected [" + message + "] is displayed");
                flag = true;
            } else {
                Reporter.log("[" + message + "] is not displayed");
            }
        } catch (Exception e) {
            Reporter.log("Exception is occured while checking element is displayed or not " + e.getMessage());
        }
       // System.out.println(getValue(webDriver, element) + "-" + element + "-" + flag);
        return flag;
    }

    public static boolean verifyText(WebDriver webDriver, WebElement element, String attNme, String message) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            if (getValue(webDriver, attNme, element).equalsIgnoreCase(message)) {
                Reporter.log("As expected [" + message + "] is displayed");
                flag = true;
            } else {
                Reporter.log("[" + message + "] is not displayed");
            }
        } catch (Exception e) {
            Reporter.log("Exception is occured while checking element is displayed or not " + e.getMessage());
        }
        //  System.out.println(getValue(webDriver, element)+"-"+element+"-"+flag);
        return flag;
    }

    public static boolean verifyCopiedText(WebDriver webDriver, WebElement element, WebElement clpElement) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        String actualCopiedText = null;
        try {
            doubleClick(webDriver,clpElement);
           //System.out.println("doclick icon " + doClick(webDriver, clpElement));

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            actualCopiedText = (String) clipboard.getData(DataFlavor.stringFlavor);

            System.out.println("actual copied text " + actualCopiedText);
            if (getValue(webDriver, element).equalsIgnoreCase(actualCopiedText)) {
                Reporter.log("As expected and copied clipboard message matches");
                //System.out.println("As expected and copied clipboard message  match");
                flag = true;
            } else {
                Reporter.log("As expected and copied clipboard message doesn't match");
             //  System.out.println("As expected and copied clipboard message doesn't match");
            }
        } catch (Exception e) {
            Reporter.log("Exception is occured while checking element is displayed or not " + e.getMessage());
           // System.out.println(e.getMessage());
        }
        System.out.println(element + "=" + actualCopiedText + "-" + getValue(webDriver, element) + "-" + flag);
        return flag;
    }

    /**
     * This method is used to select an option
     */
    public static boolean selectFromComboBox(WebDriver webDriver, WebElement element, String textValue) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
            if (element.isDisplayed() || element.isEnabled()) {
                List<WebElement> options = element.findElements(By.tagName("option"));
                for (WebElement option : options) {
                    if (textValue.equalsIgnoreCase(option.getText().trim())) {
                        option.click();
                        Reporter.log("[" + textValue + "] is selected");
                        flag = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Reporter.log("Exception occured while fnSelectFromComboBox event " + e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to check whether the alert is present in the web page or
     * not.
     *
     * @return true, if is alert present in the web page
     */
    public static boolean isAlertPresent(WebDriver webDriver) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(50));
            wait.until(ExpectedConditions.alertIsPresent());
            webDriver.switchTo().alert();
            flag = true;
        } catch (Exception e) {
            Reporter.log("Exception occured while handling alert " + e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to accept the alert messages
     *
     * @param webDriver used to get the driver object
     * @return true, if is alert present and accepted
     */
    public static boolean acceptAlert(WebDriver webDriver, String expAlertMessage) {
        boolean flag = true;
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            String actAlertMessage = alert.getText();
            if (expAlertMessage != null) {
                if (expAlertMessage.equalsIgnoreCase(actAlertMessage)) {
                    alert.accept();
                } else {
                    flag = false; //Alert is prompted but not met with condition. returning false
                }
            } else {
                alert.accept(); //Accepting the alert without comparing alert message
            }
        } catch (Exception e) {
            Reporter.log("Exception occured while handling alert " + e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to switch to a particular window
     *
     * @param webDriver used to get the driver object
     * @param title     holds the title of window
     * @return true, if is switched to a window
     */
    public static boolean switchToWindow(WebDriver webDriver, String title) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        Set<String> availableWindows = webDriver.getWindowHandles();
        if (!availableWindows.isEmpty()) {
            try {
                for (String windowId : availableWindows) {
                    if (webDriver.switchTo().window(windowId).getTitle().equals(title)) {
                        flag = true; // identified the window and set the flag to true
                        break;
                    }
                }
            } catch (Exception e) {
                Reporter.log("Exception occured while handling switch to window " + e.getMessage());
            }
        }
        return flag;
    }

    /**
     * This method used to double click on a particular element
     *
     * @param webDriver used to get the driver object
     * @param ele       holds web element
     * @return true, if it performs double click
     */
    public static boolean doubleClick(WebDriver webDriver, WebElement ele) {
        waitForJQueryToLoad(webDriver);
        boolean flag = false;
        try {
            if (ele.isDisplayed()) {
                Actions action = new Actions(webDriver);
                action.doubleClick(ele).build().perform();
                flag = true;
            }
        } catch (Exception e) {
            Reporter.log("Exception occured while fnDoubleClick event " + e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to right click on a particular element
     *
     * @param webDriver used to get the driver object
     * @param ele       holds web element
     * @return true, if it performs right click
     */
    public static boolean rightClick(WebDriver webDriver, WebElement ele) {
        boolean flag = false;
        try {
            if (ele.isDisplayed()) {
                Actions action = new Actions(webDriver);
                action.contextClick(ele).build().perform();
                flag = true;
            }
        } catch (Exception e) {
            Reporter.log("Exception occured while fnRightClick event " + e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to switch to default frame
     *
     * @param webDriver used to get the driver object
     * @return true, if it switches to default frame
     */
    public static boolean wSwitchToFrame(WebDriver webDriver) {
        boolean flag = false;
        try {
            webDriver.switchTo().defaultContent();
            flag = true;
        } catch (Exception e) {
            Reporter.log("Exception occured while switchToFrame event " + e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to switch to default frame
     *
     * @param webDriver  used to get the driver object
     * @param sFrameName holds name of the frame
     * @return true, if it switches to a particular frame
     */
    public static boolean switchToFrame(WebDriver webDriver, String sFrameName) {
        boolean flag = false;
        try {
            webDriver.switchTo().frame(sFrameName);
            flag = true;
        } catch (Exception e) {
            Reporter.log("Exception occured while switchToFrame event " + e.getMessage());
        }
        return flag;
    }

    /**
     * This method used to add list of string values to list
     *
     * @param element contains list of webelements
     * @return List<String>
     */
    public static List<String> getWebElementList(List<WebElement> element) {
        List<String> webElementList = new ArrayList<>();
        try {
            for (WebElement ele : element) {
                webElementList.add(ele.getText().trim());
            }
        } catch (Exception e) {
            Reporter.log("Exception occured while getWebElementList event " + e.getMessage());
        }
        return webElementList;
    }

    /**
     * This method used to verify drop down values
     *
     * @param sValues holds list of actual values
     * @return true, if it that value is present in the list
     */
    public static boolean verifyDropdownValues(WebElement ele, String sValues) {
        boolean flag = false;
        try {
            String[] expList = sValues.split(";");
            Select select = new Select(ele);
            List<WebElement> options = select.getOptions();
            for (WebElement we : options) {
                for (String element : expList) {
                    if (we.getText().equals(element)) {
                        flag = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Reporter.log("Exception occured while fnVerifyDropdownValues event " + e.getMessage());
        }
        return flag;
    }

    public static boolean selectVisibleTxt(WebDriver driver, List<WebElement> ele, List<String> value) {
        boolean flag = false;
        try {
            for (String s : value) {
                for (WebElement el : ele) {
                    if (getValue(driver, el).contains(s)) {
                        el.click();
                        break;
                    }
                }
            }
            flag = true;
        } catch (Exception e) {
            Reporter.log("Exception occured while fnVerifyDropdownValues event " + e.getMessage());
        }
        return flag;
    }


    public static void uploadFile(WebDriver driver, String filePath) {
        try {
            Path file = new File(filePath).toPath();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            if (Files.exists(file)) {
                String autoITExecutable = System.getProperty("user.dir") + File.separator + ConfigPropertyLoader.getConfigValue("testFilePath") + File.separator + "AutoITFileUploadWithParam.exe " + filePath;
                Runtime.getRuntime().exec(autoITExecutable);
                CommonUtils.sleepForAWhile(20000);
                Reporter.log("AutoIT script to upload : " + filePath);
            } else {
                Assert.fail("File path is not correct/file doesnt exist " + filePath);
            }
        } catch (Exception e) {
            Assert.fail("Failed to run AutoIT script : " + e.getMessage());

        }
    }

    public static void browseAndUploadFile(WebDriver driver, WebElement element, String fileName) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).click(element).build().perform();
            Reporter.log("filePath" + fileName);
            uploadFile(driver, fileName);
            Reporter.log("Successfully added the " + fileName);
            waitForJQueryToLoad(driver);
            CommonUtils.sleepForAWhile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void explicitWait(WebDriver webDriver, WebElement element) {
        try {
            WebDriverWait webWait = new WebDriverWait(webDriver, Duration.ofSeconds(Integer.parseInt(ConfigPropertyLoader.getConfigValue(IodConstants.JS_LOADING_TIME))));
            webWait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

