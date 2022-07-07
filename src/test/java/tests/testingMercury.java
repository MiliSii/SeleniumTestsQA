package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.Objects;



public class testingMercury {
    private WebDriver driver;
    //private String baseUrl;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\milicasi\\Downloads\\geckodriver-v0.31.0-win64\\geckodriver.exe");
        driver=new FirefoxDriver();

        driver.get("https://demo.guru99.com/test/newtours/");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 0)
    public void Title(){
        String expectedTitle = "Welcome: Mercury Tours";
        String URL1 = "https://demo.guru99.com/test/newtours/";
        driver.get(URL1);
        String actualTitle = driver.getTitle(); //captures the title of the page


        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test passed!");
        } else {
            System.out.println("Test Failed!");
        }

        System.out.println(driver.getTitle());


    }


    @Test(priority = 1)
    public void LogIn() throws InterruptedException{

        Thread.sleep(2000);
        WebElement element=driver.findElement(By.name("userName"));
        element.sendKeys("Mili");
        element.sendKeys(Keys.RETURN);


        WebElement element1=driver.findElement(By.name("password"));
        element1.sendKeys("lozinka");
        //element1.sendKeys(Keys.RETURN);
        System.out.println("Text Field and Password Field Set");
/*
        element.clear();
        element1.clear();
        System.out.println("Text Field and Ppassword Field Cleared");*/

        JavascriptExecutor jse1=(JavascriptExecutor) driver;
        jse1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//input[@name=\"submit\"]")));

        Thread.sleep(2000);
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ actualUrl);
        String expectedUrl="https://demo.guru99.com/test/newtours/login_sucess.php";

        Thread.sleep(2000);
        if(Objects.equals(actualUrl, expectedUrl)){
            System.out.println("Login Successfully");
        }else{System.out.println("Enter your userName and password correct");}

        Assert.assertEquals(expectedUrl,actualUrl);


    }

    @Test(priority = 2)
    public void CheckThatTourTripsAreDisplayed() throws InterruptedException {

        WebElement elem = driver.findElement(By.xpath("//img[@src=\"images/hdr_tips.gif\"]"));
        WebElement elem1 = driver.findElement(By.xpath("//img[@src=\"images/tip93.gif\"]"));
        String innerText = driver.findElement(By
                        .xpath("//table/tbody/tr/td[2]"
                                + "//table/tbody/tr[4]/td/"
                                + "table/tbody/tr/td[2]/"
                                + "table/tbody/tr[2]/td[1]/"
                                + "table[2]/tbody/tr[3]/td[2]/font"))
                .getText();
        Thread.sleep(2000);
        System.out.println(innerText);


        // Javascript executor to check image
        Boolean p = (Boolean) ((JavascriptExecutor)driver) .executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", elem);

        Boolean p1 = (Boolean) ((JavascriptExecutor)driver) .executeScript("return arguments[0].complete " + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", elem1);


        //verify if status is true
        if (p.equals(true) && p1.equals(true)&&innerText.equals("Always carry a travel first aid kit with bandages, antacids, aspirin, bee sting wipes, and other basic necessities."))
        {
            System.out.println("Tour tips  is present");
        } else {
            System.out.println("Tour tips is not present");
        }

        driver.quit();


    }
    @Test(priority = 3)
    public void destinationLink() throws InterruptedException{
        Thread.sleep(2000);

        JavascriptExecutor jse=(JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();",driver.findElement(By.linkText("Destinations")));
        Thread.sleep(2000);

        String actualLink = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ actualLink);
        String expectedLink = "https://demo.guru99.com/test/newtours/destinations.php";


        if(Objects.equals(actualLink, expectedLink)){
            System.out.println("link is clickable, the link is open:"+expectedLink);
        }else{
            System.out.println("the link can be clicked, but the wrong link is opened:"+actualLink+", the correct link should be:"+expectedLink);}

        Assert.assertEquals(actualLink, expectedLink);

    }

    @Test(priority = 4)
    public void printUrlOfCurrentPage() {
        String strUrl = driver.getCurrentUrl();
        System.out.println("Current Url is:" + strUrl);
    }

    @Test(priority = 5)
    public void Support() throws InterruptedException {
        Thread.sleep(2000);
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();",driver.findElement(By.linkText("SUPPORT")));
        Thread.sleep(2000);
        String actualLink1 = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ actualLink1);

        String expectedLink1 = "https://demo.guru99.com/test/newtours/support.php";


        if(Objects.equals(actualLink1, expectedLink1)){
            System.out.println("Support page is open:"+expectedLink1);
        }else{
            System.out.println("the link can be clicked, but the wrong link is opened:"+actualLink1+", the correct link should be:"+expectedLink1);}


    }

    @Test(priority =6)
    public void navigateToHomePage() throws InterruptedException{
        Support();

        Thread.sleep(2000);

        JavascriptExecutor jse1=(JavascriptExecutor) driver;
        jse1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//img[@src=\"images/home.gif\"]")));
        Thread.sleep(2000);

        String actualLink = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ actualLink);
        String expectedUrl="https://demo.guru99.com/test/newtours/index.php";
        Thread.sleep(2000);
        if(Objects.equals(actualLink,expectedUrl )){
            System.out.println("Home page is open");
        }else{System.out.println("Home page is not open");}

    }


    @Test(priority = 7)
    public void LogOut() throws InterruptedException {
        LogIn();
        Thread.sleep(2000);
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();",driver.findElement(By.linkText("SIGN-OFF")));
        Thread.sleep(2000);
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ actualUrl);
        String expectedUrl="https://demo.guru99.com/test/newtours/index.php";
        Thread.sleep(2000);
        if(Objects.equals(actualUrl, expectedUrl)){
            System.out.println("LogOut is successfull");
        }else{System.out.println("LogOut is not successfull");}

    }

    @Test(priority = 8)
    public void logoutFromHomePage() {
        Actions s = new Actions(driver);
        WebElement user = driver.findElement(By.linkText("SIGN-OFF"));
        s.moveToElement(user).build().perform();
        driver.findElement(By.linkText("SIGN-OFF")).click();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();

    }


}
