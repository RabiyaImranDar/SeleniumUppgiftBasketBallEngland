package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegAccStepdefs {
    private WebDriver driver;
    private long randomTime;
    private boolean termsButton = false;
    private boolean lastNameCheck = false;
    private boolean confirmPassCheck = false;
    private Actions actions;

    @Given("User opens {string} to fill out account registration page")
    public void userOpensToFillOutAccountRegistrationPage(String browser) {

        if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else driver = new ChromeDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        actions = new Actions(driver);
    }

    @And("User enters personal information {string}, {string}, {string}")
    public void userEntersPersonalInformation(String birthDate, String firstName, String lastName) {
// SELECTING (DD/MM/YYYY)
        WebElement dateOfBirth = driver.findElement(By.id("dp"));
        dateOfBirth.sendKeys(birthDate);
//Entering first name
        WebElement first = driver.findElement(By.id("member_firstname"));
        first.click();
        first.sendKeys(firstName);
// Entering last name
        WebElement last = driver.findElement(By.id("member_lastname"));
        last.sendKeys(lastName);
        if (!lastName.isEmpty()) {
            lastNameCheck = true;
        }
    }

    @And("User enters email and confirmed email {string}, {string}")
    public void userEntersEmailAndConfirmedEmail(String email, String confEmail) {
        // Entering Email Address
        WebElement emailAddress = driver.findElement(By.name("EmailAddress"));
        this.randomTime = System.currentTimeMillis();
        emailAddress.sendKeys((this.randomTime) + email);

// Entering Confirm Email Address
        WebElement confirmEmailAddress = driver.findElement(By.name("ConfirmEmailAddress"));
        confirmEmailAddress.sendKeys((this.randomTime) + confEmail);
    }

    @And("User enters password and confirmed password{string}, {string}")
    public void userEntersPasswordAndConfirmedPassword(String password, String confPass) {
// Entering Password
        WebElement pass = driver.findElement(By.name("Password"));
        pass.sendKeys(password);

// Entering Confirm Password
        WebElement confirmPassword = driver.findElement(By.name("ConfirmPassword"));
        confirmPassword.sendKeys(confPass);
        if (password.equals(confPass)) {
            this.confirmPassCheck = true;
        }
        actions.click().perform();
    }

    @And("User enters roles {string}")
    public void userEntersRoles(String roles) {

        if (roles.equalsIgnoreCase("Basketball Media")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(3) > div > label > span.box"));
        } else if (roles.equalsIgnoreCase("Club")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(6) > div > label > span.box"));
        } else if (roles.equalsIgnoreCase("Coach")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(9) > div > label > span.box"));
        } else if (roles.equalsIgnoreCase("Official")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(15) > div > label > span.box"));
        } else if (roles.equalsIgnoreCase("Player")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(18) > div > label > span.box"));
        } else if (roles.equalsIgnoreCase("Relative")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(21) > div > label > span.box"));
        } else if (roles.equalsIgnoreCase("Fan")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(12) > div > label"));
        } else if (roles.equalsIgnoreCase("Sports")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(24) > div > label > span.box"));
        } else if (roles.equalsIgnoreCase("Welfare")) {
            click(driver, By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(27) > div > label > span.box"));
        }

    }

    @And("User specifies terms and conditions {string}")
    public void userSpecifiesTermsAndConditions(String termsCons) {
        if (termsCons.equalsIgnoreCase("check")) {
            actions.click().perform();
            click(driver, By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > label > span.box"));
            this.termsButton = true;
        } else if (termsCons.equalsIgnoreCase("notCheck")) {
            this.termsButton = false;
        }
    }

    @And("User is above eighteen {string}")
    public void userIsAboveEighteen(String ageIsChecked) {
        if (ageIsChecked.equalsIgnoreCase("check")) {
            actions.click().perform();
            click(driver, By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div.md-checkbox.margin-top-10 > label > span.box"));

        }
    }

    @And("User checks confirm preferences {string}")
    public void userChecksConfirmPreferences(String confPrefs) {
        if (confPrefs.equalsIgnoreCase("check")) {

            actions.click().perform();
            click(driver, By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(4) > label > span.box"));
        }
    }

    @And("User checks code of ethics {string}")
    public void userChecksCodeOfEthics(String cOEthics) {
        if (cOEthics.equalsIgnoreCase("check")) {
            actions.click().perform();
            click(driver, By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(7) > label > span.box"));
        }
    }

    @When("User clicks the button \\(Confirm And Join)")
    public void userClicksTheButtonConfirmAndJoin() {
        click(driver, By.name("join"));
    }

    @Then("Account should be registered if {string}, {string}, {string}")
    public void accountShouldBeRegisteredIf(String lastNameIsFilled, String confirmPassIsCorrect, String termsConsIsChecked) throws InterruptedException {
        boolean actualLastName = this.lastNameCheck;

//  *************** checking last name ......
        if (lastNameIsFilled.isEmpty()) {
            String expLastN = "Last Name is required";
            String actualLastN = driver.findElement(By.cssSelector(".row:nth-child(6) .warning > span")).getText();
            assertEquals(expLastN, actualLastN); // last name checking

        }
//  *********************checking correct confirm password....
        boolean actualConfPass = this.confirmPassCheck;
        if (confirmPassIsCorrect.equals("false")) {
            String expConfPass = "Password did not match";
            String actualConfPss = driver.findElement(By.cssSelector(".warning > span")).getText();
            assertEquals(expConfPass, actualConfPss);
        }
//***************  Terms conditions checking and verifying....
        boolean actualTerms = this.termsButton;
        if (termsConsIsChecked.equalsIgnoreCase("false")) {
            String expTerms = "You must confirm that you have read and accepted our Terms and Conditions";
            String actualTm = driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/div/div/div/div/form/div[11]/div/div[2]/div[1]/span/span")).getText();
            assertEquals(expTerms, actualTm); // terms checking
        }


        // ****************  Checking necessary fields and assuring a correct MESSAGE of registering of account.....
        if (actualLastName && actualConfPass && actualTerms) {
            String expIsRegistered = "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND";
            String actualIsReg = driver.findElement(By.cssSelector(".bold:nth-child(1)")).getText();
            assertEquals(expIsRegistered, actualIsReg); // terms checking

            System.out.println("Account successfully registered!!");

        } else if ((!actualLastName) && (actualConfPass && actualTerms)) {
            System.out.println("Last Name is missing!!");
        } else if ((!actualConfPass) && (actualLastName && actualTerms)) {
            System.out.println("Confirmed Password does not match!!");
        } else if ((!actualTerms) && (actualConfPass && actualLastName)) {
            System.out.println("Terms and conditions are not checked!!");
        }

        Thread.sleep(4000);
        driver.close();
    }

    // ******************** Explicit Wait Method  **********************
    private void click(WebDriver driver, By by) {
        (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }
}
