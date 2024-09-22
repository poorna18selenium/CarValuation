package com.registration.pageobject;

import com.registration.base.WebDriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class YourDetailsPage extends WebDriverBase {

    @FindBy(xpath = "/html/body/div[1]/wbac-app/div[1]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[1]/div[2]")
    private WebElement registrationNumber;
    @FindBy(xpath = "//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[1]/div[2]")
    private WebElement make;
    @FindBy(xpath = "//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[2]/div[2]")
    private WebElement model;
    @FindBy(xpath = "//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[3]/div[2]")
    private WebElement year;

    @FindBy(id = "btn-back")
    private WebElement backButton;
    @FindBy(id = "e2e-valueDifferentVehicle")
    private WebElement valueDifferentCar;

    public YourDetailsPage() {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 60), this);
    }

    public String getRegistrationNumber() {
        return registrationNumber.getText();
    }

    public String getMake() {
        return make.getText();
    }

    public String getModel() {
        return model.getText();
    }

    public String getYear() {
        return year.getText();
    }

    public CarSearchPage clickBackButtonToSearchPage() {
        driver.navigate().back();
        //backButton.click();
        //  valueDifferentCar.click();
        driver.navigate().refresh();
        return new CarSearchPage();
    }

    public Boolean isRegistrationNumberDisplayed() {
        try {
            driver.findElement(new By.ByXPath("/html/body/div[1]/wbac-app/div[1]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[1]/div[2]"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


}

