package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



    public class GetMessages extends JFrame {

        private JTextArea msg_area ;
        private JTextField msg_text ;

        private JButton msg_send ;
        private JButton back = new JButton("<==");

        static ServerSocket ss ;
        static Socket s ;
        static DataInputStream din;
        static DataOutputStream dout;


        Connection connection;
        ResultSet resultSet;
        PreparedStatement preparedStatement;

        public GetMessages() throws IOException, ClassNotFoundException {
            super("GetMessage");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(back);
            back.addActionListener(new com.company.GetMessages.ButtonListener());
            back.setActionCommand("back");
            setVisible(true);
            back.setBounds(20, 420, 100, 20);
            connection = Connect.ConnectDb();


            setSize(500, 500); // размер полотна
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // это когда при нажатии на крестик,
            // компилятор завершал свою работу
            setLayout(null); // для того чтобы располагать объекты
            setTitle("Help");
            setResizable(false);
            setVisible(true);


            msg_text = new  JTextField();
            msg_text.setBounds(40,340,200,40);
            add(msg_text);

            msg_send = new JButton("Send");
            msg_send.setBounds(280,340,100,40);
            add(msg_send);

            msg_area = new JTextArea();
            msg_area.setBounds(40,40,380,280);
            add(msg_area);



//            msg_send.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    try {
//                        String msgout = "";
//                        msgout = msg_text.getText().trim();
//                        dout.writeUTF(msgout);
//                    }catch (Exception ex){
//                        ex.printStackTrace();
//                    }
//
//                }
//            });
     }
//
//            public void main(String args[]){
//                String msgin = "";
//                try{
//                    ss= new ServerSocket(1201);
//                    s= ss.accept();
//                    din = new DataInputStream(s.getInputStream());
//                    dout = new DataOutputStream(s.getOutputStream());
//                    while(!msgin.equals("exit")){
//                        msgin=din.readUTF();
//                        msg_area.setText(msg_area.getText().trim()+"\n"+msgin);
//                    }
//                }catch (Exception e ){
//                    e.printStackTrace();
//                }
//            }

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




