package gestioneSocketMultithreading;

import java.net.InetAddress;
import java.net.UnknownHostException;
import gestioneSocketMultithreading.gestioneClient;

public class Client {
	
	public static void main(String[] args) {
		gestioneClient client;
		String messaggioFine= "Fine";
		try { 
			System.out.println("2 Client in esecuzione");
			client = new gestioneClient(InetAddress.getLocalHost(), 2000);//istanza dell'oggetto di tipo gestioneClient a cui passo il local host e la porta in cui il server è in ascolto
				client.scrivi();
				String str= client.leggi(); //variabile di tipo string che contiene la data e l'ora attuale
				System.out.println(str);
				if(str.equals(messaggioFine)) {
					client.chiudiServer();
				}
		} catch (UnknownHostException e) { //gestione eccezzione host sconosciuto
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
