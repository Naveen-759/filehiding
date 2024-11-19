package views;

import DAO.UserDAO;
import db.model.User;
import service.GenerateOTP;
import service.SendOTPService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Welcome {

    public void welcomescreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("welcome ");
        System.out.println("1 to login ");
        System.out.println("2 to signup ");
        System.out.println("0 to exit ");
        int choice=0;
        try{
            choice =Integer.parseInt(br.readLine());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        switch(choice){
            case 1:login();break;
            case 2:signUp();break;
            case 0:System.exit(0);
        }


    }

    private void signUp()  {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter name");
        String name= sc.nextLine();
        System.out.println("enter email");
        String email= sc.nextLine();
                String genOTP= GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("enter the OTP");
                String otp=sc.nextLine();
                if(otp.equals(genOTP)){
                    User user=new User(name,email);
                    int response= UserService.SaveUser(user);
                    switch (response){
                        case 0->System.out.println("user registered");
                        case 1-> System.out.println("user already existed");

//                        case null -> System.out.println("null null null");
                        default -> System.out.println("some this error occured");
                    }
                }else{
                    System.out.println("Wrong OTP");
                }




    }

    private void login() {
        System.out.println("enter email");
        Scanner sc=new Scanner(System.in);
        String email=sc.nextLine();
        try{
            if(UserDAO.isexists(email)){
                String genOTP= GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("enter the OTP");
                String otp=sc.nextLine();
                if(otp.equals(genOTP)){
                new Userview(email).Home();
                }else{
                    System.out.println("Wrong OTP");
                }
            }
            else{
                System.out.println("user not found");
                System.exit(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

//package views;
//
//import DAO.UserDAO;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.SQLException;
//import java.util.Scanner;
//import db.model.User;
//import service.GenerateOTP;
//import service.SendOTPService;
//import service.UserService;
//
//public class Welcome {
//    public Welcome() {
//    }
//
//    public void welcomeScreen() {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Wlcome to the app");
//        System.out.println("Press 1 to login");
//        System.out.println("Press 2 to signup");
//        System.out.println("Press 0 to exit");
//        int choice = 0;
//
//        try {
//            choice = Integer.parseInt(br.readLine());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        switch (choice) {
//            case 0 -> System.exit(0);
//            case 1 -> this.login();
//            case 2 -> this.signUp();
//        }
//
//    }
//
//    private void login() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter email");
//        String email = sc.nextLine();
//
//        try {
//            if (UserDAO.isexists(email)) {
//                String genOTP = GenerateOTP.getOTP();
//                SendOTPService.sendOTP(email, genOTP);
//                System.out.println("Enter the otp");
//                String otp = sc.nextLine();
//                if (otp.equals(genOTP)) {
////                    (new UserView(email)).home();
//                    System.out.println("welcom");
//                } else {
//                    System.out.println("Wrong OTP");
//                }
//            } else {
//                System.out.println("User not found");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//    private void signUp() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter name");
//        String name = sc.nextLine();
//        System.out.println("Enter email");
//        String email = sc.nextLine();
//        String genOTP = GenerateOTP.getOTP();
//        SendOTPService.sendOTP(email, genOTP);
//        System.out.println("Enter the otp");
//        String otp = sc.nextLine();
//        if (otp.equals(genOTP)) {
//            User user = new User(name, email);
//            int response = UserService.SaveUser(user);
//            switch (response) {
//                case 0 -> System.out.println("User registered");
//                case 1 -> System.out.println("User already exists");
//            }
//        } else {
//            System.out.println("Wrong OTP");
//        }
//
//    }
//}

