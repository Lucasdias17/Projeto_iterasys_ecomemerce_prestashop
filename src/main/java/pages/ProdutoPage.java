package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProdutoPage {
	
	private WebDriver driver;
	
	private By nomeProduto = By.className("h1");
	
	private By precoProduto = By.cssSelector(".current-price span:nth-child(1)");
	
	private By tamanhoProtuto = By.id("group_1");
	
	private By selecionarCorPreta = By.xpath("//ul[@id='group_2']//input[@value='11']");
	
	private By selecionarQuantidade = By.id("quantity_wanted");
	
	private By botaoAddToCart = By.className("add-to-cart");
	
	public ProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obterNomeProduto() {
		return driver.findElement(nomeProduto).getText();
	}
	
	public String obterPrecoProduto() {
		return driver.findElement(precoProduto).getText();
	}
	
	public void selecionarOpcaoDropDown(String opcao) {
		encontrarDropdownSize().selectByVisibleText(opcao);
	}
	
	public List<String> obterOpcaoes(){
		List<WebElement> elementosSelecionados =
		encontrarDropdownSize().getAllSelectedOptions();
		
		List<String> listaOpcoesSelecionar = new ArrayList();
		for (WebElement elemento : elementosSelecionados) {
			listaOpcoesSelecionar.add(elemento.getText());
		}
		return listaOpcoesSelecionar;
	}
	
	public Select encontrarDropdownSize() {
		return new Select(driver.findElement(tamanhoProtuto));
	}
	
	public void selecionarCorPreta() {
		driver.findElement(selecionarCorPreta).click();
	}
	
	public void quantidadeProduto(int qnt){
		driver.findElement(selecionarQuantidade).clear();
		driver.findElement(selecionarQuantidade).sendKeys(Integer.toString(qnt));
		
	}
	
	public ModalProdutoPage clicarBotaAddTo() {
		driver.findElement(botaoAddToCart).click();
		return new ModalProdutoPage(driver);
	}
}
