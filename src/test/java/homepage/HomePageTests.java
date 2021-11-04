package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.CarrinhoPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.PedidoPage;
import pages.ProdutoPage;
import util.Funcoes;

public class HomePageTests extends BaseTests {
	
	@Test
	public void testContarProdutos() {
		carregarPaginaInicial();
		assertThat(homePage.contarProdutos(), is(8));
	}
	
	@Test
	public void testValidarCarrinhoCompra() {
		carregarPaginaInicial();
		int produtosNoCarrinho = homePage.obterQuantidadeProdutosCarrinho();
		System.out.println(produtosNoCarrinho);
		
		assertThat(produtosNoCarrinho, is(0));
		
	}
	ProdutoPage produtoPage;
	String nomeProduto_produtoPage;
	@Test
	public void testValidarDetalherProduto() {
		int indice = 0;
		String nomeProduto_homePage = homePage.obterNomeProduto(indice);
		String precoProduto_homePage = homePage.obterPrecoProduto(indice);
		
		System.out.println(nomeProduto_homePage);
		System.out.println(precoProduto_homePage);
		
		produtoPage = homePage.clickProduto(indice);
		
		nomeProduto_produtoPage = produtoPage.obterNomeProduto();
		String precoProduto_produtoPage = produtoPage.obterPrecoProduto();
		
		System.out.println(nomeProduto_produtoPage);
		System.out.println(precoProduto_produtoPage);
		
		assertThat(nomeProduto_homePage.toUpperCase(), is(nomeProduto_produtoPage.toUpperCase()));
		assertThat(precoProduto_homePage, is(precoProduto_produtoPage));
		
	}
	LoginPage loginPage;
	@Test
	public void testLoginComSucesso() {
		//clicar o botao sign in na home page
		loginPage = homePage.clickBotaoSignIn();
		
		//Preencher usuario e senha
		loginPage.preencherEmail("lucas216@teste.com");
		loginPage.preencherPassWord("123456");
		
		//Clicar o botao de Sign In
		loginPage.clickBotaoSignIn();
		
		//Validar se o usuario está logado
	 	loginPage.logadoComSucesso2();
		assertThat(loginPage.logadoComSucesso("Lucas Dias"), is(true));
		//volta pagina inicial
		carregarPaginaInicial();
	
	}
	ModalProdutoPage modalProdutoPage;
	@Test
	public void testIncluirProdutoNoCarrinho() {
		String tamanhoProduto = "M";
		String corProduto = "Black";
		int qntProduto = 2 ;
		
		//-pre condição - usuario logado
		if(!homePage.logadoComSucesso("Lucas Dias")) {
			testLoginComSucesso();
		}
		//teste 
		//selecionado produtos
		testValidarDetalherProduto();
		
		//selecionar tamanho
	 	List<String> listaOpces = produtoPage.obterOpcaoes();
	 	
	 	System.out.println(listaOpces.get(0));
	 	System.out.println("Tamanho da lsita " + listaOpces.size());
	 	
	 	produtoPage.selecionarOpcaoDropDown(tamanhoProduto);
	 	
	 	listaOpces = produtoPage.obterOpcaoes();
	 	System.out.println(listaOpces.get(0));
	 	System.out.println("Tamanho da lsita " + listaOpces.size());
		
		//selecionar cor
	 	produtoPage.selecionarCorPreta();
		
		//selecionar quantidade
	 	produtoPage.quantidadeProduto(qntProduto);
	 	
	 	//produtoPage.clicarBotaAddTo();
	 	
	 	//adicionar no carrinho
	 	modalProdutoPage = produtoPage.clicarBotaAddTo();
	 	
	 	//VALIDAÇOES
	 	//estava puxando com ponto antes do msg
	 	//assertThat(modalProdutoPage.confirmacaoMsgAdd(), is("Product successfully added to your shopping cart"));
	 	assertTrue(modalProdutoPage.confirmacaoMsgAdd().endsWith("Product successfully added to your shopping cart"));
	 	
	 	
	 	//Pegando o valor unitario do produto em String e transformando em Double
	 	//Metodo Replace realiza a troca do primeiro item para o segundo	 	
	 	//Metodo que converte para o double	 	
	 	String precoProdutoString = modalProdutoPage.obterPrecoProduto();
	 	precoProdutoString = precoProdutoString.replace("$", "");
	 	Double precoProduto = Double.parseDouble(precoProdutoString);
	 	
	 	//Validando cor, tamanho e quantidada da modal
	 	assertThat(modalProdutoPage.obterCor(), is(corProduto));
	 	assertThat(modalProdutoPage.obterTamanho(), is(tamanhoProduto));
	 	assertThat(modalProdutoPage.obterQuantidade(), is(Integer.toString(qntProduto)));
	 	
	 	//valindao o nome do produto com pagina e modal - Convertemos todos para maiuscolo
	 	assertThat(modalProdutoPage.obterDescricao().toUpperCase(), is(nomeProduto_produtoPage.toUpperCase()));
	 	
	 	//Pegando o subtotao do valor do item e transoformando em double
	 	//Metodo Replace realiza a troca do primeiro item para o segundo	 	
	 	//Metodo que converte para o double	 	
	 	String precoSubProdutoString = modalProdutoPage.obterSubTotal();
	 	precoSubProdutoString = precoSubProdutoString.replace("$", "");
	 	Double precoSubProduto = Double.parseDouble(precoSubProdutoString);
	 	
	 	//Fazendo a conta manual para conferencia no assert
	 	Double subTotalCalculado = qntProduto * precoProduto;
	 	
	 	assertThat(subTotalCalculado, is(precoSubProduto));
	}
	
