package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import util.Funcoes;

public class PedidoPage {
	private WebDriver driver;

	public PedidoPage(WebDriver driver) {
		this.driver = driver;
	}
	//Elementos-Variavel
	private By textoPedidoConfirmado = By.cssSelector("#content-hook_order_confirmation h3");
	
	private By textoEmail = By.cssSelector("#content-hook_order_confirmation p");
	
	private By valorTotal = By.cssSelector("div.order-confirmation-table div.order-line div.row div.bold");
	
	private By valorTotalInclus = By.cssSelector("div.order-confirmation-table tr.total-value td:nth-child(2) ");
	
	private By tipoPagamento = By.cssSelector("#order-details ul li:nth-child(2)");
	
	
	//Metodos
	public String obter_textoPedidoConfirmado() {
		return driver.findElement(textoPedidoConfirmado).getText();
	}
	
	public String obter_textoEmail() {
		//lucas216@teste.com
		String texto = driver.findElement(textoEmail).getText();
		texto = Funcoes.removeTexto(texto, "An email has been sent to the ");
		texto = Funcoes.removeTexto(texto, " address.");
		return texto;
	}
	
	public Double obter_valorTotal() {
		return Funcoes.removeCifraEDevolveDouble(driver.findElement(valorTotal).getText());
	}
	
	public Double obter_valorTotalInclus() {
		return Funcoes.removeCifraEDevolveDouble(driver.findElement(valorTotalInclus).getText());
	}
	
	public String obter_tipoPagamento() {
		//lucas216@teste.com
		String texto = driver.findElement(tipoPagamento).getText();
		texto = Funcoes.removeTexto(texto, "Payment method: Payments by ");
		return texto;
	}
	
}
