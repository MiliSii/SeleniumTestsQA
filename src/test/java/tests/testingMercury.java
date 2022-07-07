package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
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

    @Test
    public void Title(){
        String expectedTitle = "Welcome: Mercury Tours";
        String URL1 = "https://demo.guru99.com/test/newtours/";
        driver.get(URL1);
        String actualTitle = driver.getTitle(); //captures the title of the page


        /*System.out.println("Actual title is: " + actualTitle);//displays the title of the page
        System.out.println("Expected title is: " + expectedTitle);//displays the expected page title
        System.out.println("URL is: " + driver.getCurrentUrl());//displays the url of the page
       // System.out.println("Source Code is: " + driver.getPageSource()); //to fetch Source Code of Webpage*/



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
        System.out.println("Text Field Set");

        element.clear();
        element1.clear();
        System.out.println("Text Field Cleared");

        JavascriptExecutor jse1=(JavascriptExecutor) driver;
        jse1.executeScript("arguments[0].click();",driver.findElement(By.xpath("//input[@name=\"submit\"]")));

        Thread.sleep(2000);
        String strUrl3 = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ strUrl3);

        Thread.sleep(2000);
        if(Objects.equals(strUrl3, "https://demo.guru99.com/test/newtours/login_sucess.php")){
            System.out.println("Login Successfully");
        }else{System.out.println("Enter your userName and password correct");}



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
        String strUrl = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ strUrl);
        if(Objects.equals(strUrl, "https://demo.guru99.com/test/newtours/support.php")){
            System.out.println("link is clickable");
        }else{System.out.println("link is clickable");}


    }

    @Test(priority = 4)
    public void printUrlOfCurrentPage(){
        String strUrl = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ strUrl);
    }



    @Test(priority = 5)
    public void navigateToHomePage() throws InterruptedException{
        destinationLink();

        Thread.sleep(2000);

        JavascriptExecutor jse=(JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();",driver.findElement(By.xpath("//img[@src=\"images/home.gif\"]")));
        Thread.sleep(2000);

        String strUrl2 = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ strUrl2);
        Thread.sleep(2000);
        if(Objects.equals(strUrl2, "https://demo.guru99.com/test/newtours/index.php")){
            System.out.println("Home page is open");
        }else{System.out.println("Home page is not open");}

    }


    @Test(priority = 6)
    public void LogOut() throws InterruptedException {
        LogIn();
        Thread.sleep(2000);
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();",driver.findElement(By.linkText("SIGN-OFF")));
        Thread.sleep(2000);
        String strUrl4 = driver.getCurrentUrl();
        System.out.println("Current Url is:"+ strUrl4);
        Thread.sleep(2000);
        if(Objects.equals(strUrl4, "https://demo.guru99.com/test/newtours/index.php")){
            System.out.println("LogOut is successfull");
        }else{System.out.println("LogOut is not successfull");}

    }





    @AfterMethod
    public void tearDown() {
        driver.quit();

    }


}
