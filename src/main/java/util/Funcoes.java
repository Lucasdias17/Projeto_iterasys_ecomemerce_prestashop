package util;

public class Funcoes {

	public static Double removeCifraEDevolveDouble(String texto) {
	 	texto = texto.replace("$", "");
	 	return Double.parseDouble(texto);
		
	}
	
	public static int removeTextoItems(String texto) {
	 	texto = texto.replace(" items", "");
	 	return Integer.parseInt(texto);
	}
	
	public static String removeTexto(String texto, String textoRemover) {
	 	texto = texto.replace(textoRemover, "");
	 	return texto;
	}
}

