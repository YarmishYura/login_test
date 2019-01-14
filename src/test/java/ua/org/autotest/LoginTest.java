package ua.org.autotest;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class LoginTest{
    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=AddSession");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void userLogin(){
        WebElement loginField = driver.findElement(By.id("identifierId"));
        loginField.sendKeys("yarmish.yura12345@gmail.com");
        WebElement loginButton = driver.findElement(By.xpath("//span[contains(@class, 'RveJvd snByac')]"));
        loginButton.click();
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("12345yarmish");
        WebElement passwordButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'RveJvd snByac')]")));
        passwordButton.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement profileButton = driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div/div[2]/div/a/span"));
        profileButton.click();
        WebElement profileUser = driver.findElement(By.xpath("//*[@id=\"gb\"]/div[2]/div[6]/div[1]/div/div[2]"));
        String gmailUser = profileUser.getText();
        Assert.assertEquals("yarmish.yura12345@gmail.com", gmailUser);
    }

    @AfterClass
    public static void tearDown() {
        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"gb_71\"]"));
        logoutButton.click();
        driver.quit();
    }
}






