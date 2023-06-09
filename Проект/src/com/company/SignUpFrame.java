package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SignUpFrame extends JFrame {


    // static private JLabel label1; // метка
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

    private JLabel sign_up;
    private JLabel EnterYourLogin;
    private JLabel EnterYourPassword;
    private JLabel Associations;
    private JLabel Answer;
    private JLabel Money;

    private JComboBox YourAssociations;

    private JTextField YourAnswer;
    private JTextField YourLogin;
    private JTextField YourPassword;
    private JTextField AccountMoney;

    private JButton Capture;
    private JButton back= new JButton("<==");

    private String [] questions={"Your favorite book","Your first love","Your best friend","Your favorite car","Your favorite language","Your favorite country","Your lovely place","Your favorite football team","Your favorite sport","Your favorite singer"};



    public SignUpFrame(){

        super("SignUp GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connection = Connect.ConnectDb();

        add(back);
        back.addActionListener(new SignUpFrame.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,420,100,20);




        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("Sign Up");
        setResizable(false);
        setVisible(true);


        sign_up = new JLabel("Sign Up");
        sign_up.setBounds(210,20,200,20);
        add(sign_up);

        EnterYourLogin = new JLabel("Enter login: ");
        EnterYourLogin.setBounds(60,120,200,20);
        add(EnterYourLogin);

        EnterYourPassword = new JLabel("Enter Password: ");
        EnterYourPassword.setBounds(60,150,200,20);
        add(EnterYourPassword);

        Associations = new JLabel("Secret Questions: ");
        Associations.setBounds(60,180,200,20);
        add(Associations);

        Answer = new JLabel("Answer: ");
        Answer.setBounds(60,210,200,20);
        add(Answer);

        Money=new JLabel("Your money: ");
        Money.setBounds(60,240,200,20);
        add(Money);

        YourAssociations = new JComboBox(questions);
        YourAssociations.setBounds(160,180,200,20);
        add(YourAssociations);

        YourLogin = new JTextField();
        YourLogin.setBounds(160,120,200,20);
        add(YourLogin);

        YourPassword = new JTextField();
        YourPassword.setBounds(160,150,200,20);
        add(YourPassword);

        YourAnswer = new JTextField();
        YourAnswer.setBounds(160,210,200,20);
        add(YourAnswer);

        AccountMoney = new JTextField();
        AccountMoney.setBounds(160,240,200,20);
        add(AccountMoney);

        Capture = new JButton("Capture");
        Capture.setBounds(205,300,100,20);
        add(Capture);

        Capture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query = "insert into users(login , password, secret_question, answer,money) values (?,?,?,?,?)";

                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setString(1,YourLogin.getText());
                    preparedStatement.setString(2,YourPassword.getText());
                    preparedStatement.setString(3,(String)YourAssociations.getSelectedItem());
                    preparedStatement.setString(4,YourAnswer.getText());
                    preparedStatement.setString(5,AccountMoney.getText());

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
