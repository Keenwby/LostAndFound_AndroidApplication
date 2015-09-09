package ws.remote_server;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class MyServer {

    public static void main(String[] args){
        String ip = getIP();
        DefaultSocketRemoteServer a = new DefaultSocketRemoteServer(ip, 9008);
		a.start();
	}
	
	public static String getIP(){
		String strLocalHost = "";
		// get the local host name
		try{
			strLocalHost = InetAddress.getLocalHost().toString();
		}catch(UnknownHostException e){
			System.out.println("Unable to find local host");
		}
		return strLocalHost;
	}
}