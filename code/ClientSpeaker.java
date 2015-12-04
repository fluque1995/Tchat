import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.Console;
import java.net.InetAddress;

public class ClientSpeaker{

	public static void main(String[] args){

		// Server variables
		String serverIP = "172.20.92.138";
		int serverPort = 2048;
		
		// Other user variables
		int userPort;
		InetAddress direction;
		String user;
		String userIP;
		String message;

		// Buffers and socket
		Socket socketConnection = null;
		PrintWriter outPrinter;
		BufferedReader inReader;

		// Console objects
		Console cons = System.console();

		try {
			// Conexion with server
			direction = InetAddress.getByName(serverIP);
			socketConnection = new Socket(direction, serverPort);
			
			// Buffers opening
			outPrinter = new PrintWriter(socketConnection.getOutputStream(), true);
			inReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
			
			// Ask for connection to other user (MSG: "01")
			outPrinter.println("01");

			// Read server answer
			message = inReader.readLine();
			user = cons.readLine(message);

			// Send to the server the name of the user we want to contact
			outPrinter.println(user);

			// Read userIP (if user is not connected, reask)
			userIP = inReader.readLine();
			while (userIP.equals("-1")){
				System.out.println("Ese usuario no está conectado, introduzca otro.");	
				user = cons.readLine(message);
				outPrinter.println(user);
				userIP = inReader.readLine();
			}

			userPort = Integer.parseInt(inReader.readLine());
			// Get user InetAddress
			direction = InetAddress.getByName(serverIP);
			socketConnection.close();
			// Connect to other user
			socketConnection = new Socket(direction, userPort);

			// Open output printer and chat (message quit to close connection)
			outPrinter = new PrintWriter(socketConnection.getOutputStream(), true);
			do{
				message = cons.readLine("Mensaje: ");
				outPrinter.println(message);
			} while(!message.equals("quit"));
		
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de serverIP no encontrado.");
		} catch (IOException e) {
			System.err.println("Error: Problema con entrada/salida");
		}
	}
}