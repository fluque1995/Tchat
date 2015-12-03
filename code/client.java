import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client{

	private int port;
	private InetAddress direction;
	private DatagramPacket packet;
	private byte[] IPserver;
	private DatagramSocket socket;
	private String IPString;
	private PrintWriter;
	private Socket serverSocket = null;

	public Client(){
		this.port = 2048;
		this.socket = new DatagramSocket();
		this.IPString = "11.111.1.1.1.tutututu";
		this.IPserver = new byte[1024];
		this.IPserver = direction.getBytes(StandardCharsets.UTF_8);
		this.direction = InetAddress.getByAddress(IPserver);
		this.socketServicio = new Socket(host, port);

	}

	private void connect(){
		this.outPrinter = new PrintWriter(serverSocket.getOutputStream(), true);
		outPrinter.println("Hello");
		packet = new DatagramPacket(bufer, bufer.length, direccion, puerto);
		socket.send(packet);
	}

	public static void main(String[] args){

		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";

		// Socket para la conexión TCP
		Socket socketServicio=null;

		try{
			// Creamos un socket que se conecte a "host" y "port":
			//////////////////////////////////////////////////////
			// socketServicio= ... (Completar)
			//////////////////////////////////////////////////////
			socketServicio = new Socket(host, port);

			BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
			PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);

			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
			// a un array de bytes:

			// Enviamos el array por el outputStream;
			//////////////////////////////////////////////////////
			// ... .write ... (Completar)
			//////////////////////////////////////////////////////
			outPrinter.println("Al monte del volcán debes ir sin demora");

			// Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes, sólo
			// los enviará efectivamente cuando considere que tiene suficientes datos que enviar...
			// Podemos usar "flush()" para obligar a TCP a que no espere para hacer el envío:
			//////////////////////////////////////////////////////
			// ... .flush(); (Completar)
			//////////////////////////////////////////////////////
			// outputStream.flush();

			// Leemos la respuesta del servidor. Para ello le pasamos un array de bytes, que intentará
			// rellenar. El método "read(...)" devolverá el número de bytes leídos.
			//////////////////////////////////////////////////////
			// bytesLeidos ... .read... buferRecepcion ; (Completar)
			//////////////////////////////////////////////////////

			// MOstremos la cadena de caracteres recibidos:
			System.out.println("Recibido: ");
			System.out.println(inReader.readLine());

			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			// ... close(); (Completar)
			//////////////////////////////////////////////////////
			socketServicio.close();

			// Excepciones:
		} catch (UnknownHostException e){
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e){
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}