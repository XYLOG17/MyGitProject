/*
 * Project: TicTacToe
 * Class: TicTacToe.java
 * Created by: Jan Eric Keller
 * Date: 23.09.2024
 * Description: This class creates a window for playing Tic Tac Toe. I'm developing this to improve my coding 
 * skills and refresh my knowledge of Java, as I have primarily used Python in the past.
 * Version: 1.0
 */

// Importing core AWT (Abstract Window Toolkit) classes for building graphical user interfaces (GUI)
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;      


public class TicTacToe implements ActionListener { // TicTacToe class handles user interactions via ActionListener.

    Random random = new Random(); // decide who is going to start
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9]; // 9 Buttons for playing
    boolean player1_turn; // True => Player 1's turn, False => Player 2's turn

    TicTacToe(){ // constructer

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 50));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 200);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150)); // just to check if it works

        for(int i=0; i<9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    public void actionPerformed(ActionEvent e) { // Handles an action event, such as a button click (no return value).

        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) { // if empty
                        buttons[i].setForeground(new Color(255, 0, 0)); // Corrected Color constructor
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O Turn");
                        check();
                    }
                } else {
                    if (buttons[i].getText().equals("")) { // if empty
                        buttons[i].setForeground(new Color(0, 0, 255)); // Corrected Color constructor
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X Turn");
                        check();
                    }
                }
            }
        }
    }
    

    public void firstTurn() {

        try {
            Thread.sleep(2000);   
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if(random.nextInt(2)==0){ // 0 => Player 1s turn, 1 => Player 2s turn
            player1_turn = true;
            textfield.setText("X turn");
        }
        else {
            player1_turn = false;
            textfield.setText("O Turn");
        }

    }

    public void check() {
        /*
         * 0    1   2
         * 3    4   5
         * 6    7   8
         */

        // check X win conditions
        if( // top row
            (buttons[0].getText().equals("X")) &&
            (buttons[1].getText().equals("X")) &&
            (buttons[2].getText().equals("X"))
            ) {
                xWins(0, 1, 2);
            }
        if( // mid row
            (buttons[3].getText().equals("X")) &&
            (buttons[4].getText().equals("X")) &&
            (buttons[5].getText().equals("X"))
            ) {
                xWins(3, 4, 5);
            }
        if( // bot row
            (buttons[6].getText().equals("X")) &&
            (buttons[7].getText().equals("X")) &&
            (buttons[8].getText().equals("X"))
            ) {
                xWins(6, 7, 8);
            }
        if( // left column
            (buttons[0].getText().equals("X")) &&
            (buttons[3].getText().equals("X")) &&
            (buttons[6].getText().equals("X"))
            ) {
                xWins(0, 3, 6);
            }
        if( // mid column
            (buttons[1].getText().equals("X")) &&
            (buttons[3].getText().equals("X")) &&
            (buttons[7].getText().equals("X"))
            ) {
                xWins(1, 4, 7);
            }
        if( // right column
            (buttons[2].getText().equals("X")) &&
            (buttons[5].getText().equals("X")) &&
            (buttons[8].getText().equals("X"))
            ) {
                xWins(2, 5, 8);
            }
        if( // diagonal top left -> down right
            (buttons[0].getText().equals("X")) &&
            (buttons[4].getText().equals("X")) &&
            (buttons[8].getText().equals("X"))
            ) {
                xWins(0, 4, 8);
            }
        if( // diagnoal top right -> down left
            (buttons[2].getText().equals("X")) &&
            (buttons[4].getText().equals("X")) &&
            (buttons[6].getText().equals("X"))
            ) {
                xWins(2, 4, 6);
            }

        // check O win Condition
        if ( // top row
            (buttons[0].getText().equals("O")) &&
            (buttons[1].getText().equals("O")) &&
            (buttons[2].getText().equals("O"))
            ) {
                oWins(0, 1, 2);
            }
        if ( // mid row
            (buttons[3].getText().equals("O")) &&
            (buttons[4].getText().equals("O")) &&
            (buttons[5].getText().equals("O"))
            ) {
                oWins(3, 4, 5);
            }
        if ( // bot row
            (buttons[6].getText().equals("O")) &&
            (buttons[7].getText().equals("O")) &&
            (buttons[8].getText().equals("O"))
            ) {
                oWins(6, 7, 8);
            }
        if ( // left column
            (buttons[0].getText().equals("O")) &&
            (buttons[3].getText().equals("O")) &&
            (buttons[6].getText().equals("O"))
            ) {
                oWins(0, 3, 6);
            }
        if ( // mid column
            (buttons[1].getText().equals("O")) &&
            (buttons[4].getText().equals("O")) &&
            (buttons[7].getText().equals("O"))
            ) {
                oWins(1, 4, 7);
            }
        if ( // right column
            (buttons[2].getText().equals("O")) &&
            (buttons[5].getText().equals("O")) &&
            (buttons[8].getText().equals("O"))
            ) {
                oWins(2, 5, 8);
            }
        if ( // diagonal top left -> down right
            (buttons[0].getText().equals("O")) &&
            (buttons[4].getText().equals("O")) &&
            (buttons[8].getText().equals("O"))
            ) {
                oWins(0, 4, 8);
            }
        if ( // diagonal top right -> down left
            (buttons[2].getText().equals("O")) &&
            (buttons[4].getText().equals("O")) &&
            (buttons[6].getText().equals("O"))
            ) {
                oWins(2, 4, 6);
            }
    }
    
    /**
    * Called when 'X' wins the game.
    * 
    * @param a the index of the first box in the winning combination
    * @param b the index of the second box in the winning combination
    * @param c the index of the third box in the winning combination
    */
    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0; i<9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("X Wins");
    }

    /**
    * Called when 'O' wins the game.
    * 
    * @param a the index of the first box in the winning combination
    * @param b the index of the second box in the winning combination
    * @param c the index of the third box in the winning combination
    */
    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i=0; i<9; i++) {
            buttons[i].setEnabled(false);
        }
        textfield.setText("O Wins");
    }

}    