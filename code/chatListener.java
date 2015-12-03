package server;

import java.net.*
import java.io.*

public class ChatRoom extends Thread{
    
    private int ownPort = 2049;
    private DirectoryService directoryService;

    public ChatRoom(DirectoryService directoryService){
        this.directoryService = directoryService;
        

    }
}