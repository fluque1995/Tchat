import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Console;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.net.InetAddress;

public class ClientListener{

	//close sockets !
	public static void main(String[] args){

		int port;
		InetAddress direction;
		Socket socketConnection = null;
		ServerSocket serverSocket =  null;
		PrintWriter outPrinter;
		BufferedReader inReader;
		String userName;
		String message;
		String host;
		Console cons;
		
		try{
			// Conexion with server
			host = "172.20.56.161";
			port = 2048;
			direction = InetAddress.getByName(host);
			socketConnection = new Socket(direction, port);
			cons = System.console();

			// Hand shaking
			outPrinter = new PrintWriter(socketConnection.getOutputStream(), true);
			inReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
			outPrinter.println("00");
			message = inReader.readLine();
			userName = cons.readLine(message);
			System.out.println("He leído correctamente el usrname");
			outPrinter.println(userName);
			System.out.println("Usrname enviado al servidor");
			// Choose userName

			//Create serverSocket
			port = 2049;
			serverSocket = new ServerSocket(port);
			socketConnection = serverSocket.accept();

			inReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
			outPrinter = cons.writer();
			while(!message.equals("quit")){
				message = inReader.readLine();
				System.out.println(message);
			}
			
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error: Problema con entrada/salida");
		}
	}
}