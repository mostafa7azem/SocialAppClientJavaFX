package SocialAppClient.Connections;

import SocialAppGeneral.Connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by kemo on 28/10/2016.
 */

/**
 * Created by kemo on 28/10/2016.
 */
public abstract class ServerConnection implements Connection{
    private int port;
    private String serverName;
    public Socket connectionSocket;

    public ServerConnection(String serverName, int startPort) throws ServerNotFound {
        port = -1;
        this.serverName = serverName;
        while(port == -1) {
            findPort(startPort, startPort + 3);
        }
        startConnection();
        //TODO #Exception
    }
    private void findPort(int sPort, int ePort)
    {
        if (sPort == ePort) return ;
        try {
            connectionSocket = new Socket(serverName, sPort);
            //verify if the socket found is the desired socket
            verifyConnection();
            port = sPort;
            connectionSocket.setSoTimeout(5000);
        }
        catch (IOException e) {
            System.out.println("Reconnecting on " + String.valueOf(sPort));
            findPort(sPort + 1, ePort);
        }
        catch (Exception e)
        {
            //TODO #Lastly
            //export it to log file
            e.printStackTrace();
        }
    }
    private void verifyConnection() throws IOException {
        try {
            connectionSocket.setSoTimeout(200); //set time out for reading input
            DataInputStream dataInputStream = new DataInputStream(connectionSocket.getInputStream());
            byte[] b = new byte[VERIFICATION.length()];
            dataInputStream.readFully(b);//reading in bytes format because i cant make sure of the data coming from other sockets
            if (!(new String(b).equals(VERIFICATION))) throw new IOException("wrong verification code"); //throw exception if code is wrong
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
