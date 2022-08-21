import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webDriverFactory.WebDriverFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.By.*;

public class TestsHomeWorkOtus5 {

    public static Random random;
    public static int randomCity, day, year, mon;
    public static WebDriver driver;
    public static String propLogin = System.getProperty("login");//"test10.jack@yandex.ru";
    public static String propPassword = System.getProperty("password");//"Qwerty1234$";
    public static String firstName, lastName, birth, country, city, englishLevel, gender, smon, sday;
    public static String firstNameLatin = "Stas";
    public static String lastNameLatin = "Sinitsa";
    public static String blogName = "TestJack";
    public static String communicationType1 = "Facebook";
    public static String communicationType2 = "VK";
    public static String communicationAddress1 = "https://ru-ru.facebook.com/1";
    public static String communicationAddress2 = "https://vk.com/1";
    public static String company = "Рога и копыта";
    public static String work = "QA_tester";





    @BeforeEach
    public void webDriverStart(){
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Test
    public void testSavePersonalPage() throws InterruptedException {
        //Открыть Chrome в режиме полного экрана
        //Перейти на https://otus.ru
        driver.get("https://otus.ru");
        //Авторизоваться под каким-нибудь тестовым пользователем
        authorization(propLogin, propPassword);
        random = new Random();
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(xpath("//div[@class='header2__right']//div/div[1]/div[3]"))).build().perform();
        driver.findElement(xpath("//a[@title='Личный кабинет']/../a[1]")).click();
        String[] fName = {"Даниил", "Максим", "Владислав", "Никита", "Артем", "Иван", "Кирилл", "Егор", "Илья", "Андрей"};
        String[] lName = {"Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев", "Петров", "Соколов", "Лазарев", "Медведев", "Ершов"};
        int nfName = random.nextInt(fName.length-1);
        int nlName = random.nextInt(lName.length-1);
        firstName = fName[nfName];
        lastName = lName[nlName];
        birth = genBirth();
        textInput(id("id_fname"),firstName);
        textInput(id("id_lname"),lastName);
        textInput(id("id_fname_latin"),firstNameLatin);
        textInput(id("id_lname_latin"),lastNameLatin);
        textInput(id("id_blog_name"),blogName);
        textInput(name("date_of_birth"),birth);

        driver.findElement(xpath("//input[@name='country']/../div")).click();
        List<WebElement> countrys = driver.findElements(By.xpath("//input[@name='country']/../../div/div/button"));
        int nCountry = countrys.size()-2;
        int randomCountry = random.nextInt(nCountry)+ 2;
        driver.findElement(xpath("//input[@name='country']/../../div/div/button[" + randomCountry + "]")).click();
        country = driver.findElement(xpath("//input[@name='country']/../div")).getText();
        waitElement(xpath("//input[@name='city']/../div"));

        Thread.sleep(500);
        driver.findElement(xpath("//input[@name='city']/../div")).click();
        List<WebElement> citys = driver.findElements(By.xpath("//input[@name='city']/../../div/div/button"));
        int nCity = citys.size()-2;
        if (nCity > 0) randomCity = random.nextInt(nCity)+2;
        else randomCity = 2;
        driver.findElement(xpath("//input[@name='city']/../../div/div/button[" + randomCity + "]")).click();
        city = driver.findElement(xpath("//input[@name='city']/../div")).getText();
        waitElement(xpath("//input[@name='english_level']/../div"));

        Thread.sleep(500);
        driver.findElement(xpath("//input[@name='english_level']/../div")).click();
        int englishLevels = random.nextInt(6)+2;
        driver.findElement(xpath("//input[@name='english_level']/../../div/div/button[" + englishLevels + "]")).click();
        englishLevel = driver.findElement(xpath("//input[@name='english_level']/../div")).getText();

        Thread.sleep(500);
        int radButton = random.nextInt(1)+1;
        driver.findElement(xpath("//input[@name='ready_to_relocate']/../../label[" + radButton + "]")).click();

        Thread.sleep(500);
        boolean checkbox1 = random.nextBoolean();
        if (checkbox1) driver.findElement(xpath("//input[@title='Полный день']/..")).click();

        Thread.sleep(500);
        boolean checkbox2 = random.nextBoolean();
        if (checkbox2) driver.findElement(xpath("//input[@title='Гибкий график']/..")).click();

        Thread.sleep(500);
        boolean checkbox3 = random.nextBoolean();
        if (checkbox3) driver.findElement(xpath("//input[@title='Удаленно']/..")).click();

        Thread.sleep(500);
        driver.findElement(xpath("//input[@name='contact-0-service']/..")).click();
        driver.findElement(xpath("//button[@title='"+ communicationType1 +"']")).click();
        textInput(id("id_contact-0-value"),communicationAddress1);

        driver.findElement(xpath("//button[text()='Добавить']")).click();
        driver.findElement(xpath("//input[@name='contact-1-service']/..")).click();
        Thread.sleep(500);
        driver.findElement(xpath("//input[@name='contact-1-id']/..//button[@title='"+ communicationType2 +"']")).click();
        textInput(id("id_contact-1-value"),communicationAddress2);

        driver.findElement(xpath("//*[@id='id_gender']")).click();

        Thread.sleep(500);
        boolean checkbox4 = random.nextBoolean();
        if (checkbox4) {
            driver.findElement(xpath("//*[@id='id_gender']/option[2]")).click();
            gender = driver.findElement(xpath("//*[@id='id_gender']/option[2]")).getText();
        }else {
            driver.findElement(xpath("//*[@id='id_gender']/option[3]")).click();
            gender = driver.findElement(xpath("//*[@id='id_gender']/option[3]")).getText();
        }
        textInput(id("id_company"),company);
        textInput(id("id_work"),work);
        driver.findElement(xpath("//button[@title='Сохранить и продолжить']")).click();
        driver.quit();

        //Открыть Chrome в режиме полного экрана
        webDriverStart();
        //Перейти на https://otus.ru
        driver.get("https://otus.ru");
        //Авторизоваться под каким-нибудь тестовым пользователем
        authorization(propLogin, propPassword);
        Actions act2 = new Actions(driver);
        act2.moveToElement(driver.findElement(xpath("//div[@class='header2__right']//div/div[1]/div[3]"))).build().perform();
        driver.findElement(xpath("//a[@title='Личный кабинет']/../a[1]")).click();
        checkInputElement(id("id_fname"),firstName);
        checkInputElement(id("id_lname"),lastName);
        checkInputElement(id("id_fname_latin"),firstNameLatin);
        checkInputElement(id("id_lname_latin"),lastNameLatin);
        checkInputElement(id("id_blog_name"),blogName);
        checkInputElement(name("date_of_birth"),birth);
        checkDropBoxElement(xpath("//input[@name='country']/../div"),country);
        checkDropBoxElement(xpath("//input[@name='city']/../div"),city);
        checkDropBoxElement(xpath("//input[@name='english_level']/../div"),englishLevel);
        checkDropBoxElement(xpath("//input[@name='contact-0-service']/.."),communicationType1);
        checkInputElement(id("id_contact-0-value"),communicationAddress1);
        checkDropBoxElement(xpath("//input[@name='contact-1-service']/.."),communicationType2);
        checkInputElement(id("id_contact-1-value"),communicationAddress2);
        checkInputElement(id("id_company"),company);
        checkInputElement(id("id_work"),work);
    }

