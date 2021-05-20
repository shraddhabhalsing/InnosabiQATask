package com.qa.page;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import com.qa.base.Base;

public class InnosabiPage extends Base {

	@FindBy(xpath="//input[@id='email']")
	WebElement emailTextField;

	@FindBy(css="button[class='btn btn-primary']")
	WebElement nextBtn;

	@FindBy(xpath="//input[@id='password']")
	WebElement passwordTextField;

	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement loginBtn;

	@FindBy(xpath="//input[@aria-label='Search']")
	WebElement searchTextField;

	@FindBy(xpath="//a[@class='card-topic']")
	WebElement inovationChallengeLink;

	@FindBy(xpath="//button[contains(text(),'Submit idea')]")
	WebElement submitIdeaBtn;

	@FindBy(xpath="//input[@formcontrolname='title']")
	WebElement titleTextField;

	@FindBy(xpath="//div[@class='cke_wysiwyg_div cke_reset cke_enable_context_menu cke_editable cke_editable_themed cke_contents_ltr cke_show_borders']")
	WebElement describeIdeaTextField;

	@FindBys( @FindBy(xpath="//h2[@class='title']"))
	List <WebElement> verifyTitleText;

	@FindBys( @FindBy(xpath="//textarea[@class='cdk-textarea-autosize mat-autosize ng-untouched ng-pristine ng-invalid ng-star-inserted']"))
	List <WebElement> commentTextArea;

	@FindBy(xpath="//button[@class='btn-send ng-star-inserted']")
	WebElement postBtn;

	@FindBys( @FindBy(xpath="//div[@class='comment-content']//dynamic-content[@class='ng-star-inserted']"))
	List <WebElement> verifycommentPosted;

	//Initialize WebElements mentioned in the current page
	public void initializeWebElement(){
		PageFactory.initElements(driver, this);
	}

	// Login Method
	public Boolean login(String userName, String password)
	{ 
		emailTextField.sendKeys(userName);
		nextBtn.click();
		passwordTextField.sendKeys(password);
		loginBtn.click();
		if (searchTextField.isDisplayed())
			return true;
		else
			return false;
	}

	//Method to click on innovation Challenge Link 
	public Boolean clickInnovativeChallengeLink()
	{  
		inovationChallengeLink.click();
		if (submitIdeaBtn.isDisplayed())
			return true;
		else
			return false;
	}

	//Method to enter innovative idea in the form and submit
	public Boolean fillFormAndSubmit(String title,String describeIdea)
	{ 
		submitIdeaBtn.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		titleTextField.sendKeys(title);
		String getTitleFromTextField=titleTextField.getAttribute("value");
		describeIdeaTextField.sendKeys(describeIdea);
		submitIdeaBtn.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actTitleFromTextField=verifyTitleText.get(0).getText();

		if (actTitleFromTextField.equals(getTitleFromTextField))
			return true;
		else
			return false;
	}

	// Method to enter and post comment
	public Boolean verifyCartItemAndCheckOutProduct(String Comment )
	{
		String vtextAreaData = null;
		try
		{
			commentTextArea.get(0).sendKeys(Comment);
			postBtn.click();
			Thread.sleep(4000);
			vtextAreaData=verifycommentPosted.get(0).getText();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		if (vtextAreaData.equalsIgnoreCase(Comment))
			return true;
		else
			return false;
	}
}
