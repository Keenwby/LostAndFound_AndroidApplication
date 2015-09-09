package ws.local_client;

import android.os.AsyncTask;
import android.widget.Toast;
import android.content.Context;
import java.io.IOException;
import Entities.User;

/**
 * Created by wangbaiyang on 4/29/15.
 */
public class UploadUser extends AsyncTask<Object, Object, ClientSocket> implements SocketClientConstants{

    private User user = null;
    int result;

    public UploadUser(User user){
        this.user = user;
    }

    @Override
    protected ClientSocket doInBackground(Object... params) {
        ClientSocket clientSocket = new ClientSocket();
        int command = DEFAULT;
        try{
            clientSocket.sendCommand(NEWUSER);
            clientSocket.sendObject(user);
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
