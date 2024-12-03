import java.sql.*;
import java.util.ArrayList;

public class mysql_connector {

    //instantiation user
    DB_information user = new DB_information();

    mysql_connector(){

        //init jdbc driver,create connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            user.conn = DriverManager.getConnection(user.url, user.user, user.password);
        }
        catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        catch (SQLException e2) {
            e2.printStackTrace();
        }

        if(user.conn!=null){
            System.out.println("Connected to the database successfully!");
        }
        else{
            System.out.println("Failed to make connection!");
        }

    }

    //add_for_log
    public void add_data(user_information us){

        //insert element
        try{
            String sql = "INSERT INTO user(UID,user_name,pass_word) VALUES(?,?,?)";
            PreparedStatement ps = user.conn.prepareStatement(sql);
            ps.setInt(1, us.uid);
            ps.setString(2, us.user_name);
            ps.setString(3, us.pass_word);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //add_for_message
    public void add_data(user_information us,String message){

        //insert element
        try{
            String sql = "INSERT INTO message(content,time,user) VALUES(?,NOW(),?)";
            PreparedStatement ps = user.conn.prepareStatement(sql);
            ps.setString(1, message);
            ps.setString(2, us.user_name);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //delete
    //log_out
    public void delete(user_information us){

        try {
            Statement stmt = user.conn.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM user WHERE UID="+us.uid);//log-out user
            ResultSet rs_else = stmt.executeQuery("DELETE FROM message WHERE UID=null");//clear his record
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //delete_message
    public void delete(user_information us,int num){
        try {
            Statement stmt = user.conn.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM message WHERE UID="+num);//delete message
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //update
    //update for user
    public void update(user_information us) {

        try {
            String sql = "UPDATE user SET pass_word=?,user_name=? WHERE UID="+ us.uid;
            PreparedStatement ps = user.conn.prepareStatement(sql);
            ps.setString(1, us.pass_word);
            ps.setString(2, us.user_name);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //select
    //select for message only user
    public ArrayList<message> select(int user_id){

        ArrayList<message> ar = new ArrayList<message>();
        message ms = new message();
        try {
            Statement stmt = user.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT content,time FROM message WHERE UID="+user_id);
            while(rs.next()){
                 ms.content = rs.getNString("content");
                 ms.time = rs.getNString("time");
                 ar.add(ms);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ar;

    }

    //select for message all
    public ArrayList<message> select(){

        ArrayList<message> ar = new ArrayList<message>();
        message ms = new message();
        try {
            Statement stmt = user.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT content,time FROM message");
            while(rs.next()){
                ms.content = rs.getNString("content");
                ms.time = rs.getNString("time");
                ar.add(ms);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ar;

    }

    //select for user
    public ArrayList<user_information> select_for_root(){

        ArrayList<user_information> ar = new ArrayList<user_information>();
        user_information us = new user_information();
        try {
            Statement stmt = user.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT UID,user_name,pass_word FROM user");
            while(rs.next()){
                us.uid = rs.getInt("UID");
                us.user_name = rs.getNString("user_name");
                us.pass_word = rs.getNString("pass_word");
                ar.add(us);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return ar;

    }
}



















