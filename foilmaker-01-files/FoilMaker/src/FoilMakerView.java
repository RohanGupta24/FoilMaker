import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class FoilMakerView extends JFrame implements ActionListener{


    Controller controller;

    JFrame frame = new JFrame();
    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();

    //TODO We need a message box to tell the user what is happening. Update: Yeah I know. I haven't figured that out yet.


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




    private JPanel panelFirst = new JPanel();
    private JPanel panelSecond = new JPanel();
    private JPanel panelThird = new JPanel();
    private JPanel panelFourth = new JPanel();
    private JPanel panelFifth = new JPanel();
    private JPanel panelSixth = new JPanel();
    private JPanel panelSeventh = new JPanel();
    private JPanel panelEighth = new JPanel();

    private Border p1 = BorderFactory.createTitledBorder("FoilMaker!");
    private Border rest =  BorderFactory.createTitledBorder(usernameText);


    private JButton buttonLogin = new JButton("Login");
    private JButton buttonRegister = new JButton("Register");
    private JButton buttonStartNewGame = new JButton("Start New Game");
    private JButton buttonJoinAGame = new JButton("Join a Game");
    private JButton buttonStartGame = new JButton("Start Game");
    private JButton buttonJoinGame = new JButton("Join Game");
    private JButton buttonSubmitSuggestion = new JButton("Submit Suggestion");
    private JButton buttonSubmitOption = new JButton("Submit Option");
    private JButton buttonNextRound = new JButton("Next Round");
    
    public FoilMakerView(){


        controller = new Controller();
        setUpGUI();

    }

    private void setUpGUI() {
        this.panelFirst = loginPage();

        frame.add(mainPanel);
        frame.setSize(300, 500);
        frame.setTitle("FoilMaker");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setResizable(true);
        frame.setVisible(true);
        mainPanel.setLayout(layout);
        frame.setTitle("FoilMaker");


        mainPanel.add(panelFirst, "1");
        mainPanel.add(panelSecond, "2");
        mainPanel.add(panelThird, "3");
        mainPanel.add(panelFourth, "4");
        mainPanel.add(panelFifth, "5");
        mainPanel.add(panelSixth, "6");
        mainPanel.add(panelSeventh, "7");
        mainPanel.add(panelEighth, "8");


        layout.show(mainPanel, "1");
    }

    public void actionPerformed(ActionEvent e){
        JButton a = (JButton) e.getSource();
        layoutDisplay(a);
    }







    public void layoutDisplay(AbstractButton a) {
        if(a == buttonLogin) {
            boolean logged;
            do {
                logged = controller.isLogged();
                layout.show(mainPanel, "1");
            }
            while (logged == false);
            layout.show(mainPanel, "2");
        }
        else if(a == buttonRegister) {
            boolean registered;
            do {
                registered = controller.isRegistered();
                layout.show(mainPanel, "1");
            }
            while(registered == false);
            layout.show(mainPanel, "2");
        }





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
        JLabel username = new JLabel("Username");
        JLabel password = new JLabel("Password");
        JPasswordField passwordBox = new JPasswordField();
        JTextField usernameBox = new JTextField();
        //RENDER login
        //Record username and password in server when user registers so login is successful the next time
        panelFirst.setLayout(null);
        panelFirst.setBorder(p1);
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
        subPanel3.setBounds(50,300,200,20);
        subPanel3.add(buttonLogin);
        subPanel3.add(Box.createHorizontalStrut(1));
        subPanel3.add(buttonRegister);
        buttonRegister.addActionListener(this);

        return this.panelFirst;
    }

    public void optionToJoinOrStartPage() {

        JPanel secondSubPanel = new JPanel(new GridLayout(1,2));
        panelSecond.setLayout(null);
        panelSecond.setBorder(rest);
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
        //Display participants and key to user (from server)
        panelThird.add(thirdSubPanel);
        panelThird.setBorder(rest);
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









    public void joinExistingGamePage() {
        //Server should provide game key to user
        JPanel fourthSubPanel = new JPanel(new GridLayout(3,1));
        JLabel gameKeyInstructions = new JLabel("Enter the game key to join a game");
        JTextField gameKey = new JTextField();
        panelFourth.add(fourthSubPanel);
        panelFourth.setBorder(rest);
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
        panelFifth.setBorder(rest);
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
        JLabel wordIdentifcationInstructions = new JLabel("What is the word for");
        //Server is supposed to have a word identifier phrase in the "wordIdentification" panel
        // and is supposed to record the user's suggestion that will then be used in the next panel
        panelSixth.add(sixthSubPanel);
        panelSixth.setBorder(rest);
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
        //Display the suggestions recorded in the previous panel in the text for
        // the Radio Buttons
        panelSeventh.add(seventhSubPanel);
        panelSeventh.setBorder(rest);
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
        //Server displays round results and overall results in each of the panels
        // called "roundResult" and "overallResults"
        panelEighth.add(eightSubPanel);
        panelEighth.setBorder(rest);
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
