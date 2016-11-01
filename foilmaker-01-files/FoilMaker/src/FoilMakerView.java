import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * Created by rohangupta on 10/31/16.
 */
public class FoilMakerView extends JFrame implements ActionListener {
    JFrame frame = new JFrame();

    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();

    JPanel panelFirst = new JPanel();
    JPanel subPanel1 = new JPanel(new GridLayout(1,2));
    JPanel subPanel2 = new JPanel(new GridLayout(1,2));
    JPanel subPanel3 = new JPanel(new GridLayout(1,2));
    Border p1 = BorderFactory.createTitledBorder("FoilMaker!");
    JButton buttonLogin = new JButton("Login");
    JButton buttonRegister = new JButton("Register");
    JLabel username = new JLabel("Username");
    JLabel password = new JLabel("Password");
    JPasswordField passwordBox = new JPasswordField();
    JTextField usernameBox = new JTextField();

    JPanel panelSecond = new JPanel();
    JPanel secondSubPanel = new JPanel(new GridLayout(1,2));
    JButton buttonStartNewGame = new JButton("Start New Game");
    JButton buttonJoinAGame = new JButton("Join a Game");

    JPanel panelThird = new JPanel();
    JPanel thirdSubPanel = new JPanel(new GridLayout(4,1));
    JLabel keyDescription = new JLabel("Others should use this key to join your game");
    JTextField key = new JTextField();
    JPanel participants = new JPanel();
    JButton buttonStartGame = new JButton("Start Game");

    JPanel panelFourth = new JPanel();
    JPanel fourthSubPanel = new JPanel(new GridLayout(3,1));
    JLabel gameKeyInstructions = new JLabel("Enter the game key to join a game");
    JTextField gameKey = new JTextField();
    JButton buttonJoinGame = new JButton("Join Game");

    JPanel panelFifth = new JPanel();
    JPanel fifthSubPanel = new JPanel(new GridLayout(1,0));
    JLabel waitingLeader = new JLabel("Waiting for leader...");

    JPanel panelSixth = new JPanel();
    JPanel sixthSubPanel = new JPanel(new GridLayout(4,1));
    JPanel wordIdentification = new JPanel();
    JLabel wordClue = new JLabel();
    JPanel suggestion = new JPanel();
    JTextField suggestionBox = new JTextField();
    JButton buttonSubmitSuggestion = new JButton("Submit Suggestion");
    JLabel wordIdentifcationInstructions = new JLabel("What is the word for");

    JPanel panelSeventh = new JPanel();
    JPanel seventhSubPanel = new JPanel(new GridLayout(5,1));
    JLabel pickOptionDescription = new JLabel("Pick your option below");
    JRadioButton option1 = new JRadioButton();
    JRadioButton option2 = new JRadioButton();
    JRadioButton option3 = new JRadioButton();
    ButtonGroup options = new ButtonGroup();
    JButton buttonSubmitOption = new JButton("Submit Option");

    JPanel panelEighth = new JPanel();
    JPanel eightSubPanel = new JPanel(new GridLayout(3,1));
    JPanel roundResult = new JPanel();
    JPanel overallResults = new JPanel();
    JButton buttonNextRound = new JButton("Next Round");

