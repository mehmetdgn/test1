import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class darksky {


    public static  void main (String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String url= "https://darksky.net/";
        driver.get(url);
        driver.findElement(By.tagName("input")).clear();
        driver.findElement(By.tagName("input")).sendKeys("10001");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//img[@alt='Search Button']")).click();


        List<String> hours = new ArrayList<String>();
        int i=1;

        for (i=1; i<24; ) {
            String a = driver.findElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[1]/div[3]/span["+i+"]/span[1]")).getText();
            System.out.println(a);
            hours.add(a);
            i+=2;

        }
        int vh=hours.size();
        if (vh == 12) {
            System.out.println("Timeline incremented by 2 hours for next 24 hours.");
        }else {
            System.out.println("failed");
        }


        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);




        String lowtemp =driver.findElement(By.className("low-temp-text")).getText();
        System.out.println("Lowest temperature is : "+ lowtemp);
        int templow = Integer.parseInt(lowtemp.substring(0, 2));
        //System.out.println(templow );


        String hightemp =driver.findElement(By.className("high-temp-text")).getText();
        System.out.println("High temperature is : "+ hightemp);
        int  temphigh = Integer.parseInt(hightemp.substring(0, 2));
        //System.out.println(temphigh);

        String temp =driver.findElement(By.xpath("//span[@class='summary swap']")).getText();
        System.out.println("Temperature is : "+ temp);

        int  tempp = Integer.parseInt(temp.substring(0, 2));
        //System.out.println(tempp);

        if (tempp  <  temphigh && tempp > templow ){
            System.out.println("Temperature is not less than the lowest value and not greater than the highest value. ");
        }else{
            System.out.println("Temperature is not between lowest and highest value.");
        }





        driver.close();
        driver.quit();






    }




}
