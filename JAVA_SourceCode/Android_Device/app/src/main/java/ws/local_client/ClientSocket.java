package ws.local_client;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.io.IOException;

/**
 * Created by wangbaiyang on 4/30/15.
 */
public class ClientSocket implements  SocketClientConstants{

    private static String ServerIP = "10.0.19.121"; //"10.0.23.104";//"10.0.23.123";//"10.0.0.38";
    private static Socket socket = null;
    private static ObjectOutputStream oos = null;
    private static ObjectInputStream ois = null;

    protected ClientSocket() {
        try {
            socket = new Socket(ServerIP, IPORT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Server get an object from Client
    public Object getObject() throws IOException{
        Object propobject = null;
            try {
                propobject = ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return propobject;
        }
        //Server get a command from Client
        public int getCommand() throws IOException{
            String get = null;
            get = (String) getObject();
            System.out.print("Command" + get + " received!");
            return Integer.valueOf(get);
        }

        //Server send an object to Client
        public void sendObject(Object propsobject) throws IOException{
            if (propsobject == null)
                return;
            oos.writeObject(propsobject);
            oos.flush();
        }
        //Server send a command to Client
    public void sendCommand(int command) throws IOException{

        sendObject(Integer.toString(command));
        System.out.println("Command " + command + " sent!");
        oos.flush();
    }

    public void closeSocket() {

        try {
            ois = null;
            oos = null;
            socket.close();
        } catch (Exception e) {
            System.err.println
                    ("Error closing socket to " + ServerIP);
        }
    }
}
