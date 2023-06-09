package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditCombo extends JFrame {
    private JLabel ComboName;
    private JLabel Cost;
    private JLabel Content;
    private JLabel DelCombo;

    private JButton AddCombo;
    private JButton DeleteCombo;
    private JButton back= new JButton("<==");

    private JTextField EnterComboName;
    private JTextField EnterCost;
    private JTextField EnterContent;
    private JTextField EnterDelCombo;

    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedStatement;


    public  EditCombo(){
        super("Edit Combo GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connection = Connect.ConnectDb();
        add(back);
        back.addActionListener(new EditCombo.ButtonListener());
        back.setActionCommand("back");
        setVisible(true);
        back.setBounds(20,420,100,20);

        setSize(500, 500); // размер полотна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
        // компилятор завершал свою работу
        setLayout(null); // для того чтобы располагать объекты
        setTitle("Edit Combo");
        setResizable(false);
        setVisible(true);

        ComboName=new JLabel("Enter Combo Name:");
        ComboName.setBounds(40,120,200,40);
        add(ComboName);

        Content=new JLabel("Enter Content:");
        Content.setBounds(40,160,200,40);
        add(Content);

        Cost=new JLabel("Enter Cost:");
        Cost.setBounds(40,200,200,40);
        add(Cost);

        DelCombo=new JLabel("Insert Combo Name:");
        DelCombo.setBounds(40,290,200,40);
        add(DelCombo);

        EnterComboName = new JTextField();
        EnterComboName.setBounds(160,130,200,20);
        add(EnterComboName);

        EnterContent = new JTextField();
        EnterContent.setBounds(160,170,200,20);
        add(EnterContent);

        EnterCost = new JTextField();
        EnterCost.setBounds(160,210,200,20);
        add(EnterCost);

        EnterDelCombo = new JTextField();
        EnterDelCombo.setBounds(160,300,200,20);
        add(EnterDelCombo);

        AddCombo= new JButton("Add Combo");
        AddCombo.setBounds(210,250,100,40);
        add(AddCombo);

       DeleteCombo = new JButton("Delete Combo");
       DeleteCombo.setBounds(210,340,200,40);
       add(DeleteCombo);

        AddCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String query = "insert into combobox(combo_name,content,combo_cost) values (?,?,?)";

                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setString(1,EnterComboName.getText());
                    preparedStatement.setString(2,EnterContent.getText());
                    preparedStatement.setString(3,EnterCost.getText());

                    preparedStatement.execute();

                    JOptionPane.showMessageDialog(null, "New combobox was successfully added");
//

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        DeleteCombo.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String query = "delete from combobox where combo_name =? ";
                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setString(1, EnterDelCombo.getText());


                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Combobox was successfully removed");

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }));

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
