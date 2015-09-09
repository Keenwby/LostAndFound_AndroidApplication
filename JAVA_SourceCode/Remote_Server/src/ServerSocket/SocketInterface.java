package ServerSocket;

public interface SocketInterface {

	public boolean openConnection();
    
	public void handleSession();
	
    public void closeSession();
}
