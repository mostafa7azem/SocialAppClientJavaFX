package SocialAppClient;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by kemo on 08/11/2016.
 */
public class UtilityConnection extends ServerConnection {
    public UtilityConnection(String id, int port) throws Exception {
        //connect to server port 6100 which responsible for notifications
        //TODO #config
        super("127.0.0.1",port);
        sendId(id);
    }
    @Override
    public void startConnection() {
        //start listening to server commands in another Thread


    }
    private void sendId(String id)
    {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(connectionSocket.getOutputStream());
            dataOutputStream.writeUTF(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Socket getConnectionsocket()
    {
        return connectionSocket;
    }
}