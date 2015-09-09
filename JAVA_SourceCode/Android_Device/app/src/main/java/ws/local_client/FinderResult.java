package ws.local_client;

import android.os.AsyncTask;

import java.io.IOException;

import Entities.Category;

/**
 * Created by wangbaiyang on 5/2/15.
 */
public class FinderResult extends AsyncTask<Object, Object, ClientSocket> implements SocketClientConstants{

    private int result = -1;
    private Category cat;

    public FinderResult(int result, Category cat){
        switch (result){
            case 1:
                this.result = CHECK_PASS;
                break;
            case 0:
                this.result = CHECK_FAIL;
                break;
        }

        this.cat = cat;
    }

    @Override
    protected ClientSocket doInBackground(Object... params) {
        ClientSocket clientSocket = new ClientSocket();
        try{
            clientSocket.sendCommand(FINDERRESULT);
            clientSocket.sendCommand(result);
            clientSocket.sendObject(cat);

        }catch (IOException e){
            e.printStackTrace();
        }
        return clientSocket;
    }

    @Override
    protected void onPostExecute(ClientSocket clientSocket) {
        clientSocket.closeSocket();
    }
}
