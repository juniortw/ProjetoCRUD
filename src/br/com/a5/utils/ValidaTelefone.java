package br.com.a5.utils;

public class ValidaTelefone {
	
	public static boolean isTelefone(String numeroTelefone) {

		if (numeroTelefone.length() == 11) {
			return true;
		} else {

			if (numeroTelefone.length() == 9)
				return true;

			if (numeroTelefone.length() == 10)
				numeroTelefone = numeroTelefone.substring(2, numeroTelefone.length());

			return !(numeroTelefone.startsWith("1") || numeroTelefone.startsWith("2") || numeroTelefone.startsWith("3")
					|| numeroTelefone.startsWith("4") || numeroTelefone.startsWith("5"));
		}

	}

}
