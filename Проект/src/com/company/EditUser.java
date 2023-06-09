package com.company;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditUser extends JFrame{
    private JLabel UserName;
    private JLabel UserPassword;
    private JLabel UserMoney;

    private JButton changeName;
    private JButton changePassword;
    private JButton addMoney;
    private JButton deleteAccount;
    private JButton back= new JButton("<==");

    private  JTextField ChangeUserName;
    private JTextField ChangeUserPassword;
    private JTextField ChangeUserMoney;


    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    String userLogin = MainFrame.getLogin();

    public User auth_user;

    public EditUser (User auth_user)  {
        super("Edit Admin GUI");
        this.auth_user = auth_user;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connection = Connect.ConnectDb();

        add(back);
        back.addActionListener(new EditUser.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,420,100,20);


        connection = Connect.ConnectDb();
        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("User Profile");

        UserName = new JLabel("Your Login: " + auth_user.getLogin());
        UserName.setBounds(40,80,200,20);
        add(UserName);

        UserPassword = new JLabel("Your password: " + auth_user.getPassword());
        UserPassword.setBounds(40,120,200,20);
        add(UserPassword);

        UserMoney = new JLabel("Your money: " + auth_user.getMoney());
        UserMoney.setBounds(40,150,200,20);
        add(UserMoney);

        changeName = new JButton("Change Name");
        changeName.setBounds(20,200,150,20);
        add(changeName);

        changePassword = new JButton("Change Password");
        changePassword.setBounds(20,240,150,20);
        add(changePassword);

        addMoney = new JButton("Add Money");
        addMoney.setBounds(20,280,150,20);
        add(addMoney);

        deleteAccount = new JButton("Delete My Account");
        deleteAccount.setBounds(320,420,150,20);
        add(deleteAccount);

        ChangeUserName = new JTextField();
        ChangeUserName.setBounds(200,200,200,20);
        add(ChangeUserName);

        ChangeUserPassword = new JTextField();
        ChangeUserPassword.setBounds(200,240,200,20);
        add(ChangeUserPassword);

        ChangeUserMoney = new JTextField();
        ChangeUserMoney.setBounds(200,280,200,20);
        add(ChangeUserMoney);



        String query = "select * from users where login=? and id =?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, auth_user.getLogin());
            preparedStatement.setInt(2,auth_user.getId());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        changeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String query1 = "update users set login=? where id=?" ;
                    preparedStatement = connection.prepareStatement(query1);

                    preparedStatement.setString(1, ChangeUserName.getText());
                    preparedStatement.setInt(2, auth_user.getId());


                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Login was changed");

                    auth_user.setLogin(ChangeUserName.getText());

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String query1 = "update users set password=? where id=?" ;
                    preparedStatement = connection.prepareStatement(query1);


                    preparedStatement.setString(1, ChangeUserPassword.getText());
                    preparedStatement.setInt(2, auth_user.getId());


                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Password was changed");


                    auth_user.setPassword(ChangeUserPassword.getText());

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        addMoney.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query1 = "update users set money=? where id=?" ;
                    preparedStatement = connection.prepareStatement(query1);
                    int money = auth_user.getMoney() + Integer.parseInt( ChangeUserMoney.getText());
                    preparedStatement.setInt(1, money);
                    preparedStatement.setInt(2, auth_user.getId());


                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Money has been added");

                    auth_user.setMoney(money);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String query1 = "delete from users where id=?" ;
                    preparedStatement = connection.prepareStatement(query1);
                    preparedStatement.setInt(1, auth_user.getId());


                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Your account was removed");


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

                new LoginFrameUser(auth_user).setVisible(true);
            }
        }
    }
}
