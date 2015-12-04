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

	public static void main(String[] args){

		// Server variables
		int serverPort = 2048;
		String serverIP = "172.20.57.75";

		// Other user variables
		InetAddress direction;
		String userName;
		int userPort;
		String message;

		// Sockets and buffers
		Socket socketConnection = null;
		ServerSocket serverSocket =  null;
		PrintWriter outPrinter;
		BufferedReader inReader;
		
		// Console object
		Console cons = System.console();
		
		try{
			// Conexion with server
			direction = InetAddress.getByName(serverIP);
			socketConnection = new Socket(direction, serverPort);

			// Buffers opening
			outPrinter = new PrintWriter(socketConnection.getOutputStream(), true);
			inReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
			
			// Register on server db (MSG: "00")
			outPrinter.println("00");
			message = inReader.readLine();
			
			// Choose userName
			userName = cons.readLine(message);
			outPrinter.println(userName);

			userPort = Integer.parseInt(inReader.readLine());
			//Create serverSocket
			serverSocket = new ServerSocket(userPort);
			socketConnection.close();
			
			System.out.println("Registrado correctamente.");
			System.out.println("Esperando conexión entrante...");

			// Accept connection from other user
			socketConnection = serverSocket.accept();
			System.out.println("Recibida conexión de otro usuario");
			// Open input buffer and receive messages
			inReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
			while(!message.equals("quit")){
				message = inReader.readLine();
				System.out.println(message);
			}
			System.out.println("El usuario se ha ido");
			socketConnection.close();
			
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de  serverIP no encontrado.");
		} catch (IOException e) {
			System.err.println("Error: Problema con entrada/salida en ClientListener");
		}
	}
}