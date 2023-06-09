package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Admin extends JFrame {

    private JLabel welcome;
    private JLabel login;
    private JLabel password;

    private JTextField enterLogin;

    private JPasswordField enterPassword;


    private JButton back= new JButton("<==");
    private JButton AdminPage;


    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public Admin(){

        super("Admin GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connection = Connect.ConnectDb();

        add(back);
        back.addActionListener(new Admin.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,420,100,20);


        connection = Connect.ConnectDb();
        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("Admin");

        welcome = new JLabel("Welcome to Admin page");
        welcome.setBounds(160,20,200,20);
        add(welcome);

        login = new JLabel("Login: ");
        login.setBounds(50,150,40,20);
        add(login);

        password = new JLabel("Password: ");
        password.setBounds(50,180,80,20);
        add(password);

        enterLogin = new JTextField();
        enterLogin.setBounds(130,150,200,20);
        add(enterLogin);

        enterPassword = new JPasswordField();
        enterPassword.setBounds(130,180,200,20);
        add(enterPassword);

        AdminPage = new JButton("Enter");
        AdminPage.setBounds(180,210,100,20);
        add(AdminPage);




        AdminPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String query = "select * from admin where login=? and password =?";

                    preparedStatement=connection.prepareStatement(query);

                    preparedStatement.setString(1,enterLogin.getText());
                    preparedStatement.setString(2,enterPassword.getText());

                    resultSet=preparedStatement.executeQuery();



                    if(resultSet.next()){


                        resultSet.close();

                        preparedStatement.close();

                        setVisible(false);

                        LoginFrameAdmin loginFrameAdmin =new LoginFrameAdmin();
                        loginFrameAdmin.setVisible(true);


                    }else{
                        JOptionPane.showMessageDialog(null,"Incorrect login and password");
                    }

                }catch (SQLException ex){
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

                new MainFrame().setVisible(true);
            }
        }
    }
}
