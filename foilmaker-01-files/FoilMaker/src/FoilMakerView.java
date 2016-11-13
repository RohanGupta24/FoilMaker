import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.util.ArrayList;


public class FoilMakerView implements ActionListener {


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

    static private JTextField participantsTextField = new JTextField();
    static private String userChoice;
    static private JTextField suggestionBox = new JTextField();
    private JLabel wordClue = new JLabel();



    private JPanel panelFirst = new JPanel();
    private JPanel panelSecond = new JPanel();
    private JPanel panelThird = new JPanel();
    private JPanel panelFourth = new JPanel();
    private JPanel panelFifth = new JPanel();
    private JPanel panelSixth = new JPanel();
    private JPanel panelSeventh = new JPanel();
    private JPanel panelEighth = new JPanel();

    private Border p1 = BorderFactory.createTitledBorder("FoilMaker!");
    private Border rest;
    private boolean isGameOver = false;


    private JButton buttonLogin = new JButton("Login");
    private JButton buttonRegister = new JButton("Register");
    private JButton buttonStartNewGame = new JButton("New Game");
    private JButton buttonJoinAGame = new JButton("Join a Game");
    private JButton buttonStartGame = new JButton("Start Game");
    private JButton buttonJoinGame = new JButton("Join Game");
    private JButton buttonSubmitSuggestion = new JButton("Submit Suggestion");
    private JButton buttonSubmitOption = new JButton("Submit Option");
    private JButton buttonNextRound = new JButton("Next Round");

    private ArrayList<JRadioButton> optionsList = new ArrayList<JRadioButton>();

    private ArrayList<String> participantsList = new ArrayList<String>();


    private JLabel messageBox = new JLabel();

    private JTextArea overallResultText = new JTextArea(5,20);
    private JTextField roundResultText = new JTextField();



    private JTextField gameKey;

    private ArrayList<String> suggestionsList = new ArrayList<String>();

    private int time = 0;

    
    public FoilMakerView(){


        controller = new Controller();
        setUpGUI();

    }

    public void actionPerformed(ActionEvent e) {

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
        mainPanel.add(panelFirst, "1");
        mainPanel.add(panelSecond, "2");
        mainPanel.add(panelThird, "3");
        mainPanel.add(panelFourth, "4");
        mainPanel.add(panelFifth, "5");
        mainPanel.add(panelSixth, "6");
        mainPanel.add(panelSeventh, "7");
        mainPanel.add(panelEighth, "8");
        layout.show(mainPanel, "1");
        messageBox.setText("Welcome");
        frame.add(messageBox, BorderLayout.PAGE_END);

        optionsList.add(new JRadioButton());
        optionsList.add(new JRadioButton());
        optionsList.add(new JRadioButton());

    }

