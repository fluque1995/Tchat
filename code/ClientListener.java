import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.Console;

public class ClientListener{

	//close sockets !
	public static void main(String[] args){

		byte[] host;
		int port;
		int ownPort;
		inetAddress direction;
		Socket socket = null;
		Socket socketConnection = null;
		ServerSocket serverSocket =  null;
		PrintWriter outPrinter;
		BufferedReader inReader;
		String userName;


		// Conexion with server
		this.host = "tututututututututu".getBytes(StandardCharsets.UTF_8);
		this.port = 2048;
		this.direction = inetAddress.getByAddress(host,port);
		this.socket = new Socket(direction, port);

		// Hand shaking
		outPrinter = new PrintWriter(socket.getOutputStream(), true);
		outPrinter.println("00");
		inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// Choose userName
		String userName;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Introduzca nombre de usuario:");
		userName = br.readLine();

		outPrinter.println(userName2);

		//Create serverSocket
		serverSocket = new ServerSocket(ownPort);
		socketConnection = serverSocket.accept();


	}
}