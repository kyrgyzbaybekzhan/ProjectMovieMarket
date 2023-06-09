package com.company;

import javax.swing.*;

public class Run  extends JFrame {

        static JProgressBar b;
        static JFrame f;
        public Run() {
            f = new JFrame("MovieMarket");

            JPanel p = new JPanel();                        // create a panel

            b = new JProgressBar();                        // create a progressbar

            b.setValue(0);                                // set initial value
            b.setStringPainted(true);

            p.add(b);                                    // add progressbar

            f.add(p);                                    // add panel


            f.setSize(300, 150);            // set the size of the frame
            f.setVisible(true);

            fill();
            f.setVisible(false);
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        }

        public static void fill()
        {
            int i = 0;
            try {
                while (i <= 100) {
                    b.setValue(i);                    // fill the menu bar

                    Thread.sleep(10);            // delay the thread
                    i += 1;
                }
            }
            catch (Exception e) {
            }
        }
    }

