package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditMovie extends JFrame {
    private JLabel MovieName;
    private JLabel Genre;
    private JLabel Cost;
    private JLabel DelMovie;

    private JButton AddMovie;
    private JButton DeleteMovie;
    private JButton back= new JButton("<==");

    private JTextField EnterMovieName;
    private JTextField EnterCost;
    private JTextField EnterDelMovie;

    private JComboBox EnterGenre;

    private String [] genres={"Action movie", "Western", "Gangster Movie", "Detective", "Drama", "Historical film" ,"Comedy","Anime","Melodrama", "Musical film","Biography","Sport","Noir Political film", "Adventure Film", "Fairy tale","Tragedy ","Tragicomedy","Thriller","Fantastic movie","Horror", "Movie","Disaster Movie"};


    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    public EditMovie (){
        super("Edit");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connection = Connect.ConnectDb();
        add(back);
        back.addActionListener(new EditMovie.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,420,100,20);

        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("Edit Movie");
        setResizable(false);
        setVisible(true);

        MovieName=new JLabel("Enter Movie Name:");
        MovieName.setBounds(40,120,200,40);
        add(MovieName);

        Genre=new JLabel("Enter Genre:");
        Genre.setBounds(40,150,200,40);
        add(Genre);

        Cost=new JLabel("Enter Cost:");
        Cost.setBounds(40,180,200,40);
        add(Cost);

        DelMovie= new JLabel("Insert Movie Name:");
        DelMovie.setBounds(40,270,200,40);
        add(DelMovie);

        EnterMovieName = new JTextField();
        EnterMovieName.setBounds(160,130,200,20);
        add(EnterMovieName);

        EnterGenre = new JComboBox(genres);
        EnterGenre.setBounds(160,160,200,20);
        add(EnterGenre);

        EnterCost = new JTextField();
        EnterCost.setBounds(160,190,200,20);
        add(EnterCost);

        EnterDelMovie = new JTextField();
        EnterDelMovie.setBounds(160,280,200,20);
        add(EnterDelMovie);

        AddMovie= new JButton("Add Movie");
        AddMovie.setBounds(210,230,100,40);
        add(AddMovie);

        DeleteMovie = new JButton("Delete Movie");
        DeleteMovie.setBounds(210,320,150,40);
        add(DeleteMovie);



        AddMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query = "insert into movie(MovieName, Genre,Cost) values (?,?,?)";

                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setString(1,EnterMovieName.getText());
                    preparedStatement.setString(2,(String)EnterGenre.getSelectedItem());
                    preparedStatement.setString(3,EnterCost.getText());

                    preparedStatement.execute();

                    JOptionPane.showMessageDialog(null, "New movie was successfully added");
//                    resultSet.close();
//
//                    preparedStatement.close();

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        DeleteMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String query = "delete from movie where MovieName =? " ;
                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setString(1, EnterDelMovie.getText());


                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Movie was successfully removed");

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
