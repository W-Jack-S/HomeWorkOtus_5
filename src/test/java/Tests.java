import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.*;

public class Tests extends WebDriverInit{
    public static int day;
    public static int randomCity;


    @Test
    public void test() throws InterruptedException {
        //Открыть Chrome в режиме полного экрана
        chrome();
        driver.manage().window().maximize();
        //Перейти на https://otus.ru
        driver.get("https://otus.ru");
        //Авторизоваться под каким-нибудь тестовым пользователем
        driver.findElement(xpath("//button")).click();
        driver.findElement(xpath("//form[@action='/login/']//input[@name='email']")).sendKeys("test10.jack@yandex.ru");
        driver.findElement(xpath("//form[@action='/login/']//input[@name='password']")).sendKeys("Qwerty1234$");
        driver.findElement(xpath("//form[@action='/login/']//button[@type='submit']")).click();
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(xpath("//div[@class='header2__right']//div/div[1]/div[3]"))).build().perform();
        driver.findElement(xpath("//a[@title='Личный кабинет']/../a[1]")).click();
        Random random = new Random();
        String[] fName = {"Даниил", "Максим", "Владислав", "Никита", "Артем", "Иван", "Кирилл", "Егор", "Илья", "Андрей"};
        String[] lName = {"Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев", "Петров", "Соколов", "Лазарев", "Медведев", "Ершов"};
        int nfName = random.nextInt(fName.length-1);
        int nlName = random.nextInt(lName.length-1);

        textInput(id("id_fname"),fName[nfName]);
        textInput(id("id_lname"),lName[nlName]);
        textInput(id("id_fname_latin"),"Stas");
        textInput(id("id_lname_latin"),"Sinitsa");
        textInput(id("id_blog_name"), "TestJack");
        int minYear = 1980;
        int maxYear = 2005;
        int diffYear = maxYear - minYear;
        int year = random.nextInt(diffYear + 1) + minYear;
        int mon = random.nextInt(11)+1;
        switch (mon){
            case 2:
                day = random.nextInt(27)+1;
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
              day = random.nextInt(30)+1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
            day = random.nextInt(29)+1;
                break;

        }
        textInput(name("date_of_birth"),day + "." + mon +"." + year);
        driver.findElement(xpath("//input[@name='country']/../div")).click();
        List<WebElement> country = driver.findElements(By.xpath("//input[@name='country']/../../div/div/button"));
        int nCoutry = country.size()-2;
        int randomCoutry = random.nextInt(nCoutry)+ 2;
        driver.findElement(xpath("//input[@name='country']/../../div/div/button[" + randomCoutry + "]")).click();
        Thread.sleep(1000);
        driver.findElement(xpath("//input[@name='city']/../div")).click();
        List<WebElement> city = driver.findElements(By.xpath("//input[@name='city']/../../div/div/button"));
        int nCity = city.size()-2;
        if (nCity > 0) randomCity = random.nextInt(nCity)+2;
        else randomCity = 2;
        driver.findElement(xpath("//input[@name='city']/../../div/div/button[" + randomCity + "]")).click();
        Thread.sleep(1000);
        driver.findElement(xpath("//input[@name='english_level']/../div")).click();
        int englishLevel = random.nextInt(6)+2;
        driver.findElement(xpath("//input[@name='english_level']/../../div/div/button[" + englishLevel + "]")).click();
        Thread.sleep(1000);
        int radButton = random.nextInt(1)+1;
        driver.findElement(xpath("//input[@name='ready_to_relocate']/../../label[" + radButton + "]")).click();
        Thread.sleep(1000);
        boolean checkbox1 = random.nextBoolean();
        if (checkbox1) driver.findElement(xpath("//input[@title='Полный день']/..")).click();
        Thread.sleep(1000);
        boolean checkbox2 = random.nextBoolean();
        if (checkbox2) driver.findElement(xpath("//input[@title='Гибкий график']/..")).click();
        Thread.sleep(1000);
        boolean checkbox3 = random.nextBoolean();
        if (checkbox3) driver.findElement(xpath("//input[@title='Удаленно']/..")).click();
        Thread.sleep(1000);
        driver.findElement(xpath("//input[@name='contact-0-service']/..")).click();
        driver.findElement(xpath("//button[@title='VK']")).click();
        textInput(id("id_contact-0-value"),"https://vk.com/1");
        driver.findElement(xpath("//*[@id='id_gender']")).click();
        Thread.sleep(1000);
        boolean checkbox4 = random.nextBoolean();
        if (checkbox4) driver.findElement(xpath("//*[@id='id_gender']/option[2]")).click();
        else driver.findElement(xpath("//*[@id='id_gender']/option[3]")).click();
        textInput(id("id_company"),"Рога и копыта");
        textInput(id("id_work"),"QA_tester");
        driver.quit();
        //Открыть Chrome в режиме полного экрана
        chrome();
        driver.manage().window().maximize();
        //Перейти на https://otus.ru
        driver.get("https://otus.ru");
        //Авторизоваться под каким-нибудь тестовым пользователем
        driver.findElement(xpath("//button")).click();
        driver.findElement(xpath("//form[@action='/login/']//input[@name='email']")).sendKeys("test10.jack@yandex.ru");
        driver.findElement(xpath("//form[@action='/login/']//input[@name='password']")).sendKeys("Qwerty1234$");
        driver.findElement(xpath("//form[@action='/login/']//button[@type='submit']")).click();

    }

  //  @AfterEach
    public void exit(){
        if(driver != null) driver.quit();
    }
}