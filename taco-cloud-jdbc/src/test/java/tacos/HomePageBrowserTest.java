package tacos;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class HomePageBrowserTest {

  @Value("${local.server.port}")
  private int port;
  private static HtmlUnitDriver browser;  
  
  @BeforeClass
  public static void setup() {
    browser = new HtmlUnitDriver();
    
    browser.manage().timeouts()
          .implicitlyWait(Duration.ofSeconds(10));
  }
  
  @AfterClass
  public static void teardown() {
    browser.quit();
  }
  
  @Test
  public void testHomePage() {
    String homePage = "http://localhost:" + port;
    browser.get(homePage);
    
    String titleText = browser.getTitle();
    Assert.assertEquals("Taco Cloud", titleText);
    
    String h1Text = browser.findElement(By.tagName("h1")).getText();
    Assert.assertEquals("Welcome to...", h1Text);
    
    String imgSrc = browser.findElement(By.tagName("img"))
                                              .getAttribute("src");
    Assert.assertEquals(homePage + "/images/TacoCloud.png", imgSrc);
  }
  
  
}