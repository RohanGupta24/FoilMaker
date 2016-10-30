//View
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.Border;


public class View extends JFrame {

    JPanel mainPanel = new JPanel();
    CardLayout layout = new CardLayout();

    JPanel panelFirst = new JPanel();
    Border p1 = BorderFactory.createTitledBorder("FoilMaker!");
    JButton buttonLogin = new JButton("Login");
    JButton buttonRegister = new JButton("Register");
    JLabel username = new JLabel("Username");
    JLabel password = new JLabel("Password");
    JPasswordField passwordBox = new JPasswordField();
    JTextField usernameBox = new JTextField();

    JPanel panelSecond = new JPanel();
    Border rest = BorderFactory.createTitledBorder("Bob");
    JButton buttonStartNewGame = new JButton("Start New Game");
    JButton buttonJoinAGame = new JButton("Join a Game");

    JPanel panelThird = new JPanel();
    JLabel keyDescription = new JLabel("Others should use this key to join your game");
    JTextField key = new JTextField();
    JPanel participants = new JPanel();
    JButton buttonStartGame = new JButton("Start Game");

    JPanel panelFourth = new JPanel();
    JLabel gameKeyInstructions = new JLabel("Enter the game key to join a game");
    JTextField gameKey = new JTextField();
    JButton buttonJoinGame = new JButton("Join Game");

    JPanel panelFifth = new JPanel();

    public View() {
        panelFirst.setLayout(new GridLayout(3,2));
        panelFirst.add(username);
        String username = passwordBox.getText();
        panelFirst.add(usernameBox);
        panelFirst.add(password);
        panelFirst.add(passwordBox);
        String password = passwordBox.getText();
        panelFirst.add(buttonLogin);
        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton a = (JButton) e.getSource();
                if(a == buttonLogin) {
                  layout.show(mainPanel, "2");
            }
        }
    });
        panelFirst.add(buttonRegister);
        buttonRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton a = (JButton) e.getSource();
                if(a == buttonRegister) {
                    layout.show(mainPanel, "2");
                }
            }
        });

        panelFirst.setBackground(Color.DARK_GRAY);

        panelSecond.add(buttonStartNewGame);
        buttonStartNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(mainPanel, "3");
            }
        });
        panelSecond.add(buttonJoinAGame);
        buttonJoinAGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layout.show(mainPanel, "4");
            }
        });
        panelSecond.setBackground(Color.DARK_GRAY);

        panelThird.add(keyDescription);
        panelThird.add(key);
        panelThird.add(participants);
        Border forParticipants = BorderFactory.createTitledBorder("Participants");
        participants.setBorder(forParticipants);
        participants.setBackground(Color.orange);
        panelThird.add(buttonStartGame);
        buttonStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panelThird.setBackground(Color.DARK_GRAY);

        panelFourth.add(gameKeyInstructions);
        panelFourth.add(gameKey);
        panelFourth.add(buttonJoinGame);
        panelFourth.setBackground(Color.DARK_GRAY);

        mainPanel.setLayout(layout);
        mainPanel.add(panelFirst, "1");
        mainPanel.add(panelSecond, "2");
        mainPanel.add(panelThird, "3");
        mainPanel.add(panelFourth, "4");
        mainPanel.add(panelFifth, "5");
        setTitle("FoilMaker");
        panelFirst.setBorder(p1);
        panelSecond.setBorder(rest);
        panelThird.setBorder(rest);
        panelFourth.setBorder(rest);

        add(mainPanel);
        setLocation(640,480);
        setMinimumSize(new Dimension(300,500));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        layout.show(mainPanel, "1");
        setVisible(true);
    }

    public static void main(String[] args) {
        new View();
    }

}
