package ws.local_client;

import android.os.AsyncTask;

import java.io.IOException;

import Entities.User;

/**
 * Created by wangbaiyang on 5/3/15.
 */
public class UpdateUser extends AsyncTask<Object, Object, ClientSocket> implements SocketClientConstants{

    private User user = null;
    int result;

    public UpdateUser(User user){
        this.user = user;
    }

    @Override
    protected ClientSocket doInBackground(Object... params) {
        ClientSocket clientSocket = new ClientSocket();
        int command = DEFAULT;
        try{
            clientSocket.sendCommand(UPDATEUSER);
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

