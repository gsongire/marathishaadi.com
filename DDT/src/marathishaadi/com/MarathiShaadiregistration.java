package marathishaadi.com;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;


public class MarathiShaadiregistration {
	

WebDriver driver;
String baseurl = "https://www.marathishaadi.com";
String CSV_file = "D:\\Data driven framework\\multiple test data.csv";


@BeforeClass
public void openBrowser() throws InterruptedException{
System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
}

@Test
public void registration() throws InterruptedException{
  driver.get(baseurl);
  driver.findElement(By.className("btn-primary form-control")).click();

//Read CSV file

CSVReader reader = null;
try{

reader = new CSVReader(new FileReader(CSV_file));
String[] cell=reader.readNext();

while((cell= reader.readNext())!=null){
String keyword = cell[0];
driver.findElement(By.id("email")).sendKeys(keyword);
System.out.println("System enters in email id field");

String keyword1 = cell[1];
driver.findElement(By.id("password1")).sendKeys(keyword1);
System.out.println("System enters in password field");

String keyword2 = cell[2];
Select drpCreatprofile = new Select(driver.findElement(By.className("Dropdown-placeholder")));
drpCreatprofile.selectByVisibleText(keyword2);
System.out.println("System selects profile create for");
}

}catch(IOException e){
e.printStackTrace();
}
}

@AfterTest
public void verificationpanel2(){

Select community = new Select(driver.findElement(By.className("btn btn-primary btn-md btn-block")));
String defaultcommunity = community.getFirstSelectedOption().getText();
System.out.println("The default community is...." + defaultcommunity);
String expected = "Marathi";

if(community.equals(expected))
{

System.out.println("Mothertoung matches with community");

}
else{
System.out.println("Mothertoung not matches with community");
} 
}}
