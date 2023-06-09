package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class LoginFrameUser extends JFrame {
    //private JLabel label1; // метка
    //private JLabel labelRes; // метка
    // private JLabel labelAges; // метка
    // private JTextField jTextField; // для ввода текста
    // private JTextField res; // для ввода текста
    // private JButton sendButton; // кнопка для результата
    //private JComboBox ages; // выборка
    // private JTextArea textArea; // для отображения большого текста
    public String user_id;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    private JLabel loginUser;
    private JLabel User;
    private JLabel selectFilms;
    private JLabel selectCombo;
    private JLabel Balance;

    private JComboBox films;
    private JComboBox combo;

    private JButton buy;
    private JButton MyProfile;
    private JButton Help;
    private JButton back= new JButton("<==");

    public User auth_user;
    public void setUser_id(String id){
        user_id = id;
    }





    public LoginFrameUser(User auth_user){
        super("LoginFrameUser GUI");
        this.auth_user = auth_user;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(back);
        connection = Connect.ConnectDb();
        back.addActionListener(new LoginFrameUser.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,420,100,20);

        ArrayList <String> movie = new ArrayList<>();
        ArrayList <String> combobox = new ArrayList<>();
        ArrayList <String> users = new ArrayList<>();
        String userLogin = MainFrame.getLogin();

        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("UserPage");
        setResizable(false);
        setVisible(true);

        loginUser = new JLabel(auth_user.getLogin());
        loginUser.setBounds(220,20,200,20);
        add(loginUser);

//        User = new JLabel("Guest");
//        User.setBounds(220,40,200,20);
//        add(User);

        selectFilms = new JLabel("Select films: ");
        selectFilms.setBounds(30, 120,200,20);
        add(selectFilms);

        selectCombo = new JLabel("Select combo: " );
        selectCombo.setBounds(30,150,200,20);
        add(selectCombo);



        Balance = new JLabel("Balance: "+auth_user.getMoney());
        Balance.setBounds(20,20,200,20);
        add(Balance);


        try {
            String query = "select * from movie";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                String movie1 = resultSet.getString("MovieName") + "," + resultSet.getString("Genre") + "," + resultSet.getString("Cost");
                movie.add(movie1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        films = new JComboBox(movie.toArray());

        films.setBounds(130,120,250,20);
        add(films);

        try {
            String query = "select * from combobox";
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String combobox1 = resultSet.getString("combo_name") + "," + resultSet.getString("content") + "," + resultSet.getString("combo_cost");
                combobox.add(combobox1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        combo = new JComboBox(combobox.toArray());
        combo.setBounds(130,150,250,20);
        add(combo);


        buy = new JButton("Buy");
        buy.setBounds(200,180,100,30);
        add(buy);

        MyProfile = new JButton("My Profile");
        MyProfile.setBounds(370,20,100,20);
        add(MyProfile);

        Help = new JButton("Help");
        Help.setBounds(370,420,100,20);
        add(Help);



        MyProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    EditUser editUser = new EditUser(auth_user);

                    dispose();
            }
        });

        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query1 = "update users set money=? where id=?" ;
                    preparedStatement = connection.prepareStatement(query1);

                    String filmValue= films.getSelectedItem().toString();//Select text from combobox
                    int indexFilm = filmValue.indexOf(',',filmValue.indexOf(",") + 1);//Getting index second ','
                    filmValue=filmValue.substring(indexFilm+1);//Cutting only price

                    String comboValue= combo.getSelectedItem().toString();//Select text from combobox
                    int indexCombo = comboValue.indexOf(',',comboValue.indexOf(",") + 1);//Getting index second ','
                    comboValue=comboValue.substring(indexCombo+1);//Cutting only price

                    int bought= Integer.parseInt(filmValue) + Integer.parseInt(comboValue);

                    int money = auth_user.getMoney() - bought;
                    preparedStatement.setInt(1, money);
                    preparedStatement.setInt(2, auth_user.getId());
                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Items were bought");
                    auth_user.setMoney(money);

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        Help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    SendMessages sendMessages = new SendMessages(auth_user);



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

                new MainFrame().setVisible(true);
            }
        }
    }

}
