package org.vaadin.addon.customfield.demo.itest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.vaadin.addon.customfield.demo.CustomFieldApplication;

public class WrappingFieldTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private Server server;

	@Before
	public void setUp() throws Exception {
		server = CustomFieldApplication.startInEmbeddedJetty();
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:8888/";
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	@Test
	public void testWrappingField() throws Exception {
		driver.get(baseUrl + "/?restartApplication");
		driver.findElement(By.cssSelector("div.v-filterselect-button")).click();
		driver.findElement(
				By.xpath("//div[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr[2]/td"))
				.click();
		driver.findElement(
				By.xpath("//div[@id='ROOT-2521314']/div/div[2]/div/div/div/div/div[2]/div/div/div/div/div/div[2]/div/div/div/div/div[2]/div/div/div"))
				.click();
		driver.findElement(
				By.xpath("//div[@id='VAADIN_COMBOBOX_OPTIONLIST']/div/div[2]/table/tbody/tr[2]/td"))
				.click();

		String t = getNotificationString();
		assertTrue(t.contains("selected city is Turku"));
	}

	@Test
	public void testAddressForm() throws Exception {
		driver.get(baseUrl + "/?restartApplication");
		driver.findElement(By.xpath("//div[text()='Address Form']")).click();
		WebElement findElement = driver.findElement(By
				.xpath("//span[text() = 'City']"));
		assertNotNull(findElement);
	}

	@Test
	public void testConversions() throws Exception {
		driver.get(baseUrl + "/?restartApplication");
		driver.findElement(
				By.xpath("//div[@id='ROOT-2521314']/div/div[2]/div/div/div/div/div/table/tbody/tr/td[2]/div/div/div"))
				.click();
		WebElement e = driver.findElement(By.xpath("//option[text()='Turku']"));
		e.click();
		WebElement select = driver.findElement(By
				.cssSelector("span.v-button-wrap"));
		select.click();

		String notificationString = getNotificationString();
		assertTrue(notificationString.contains("Turku"));

	}

	@Test
	public void testEmbeddedForm() throws Exception {
		driver.get(baseUrl + "/?restartApplication");
		driver.findElement(By.xpath("//div[text()='Embedded Form']")).click();
		driver.findElement(By.cssSelector("div.v-table-cell-wrapper")).click();
		WebElement addressinput = driver.findElements(
				By.className("v-textfield")).get(2);
		addressinput.clear();
		addressinput.sendKeys("Ruukinkatu 2222");

		driver.findElement(By.xpath("//span[text()='Apply']")).click();
		driver.findElement(By.xpath("//tr[2]/td/div")).click();
		driver.findElement(By.cssSelector("div.v-table-cell-wrapper")).click();
		Thread.sleep(400);
		assertEquals("Ruukinkatu 2222",
				driver.findElements(By.className("v-textfield")).get(2)
						.getAttribute("value"));

	}

	@Test
	public void testBooleanField() throws Exception {
		driver.get(baseUrl + "/?restartApplication");
		driver.findElement(By.xpath("//div[text()='Boolean Field']")).click();
		driver.findElement(By.xpath("//span[text()='Click me']")).click();
		driver.findElement(By.xpath("//span[text()='Submit']")).click();
		boolean contains = getNotificationString().contains(
				"field value is true");
		assertTrue(contains);
	}

	private String getNotificationString() {
		List<WebElement> findElements = driver.findElements(By
				.className("v-Notification"));
		WebElement webElement = findElements.get(findElements.size() - 1);
		return webElement.getText();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
		server.stop();
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
