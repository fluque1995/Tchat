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

	//close sockets !
	public static void main(String[] args){

		byte[] host;
		int port;
		int userPort = 2049;
		InetAddress direction;
		Socket socket = null;
		Socket socketConnection = null;
		PrintWriter outPrinter;
		BufferedReader inReader;
		String user;
		String userIP;
		String message;
		Console cons;

		try {
			// Conexion with server
			host = "IP";
			port = 2049;
			direction = InetAddress.getByAddress(host);
			socket = new Socket(direction, port);

			// Hand shaking
			outPrinter = new PrintWriter(socket.getOutputStream(), true);
			inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			outPrinter.println("01");
			message = inReader.readLine();
			user = cons.readLine(message);
			outPrinter.println(user);
			userIP = inReader.readLine();
			
			if (!userIP.equals("-1")){
				direction = InetAddress.getByAddress(host);
				socket = new Socket(direction, port);
				while(true){
					message = c.readLine("Mensaje: ");
					outPrinter.println(message);
				}
			}
		}
		catch (IOException e) {
			System.err.println("Error");
		}
	}
}