package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

public class ModalProdutoPage {
	
	private WebDriver driver;
	
	public ModalProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By msgAddProduct = By.id("myModalLabel");
	
	private By titleProduct = By.className("product-name");	
	
	private By precoProduct = By.cssSelector("div.col-md-6 p.product-price");
	
	private By listaDeInformacao = By.cssSelector("div.divide-right .col-md-6:nth-child(2) span strong");
	
	private By subTotal = By.cssSelector(".cart-content p:nth-child(2) span.value");
	
	private By botaoProceedToCheckout = By.cssSelector("div.cart-content-btn .btn-primary");
	
	public String confirmacaoMsgAdd () {
		//controla o tempo de espera, e faz o tratamento da excensão
		FluentWait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		//esperar ate aparecer msg do elemento
		wait.until(ExpectedConditions.visibilityOfElementLocated(msgAddProduct));
		
		return driver.findElement(msgAddProduct).getText();
	}
	
	public String obterDescricao() {
		return driver.findElement(titleProduct).getText();
	}
	
	public String obterPrecoProduto() {
		return driver.findElement(precoProduct).getText();
	}
	
	public String obterTamanho() {
		return driver.findElements(listaDeInformacao).get(0).getText();
	}
	
	public String obterCor() {
		return driver.findElements(listaDeInformacao).get(1).getText();
	}
	
	public String obterQuantidade() {
		return driver.findElements(listaDeInformacao).get(2).getText();	
	}
	
	public String obterSubTotal() {
		return driver.findElement(subTotal).getText();
	}
	
	public CarrinhoPage clicarBotaoProceedToCheckout() {
		driver.findElement(botaoProceedToCheckout).click();
		return new CarrinhoPage(driver);
	}

}
