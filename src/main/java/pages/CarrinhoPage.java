package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarrinhoPage {

	private WebDriver driver;

	public CarrinhoPage(WebDriver driver) {
		this.driver = driver;
	}

	// ELEMENTOS
	private By nomeProduto = By.cssSelector("div.product-line-info a");

	private By precoProduto = By.cssSelector("span.price");

	private By tamanhoProduto = By
			.xpath("//div[contains(@class,'product-line-grid-body')]//div[3]/span[contains(@class,'value')]");

	private By corProduto = By
			.xpath("//div[contains(@class,'product-line-grid-body')]//div[4]/span[contains(@class,'value')]");

	private By input_quantidadeProduto = By.cssSelector("input.js-cart-line-product-quantity");

	private By subTotalProduto = By.cssSelector("span.product-price strong");

	private By numeroItensTotal = By.cssSelector("span.js-subtotal");

	private By subTotalTotal = By.cssSelector("#cart-subtotal-products span.value");

	private By shippTotal = By.cssSelector("#cart-subtotal-shipping span.value");

	private By totalTaxExcTotal = By.cssSelector("div.cart-summary-totals .cart-summary-line:nth-child(1) span.value");

	private By totoalTexIncTotal = By.cssSelector("div.cart-summary-totals .cart-summary-line:nth-child(2) span.value");

	private By taxaTotal = By.cssSelector("div.cart-summary-totals .cart-summary-line:nth-child(3) span.value");
	
	private By botaoProceed = By.cssSelector(".text-sm-center a.btn-primary");

	// METODOS
	public String obter_nomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}

	public String obter_precoProduto() {
		return driver.findElement(precoProduto).getText();
	}

	public String obter_tamanhoProduto() {
		return driver.findElement(tamanhoProduto).getText();
	}

	public String obter_corProduto() {
		return driver.findElement(corProduto).getText();
	}

	public String obter_input_quantidadeProduto() {
		return driver.findElement(input_quantidadeProduto).getAttribute("value");
	}

	public String obter_subTotalProduto() {
		return driver.findElement(subTotalProduto).getText();
	}

	public String obter_numeroItensTotal() {
		return driver.findElement(numeroItensTotal).getText();
	}

	public String obter_subTotalTotal() {
		return driver.findElement(subTotalTotal).getText();
	}

	public String obter_shippTotal() {
		return driver.findElement(shippTotal).getText();
	}

	public String obter_totalTaxExcTotal() {
		return driver.findElement(totalTaxExcTotal).getText();
	}

	public String obter_totoalTexIncTotal() {
		return driver.findElement(totoalTexIncTotal).getText();
	}

	public String obter_taxaTotal() {
		return driver.findElement(taxaTotal).getText();
	}
	
	public CheckoutPage clicarBotaoProceed(){
		driver.findElement(botaoProceed).click();
		return new CheckoutPage(driver);
	}

}
