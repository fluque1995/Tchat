import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server{

    public static void main(String[] args){
        ServerSocket serverSocket = null;
        int port = 2048;
        Socket socketConnection;
        HashMap<String, InetAddress> users = new HashMap<String, InetAddress>();
        
        try{
            serverSocket = new ServerSocket(port);
        }catch (IOException e){
            System.err.println("Error: no se pudo atender en el puerto " + port);
        }
        
        while(true){
            
            try{

                socketConnection = serverSocket.accept();
                System.out.println("Se intentan conectar conmigo");
                PrintWriter outPrinter = new PrintWriter(socketConnection.getOutputStream(), true);
                BufferedReader inReader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
                String code = inReader.readLine();
        
                if (code.equals("00")){
                    outPrinter.println("Bienvenido a Tchat. Introduzca su nombre de usuario: ");
                    String usrname = inReader.readLine();
                    users.put(usrname, socketConnection.getInetAddress());
                }
        
                if (code.equals("01")){
                    outPrinter.println("Bienvenido a Tchat. Introduzca el nombre del usuario al que se quiere conectar: ");
                    String usrname = inReader.readLine();
                    String address_to_send = null;
                    if (users.containsKey(usrname)){
                        address_to_send = users.get(users).getHostAddress();
                    } else {
                        address_to_send = "-1";
                    }
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
