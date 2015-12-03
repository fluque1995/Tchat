import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server{

    public static void main(String[] args){

        // Sockets and port
        ServerSocket serverSocket = null;
        Socket socketConnection = null;
        int port = 2048;

        // Collection of connected users
        HashMap<String, InetAddress> users = new HashMap<String, InetAddress>();
        
        // Open server socket
        try{
            serverSocket = new ServerSocket(port);
        }catch (IOException e){
            System.err.println("Error: no se pudo atender en el puerto " + port);
        }
        
        while(true){
            
            try{

                // Accept client connection
                socketConnection = serverSocket.accept();
                System.out.println("Se intentan conectar conmigo");

                // Open I/O buffers
                PrintWriter outPrinter = new PrintWriter(socketConnection.getOutputStream(), true);
                BufferedReader inReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
                
                // Read action code
                String code = inReader.readLine();
                
                // Action for code "00" (Register user)
                if (code.equals("00")){
                    outPrinter.println("Bienvenido a Tchat. Introduzca su nombre de usuario: ");
                    String usrname = inReader.readLine();
                    users.put(usrname, socketConnection.getInetAddress());
                }
                
                // Action for code "01" (Answer connection with user IP)
                if (code.equals("01")){
                    outPrinter.println("Bienvenido a Tchat. Introduzca el nombre del usuario al que se quiere conectar: ");
                    String usrname = inReader.readLine();
                    String address_to_send = "-1";
                    while(!users.containsKey(usrname)){
                        outPrinter.println(address_to_send);
                        usrname = inReader.readLine();
                    }
                    address_to_send = users.get(usrname).getHostAddress();
                    outPrinter.println(address_to_send);
                }

                System.out.println("Cierro la conexión con el cliente");
                socketConnection.close();
            
            }catch(IOException e){
                System.err.println("Se ha producido un error en el servidor");
            }
        }

    }
}
