import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



public class Main {

    //create thread pool 2 core thread 8 common thread
    static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            2,
            10,
            10L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(),
            new CustomThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    static CustomTcpTask customTcpTask = new CustomTcpTask();
    static CustomPortTask customPortTask = new CustomPortTask();

    public static void main(String[] args) {

        //execute tcp task listen port get tcp connection
        threadPool.execute(customTcpTask);
        //itr tcp connections search post arrive message
        threadPool.execute(customPortTask);
    }
}