package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By campoEmail = By.name("email");
	
	private By campoPassWord = By.name("password");
	
	private By botaoSignIn = By.id("submit-login");
	
	private By usuarioLogado = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	
	
	public void preencherEmail (String email) {
		driver.findElement(campoEmail).sendKeys(email);
	}
	
	public void preencherPassWord(String password) {
		driver.findElement(campoPassWord).sendKeys(password);
	}
	
	public void clickBotaoSignIn() {
		driver.findElement(botaoSignIn).click();
	}
	
	public boolean logadoComSucesso(String texto) {
	 	return	texto.contentEquals(driver.findElement(usuarioLogado).getText());
	}
	
	public void logadoComSucesso2() {
	  	String l = driver.findElement(usuarioLogado).getText();
	  	System.out.println(l);
	 	
	}
	

}
