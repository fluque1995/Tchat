public class DirectoryService{

	private Map<String, String> users = new HashMap<String, String>();

	public void connect(String name, String IP){
			users.put(name,IP);
	}

	public void disconnect(String name){
			users.remove(name);
	}

	public Map<String, String> getUssers(){
			return users.values();
	}

	/*public BOOOOOM(){
		System.out.println("Mariadel se caga en Paco corazon corazon");
		users.clear();
	}*/


	public String isConnected(String name){
		if(users.containsKey(name)){
			return users.get(name);
		}
		else{
			return "No conectado";
		}
	}
}