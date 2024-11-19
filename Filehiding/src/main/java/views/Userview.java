package views;

import DAO.DataDAO;
import db.model.Data;
import db.model.User;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Userview{
    private final String email;
    Userview(String email){
        this.email=email;
    }
    public  void Home(){
        do{
            System.out.println("Welcome"+ this.email);
            System.out.println("1 to show hidden file");
            System.out.println("2 to hide a new file");
            System.out.println("3 to unhide");
            System.out.println("0 to exit");
            Scanner sc= new Scanner(System.in);
            int ch=Integer.parseInt(sc.nextLine());


            switch (ch){
                case 1->{
                    try {
                        List<Data> files=DataDAO.getAllfiles(this.email);
                        System.out.println(files.toString());
                        System.out.println("ID - File Name");
                        for (Data file:files){
                            System.out.println(file.getId()+" "+ file.getFilename());
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2->{
                    System.out.println("enter the file path");
                    String path=sc.nextLine();
                    File f=new File(path);
                    Data file=new Data(0,f.getName(),path,this.email);
                    try {
                        DataDAO.hideFile(file);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException x) {
                        x.printStackTrace();
                    }
                }
                case 3->{
                    List<Data> files= null;
                    try {
                        files = DataDAO.getAllfiles(this.email);

                    System.out.println("ID - File Name");
                    for (Data file:files){
                        System.out.println(file.getId()+" "+  file.getFilename());
                    }
                    System.out.println("enter id of file to  hide");
                    int id=Integer.parseInt(sc.nextLine());
                    boolean isValidID=false;
                    for(Data file:files){
                        if(file.getId()==id){
                            isValidID=true;
                            break;
                        }

                    }
                    if(isValidID){
                        DataDAO.unhide(id);
                    }
                    else{
                        System.out.println("wrong id");
                    }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case 0->{
                    System.exit(0);
                }
            }
        }while(true);
    }
}