   @AfterEach
    public void exit(){
        if(driver != null) driver.quit();
    }

    public boolean checkElement(By element){
        try{
            driver.findElement(element);
            return true;
        } catch (NoSuchElementException e) {return false;}
    }

    public void textInput(By elementBy, String text) {
        String input = driver.findElement(elementBy).getAttribute("value");
        driver.findElement(elementBy).click();
        if (input != "") {
            driver.findElement(elementBy).clear();
        }
        //!!!!МЕТОД .clear() ОТРАБАТЫВАЕТ НЕ ВСЕГДА - ПРОБЛЕМЫ С ОЧИСТКОЙ ПОЛЕЙ С АВТОЗАПОЛНЕНИЕМ
        //НИЖЕ - АВАРИЙНЫЙ КОСТЫЛЬ: ЗАБИТЬ ПОЛЕ BackSpace'ом
        String inputAfter = driver.findElement(elementBy).getAttribute("value");
        while (inputAfter != "") {
            driver.findElement(elementBy).sendKeys("\b");
            inputAfter = driver.findElement(elementBy).getAttribute("value");
        }
        driver.findElement(elementBy).sendKeys(text);
    }

    public void authorization(String login, String password){
        driver.findElement(xpath("//button")).click();
        WebElement form = driver.findElement(xpath("//form[@action='/login/']"));
        form.findElement(xpath(".//input[@name='email']")).sendKeys(login);
        form.findElement(xpath(".//input[@name='password']")).sendKeys(password);
        form.findElement(xpath(".//button[@type='submit']")).click();
    }

    public String genBirth() {
        int minYear = 1980;
        int maxYear = 2005;
        int diffYear = maxYear - minYear;
        year = random.nextInt(diffYear + 1) + minYear;
        mon = random.nextInt(11) + 1;
        switch (mon) {
            case 2:
                day = random.nextInt(27) + 1;
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = random.nextInt(30) + 1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = random.nextInt(29) + 1;
                break;
        }
        if (mon < 10) smon = "0" + Integer.toString(mon);
        else smon = Integer.toString(mon);

        if (day < 10) sday = "0" + Integer.toString(day);
        else sday = Integer.toString(day);

        return sday + "." + smon + "." + year;
    }
    public static void waitElement(By elementBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(elementBy));
    }

    public static void checkDropBoxElement(By elementBy, String expectedText){
        assertEquals(driver.findElement(elementBy).getText(), expectedText);
    }

    public static void checkInputElement(By elementBy, String expectedText){
        assertEquals(driver.findElement(elementBy).getAttribute("value"), expectedText);
    }
}