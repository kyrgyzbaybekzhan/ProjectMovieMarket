package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainFrame extends JFrame {
    //private JLabel label1; // метка
    //private JLabel labelRes; // метка
    // private JLabel labelAges; // метка
    // private JTextField jTextField; // для ввода текста
    // private JTextField res; // для ввода текста
    // private JButton sendButton; // кнопка для результата
    //private JComboBox ages; // выборка
    // private JTextArea textArea; // для отображения большого текста

    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    private JLabel welcome;
    private JLabel login;
    private JLabel password;
    private JLabel picture;

    JFrame frame;

    private static JTextField enterLogin;

    private JPasswordField enterPassword;

    private JButton login1;
    private JButton signUp;
    private JButton forgot;
    private JButton admin;

    public static String getLogin() {
        return enterLogin.getText();
    }



    public MainFrame() {


        connection = Connect.ConnectDb();
        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("MovieMarket");



        welcome = new JLabel("Welcome to the MovieMarket");
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

        login1 = new JButton("Login");
        login1.setBounds(110,220,100,20);
        add(login1);

        signUp = new JButton("Sign up");
        signUp.setBounds(250,220,100,20);
        add(signUp);

        forgot = new JButton("Forgot?");
        forgot.setBounds(180,260,100,20);
        add(forgot);

        admin=new JButton("Admin");
        admin.setBounds(180,300,100,20);
        add(admin);

        login1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String query = "select * from users  where login=? and password =?";

                    preparedStatement=connection.prepareStatement(query);

                    preparedStatement.setString(1,enterLogin.getText());
                    preparedStatement.setString(2,enterPassword.getText());

                    resultSet=preparedStatement.executeQuery();



                    if(resultSet.next()){

                        int user_id = resultSet.getInt("id");
                        String login =resultSet.getString("login");
                        String password =resultSet.getString("password");
                        String secret_question =resultSet.getString("secret_question");
                        String answer =resultSet.getString("answer");
                        int money =resultSet.getInt("money");

                        User auth_user = new User();
                        auth_user.setId(user_id);
                        auth_user.setLogin(login);
                        auth_user.setPassword(password);
                        auth_user.setSecret_question(secret_question);
                        auth_user.setAnswer(answer);
                        auth_user.setMoney(money);

                        resultSet.close();
                        preparedStatement.close();
                        setVisible(false);

                        LoginFrameUser loginFrameUser = new LoginFrameUser(auth_user);

                        loginFrameUser.setVisible(true);


                    }else{
                        JOptionPane.showMessageDialog(null,"Incorrect login and password");
                    }

                }catch (SQLException ex){
                    ex.printStackTrace();

                }
            }
        });

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpFrame signUpFrame = new SignUpFrame();
                dispose();
            }
        });

        forgot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForgotFrame forgotFrame = new ForgotFrame();
                dispose();
            }
        });

        admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin1 = new Admin();
                dispose();
            }
        });



    }
    public static void main( String[] args ) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });

    }

}
