package ws.local_client;

import android.os.AsyncTask;

import java.io.IOException;

import Entities.Category;

/**
 * Created by wangbaiyang on 5/1/15.
 */
public class DownloadList extends AsyncTask<Object, Object, ClientSocket> implements SocketClientConstants{

    private Category cat = null;
    int result = DEFAULT;

    public DownloadList(Category cat){
        this.cat = cat;
    }

    @Override
    protected ClientSocket doInBackground(Object... params) {
        ClientSocket clientSocket = new ClientSocket();
        try{
            clientSocket.sendCommand(OWNERCATEGORYLIST);
            clientSocket.sendObject(cat);
            this.cat = (Category) clientSocket.getObject();
            this.result = cat.equals(null) ? SAVE_FAIL : SAVE_SUCCESS;

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

    public Category getList(){
        return this.cat;
    }
}