	//Valores esperados para utilizar no assert
	
	String esp_corProduto = "Black";
	int esp_input_quantidadeProduto = 2;
	String esp_nomeProduto = "Hummingbird printed t-shirt";
	Double esp_precoProduto = 19.12;
	Double esp_subTotalProduto = esp_precoProduto * esp_input_quantidadeProduto;
	String esp_tamanhoProduto = "M";
	
	int esp_numeroItensTotal = esp_input_quantidadeProduto;
	Double esp_shippTotal = 7.00;
	Double esp_subTotalTotal = esp_subTotalProduto;
	Double esp_taxaTotal = 0.00;
	Double esp_totalTaxExcTotal = esp_subTotalTotal + esp_shippTotal;
	Double esp_totoalTexIncTotal = esp_totalTaxExcTotal + esp_taxaTotal;

	CarrinhoPage carrinhoPage;
	@Test
	public void testIrParaCarrinho() {
		//Pré-codição
		//Produto deve está na pagina modal
		testIncluirProdutoNoCarrinho();
		
		carrinhoPage = modalProdutoPage.clicarBotaoProceedToCheckout();
		
		//teste carrinho
		
		//Validação da Tela
		System.out.println("*****Produto*****");
		System.out.println(carrinhoPage.obter_corProduto()); //Black
		System.out.println(carrinhoPage.obter_input_quantidadeProduto()); //2
		System.out.println(carrinhoPage.obter_nomeProduto()); //Hummingbird printed t-shirt
		System.out.println(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_precoProduto())); //$19.12
		System.out.println(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_subTotalProduto())); //$38.24
		System.out.println(carrinhoPage.obter_tamanhoProduto()); // M
		
		System.out.println("*****Total*****");
		System.out.println(Funcoes.removeTextoItems(carrinhoPage.obter_numeroItensTotal())); //2 items
		System.out.println(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_shippTotal())); // $7.00
		System.out.println(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_subTotalTotal())); //$38.24
		System.out.println(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_taxaTotal())); //$0.00
		System.out.println(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_totalTaxExcTotal())); //$45.24
		System.out.println(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_totoalTexIncTotal())); //$45.24
		
		assertThat(carrinhoPage.obter_corProduto(), is(esp_corProduto));
		assertThat(Integer.parseInt(carrinhoPage.obter_input_quantidadeProduto()), is(esp_input_quantidadeProduto));
		assertThat(carrinhoPage.obter_nomeProduto(), is(esp_nomeProduto));
		assertThat(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_precoProduto()), is(esp_precoProduto));
		assertThat(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_subTotalProduto()), is(esp_subTotalProduto));
		assertThat(carrinhoPage.obter_tamanhoProduto(), is(esp_tamanhoProduto));
		
		assertThat(Funcoes.removeTextoItems(carrinhoPage.obter_numeroItensTotal()), is(esp_numeroItensTotal));
		assertThat(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_shippTotal()), is(esp_shippTotal));
		assertThat(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_subTotalTotal()), is(esp_subTotalTotal));
		assertThat(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_taxaTotal()), is(esp_taxaTotal));
		assertThat(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_totalTaxExcTotal()), is(esp_totalTaxExcTotal));
		assertThat(Funcoes.removeCifraEDevolveDouble(carrinhoPage.obter_totoalTexIncTotal()), is(esp_totoalTexIncTotal));

	}
	CheckoutPage checkoutPage;
	String esp_nomeCliente = "Lucas Dias";
	@Test
	public void testIrParaChekout() {
		//Pre-condição
		
		//Produto disponivel
		testIrParaCarrinho();
		
		//clincar botao
		checkoutPage = carrinhoPage.clicarBotaoProceed();
		
		//Teste.
		//System.out.println(Funcoes.removeCifraEDevolveDouble(checkoutPage.obter_totalTaxIncTotal()));
		//System.out.println(esp_totoalTexIncTotal);
		assertThat(Funcoes.removeCifraEDevolveDouble(checkoutPage.obter_totalTaxIncTotal()), is(esp_totoalTexIncTotal));
		
		//assertThat(checkoutPage.obter_nomeCliente(), is(esp_nomeCliente));
		//startWith - Ele verficar o começo do retorno
		assertTrue(checkoutPage.obter_nomeCliente().startsWith(esp_nomeCliente));
		
		//Preencher informação
		
		checkoutPage.clicarBotaoContinuar();
		
		//Validar informação na tela
		String esp_shippingValor = checkoutPage.obter_shippingValor();
		esp_shippingValor = Funcoes.removeTexto(esp_shippingValor, " tax excl.");
		Double esp_shippingValorDouble = Funcoes.removeCifraEDevolveDouble(esp_shippingValor);
		
		assertThat(esp_shippingValorDouble, is(esp_shippTotal));
		
		checkoutPage.clicarBotaoContinuarShipping();
		
		//Selecionar opçao "pay By check"
		checkoutPage.selecionarRadioPayByCheck();
		//Validar valor do cheque
		String esp_valorAmauntPayByCheck  = checkoutPage.obter_amauntPayByCheck();
		esp_valorAmauntPayByCheck = Funcoes.removeTexto (esp_valorAmauntPayByCheck, " (tax incl.)");
		double esp_valorAmauntPayByCheckDouble = Funcoes.removeCifraEDevolveDouble(esp_valorAmauntPayByCheck);
		
		assertThat(esp_valorAmauntPayByCheckDouble, is(esp_totoalTexIncTotal));
		//clicar no oppa "i dree"
		
		checkoutPage.selecionarCheckBoxIGree();
		assertTrue(checkoutPage.confirmarSelecaoCheckBoxIGree());
	}
	
	@Test
	public void testFinalizarPedido() {
		//Pré condição
		//Checkout completamente concluido
		testIrParaChekout();
		
		//Teste
		//Clicar no botão para confirmar
		PedidoPage pedidoPage = checkoutPage.clicarBotaConfirmar();
		//Validar valores da tela
		//assertThat(pedidoPage.obter_textoPedidoConfirmado().toUpperCase(), is("YOUR ORDER IS CONFIRMED")); Resultado trás o icons, então e melhor utilizar o assrtTrue
		//endsWith, ele pega somento o final do texto
		assertTrue(pedidoPage.obter_textoPedidoConfirmado().endsWith("YOUR ORDER IS CONFIRMED"));
		
		assertThat(pedidoPage.obter_textoEmail(), is("lucas216@teste.com"));
		
		assertThat(pedidoPage.obter_valorTotal(), is(esp_subTotalTotal));
		
		assertThat(pedidoPage.obter_valorTotalInclus(), is(esp_totoalTexIncTotal));
		
		assertThat(pedidoPage.obter_tipoPagamento(), is("check"));
		
	}
}
