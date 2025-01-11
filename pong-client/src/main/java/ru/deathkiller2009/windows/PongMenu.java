package ru.deathkiller2009.windows;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PongMenu extends JFrame {

    public PongMenu() {
        setTitle("Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);

        setResizable(false);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        Font pixelFont = new Font("Monospaced", Font.BOLD, 24);

        JLabel titleLabel = new JLabel("П О Н Г");
        titleLabel.setFont(pixelFont);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JButton startButton = createButton("Одиночная игра", pixelFont);
        JButton multyplayer = createButton("Мультиплеер", pixelFont);
        JButton exitButton = createButton("Выход", pixelFont);

        startButton.addActionListener(e -> {
            try {
                Socket socket = new Socket("localhost", 8082);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String request = """
                        START_GAME\r
                        \r
                        {
                            "player": "deathkiller2009",
                            "mode": "singleplayer"
                        }
                        """;
                dataOutputStream.writeUTF(request);
                GameWindow gameWindow = new GameWindow(socket);
                Thread thread = new Thread(gameWindow);
                thread.setDaemon(true);
                thread.start();
            } catch (IOException ignore) {
            }
        });

        multyplayer.addActionListener(e -> new LobbyWindow("deathkiller2009"));

        exitButton.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50); // Отступы

        panel.add(titleLabel, gbc);
        panel.add(startButton, gbc);
        panel.add(multyplayer, gbc);
        panel.add(exitButton, gbc);

        add(panel);

        setVisible(true);
    }

    private JButton createButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }

}