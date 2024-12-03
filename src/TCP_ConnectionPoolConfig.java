//support less 10 connections visit serves
//each connection will close in 1 min without use
public class TCP_ConnectionPoolConfig {

    private int MaxConnection = 10;
    private long TimeOut = 60000;

    protected int limitConnection(){
        return this.MaxConnection;
    }

    protected long limitTime(){
        return this.TimeOut;
    }
}
