package qaEvaluation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FormSubmission {

	WebDriver driver;
	ChromeOptions options;
	String firstName = "user";
	String lastName = "user1";
	String email = "user1@gmail.com";
	String currentCompany = "company";
	String mobile = "9898987654";
	String dob = "12/01/1990";
	String position = "QA";
	String portfolioLink = "linkedin";
	String salary = "1000000";
	String dateOfJoin = "ASAP";
	String address = "mumbai";
	String resume = "C:\\Users\\TOSHIBA\\Downloads\\Manisha mishra.docx";
	String db_fName;
	String db_lName;
	String db_email;
	String db_company;
	String db_mob;
	String db_dob;
	String db_position;
	String db_portfolio;
	String db_salary;
	String db_doj;
	String db_address;
	String emailSent_Flag;
	WebElement fname;
	WebElement lName;
	WebElement emailnput;
	WebElement currentCompanyInput;
	WebElement mobileInput;
	WebElement dobInput;
	WebElement positionInput;
	WebElement portfolioInput;
	WebElement salaryInput;
	WebElement doj;
	WebElement addressInput;
	WebElement doc;
	WebElement submitButton;

	String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	Statement stmt = null;
	String hostname;
	String DB_URL = "jdbc:mysql://dummyTable";
	String USER_NAME = "qateam";
	String PASS_TEST = "qaPass";
	Connection conn = null;
	ResultSet rs = null;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().browserVersion("87.0.4280.141").setup();
		options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--window-size=1920,1080");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}

	@Test
	public void submitEmptyForm() {
		String URL = "http://" + "sfwebhtml" + ":" + "t63KUfxL5vUyFLG4eqZNUcuRQ" + "@"
				+ "sfwebhtml.sourcefuse.com/automation-form-demo-for-interview/";
		driver.get(URL);
		// TC1 click submit with empty values
		submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
		submitButton.click();
	}

	// TC 2
	@Test
	public void softAssertTC() {
		fname = driver.findElement(By.xpath("//div[@id='fnameInput']//input[@type='text']"));
		lName = driver.findElement(By.xpath("//div[@id='lnameInput']//input[@type='text']"));
		emailnput = driver.findElement(By.xpath("//div[@id='emailInput']//input[@type='email']"));
		currentCompanyInput = driver.findElement(By.xpath("//div[@id='curCompanyInput']//input[@type='text']"));
		mobileInput = driver.findElement(By.xpath("//div[@id='mobInput']//input[@type='tel']"));
		dobInput = driver.findElement(By.xpath("//div[@class='input-group date']//input[@type='text']"));
		positionInput = driver.findElement(By.xpath("//div[@id='positionInput']//input[@type='text']"));
		portfolioInput = driver.findElement(By.xpath("//div[@id='portfolioInput']//input[@type='url']"));
		salaryInput = driver.findElement(By.xpath("//div[@id='salaryInput']//input[@type='text']"));
		doj = driver.findElement(By.xpath("//div[@id='whenStartInput']//input[@type='text']"));
		addressInput = driver.findElement(By.xpath("//div[@id='addressInput']//textarea[@id='address']"));
		doc = driver.findElement(By.xpath("//div[@id='resumeInput']//input[@type='file']"));
		fname.sendKeys(firstName);
		lName.sendKeys(lastName);
		emailnput.sendKeys(email);
		currentCompanyInput.sendKeys(currentCompany);
		mobileInput.sendKeys(mobile);
		dobInput.sendKeys(dob);
		positionInput.sendKeys(position);
		portfolioInput.sendKeys(portfolioLink);
		salaryInput.sendKeys(salary);
		doj.sendKeys(dateOfJoin);
		addressInput.sendKeys(address);

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(fname.getText(), firstName);
		sa.assertEquals(lName.getText(), lastName);
		sa.assertEquals(emailnput.getText(), email);
		sa.assertEquals(currentCompanyInput.getText(), currentCompany);
		sa.assertEquals(mobileInput.getText(), mobile);
		sa.assertEquals(dobInput.getText(), dob);
		sa.assertEquals(positionInput.getText(), position);
		sa.assertEquals(salaryInput.getText(), salary);
		sa.assertEquals(doj.getText(), dateOfJoin);
		sa.assertEquals(addressInput.getText(), address);
		sa.assertAll();

	}

	@Test
	public void hardAssertTC() {
		Assert.assertEquals(fname.getText(), firstName);
		Assert.assertEquals(lName.getText(), lastName);
		Assert.assertEquals(emailnput.getText(), email);
		Assert.assertEquals(currentCompanyInput.getText(), currentCompany);
		Assert.assertEquals(mobileInput.getText(), mobile);
		Assert.assertEquals(dobInput.getText(), dob);
		Assert.assertEquals(positionInput.getText(), position);
		Assert.assertEquals(salaryInput.getText(), salary);
		Assert.assertEquals(doj.getText(), dateOfJoin);
		Assert.assertEquals(addressInput.getText(), address);
	}

	@Test
	public void submitForm() {
		doc.sendKeys(resume);
		driver.findElement(By.xpath("//input[@id='no']")).click();
		submitButton.click();
	}

	@Test
	public void db_Verification() {
		try {
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// Open a connection
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASS_TEST);
			System.out.println("Connected successfully to database...");

			// Execute a query
			System.out.println("Reading server information from Weaver");
			stmt = conn.createStatement();
			String sql1 = "select * from tabler where email_id = '" + email + "'";
			System.out.println(sql1);
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				db_fName = rs.getString(1);
				db_lName = rs.getString(2);
				db_email = rs.getString(3);
				db_company = rs.getString(4);
				db_mob = rs.getString(5);
				db_dob = rs.getString(6);
				db_position = rs.getString(7);
				db_portfolio = rs.getString(7);
				db_salary = rs.getString(8);
				db_doj = rs.getString(8);
				db_address = rs.getString(9);
				emailSent_Flag = rs.getString(10);
			}
			Assert.assertEquals(db_fName, firstName);
			Assert.assertEquals(db_lName, lastName);
			Assert.assertEquals(db_email, email);
			Assert.assertEquals(db_dob, dob);
			Assert.assertEquals(db_company, currentCompany);
			Assert.assertEquals(db_mob, mobile);
			Assert.assertEquals(db_position, position);
			Assert.assertEquals(db_portfolio, portfolioLink);
			Assert.assertEquals(db_salary, salary);
			Assert.assertEquals(db_doj, dateOfJoin);
			Assert.assertEquals(db_address, address);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void email_SentVerification() {
		String sql1 = "select emailSent_Flag from tabler where email_id = '" + email + "'";
		System.out.println(sql1);
		try {
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				emailSent_Flag = rs.getString(1);
			}
			if (emailSent_Flag == "Y") {
				System.out.println("Email Sent");
			} else
				System.out.println("Email not sent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void close() {
		driver.quit();
	}
}
