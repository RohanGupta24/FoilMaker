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

    Controller controller = new Controller();




    JFrame frame = new JFrame();
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();

    //TODO We need a message box to tell the user what is happening 


    //Setter methods
    private String option1Text;
    private String option2Text;
    private String option3Text;
    private String wordClueText;
    private String gameKeyText;

    //Getter methods
    private String usernameText;
    private String passwordText;
    private String checkKeyValidity;
    private String userSuggestion;



    //Global Panels
    //I moved these from the individual methods up here so that all
    //the methods in the class can access them.
    private JPanel panelFirst = new JPanel();
    private JPanel panelSecond = new JPanel();
    private JPanel panelThird = new JPanel();
    private JPanel panelFourth = new JPanel();//This panel had to previous deceleration. Is it suposed to be declared
    // in the controller?
    private JPanel panelFifth = new JPanel();
    private JPanel panelSixth = new JPanel();
    private JPanel panelSeventh = new JPanel();
    private JPanel panelEighth = new JPanel();





    public FoilMakerView() {
        setUpGUI();
    }

    private void setUpGUI() {
        frame.add(mainPanel);
        frame.setSize(300, 500);
        frame.setTitle("FoilMaker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        layout.show(mainPanel, "1");
        frame.setResizable(true);
        frame.setVisible(true);
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
        panelFirst.setBorder(p1);       //
        panelSecond.setBorder(rest);    //
        panelThird.setBorder(rest);     //
        panelFourth.setBorder(rest);    //TODO These are not accessible the way they are now
        panelFifth.setBorder(rest);     //
        panelSixth.setBorder(rest);     //
        panelSeventh.setBorder(rest);   //
        panelEighth.setBorder(rest);    //

    }

    public void actionPerformed(ActionEvent e) {
        JButton a = (JButton) e.getSource();
        layoutDisplay(a);
    }




    /*
    TODO: Where are these buttons being created?
     */


    public void layoutDisplay(AbstractButton a) {
        if(a == buttonLogin || a == buttonRegister) {
            boolean registered;
            do {
                registered = controller.isRegistered();
                layout.show(mainPanel, "1");
            }
            while(registered == false);
            layout.show(mainPanel, "2");
        }



        //TODO Create one of these for a separate LoginButton


        else if(a == buttonStartNewGame) {
            boolean gameStarted;
            do {
                gameStarted = controller.isNewGameStarted();
                layout.show(mainPanel, "2");
            }
            while(gameStarted == false);
            layout.show(mainPanel, "3");
        }
        else if(a == buttonJoinAGame) {
            boolean joinAGame;
            do {
                joinAGame = controller.isJoinAGame();
                layout.show(mainPanel, "2");
            }
            while(joinAGame == false);
            layout.show(mainPanel, "4");
        }
        else if(a == buttonStartGame) {
            boolean startGame;
            do {
                startGame = controller.isGameStarted();
                layout.show(mainPanel, "3");
            }
            while(startGame == false);
            layout.show(mainPanel, "5");
        }
        else if(a == buttonJoinGame) {
            boolean joinGame;
            do {
                joinGame = controller.isJoinGame();
                layout.show(mainPanel, "3");
            }
            while(joinGame == false);
            layout.show(mainPanel, "6");
        }
        else if(a == buttonSubmitSuggestion) {
            boolean submitSuggestion;
            do {
                submitSuggestion = controller.isSubmitSuggestion();
                layout.show(mainPanel, "6");
            }
            while(submitSuggestion == false);
            layout.show(mainPanel, "7");
        }
        else if(a == buttonSubmitOption) {
            boolean submitOption;
            do {
                submitOption = controller.isSubmitOption();
                layout.show(mainPanel, "7");
            }
            while(submitOption == false);
            layout.show(mainPanel, "8");
        }
        else if(a == buttonNextRound) {
            boolean nextRound;
            do {
                nextRound = controller.isNextRound();
                layout.show(mainPanel, "8");
            }
            while(nextRound == false);
            layout.show(mainPanel, "6");
        }
    }

    public JPanel loginPage() {

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
        //RENDER login
        //Record username and password in server when user registers so login is successful the next time
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
        usernameText = usernameBox.getText();
        passwordText = passwordBox.getText();
        Border rest = BorderFactory.createTitledBorder(username);
        subPanel3.setBounds(50,300,200,20);
        subPanel3.add(buttonLogin);
        subPanel3.add(Box.createHorizontalStrut(1));
        subPanel3.add(buttonRegister);
        buttonRegister.addActionListener(new FoilMakerView());

        return this.panelFirst;
    }

    public void optionToJoinOrStartPage() {

        JPanel secondSubPanel = new JPanel(new GridLayout(1,2));
        JButton buttonStartNewGame = new JButton("Start New Game");
        JButton buttonJoinAGame = new JButton("Join a Game");
        panelSecond.setLayout(null);
        panelSecond.add(secondSubPanel);
        secondSubPanel.setSize(500,200);
        secondSubPanel.setBounds(50,200,200,20);
        secondSubPanel.add(buttonStartNewGame);
        secondSubPanel.add(buttonJoinAGame);
        buttonJoinAGame.addActionListener(new FoilMakerView());
    }

    public void displayParticipantsPage() {

        JPanel thirdSubPanel = new JPanel(new GridLayout(4,1));
        JLabel keyDescription = new JLabel("Others should use this key to join your game");
        JTextField key = new JTextField();
        JPanel participants = new JPanel();
        JButton buttonStartGame = new JButton("Start Game");
        //Display participants and key to user (from server)
        panelThird.add(thirdSubPanel);
        thirdSubPanel.setSize(500,200);
        thirdSubPanel.setBounds(50,125,200,20);
        thirdSubPanel.add(keyDescription);
        thirdSubPanel.add(key);
        key.setText(gameKeyText); //IMPORTANT: This text should change based on the server's response
        key.setEditable(false);
        thirdSubPanel.add(participants);
        Border forParticipants = BorderFactory.createTitledBorder("Participants");
        participants.setBorder(forParticipants);
        participants.setBackground(Color.orange);
        thirdSubPanel.add(buttonStartGame);
    }




    /*
    TODO Where is the fourthSubPanel?
     */




    public void joinExistingGamePage() {
        //Server should provide game key to user
        panelFourth.add(fourthSubPanel);
        fourthSubPanel.add(gameKeyInstructions);
        fourthSubPanel.add(gameKey);
        checkKeyValidity = gameKey.getText(); //The server should check to see if the game key is valid
        fourthSubPanel.add(buttonJoinGame);
        buttonJoinGame.addActionListener(new FoilMakerView());
    }

    public void waitingLeaderPage() {

        JPanel fifthSubPanel = new JPanel(new GridLayout(1,0));
        JLabel waitingLeader = new JLabel("Waiting for leader...");
        //Difficult: waiting leader (response from server)
        //Server is supposed to recognize when the leader has entered the game
        panelFifth.add(fifthSubPanel);
        fifthSubPanel.add(waitingLeader);
        waitingLeader.setVerticalAlignment(waitingLeader.CENTER);
        //Server does work here in order to switch to new Panel
        //Once ready, switch to Panel 6
    }

    public void wordIdentificationPage() {

        JPanel sixthSubPanel = new JPanel(new GridLayout(4,1));
        JPanel wordIdentification = new JPanel();
        JLabel wordClue = new JLabel();
        JPanel suggestion = new JPanel();
        JTextField suggestionBox = new JTextField();
        JButton buttonSubmitSuggestion = new JButton("Submit Suggestion");
        JLabel wordIdentifcationInstructions = new JLabel("What is the word for");
        //Server is supposed to have a word identifier phrase in the "wordIdentification" panel
        // and is supposed to record the user's suggestion that will then be used in the next panel
        panelSixth.add(sixthSubPanel);
        sixthSubPanel.setSize(300,500);
        sixthSubPanel.add(wordIdentifcationInstructions);
        sixthSubPanel.add(wordIdentification);
        wordIdentification.add(wordClue);
        //This text should change based on the server's response
        wordClue.setText(wordClueText); //This text changes based on the server's response
        wordIdentification.setBackground(Color.orange);
        sixthSubPanel.add(suggestion);
        Border suggestionBorder = BorderFactory.createTitledBorder("Your Suggestion");
        suggestion.setBorder(suggestionBorder);
        suggestion.add(suggestionBox);
        //IMPORTANT: Record userSuggestion in server
        userSuggestion = suggestionBox.getText();
        sixthSubPanel.add(buttonSubmitSuggestion);
        buttonSubmitSuggestion.addActionListener(new FoilMakerView());
    }

    public void pickOptionPage() {

        JPanel seventhSubPanel = new JPanel(new GridLayout(5,1));
        JLabel pickOptionDescription = new JLabel("Pick your option below");
        JRadioButton option1 = new JRadioButton();
        JRadioButton option2 = new JRadioButton();
        JRadioButton option3 = new JRadioButton();
        ButtonGroup options = new ButtonGroup();
        JButton buttonSubmitOption = new JButton("Submit Option");
        //Display the suggestions recorded in the previous panel in the text for
        // the Radio Buttons
        panelSeventh.add(seventhSubPanel);
        seventhSubPanel.add(pickOptionDescription);
        options.add(option1);
        options.add(option2);
        options.add(option3);
        seventhSubPanel.add(option1);
        option1.setText(option1Text); //Replace with server's record
        seventhSubPanel.add(option2);
        option2.setText(option2Text); //Replace with server's record
        seventhSubPanel.add(option3);
        option3.setText(option3Text); //Replace with server's record
        seventhSubPanel.add(buttonSubmitOption);
        buttonSubmitOption.addActionListener(new FoilMakerView());
    }

    public void resultsPage() {

        JPanel eightSubPanel = new JPanel(new GridLayout(3,1));
        JPanel roundResult = new JPanel();
        JPanel overallResults = new JPanel();
        JButton buttonNextRound = new JButton("Next Round");
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
    }

    public void setOption1Text(String option1Text) {
        this.option1Text = option1Text;
    }

    public void setOption2Text(String option2Text) {
        this.option2Text = option2Text;
    }

    public void setOption3Text(String option3Text) {
        this.option3Text = option3Text;
    }

    public void setWordClueText(String wordClueText) {
        this.wordClueText = wordClueText;
    }

    public void setGameKeyText(String gameKeyText) {
        this.gameKeyText = gameKeyText;
    }

    public String getUsernameText() {
        return this.usernameText;
    }

    public String getPasswordText() {
        return this.passwordText;
    }

    public String getCheckKeyValidity() {
        return this.checkKeyValidity;
    }

    public String getUserSuggestion() {
        return this.userSuggestion;
    }
}