    public void layoutDisplay(AbstractButton a) {



        if(a == buttonLogin) {
            boolean logged = controller.isLogged();
            if(logged == false) {
                loginPage();
                layout.show(mainPanel, "1");
            }
            else {
                optionToJoinOrStartPage();
                layout.show(mainPanel, "2");
            }
        }
        else if(a == buttonRegister) {
            boolean registered = controller.isRegistered();
            if(registered == false) {
                loginPage();
                layout.show(mainPanel, "1");
                ;

            }
            else {
                loginPage();
                layout.show(mainPanel, "1");
            }
        }
        else if(a == buttonStartNewGame) {
            boolean gameStarted = controller.isNewGameStarted();
            if(gameStarted == false) {


                optionToJoinOrStartPage();
                layout.show(mainPanel, "2");
            }
            else {


                Thread waitForQuestion = new Thread(){

                    public void run(){
                        startGetParticipants();

                        panelThird.updateUI();
                        layout.show(mainPanel,"3");
                    }
                };

                waitForQuestion.start();



                displayParticipantsPage();
                layout.show(mainPanel, "3");





            }
        }
        else if(a == buttonJoinAGame) {
            boolean joinAGame = controller.isJoinAGame();
            if(joinAGame == false) {
                optionToJoinOrStartPage();
                layout.show(mainPanel, "2");
            }
            else {
                joinExistingGamePage();
                layout.show(mainPanel, "4");
            }
        }
        else if(a == buttonStartGame) {
            boolean startGame = controller.isGameStarted();
            if(startGame == false) {

                displayParticipantsPage();
                layout.show(mainPanel, "3");

            }
            else {


                wordIdentificationPage();
               layout.show(mainPanel, "6");



            }

        }
        else if(a == buttonJoinGame) {
            boolean joinGame = controller.isJoinGame();
            if(joinGame == false) {

                gameKey.setText("");
                layout.show(mainPanel, "4");
            }
            else {



                waitingLeaderPage();
                layout.show(mainPanel, "5");

                Thread waitForQuestion = new Thread(){

                    public void run(){
                        String serverMessage = getServerResponse();
                        System.out.println("In thread: " + serverMessage);

                        setWordClueText(serverMessage.substring(serverMessage.indexOf('-') + 2, serverMessage
                                .lastIndexOf('-') - 1));
                        wordIdentificationPage();
                        layout.show(mainPanel,"6");
                    }
                };

                waitForQuestion.start();


            }
        }
        else if(a == buttonSubmitSuggestion) {
            boolean submitSuggestion = controller.isSubmitSuggestion();

            if(getUserSuggestion().equals(this.controller.getRightAnswer())){
                messageBox.setText("Choose a different answer");
                mainPanel.updateUI();
                panelSixth.updateUI();
                layout.show(mainPanel, "6");

            }


            if(submitSuggestion == false) {
                mainPanel.updateUI();
                panelSixth.updateUI();
                layout.show(mainPanel, "6");
            }
            else {




                pickOptionPage();
                layout.show(mainPanel, "7");
            }
        }
        else if(a == buttonSubmitOption) {
            boolean submitOption = controller.isSubmitOption();

            if(time > 0){
                mainPanel.updateUI();
                layout.show(mainPanel,"8");
                System.out.println("Should show results page");
                buttonNextRound.setEnabled(false);
                return;
            }

            if(submitOption == false) {
                pickOptionPage();
                layout.show(mainPanel, "7");
            }
            else{


                resultsPage();
                layout.show(mainPanel, "8");
            }
        }
        else if(a==buttonNextRound){


            panelSixth.updateUI();
            layout.show(mainPanel,"6");


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






        subPanel3.setBounds(50,300,200,20);
        subPanel3.add(buttonLogin);
        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Moved the get text to button press
                usernameText = usernameBox.getText();
                passwordText = passwordBox.getText();
                usernameBox.setText("");
                passwordBox.setText("");
                JButton a = (JButton) e.getSource();
                layoutDisplay(a);
            }
        });
        subPanel3.add(Box.createHorizontalStrut(1));
        subPanel3.add(buttonRegister);
        buttonRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Moved the get text to button press
                usernameText = usernameBox.getText();
                passwordText = passwordBox.getText();
                usernameBox.setText("");
                passwordBox.setText("");

                JButton a = (JButton) e.getSource();


                layoutDisplay(a);
            }
        });

        return this.panelFirst;
    }

    public void optionToJoinOrStartPage() {

        rest = BorderFactory.createTitledBorder(usernameText);
        JPanel secondSubPanel = new JPanel(new GridLayout(1,2));
        panelSecond.setLayout(null);
        panelSecond.setBorder(rest);
        panelSecond.add(secondSubPanel);
        secondSubPanel.setSize(500,200);
        secondSubPanel.setBounds(50,200,200,20);
        secondSubPanel.add(buttonStartNewGame);
        secondSubPanel.add(buttonJoinAGame);
        messageBox.setText("Welcome!");

        buttonJoinAGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                JButton a = (JButton) e.getSource();
                layoutDisplay(a);
            }
        });
        buttonStartNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton a = (JButton) e.getSource();


                layoutDisplay(a);
            }
        });
    }

    public void displayParticipantsPage() {

        JPanel thirdSubPanel = new JPanel(new GridLayout(4,1));
        JLabel keyDescription = new JLabel("Others should use this key to join your game");
        JTextField key = new JTextField();
        JPanel participants = new JPanel();
        participantsTextField.setVisible(false);
        //Display participants and key to user (from server)
        key.setColumns(8);
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
        participants.add(participantsTextField);
        for(int i = 0; i < participantsList.size(); i++) {
            JLabel participantName = new JLabel(participantsList.get(i));
            participants.add(participantName);
        }
        thirdSubPanel.add(buttonStartGame);

        //if all participants have joined

        buttonStartGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                JButton a = (JButton) e.getSource();
                layoutDisplay(a);
            }
        });
    }

    public void joinExistingGamePage() {
        //Server should provide game key to user
        JPanel fourthSubPanel = new JPanel(new GridLayout(3,1));
        JLabel gameKeyInstructions = new JLabel("Enter the game key to join a game");
        gameKey = new JTextField();
        panelFourth.add(fourthSubPanel);
        panelFourth.setBorder(rest);
        fourthSubPanel.add(gameKeyInstructions);
        fourthSubPanel.add(gameKey);
        fourthSubPanel.add(buttonJoinGame);

        buttonJoinGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                JButton a = (JButton) e.getSource();


                checkKeyValidity = gameKey.getText(); //The server should check to see if the game key is valid
                a = (JButton) e.getSource();
                layoutDisplay(a);
            }
        });
    }

    public void waitingLeaderPage() {

        JPanel fifthSubPanel = new JPanel(new GridLayout(1,0));
        JLabel waitingLeader = new JLabel("Waiting for leader...");
        //Difficult: waiting leader (response from server)
        //Server is supposed to recognize when the leader has entered the game
        panelFifth.add(fifthSubPanel);
        panelFifth.setBorder(rest);
        fifthSubPanel.add(waitingLeader);
        //Server does work here in order to switch to new Panel
        //Once ready, switch to Panel 6
    }

    public void wordIdentificationPage() {

        JPanel sixthSubPanel = new JPanel(new GridLayout(4,1));
        JPanel wordIdentification = new JPanel();

        JPanel suggestion = new JPanel();
        messageBox.setText("Enter your Foil");

        suggestionBox.setColumns(8);

        suggestionBox.setVisible(true);
        suggestionBox.setSelectionColor(Color.BLACK);
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

        System.out.println("UserSuggestion" + userSuggestion);
        buttonSubmitSuggestion.setMargin(new Insets(0,0,0,0));
        sixthSubPanel.add(buttonSubmitSuggestion);

        sixthSubPanel.updateUI();

        buttonSubmitSuggestion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                userSuggestion = suggestionBox.getText();
                sixthSubPanel.updateUI();
                panelSixth.updateUI();
                panelSixth.setVisible(true);

                JButton a =(JButton) e.getSource();
                layoutDisplay(a);
            }
        });
    }

    public void pickOptionPage() {

        JPanel seventhSubPanel = new JPanel(new GridLayout(5,1));
        JLabel pickOptionDescription = new JLabel("Pick your option below");
        for(int i = 0; i < optionsList.size(); i++) {
            seventhSubPanel.add(optionsList.get(i));
        }

        messageBox.setText("Pick the Right Answer");
        //Display the suggestions recorded in the previous panel in the text for
        // the Radio Buttons
        panelSeventh.setLayout(new BorderLayout());
        panelSeventh.add(seventhSubPanel);
        panelSeventh.setBorder(rest);
        seventhSubPanel.add(pickOptionDescription);
        ButtonGroup options = new ButtonGroup();
        for(JRadioButton b: optionsList){
            options.add(b);
        }

        seventhSubPanel.add(buttonSubmitOption);

        buttonSubmitOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for(int i = 0; i < optionsList.size(); i ++){
                    if(optionsList.get(i).isSelected()){
                        userChoice = optionsList.get(i).getText();
                    }
                }


                System.out.println("Pick Option Button actionListiner");
                JButton a = (JButton) e.getSource();
                layoutDisplay(a);

            }
        });
    }

    public void resultsPage() {

        time++;

        JPanel eightSubPanel = new JPanel(new GridLayout(3,1));
        JPanel roundResult = new JPanel();

        roundResult.add(roundResultText);
        roundResultText.setText(this.controller.getRoundResult());
        JPanel overallResults = new JPanel();

        //overallResults.add(overallResultText);
        overallResultText.setText(this.controller.getOverallResult());

        roundResultText.setEditable(false);
        overallResultText.setEditable(false);

        JScrollPane scroll = new JScrollPane(overallResultText);
        //BoundedRangeModel brm = overallResultText.getHorizontalVisibility();

        //scroll.setModel(brm);
        //overallResults.add(scroll);
        overallResults.add(scroll);



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
        //if server still has more words

        if(isGameOver == true){
            buttonNextRound.setEnabled(false);
        }




        //otherwise
        buttonNextRound.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {



                JButton a = (JButton) e.getSource();
                layoutDisplay(a);


            }
        });
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
        mainPanel.updateUI();
        panelSixth.updateUI();
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

    public void setUsernameText(String text) { this.usernameText = text;}


    public String getCheckKeyValidity() {
        return this.checkKeyValidity;
    }

    public String getUserSuggestion() {
        return this.userSuggestion;
    }

    public void setParticipantsTextField(String participantsTextField){

        this.participantsTextField.setText(participantsTextField);

        layout.show(mainPanel,"3");
        panelThird.updateUI();
    }




    public void setMessageBox(String message){
        this.messageBox.setText(message);
        mainPanel.updateUI();
        panelFirst.updateUI();
    }


    public String getServerResponse(){
        return this.controller.getServerResponse();
    }

    public void startGetParticipants(){
        this.controller.getPersons();
    }

    public void getLeaderQuestion(){
        this.controller.getLeaderQuestion();
    }


    public void addOption(String option){

        JRadioButton temp = new JRadioButton(option);

        optionsList.add(temp);

    }



    public String getUserChoice(){
        return userChoice;
    }

    public void setOver(boolean over){
        this.isGameOver = over;
    }

    public void setSuggestionBox(String message){
        suggestionBox.setText(message);
    }


    public void setParticipantsTextFieldVisible(){
        participantsTextField.setVisible(true);
    }

    public void testSetWordClue(String message){
        wordClue.setText(message);
    }

    public void setOptionsListText(String message, int i){
        optionsList.get(i).setText(message);
        mainPanel.updateUI();

        panelSixth.revalidate();
        panelSixth.repaint();
        userSuggestion = suggestionBox.getText();
        panelSixth.updateUI();
        panelSixth.setVisible(true);
    }

    public boolean getOver(){
        return isGameOver;
    }

    public int getTime(){
        return time;
    }

    public void setResults(String result1, String result2){

        this.roundResultText.setText(result1);
        this.overallResultText.setText(result2);
    }



}
