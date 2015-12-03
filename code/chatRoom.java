package server;

import java.net.*
import java.io.*
import directoryService.*

public class chatRoom{

    private DirectoryService directoryService
    private int connectionPort;
    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private PrintWriter usrPrintWriter;
    private String buffer = null;
    private Semaphore buffer_full;
    
    public MainServer(){

        ownInetaddr = InetAddress.getByName("localhost");
        directoryService = new DirectoryService();
        connectionPort = 2048;
        serverSocket = new ServerSocket(connectionPort);
        connectionSocket = null;

    }

    public static void main(String[] args) {
        
        ChatRoom handler = new ChatRoom(directoryService);

        do {
            connectionSocket = serverSocket.accept();
            usrPrintWriter = new PrintWriter(connectionSocket.getOutputStream(), true);
            handler.connect(connectionSocket, usrPrintWriter);
        } while(true);
    }
}