import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;

public class SelendroidTest {
    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "4.65 720p Galaxy Nexus API 26");
        caps.setCapability("udid", "emulator-5554");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "8.0.0");
        caps.setCapability("appPackage", "io.selendroid.testapp");
        caps.setCapability("appActivity", "io.selendroid.testapp.HomeScreenActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void displayTest() {
            displayClick();//İlk çalıştırmada texti görmesi gerek
            Assert.assertThrows(NoSuchElementException.class, this::displayClick); //İkinci çalıştırmada ise text kapandığı için göremeyecek
    }

    @Test
    public void focusClick() {
        WebElement displayFocus = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/topLevelElementTest")));
        focusText("true");
        displayFocus.click();
        focusText("false");
    }

    @Test
    public void registerClick() {
        WebElement folderButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/startUserRegistration")));
        folderButton.click();
        WebElement usernameArea = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/inputUsername")));
        usernameArea.click();
        usernameArea.sendKeys("Ali");
        WebElement emailArea = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/inputEmail")));
        emailArea.click();
        emailArea.sendKeys("ali@gmail");
        WebElement passwordArea = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/inputPassword")));
        passwordArea.click();
        passwordArea.sendKeys("1234");
        driver.navigate().back();
        WebElement nameArea = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/inputName")));
        nameArea.click();
        nameArea.clear();
        nameArea.sendKeys("Mr.Ali");
        driver.navigate().back();
        WebElement programmingLangugeSelect = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/input_preferedProgrammingLanguage")));
        programmingLangugeSelect.click();
        WebElement java = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.CheckedTextView[6]")));
        java.click();
        WebElement acceptAdds = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/input_adds")));
        acceptAdds.click();
        WebElement registerUserButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/btnRegisterUser")));
        registerUserButton.click();

        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_name_data")));
        Assert.assertEquals(name.getText(), "Mr.Ali");
        WebElement username =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_username_data")));
        Assert.assertEquals(username.getText(), "Ali");
        WebElement password =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_password_data")));
        Assert.assertEquals(password.getText(), "1234");
        WebElement email =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_email_data")));
        Assert.assertEquals(email.getText(), "ali@gmail");
        WebElement programmingLanguge =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_preferedProgrammingLanguage_data")));
        Assert.assertEquals(programmingLanguge.getText(), "Java");
        WebElement iAcceptAdds =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("io.selendroid.testapp:id/label_acceptAdds_data")));
        Assert.assertEquals(iAcceptAdds.getText(), "true");

        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/buttonRegisterUser")));
        registerButton.click();
    }

    @Test
    public void zEnClick() throws InterruptedException {
        enButton();
        noButton();
        enButton();
        agreeButton();
    }

    public void enButton() {
        WebElement enButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/buttonTest")));
        enButton.click();
    }

    public void noButton() {
        WebElement noButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button2")));
        noButton.click();
        WebElement toastContinue =driver.findElement(By.xpath("/hierarchy/android.widget.Toast"));
        Assert.assertEquals(toastContinue.getText(), "Activity will continue");
    }

    public void agreeButton() throws InterruptedException {
        WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
        agreeButton.click();
        Assert.assertFalse(isBrowserOpen()); //Kapandığını görmem lazım
    }

    public boolean isBrowserOpen() {
        try {
            driver.getTitle();
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public void focusText(String focus) {
        WebElement textField = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/my_text_field")));
        String isFocus = textField.getAttribute("focused");
        Assert.assertEquals(isFocus, focus);
    }

    public void displayClick() {
        WebElement displayButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/visibleButtonTest")));
        displayButton.click();
        WebElement displayText = driver.findElement(By.id("io.selendroid.testapp:id/visibleTextView"));
        Assert.assertNotNull(displayText);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
