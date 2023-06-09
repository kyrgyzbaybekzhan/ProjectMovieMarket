package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class EditAdmin extends JFrame {
    private JLabel login;
    private JLabel login1;
    private JLabel password;
    private JLabel password1;

    private JTextField enterLogin;
    private JTextField enterPassword;
    private JTextField login11;
    private JTextField password11;

    private JButton back= new JButton("<==");
    private JButton AddAdmin;
    private JButton DeleteAdmin;


    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;


    public EditAdmin(){
        super("Edit Admin GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connection = Connect.ConnectDb();

        add(back);
        back.addActionListener(new EditAdmin.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,420,100,20);


        connection = Connect.ConnectDb();
        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("Edit Admin");

        login = new JLabel("Login: ");
        login.setBounds(50,150,40,20);
        add(login);

        password = new JLabel("Password: ");
        password.setBounds(50,180,80,20);
        add(password);

        enterLogin = new JTextField();
        enterLogin.setBounds(130,150,200,20);
        add(enterLogin);

        enterPassword = new JTextField();
        enterPassword.setBounds(130,180,200,20);
        add(enterPassword);

        AddAdmin = new JButton("Add Admin");
        AddAdmin.setBounds(180,210,100,20);
        add(AddAdmin);

        DeleteAdmin =new JButton("Delete Admin");
        DeleteAdmin.setBounds(180,370,150,20);
        add(DeleteAdmin);

        login1=new JLabel("Insert login:");
        login1.setBounds(50,290,100,20);
        add(login1);

        password1=new JLabel("Insert password:");
        password1.setBounds(50,330,100,20);
        add(password1);

        login11=new JTextField();
        login11.setBounds(150,290,200,20);
        add(login11);

        password11=new JTextField();
        password11.setBounds(150,330,200,20);
        add(password11);



        AddAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query = "insert into admin(login , password) values (?,?)";

                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setString(1,enterLogin.getText());
                    preparedStatement.setString(2,enterPassword.getText());

                    preparedStatement.execute();

                    JOptionPane.showMessageDialog(null, "New account created");
//                    resultSet.close();
//
//                    preparedStatement.close();

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        DeleteAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                String query1 = "delete from admin where login =? and password =?" ;
                preparedStatement = connection.prepareStatement(query1);

                preparedStatement.setString(1, login11.getText());
                preparedStatement.setString(2, password11.getText());

                preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Admin was successfully removed");


            }catch (Exception ex){
                ex.printStackTrace();
            }

            }


        });


    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals("back")) {
                dispose();

                new LoginFrameAdmin().setVisible(true);
            }
        }
    }

}
