package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ForgotFrame extends JFrame {
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

    private JLabel Forgot;

    private JLabel EnterForgotLogin;
    private JLabel SecretQuestion ;
    private JLabel Answer;
    private JLabel YourPassword;

    private JTextField ForgotLogin;
    private JTextField EnterSecretQuestion;
    private JTextField YourAnswer;
    private JTextField YourOldPassword;

    private JButton Search;
    private JButton Retrieve;
    private JButton back= new JButton("<==");



    public ForgotFrame(){
        super("Forgot GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connection = Connect.ConnectDb();
        add(back);
        back.addActionListener(new ForgotFrame.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,420,100,20);

        connection = Connect.ConnectDb();

        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("Forgot");
        setResizable(false);
        setVisible(true);


        Forgot = new JLabel("Forgot");
        Forgot.setBounds(220,20,200,20);
        add(Forgot);

        EnterForgotLogin = new JLabel("Enter login: ");
        EnterForgotLogin.setBounds(30,120,200,20);
        add(EnterForgotLogin);

        SecretQuestion = new JLabel("Secret Question: ");
        SecretQuestion.setBounds(30,150,200,20);
        add(SecretQuestion);

        Answer = new JLabel("Answer: ");
        Answer.setBounds(30,180,200,20);
        add(Answer);

        YourPassword = new JLabel("Your Password: ");
        YourPassword.setBounds(30,210,200,20);
        add(YourPassword);

        ForgotLogin = new JTextField();
        ForgotLogin.setBounds(130,120,200,20);
        add(ForgotLogin);

        EnterSecretQuestion = new JTextField();
        EnterSecretQuestion.setBounds(130,150,200,20);
        add(EnterSecretQuestion);

        YourAnswer = new JTextField();
        YourAnswer.setBounds(130,180,200,20);
        add(YourAnswer);

        YourOldPassword = new JTextField();
        YourOldPassword.setBounds(130,210,200,20);
        add(YourOldPassword);

        Search = new JButton("Search");
        Search.setBounds(340,120,100,20);
        add(Search);



        Retrieve = new JButton("Retrieve");
        Retrieve.setBounds(340,180,100,20);
        add(Retrieve);

        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Search();
            }
        });


        Retrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Retrieve();
            }
        });










    }

    public void Search(){
        try {
            String text = ForgotLogin.getText();

            String query = "select * from users where Login ='" + text + "'" ;

            Statement statement = connection.createStatement();


            resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                JOptionPane.showMessageDialog(null,"That's login true");

                String secretP = resultSet.getString("secret_question");



                EnterSecretQuestion.setText(secretP);
//                resultSet.close();
//                preparedStatement.close();

            }else {
                JOptionPane.showMessageDialog(null, "Incorrect login");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Retrieve(){

        try{
            String a2 = YourAnswer.getText();
            String query = "select * from users where answer = '" + a2 + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeQuery();

            Statement statement = connection.createStatement();


            resultSet = statement.executeQuery(query);

            if (resultSet.next()){
                String ans = resultSet.getString("answer");

                String pas = resultSet.getString("password");

                if (a2.equalsIgnoreCase(ans)){
                    JOptionPane.showMessageDialog(null, "Correct answer");
                    YourOldPassword.setText(pas);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect answer");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


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
