//manage Thread pool connection
public class TCP_ConnectionPoolManager {
    protected TCP_ConnectionPool connectionPool;

    public void initialize(TCP_ConnectionPoolConfig config){
        connectionPool = new TCP_ConnectionPool(config);
    }
}
