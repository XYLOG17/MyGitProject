/*
 * Project: TicTacToe
 * Class: TicTacToe.java
 * Created by: Jan Eric Keller
 * Date: 23.09.2024
 * Description: This class creates a window for playing Tic Tac Toe. I'm developing this to improve my coding 
 * skills and refresh my knowledge of Java, as I have primarily used Python in the past.
 * Version: 1.0, source: https://www.youtube.com/watch?v=rA7tfvpkw0I, simple version of tic-tac-toe
 */

 import java.awt.*;         // Abstract Window Toolkit packag, includes components for building graphical user interfaces (GUIs) such as buttons, text fields, etc.
import java.awt.event.*;    // to handling events (like mouse clicks, keyboard input, etc.) and it's needed for adding interactivity to GUI components
import java.util.*;         // includes data structures like ArrayList, HashMap, Date, etc.
import javax.swing.*;       // Swing is used to create more modern and flexible GUIs compared to AWT and It provides components like JFrame, JButton, JPanel, and more.

public class TicTacToe implements ActionListener { // TicTacToe class handles user interactions via ActionListener.

    Random random = new Random(); // decide who is going to start
    JFrame frame = new JFrame("Tic-Tac-Toe"); // create the frame for the game with a title
    JPanel title_panel = new JPanel(); // a panel at the top of the window to show a text
    JPanel button_panel = new JPanel(); // the game board itself
    JLabel textfield = new JLabel(); // the textfield which will be shown in the title_panel
    JButton[] buttons = new JButton[9]; // 9 Buttons for playing
    boolean player1_turn; // True => Player 1's turn, False => Player 2's turn
    int turns = 0; // check for a tie

    TicTacToe(){ // constructer

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closes the window when it's exited
        frame.setSize(800, 800); // set the size of the frame
        frame.getContentPane().setBackground(new Color(50, 50, 50)); // set backgroundcolor of the frame
        frame.setLayout(new BorderLayout()); // https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
        frame.setVisible(true); // make the frame visible
        frame.setLocationRelativeTo(null); // put the frame in the center of the screen

        textfield.setBackground(new Color(25, 25, 25)); // set backgroundcolor of the textfield
        textfield.setForeground(new Color(25, 255, 0)); // set color of the text
        textfield.setFont(new Font("Ink Free", Font.BOLD, 50)); // set style of font, size and make it bold
        textfield.setHorizontalAlignment(JLabel.CENTER); // put the text into the center of the label
        textfield.setText("Tic-Tac-Toe"); // original text for this label
        textfield.setOpaque(true); // use the backgroundcolor of this label for the titel panel

        title_panel.setLayout(new BorderLayout());// https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
        title_panel.setBounds(0, 0, 800, 200); // x and y are the coordination of the upper left corner of the panel

        button_panel.setLayout(new GridLayout(3, 3)); // https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
        button_panel.setBackground(new Color(150, 150, 150)); // used just to check if it works, sets backgroundcolor of the panel

        for(int i=0; i<9; i++) { // for loop, iterates 9 times from 0 to 8
            buttons[i] = new JButton(); // create a button
            button_panel.add(buttons[i]); // add button to panel
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120)); //set style of font, size and make it bold
            buttons[i].setFocusable(false); // access with keyboard is denied
            buttons[i].addActionListener(this); // this is for the current object and activates to interact with a klick
        }

        // put everything togethere at the correct place
        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn(); // activate function to desice whose move it is
    }

    public void actionPerformed(ActionEvent e) { // Handles an action event, such as a button click (no return value).
        /**
         * Function is activated by clicking on a button and will change the button as the players turn is
         */
        for (int i = 0; i < 9; i++) { // loops through all buttons
            if (e.getSource() == buttons[i]) { // check each button for the activation click
                turns++;
                if (player1_turn) { // change is depending on whose turn it is
                    if (buttons[i].getText().equals("")) { // if empty
                        buttons[i].setForeground(new Color(255, 0, 0)); // Corrected Color constructor
                        buttons[i].setText("X"); // text in this button
                        player1_turn = false; // player 2 will continue
                        textfield.setText("O Turn"); // chance text in title panel
                        check(); // check if there's already a winner
                    }
                } else { // change is depending on whose turn it is
                    if (buttons[i].getText().equals("")) { // if empty
                        buttons[i].setForeground(new Color(0, 0, 255)); // Corrected Color constructor
                        buttons[i].setText("O"); // text in this button
                        player1_turn = true; // player 1 will continue
                        textfield.setText("X Turn"); // chance text in title panel
                        check(); // check if there's already a winner
                    }
                }
            }
        }
    }
    
    public void firstTurn() { // function to decide randomly who will beginn

        try {
            Thread.sleep(2000); // wait for 2 sec so that the title Tic-Tac-Toe is readable
        } catch (InterruptedException e) { // catches the exception
            e.printStackTrace(); // prints the error message for this interruption in the console
        }


        if(random.nextInt(2)==0){ // 0 => Player ones turn, 1 => Player twos turn
            player1_turn = true; // player ones turn
            textfield.setText("X turn"); // change title panel
        }
        else {
            player1_turn = false; // player twos turn
            textfield.setText("O Turn"); // change title panel
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
        if (turns == 9) { // after 9 klicks and no winner is found, we have a tie
            for(int i=0; i < 9; i++){
                buttons[i].setBackground(Color.ORANGE); // change the buttons
                textfield.setText("Tie"); // no winner found and change title

            }
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