import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

//one connection maintain two property for input and output
public class TCP_Connection {

    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected Socket socket;
    public long start_time = System.currentTimeMillis();

    TCP_Connection(Socket socket){
        try{
            this.inputStream = socket.getInputStream();
            this.outputStream = socket.getOutputStream();
            this.socket = socket;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
