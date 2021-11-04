package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By totalTaxIncTotal = By.cssSelector(".cart-total span.value");
	
	private By nomeCliente = By.cssSelector("div.address");
	
	private By botaoContinuar = By.name("confirm-addresses");
	
	private By shippingValor = By.cssSelector("span.carrier-price");
	
	private By botaoContinuarShipping = By.name("confirmDeliveryOption");
	
	private By radioPayByCheck = By.id("payment-option-1");
	
	private By amauntPayByCheck = By.cssSelector("#payment-option-1-additional-information > section > dl > dd:nth-child(2)");
	
	private By checkBoxIGree = By.id("conditions_to_approve[terms-and-conditions]");
	
	private By botaoConfirmar = By.cssSelector("#payment-confirmation .btn");
	
	
	public String obter_totalTaxIncTotal() {
		return driver.findElement(totalTaxIncTotal).getText();
	}
	
	public String obter_nomeCliente() {
		return driver.findElement(nomeCliente).getText();
	}
	
	public void clicarBotaoContinuar() {
		driver.findElement(botaoContinuar).click();
	}
	public String obter_shippingValor() {
		return driver.findElement(shippingValor).getText();
	}
	
	public void clicarBotaoContinuarShipping() {
		driver.findElement(botaoContinuarShipping).click();
	}
	
	public void selecionarRadioPayByCheck() {
		driver.findElement(radioPayByCheck).click();
	}
	
	public String obter_amauntPayByCheck() {
		return driver.findElement(amauntPayByCheck).getText();
	}
	public void selecionarCheckBoxIGree() {
		driver.findElement(checkBoxIGree).click();
	}
	public boolean confirmarSelecaoCheckBoxIGree () {
		return driver.findElement(checkBoxIGree).isSelected();
	}
	
	public PedidoPage clicarBotaConfirmar() {
		driver.findElement(botaoConfirmar).click();
		return new PedidoPage(driver);
	}

}
