package ws.local_client;

import android.os.AsyncTask;

import java.io.IOException;

import Entities.Category;

/**
 * Created by wangbaiyang on 5/2/15.
 */
public class OwnerResult extends AsyncTask<Object, Object, ClientSocket> implements SocketClientConstants {

    private int result = -1;
    private Category cat;

    public OwnerResult(Category cat) {
        this.cat = cat;
    }

    @Override
    protected ClientSocket doInBackground(Object... params) {
        ClientSocket clientSocket = new ClientSocket();
        try {
            clientSocket.sendCommand(OWNRESULT);
            clientSocket.sendObject(cat);
            result = clientSocket.getCommand();
            this.cat = (Category)clientSocket.getObject();

        } catch (IOException e) {
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
            case CHECK_PASS:
                return true;

            case CHECK_FAIL:
                return false;

            default:
                return false;
        }
    }
    public Category getCat(){
        return this.cat;
    }
}