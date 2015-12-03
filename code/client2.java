import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.Console;

static PrintWriter outPrinter;
static BufferedReader inReader;

public class Listener implements Runnable{
	private thread thr;
	Console windowChat;
	PrintWriter pW;

	public Listener(){
		thr = new Thread(this, "Listener");
		windowChat = new Console();
		pW = windowChat.writer();
	}

	public void run(){
		while(true){
			pW.println(inReader.readLine());
		}
	}
}

public class Producer implements Runnable{
	private thread thr;

	public Producer(){
		thr = new Thread(this, "Producer");
	}

	public void run(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String message;
		while(true){
			message = br.readLine();
			outPrinter.println(message);
		}
	}
}


public class Client{

	private byte[] host;
	private int port;
	private inetAddress direction;
	private Socket serverSocket = null;

	public Client(){
		this.host = "tututututututututu".getBytes(StandardCharsets.UTF_8);
		this.port = 2048;
		this.direction = inetAddress.getByAddress(host,port);
	}

	private void connect(){

		// Handshaking
		this.serverSocket = new Socket(host, port);
		outPrinter = new PrintWriter(serverSocket.getOutputStream(), true);
		outPrinter.println("CONNECT");
		inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));

		// Conection with new server thread
		this.port = 2049;
		serverSocket.close();
		this.serverSocket = new Socket(host, port);
		outPrinter = new PrintWriter(serverSocket.getOutputStream(), true);
		inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
	}

	private boolean logIn(String usrName){
		boolean valid = false;
		this.outPrinter.println(usrName);
	}


	public static void main(String[] args){
		Client client = new Client();
		String user;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		client.connect();

		System.out.println("Introduzca nombre de usuario:");
		user = br.readLine();
		client.logIn(user);

		Producer producer = new Producer();
		Listener listener = new Listener();

		producer.thr.start();
		listener.thr.start();
	}
}