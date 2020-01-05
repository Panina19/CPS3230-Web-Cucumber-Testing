package test.cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import cucumber.api.java.Before;
import cucumber.api.java.After;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObjects.ScanMaltaPageObject;

import static org.junit.Assert.assertTrue;

public class ScanMaltaStepDefs {

    WebDriver browser = null;
    String chromeDriverFilePath;
    public ScanMaltaPageObject scanPageObject;

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000);
        } catch (Exception e) {}
    }

    @Before
    public void setup() {
        chromeDriverFilePath = "C:/Users/PANINA/Desktop/Software_Testing/web/webtesting1/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromeDriverFilePath);
        browser = new ChromeDriver();
        scanPageObject = new ScanMaltaPageObject(browser);
        browser.manage().window().maximize();
    }

    @After
    public void teardown() {
        browser.quit();
        browser = null;
        scanPageObject = null;
    }

    //Scenario 1
    @Given("I am a user on the SCANMalta website")
    public void i_am_a_user_on_the_scanmalta_website() {
        scanPageObject.getWebPage();
    }

    @When("I log in using valid credentials using email {string} and password {string}")
    public void i_log_in_using_valid_credentials_using_email_and_password(String emailAddress, String password) {
        scanPageObject.login(emailAddress, password);
    }

    @Then("I should be logged in")
    public void i_should_be_logged_in() {
        scanPageObject.testLoginSuccess();
    }

    //Scenario 2
     @When("I log in using invalid credentials using email {string} and password {string}")
     public void i_log_in_using_invalid_credentials_using_email_and_password(String emailAddress, String password){
            scanPageObject.login(emailAddress, password);
     }
    
     @Then("I should not be logged in")
     public void i_should_not_be_logged_in() {
        scanPageObject.testLoginFailure();
     }

    //Scenario 3
    @Given("I am a logged in user on the SCANMalta website using email {string} and password {string}")
    public void i_am_a_logged_in_user_on_the_scanmalta_website_using_email_and_password(String emailAddress, String password) {
        scanPageObject.getWebPage();
        scanPageObject.login(emailAddress, password);
    }

    @When("I search for a product under {string}")
    public void i_search_for_a_product_under(String searchTerm) {
    scanPageObject.search(searchTerm);
    }

    @When("I select the first product in the list")
    public void i_select_the_first_product_in_the_list() {
        scanPageObject.selectFirstProduct();
    }

    @Then("I should see the product details")
    public void i_should_see_the_product_details() {
        scanPageObject.seeProductDetails();
    }

    //Scenario 4
    @Given("my shopping cart is empty")
    public void my_shopping_cart_is_empty() {
        scanPageObject.cartIsEmpty();
    }

    @When("I view the details of a product like {string}")
    public void i_view_the_details_of_a_product(String productName) {
        scanPageObject.search(productName);
        scanPageObject.selectFirstProduct();
    }

    @When("I choose to buy the product")
    public void i_choose_to_buy_the_product() {
        scanPageObject.buyProduct();
    }

    @Then("my shopping cart should contain {int} item")
    public void my_shopping_cart_should_contain_item(int itemNo) {
        scanPageObject.testCartHasOnlyXItems(itemNo);
    }

    //Scenario 5
    @When("I add {int} products to my shopping cart")
    public void i_add_num_products_products_to_my_shopping_cart(int itemNo) {
        scanPageObject.selectXProductsAndAddToCart(itemNo);
    }

    @Then("my shopping cart should contain {int} items")
    public void my_shopping_cart_should_contain_num_products_items(int itemNo) {
        scanPageObject.testCartHasOnlyXItems(itemNo);
    }

    //Scenario 6
    @Given("my shopping cart has {int} products like {string} and {string}")
    public void my_shopping_cart_has_products_like_and(int itemNo, String productName1, String productName2) {
        scanPageObject.cartIsEmpty();
        scanPageObject.addFirstFoundProductToCart(productName1);
        scanPageObject.addFirstFoundProductToCart(productName2);
        scanPageObject.getCartItemNo();
        scanPageObject.testCartHasOnlyXItems(itemNo);
    }

    @When("I remove the first product in my cart")
    public void i_remove_the_first_product_in_my_cart() {
        scanPageObject.removeFirstProductInCart();
    }

}
