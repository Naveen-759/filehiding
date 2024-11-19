package DAO;

import db.Myconnection;
import db.model.Data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataDAO {
    public static List<Data> getAllfiles(String email) throws SQLException {
        Connection con= Myconnection.getconnection();
        PreparedStatement ps=con.prepareStatement("Select * from data where email=?");
        ps.setString(1,email);
        ResultSet rs=ps.executeQuery();
        List<Data> files =new ArrayList<>();
        while(rs.next()){
            int id=rs.getInt(1);
            String name=rs.getString(2);
            String path=rs.getString(3);
            files.add(new Data(id,name,path));
        }
        return files;
    }
    public static void hideFile(Data files) throws SQLException, IOException ,  FileNotFoundException{
        Connection con=Myconnection.getconnection();
        PreparedStatement ps=con.prepareStatement("insert into data (name,path,email,bin_data) values (?,?,?,?)");
        ps.setString(1,files.getFilename());
        ps.setString(3,files.getEmail());
        ps.setString(2,files.getPath());
        File f=new File(files.getPath());
        FileReader fr= new FileReader(f);
        ps.setCharacterStream(4,fr,f.length());
        int ans=ps.executeUpdate();
        fr.close();
        f.delete();
//        return ans;

    }
    public  static void unhide(int id)throws SQLException,IOException{
        Connection con=Myconnection.getconnection();
        PreparedStatement ps=con.prepareStatement("Select path,bin_data from data where id=?");
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        rs.next();
        String path=rs.getString("path");
        Clob c=rs.getClob("bin_data");
        Reader r=c.getCharacterStream();
        FileWriter fw=new FileWriter(path);
        int i;
        while((i=r.read())!=-1){
            fw.write((char) i);

        }
        fw.close();
        ps=con.prepareStatement("delete from data where id=?");
        ps.setInt(1,id);
        ps.executeUpdate();
        System.out.println("Successfully unhidden");


    }
}