    public FoilMakerView() {
        setUpGUI();
        frame.add(mainPanel);
        frame.setSize(300, 500);
        frame.setTitle("FoilMaker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        layout.show(mainPanel, "1");
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FoilMakerView();
    }

    private void setUpGUI() {
        //RENDER login
        //Record username and password in server when user registers to login is successful the next time
        panelFirst.setLayout(null);
        panelFirst.add(subPanel1);
        subPanel1.setSize(500,200);
        subPanel1.add(username);
        subPanel1.add(Box.createHorizontalStrut(5));
        subPanel1.add(usernameBox);
        subPanel1.setBounds(50,125,200,20);
        panelFirst.add(subPanel2);
        subPanel2.setSize(500,200);
        subPanel2.add(password);
        subPanel2.add(Box.createHorizontalStrut(5));
        subPanel2.add(passwordBox);
        subPanel2.setBounds(50,150,200,20);
        panelFirst.add(subPanel3);
        //IMPORTANT: This retrieves username and password to record in server
        String username = usernameBox.getText();
        String password = passwordBox.getText();
        Border rest = BorderFactory.createTitledBorder(username);
        subPanel3.setBounds(50,300,200,20);
        subPanel3.add(buttonLogin);
        subPanel3.add(Box.createHorizontalStrut(1));
        subPanel3.add(buttonRegister);
        buttonRegister.addActionListener(new FoilMakerView());

        panelSecond.setLayout(null);
        panelSecond.add(secondSubPanel);
        secondSubPanel.setSize(500,200);
        secondSubPanel.setBounds(50,200,200,20);
        secondSubPanel.add(buttonStartNewGame);
        secondSubPanel.add(buttonJoinAGame);
        buttonJoinAGame.addActionListener(new FoilMakerView());

        //Display participants and key to user (from server)
        panelThird.add(thirdSubPanel);
        thirdSubPanel.setSize(500,200);
        thirdSubPanel.setBounds(50,125,200,20);
        thirdSubPanel.add(keyDescription);
        thirdSubPanel.add(key);
        key.setText("ypw"); //IMPORTANT: This text should change based on the server's response
        key.setEditable(false);
        thirdSubPanel.add(participants);
        Border forParticipants = BorderFactory.createTitledBorder("Participants");
        participants.setBorder(forParticipants);
        participants.setBackground(Color.orange);
        thirdSubPanel.add(buttonStartGame);
        //Server should provide game key to user
        panelFourth.add(fourthSubPanel);
        fourthSubPanel.add(gameKeyInstructions);
        fourthSubPanel.add(gameKey);
        String checkKeyValidity = gameKey.getText(); //The server should check to see if the game key is valid
        fourthSubPanel.add(buttonJoinGame);
        buttonJoinGame.addActionListener(new FoilMakerView());

        //Difficult: waiting leader (response from server)
        //Server is supposed to recognize when the leader has entered the game
        panelFifth.add(fifthSubPanel);
        fifthSubPanel.add(waitingLeader);
        waitingLeader.setVerticalAlignment(waitingLeader.CENTER);
        //Server does work here in order to switch to new Panel
        //Once ready, switch to Panel 6

        //Server is supposed to have a word identifier phrase in the "wordIdentification" panel
        // and is supposed to record the user's suggestion that will then be used in the next panel
        panelSixth.add(sixthSubPanel);
        sixthSubPanel.setSize(300,500);
        sixthSubPanel.add(wordIdentifcationInstructions);
        sixthSubPanel.add(wordIdentification);
        wordIdentification.add(wordClue);
        //This text should change based on the server's response
        wordClue.setText("A group of zebras"); //This text changes based on the server's response
        wordIdentification.setBackground(Color.orange);
        sixthSubPanel.add(suggestion);
        Border suggestionBorder = BorderFactory.createTitledBorder("Your Suggestion");
        suggestion.setBorder(suggestionBorder);
        suggestion.add(suggestionBox);
        //IMPORTANT: Record userSuggestion in server
        String userSuggestion = suggestionBox.getText();
        sixthSubPanel.add(buttonSubmitSuggestion);
        buttonSubmitSuggestion.addActionListener(new FoilMakerView());

        //Display the suggestions recorded in the previous panel in the text for
        // the Radio Buttons
        panelSeventh.add(seventhSubPanel);
        seventhSubPanel.add(pickOptionDescription);
        options.add(option1);
        options.add(option2);
        options.add(option3);
        seventhSubPanel.add(option1);
        option1.setText("A zippy-do"); //Replace with server's record
        seventhSubPanel.add(option2);
        option2.setText("A zig zag"); //Replace with server's record
        seventhSubPanel.add(option3);
        option3.setText("A dazzle"); //Replace with server's record
        seventhSubPanel.add(buttonSubmitOption);
        buttonSubmitOption.addActionListener(new FoilMakerView());

        //Server displays round results and overall results in each of the panels
        // called "roundResult" and "overallResults"
        panelEighth.add(eightSubPanel);
        eightSubPanel.add(roundResult);
        Border r = BorderFactory.createTitledBorder("Round Result");
        roundResult.setBorder(r);
        roundResult.setBackground(Color.green);
        eightSubPanel.add(overallResults);
        Border q = BorderFactory.createTitledBorder("Overall Results");
        overallResults.setBorder(q);
        overallResults.setBackground(Color.orange);
        eightSubPanel.add(buttonNextRound);
        buttonNextRound.addActionListener(new FoilMakerView());

        mainPanel.setLayout(layout);
        setTitle("FoilMaker");
        mainPanel.add(panelFirst, "1");
        mainPanel.add(panelSecond, "2");
        mainPanel.add(panelThird, "3");
        mainPanel.add(panelFourth, "4");
        mainPanel.add(panelFifth, "5");
        mainPanel.add(panelSixth, "6");
        mainPanel.add(panelSeventh, "7");
        mainPanel.add(panelEighth, "8");
        panelFirst.setBorder(p1);
        panelSecond.setBorder(rest);
        panelThird.setBorder(rest);
        panelFourth.setBorder(rest);
        panelFifth.setBorder(rest);
        panelSixth.setBorder(rest);
        panelSeventh.setBorder(rest);
        panelEighth.setBorder(rest);

    }

    public void actionPerformed(ActionEvent e) {
        JButton a = (JButton) e.getSource();
        layoutDisplay(a);
    }

    public void layoutDisplay(AbstractButton a) {
        if(a == buttonLogin || a == buttonRegister) {
            layout.show(mainPanel, "2");
        }
        else if(a == buttonStartNewGame) {
            layout.show(mainPanel, "3");
        }
        else if(a == buttonJoinAGame) {
            layout.show(mainPanel, "4");
        }
        else if(a == buttonStartGame) {
            layout.show(mainPanel, "6");
        }
        else if(a == buttonJoinGame) {
            layout.show(mainPanel, "5");
        }
        else if(a == buttonSubmitSuggestion) {
            layout.show(mainPanel, "7");
        }
        else if(a == buttonSubmitOption) {
            layout.show(mainPanel, "8");
        }
        else if(a == buttonNextRound) {
            layout.show(mainPanel, "6");
        }
    }
}
