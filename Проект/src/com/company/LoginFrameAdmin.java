 package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

 public class LoginFrameAdmin extends JFrame {

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

    private JLabel admin;

    private JButton editMovie;
    private JButton editCombo;
    private JButton editAdmin;
    private JButton getMessages;
    private JButton back= new JButton("<==");

    public LoginFrameAdmin() {
        super("LoginFrameAdmin GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(back);

        back.addActionListener(new LoginFrameAdmin.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,320,100,20);
        connection = Connect.ConnectDb();

        setSize(400, 400); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("AdminPage");
        setResizable(false);
        setVisible(true);

        editMovie = new JButton("Edit Movie");
        editMovie.setBounds(140,20,100,60);
        add(editMovie);

        editCombo = new JButton("Edit Combo");
        editCombo.setBounds(140,100,100,60);
        add(editCombo);

        editAdmin = new JButton("Edit Admin");
        editAdmin.setBounds(140,180,100,60);
        add(editAdmin);

        getMessages = new JButton("Get Users Messages");
        getMessages.setBounds(90,260,200,30);
        add(getMessages);

        getMessages.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    GetMessages getMessages = new GetMessages();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                dispose();
            }
        });


        editMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            EditMovie editMovie = new EditMovie();
            dispose();
            }
        });

        editCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            EditCombo editCombo = new EditCombo();
            dispose();
            }
        });

        editAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             EditAdmin editAdmin1 = new EditAdmin();
             dispose();
            }
        });

    }





    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            if (cmd.equals("back")) {
                dispose();

                new Admin().setVisible(true);


            }
        }
    }
}
