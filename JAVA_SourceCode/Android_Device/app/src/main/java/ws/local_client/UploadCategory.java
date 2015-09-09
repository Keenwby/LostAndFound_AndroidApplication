package ws.local_client;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Entities.Category;

/**
 * Created by wangbaiyang on 4/30/15.
 */
public class UploadCategory extends AsyncTask<Object, Object, ClientSocket> implements SocketClientConstants{

    private Category cat = null;
    int result;

    public UploadCategory(Category cat){
        this.cat = cat;
    }

    @Override
    protected ClientSocket doInBackground(Object... params) {
        ClientSocket clientSocket = new ClientSocket();
        int command = DEFAULT;
        try{
            clientSocket.sendCommand(FINDERUPLOAD);
            clientSocket.sendObject(cat);
            command = clientSocket.getCommand();
            this.result = command;

        }catch (IOException e){
            e.printStackTrace();
        }
        return clientSocket;
    }

    @Override
    protected void onPostExecute(ClientSocket clientSocket) {
        clientSocket.closeSocket();
        //System.out.print("User Upload Success!");
    }

    public boolean checkStatus() {

        System.out.println(result);
        switch (this.result) {
            case SAVE_SUCCESS:
                return true;

            case SAVE_FAIL:
                return false;

            default:
                return false;
        }
    }

}
