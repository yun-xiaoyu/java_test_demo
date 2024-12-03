import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

    private final AtomicInteger i =new AtomicInteger(1);

    public Thread newThread(Runnable r){
        Thread thread = new Thread(r);
        thread.setName("thread No."+i.getAndIncrement());
        return thread;
    }

}
