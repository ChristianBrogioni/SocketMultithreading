package gestioneSocketMultithreading;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class gestioneClient {
	
	Socket socket;
	BufferedReader inDalServer;
	BufferedReader tastiera;
	DataOutputStream outVersoServer;
	String messaggioDelServer;


		
		public gestioneClient(InetAddress ip, int porta) { //costruttore della classe gestioneClient. Gli passo come parametri l'ip a cui deve connettersi il client e la porta in cui il server è in ascolto
			
			try {
				socket= new Socket(ip, porta); //apro socket specificando ip e numero di porta del server
				inDalServer= new BufferedReader(new InputStreamReader(socket.getInputStream())); //il client può ricevere dati dal server leggendo dall'InputStream
				tastiera= new BufferedReader(new InputStreamReader(System.in)); //InputStreamReader trasforma uno stream di byte in uno stream di caratteri. BufferedReader ci permette di leggere riga per riga
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
		
		
		public String leggi() { //metodo leggi che restituisce una stringa (numero di secondi del countdown della comunicazione)
			messaggioDelServer="";
			try {
				System.out.println("7 risposta del server");
				messaggioDelServer = inDalServer.readLine(); //lettura dell'InputStream tramite metodo readLine
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return messaggioDelServer;
		}
		
		public void scrivi() {
			try {
				DataOutputStream outVersoServer= new DataOutputStream(socket.getOutputStream()); //il client può scrivere sull'OutputStream
				System.out.println("4 Messaggio per il server: ");
				String messaggioPerServer = tastiera.readLine(); //inserimento di una stringa da tastiera
				System.out.println("5 Invio della stringa");
				outVersoServer.writeBytes(messaggioPerServer+"\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void chiudiServer() {
			try {
				System.out.println("Chiusura comunicazione con il server");
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


}
