import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class CustomTask implements Runnable{

    protected String choice;
    protected OutputStream outputStream;

    @Override
    public void run() {

        int choice = Integer.parseInt(this.choice);
        String s_contain = null;
        user_information userInformation = new user_information();
        int line = -1;

        switch (choice){


            case 1:
                mysql_connector mc =new mysql_connector();
                ArrayList<message> ar =  mc.select();
                Iterator<message> it = ar.iterator();
                while(it.hasNext()){
                    String s = "";
                    s += it.next().num;
                    s +=" ";
                    s += it.next().content;
                    s +=" ";
                    s += it.next().time;
                    PrintWriter out = new PrintWriter(outputStream,true);
                    out.println(s);
                }
                break;

            case 2:
                mysql_connector mc1 =new mysql_connector();
                ArrayList<message> ar1 =  mc1.select(userInformation.uid);
                Iterator<message> it1 = ar1.iterator();
                while(it1.hasNext()){
                    String s = "";
                    s += it1.next().num;
                    s +=" ";
                    s += it1.next().content;
                    s +=" ";
                    s += it1.next().time;
                    PrintWriter out = new PrintWriter(outputStream,true);
                    out.println(s);
                }
                break;

            case 3:
                mysql_connector mc2 =new mysql_connector();
                mc2.add_data(userInformation,s_contain);
                break;

            case 4:
                mysql_connector mc3 =new mysql_connector();
                mc3.delete(userInformation,line);
                break;


        }
    }
}
