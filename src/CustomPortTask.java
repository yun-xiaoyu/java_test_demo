import java.io.IOException;

public class CustomPortTask implements Runnable {
    @Override
    public void run() {

        while(true){

            try {

                if(new TCP_ConnectionPoolManager().connectionPool.getInformation()!=null){

                    CustomTcpTask customTcpTask = new CustomTcpTask();
                    Main.threadPool.execute(customTcpTask);
                }
            }
            catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }
}
