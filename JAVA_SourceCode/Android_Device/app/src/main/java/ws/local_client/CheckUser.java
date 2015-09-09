package ws.local_client;

import android.os.AsyncTask;

import java.io.IOException;

import Entities.User;
import ui.shared.LogInFragment;

/**
 * Created by wangbaiyang on 5/1/15.
 */
public class CheckUser extends AsyncTask<Object, Object, ClientSocket> implements SocketClientConstants{

    private User user = null;
    int result;

    public CheckUser(User user){
        this.user = user;
    }

    @Override
    protected ClientSocket doInBackground(Object... params) {
        ClientSocket clientSocket = new ClientSocket();
        int command = DEFAULT;
        try{
            clientSocket.sendCommand(CHECKUSER);
            clientSocket.sendObject(user);
            command = clientSocket.getCommand();
            this.result = command;
            System.out.println("result = " + result);

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

    public int checkStatus() {

        switch (this.result) {
            case CHECK_PASS:
                return LogInFragment.PASS;

            case CHECK_FAIL:
                return LogInFragment.FAIL;

            case SAVE_FAIL:
                return LogInFragment.NOTFOUND;

            default:
                return LogInFragment.NOTFOUND;
        }
    }

}
