import java.io.IOException;
import java.net.ServerSocket;

public class CustomTcpTask implements Runnable{

    public void run() {

        TCP_ConnectionPoolManager tcpConnectionPoolManager = new TCP_ConnectionPoolManager();

        try {

            ServerSocket serverSocket = new ServerSocket(7878);

            while(true){

                tcpConnectionPoolManager.connectionPool.getConnection(serverSocket.accept());
            }
        }
        catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
