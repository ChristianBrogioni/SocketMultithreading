package gestioneSocketMultithreading;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class gestioneServer {
	
	ServerSocket serverSocket;
	Socket socket;
	ClientHandler thread;
	
	public gestioneServer(int porta) {
		try {
			serverSocket= new ServerSocket(porta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void accetta() { 
		try {
			System.out.println("1 Server in attesa");
			socket=serverSocket.accept(); //ritorno del metodo accept, è un metodo bloccante (non succede nulla finchè non arriva la richiesta)
			System.out.println("3 Server socket: "+socket);
			thread= new ClientHandler(socket);
			thread.start();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
