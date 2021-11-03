package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver driver;
	
	List<WebElement> listaProdutos = new ArrayList();
	//encontra os produtos do inicio
	private By produtos = By.className("product-description");
	// valor do carrinho
	private By textProdutosCarrinho = By.className("cart-products-count");
	// nome do produto
	private By descricoesDosProdutos = By.cssSelector(".product-description a");
	// preço do produto
	private By precoProduto = By.className("price");
	// botao sign in
	private By botaoSignIn = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	//conferir acesso com sucesso
	private By usuarioLogado = By.cssSelector("#_desktop_user_info span.hidden-sm-down");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;   
	}
	
	public int contarProdutos() {
		carregarListaProdutos();
		return listaProdutos.size();
	}
	
	private void carregarListaProdutos() {
		listaProdutos = driver.findElements(produtos);
		
	}
	
	public int obterQuantidadeProdutosCarrinho() {
		String quantidadeProdutos = driver.findElement(textProdutosCarrinho).getText();
		quantidadeProdutos = quantidadeProdutos.replace("(", "");
		quantidadeProdutos = quantidadeProdutos.replace(")", "");
		
		int qtdProdutosCarrinho = Integer.parseInt(quantidadeProdutos);
		
		return qtdProdutosCarrinho;
	}
	
	public String obterNomeProduto(int indice) {
		return driver.findElements(descricoesDosProdutos).get(indice).getText();
	}
	
	public String obterPrecoProduto(int indice) {
		return driver.findElements(precoProduto).get(indice).getText();
	}
	public ProdutoPage clickProduto(int indice) {
		driver.findElements(descricoesDosProdutos).get(indice).click();
		return new ProdutoPage(driver);
	}
	
	public LoginPage clickBotaoSignIn() {
		driver.findElement(botaoSignIn).click();
		return new LoginPage(driver);
	}
	
	public boolean logadoComSucesso(String texto) {
	 	return	texto.contentEquals(driver.findElement(usuarioLogado).getText());
	}
	
	public void logadoComSucesso2() {
	  	String l = driver.findElement(usuarioLogado).getText();
	  	System.out.println(l);
	 	
	}
	
 

}
