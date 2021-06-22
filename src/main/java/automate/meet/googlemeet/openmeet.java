package automate.meet.googlemeet;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class settingup {

    WebDriver driver;
    
    @RequestMapping(value = "startmeet")
    public void setupmeet() throws IOException, InterruptedException {
        geckosetup();
        
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter email: ");
        String email=in.readLine();
        System.out.println("Enter password: ");
        String pass=in.readLine();
        geturl(email,pass);
    }

    private void geckosetup() throws IOException {
        File geck=new File("geckodriver");
        System.setProperty("webdriver.firefox.driver", geck.getCanonicalPath());
        ffsetup();
    }

    private void ffsetup(){
        File newff= new File("firefox/firefox");
        FirefoxBinary binary=new FirefoxBinary(newff);

        // FirefoxProfile ffprof=new FirefoxProfile();

        FirefoxOptions ffopt=new FirefoxOptions();
        ffopt.setBinary(binary);
        // ffopt.setCapability("marionette", true);
        

        driver=new FirefoxDriver(ffopt);
    }

    private void geturl(String email,String pass)throws InterruptedException{
        String url="https://apps.google.com/intl/en-GB/meet/";
        String xpath_startmeeting="/html/body/header/div[1]/div/div[3]/div[1]/div/span[3]/a/button/span";
        String xpath_username="//input[@id=\"identifierId\"]";
        String xpath_next="/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/div/button/span";
        String xpath_pass="/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div/form/span/section/div/div/div[1]/div[1]/div/div/div/div/div[1]/div/div[1]/input";
        // String xpath_submit="/html/body/div[1]/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div/div[1]/div/div/button/span";
        
        Actions actions=new Actions(driver);

        WebDriverWait wait=new WebDriverWait(driver, 10000);
        driver.get(url);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath_startmeeting)));
        driver.findElement(By.xpath(xpath_startmeeting)).click();
        // WebElement element=wait.until(ExpectedConditions.elementToBeClickable(By.id("")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath_username)));
        driver.findElement(By.xpath(xpath_username)).sendKeys(email);
        driver.findElement(By.xpath(xpath_next)).click();
        
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_pass)));
        WebElement pswd= driver.findElement(By.xpath(xpath_pass));
        // actions.moveToElement(pswd).perform();
        pswd.sendKeys(pass);
        pswd.sendKeys(Keys.ENTER);
        // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_submit)));
        // WebElement submt= driver.findElement(By.xpath(xpath_submit));
        // actions.moveToElement(submt);
        // actions.click(submt);
        // actions.click();
        // submt.click();

    }
}    