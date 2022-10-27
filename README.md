**Selendroid Mobile Automation**


Project testing selendriod-test-app. 
In this project; Appium, Appium Inspector, Android Studio(For Emulator) and Intellij idea tools were used.

Scenario 1; 

@Test
    public void displayTest() {
            displayClick();//İlk çalıştırmada texti görmesi gerek
            Assert.assertThrows(NoSuchElementException.class, this::displayClick); //İkinci çalıştırmada ise text kapandığı için göremeyecek
    }
    
     public void displayClick() {
        WebElement displayButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/visibleButtonTest")));
        displayButton.click();
        WebElement displayText = driver.findElement(By.id("io.selendroid.testapp:id/visibleTextView"));
        Assert.assertNotNull(displayText);
    }

1-'Display text view' is clicked. Verifies that the Text field is not null.
and 'Display text view' is clicked. Verified that the text field is not on the screen.

Scenario 2;

 @Test
    public void focusClick() {
        WebElement displayFocus = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/topLevelElementTest")));
        focusText("true");
        displayFocus.click();
        focusText("false");
    }
    
     public void focusText(String focus) {
        WebElement textField = wait.until(ExpectedConditions.elementToBeClickable(By.id("io.selendroid.testapp:id/my_text_field")));
        String isFocus = textField.getAttribute("focused");
        Assert.assertEquals(isFocus, focus);
    }
    
    1-Verifies that the focus property of the field 'my_text_field' is true.
    2-Click the 'Display and focus on layout' button. Verifies that the focus property of the field 'my_text_field' is false.
    
    Scenario 3;
    
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
    
    1- Click 'startUserRegistrationCD'.
    2-'Username' field is clicked and 'Ali' is written.
      'E-Mail' field is clicked and 'ali@gmail' is written.
      'Password' field is clicked and '1234' is written.
      'Name' field is clicked and 'Mr.Ali' is written.
      'Programming Language' field is clicked and 'Java' is selected.
      'Accept Add' area is clicked.
      'Register User' button is clicked.
    3-Matches the information on the previous screen.
    
    Scenario 4;
    
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
    
    1- Click 'EN Button'.
    2- Click the 'No, no' button and The 'Event will continue' message is confirmed on the screen.
    3- Click 'EN Button'.
    4- Click the 'I agree' button and confirmation that the application is closed.
      
    
    
