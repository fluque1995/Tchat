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

                ServerThread serverThread = new ServerThread(socketConnection, users);
            
                serverThread.start();
            }catch(IOException e){
                System.err.println("Se ha producido un error en el servidor");
            }
        }
    }
}
