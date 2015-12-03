import java.net.*
import java.io.*

public class DirectoryService{

	private Map<Socket, PrintWriter> users;

	public DirectoryService(){
		this.users = new HashMap<Socket, PrintWriter>();
	}

	public void connect(Socket socketConnection, PrintWriter usrPrintWriter){
			users.put(socketConnection, usrPrintWriter);
	}

	public void disconnect(Socket socketConnection){
			users.remove(socketConnection);
	}

}