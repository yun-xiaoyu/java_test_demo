import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TCP_ConnectionPool {
    private List<TCP_Connection> connections = new ArrayList<>();
    private TCP_ConnectionPoolConfig config;

    public TCP_ConnectionPool(TCP_ConnectionPoolConfig config){
        this.config = config;
    }

    public boolean getConnection(Socket socket) throws IOException{

        if(socket == null){
            return false;
        }

        if(this.config.limitConnection()>connections.size()){
            connections.add(new TCP_Connection(socket));
            return true;
        }else{
            return false;
        }
    }

    public void releaseConnection(TCP_Connection connection) throws IOException{

        if(connection.start_time - System.currentTimeMillis()<=config.limitTime()){
            connection.socket.close();
        }
    }

    public String getInformation() throws IOException {

        Iterator<TCP_Connection> it = this.connections.iterator();

        while(it.hasNext()){

            TCP_Connection con = it.next();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.inputStream));
            String receivedMessage= bufferedReader.readLine();

            if(receivedMessage != null){
                con.start_time = System.currentTimeMillis();
                return receivedMessage;
            }

        }
        return null;
    }

}
