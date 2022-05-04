package gestioneSocketMultithreading;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.Timestamp;

public class ClientHandler extends Thread {
	
	private final Socket clientSocket;
	BufferedReader inDalClient;
	DataOutputStream outVersoClient;
	String messaggioDelClient;
	String str;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}
	
	@Override
	public void run() {
		try {
			
			inDalClient= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			outVersoClient = new DataOutputStream(clientSocket.getOutputStream());
				for(;;) {
					outVersoClient.writeBytes("Ciao client" +"\r\n");
					str= leggi();
					System.out.println(str);
					
				}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

public String leggi() throws IOException{
			String sinc= "sinc";
			String fine= "Fine";
			messaggioDelClient= inDalClient.readLine(); //lettura dell'InputStream tramite metodo readLine
			System.out.println("Messaggio del client: "+ messaggioDelClient);
			if(messaggioDelClient.equals(sinc)) {
				String timeString = new Timestamp(System.currentTimeMillis()).toString(); //ottengo la data e l'ora corrente trasformandola in stringa tramite metodo toString. La trasformo in stringa in modo da poter utilizzare il metodo writeBytes
				outVersoClient.writeBytes(timeString+"\r\n"); //scrittura sull'OutputStream tramite metodo writeBytes. La concatenazione dei caratteri di controllo di fine riga "\r\n" è necessaria per l'utilizzo del metodo readLine()
			}
			if(messaggioDelClient.equalsIgnoreCase(fine)) {
				chiudiClient();
			}
		return messaggioDelClient;
	}

public void scrivi(String messaggioRicevuto) throws IOException {
	
		System.out.println("6 messaggio Ricevuto");
		outVersoClient.writeBytes(messaggioRicevuto+"ricevuto e ritrasmesso" +"\r\n"); //scrittura sull'OutputStream tramite metodo writeBytes. La concatenazione dei caratteri di controllo di fine riga "\r\n" è necessaria per l'utilizzo del metodo readLine()
}

public void chiudiClient() {
	try {
		
		if (outVersoClient != null) {
			System.out.println("Chiusura dell'OutputStream");
            outVersoClient.close();
        }
        if (inDalClient != null) {
        	System.out.println("Chiusura dell'InputStream");
            inDalClient.close();
        }
		System.out.println("Chiusura comunicazione con il client");
		clientSocket.close(); // se volevo comunicare con altri client dovevo fare socket.flush() (esce dalla ram e va nell'HD mantenendo l'output stream aperto per future operazioni. Se volessi riutilizzare lo stream dopo averlo chiuso avrei un NullPointerException)
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
