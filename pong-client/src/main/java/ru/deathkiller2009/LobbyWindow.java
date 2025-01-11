package ru.deathkiller2009;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class LobbyWindow extends JFrame {

    private JLabel waitingLabel;
    private JButton cancelButton;
    private Socket socket;
    private String playerName;

    public LobbyWindow(String playerName) {
        this.playerName = playerName;
        setTitle("Лобби");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        waitingLabel = new JLabel("Ожидание второго игрока...");
        waitingLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        waitingLabel.setForeground(Color.WHITE);

        cancelButton = new JButton("Отмена");
        cancelButton.setFont(new Font("Monospaced", Font.BOLD, 14));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        cancelButton.setPreferredSize(new Dimension(100, 30));

        cancelButton.addActionListener(e -> {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            dispose();
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        panel.add(waitingLabel, gbc);
        panel.add(cancelButton, gbc);

        add(panel);
        setVisible(true);

        connectToServer();
    }

    private void connectToServer() {
        new Thread(() -> {
            try {
                socket = new Socket("localhost", 8082);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String request = """
                        START_MULTIPLAYER_GAME\r
                        \r
                        {
                            "player": "%s"
                        }
                        """.formatted(playerName);
                dataOutputStream.writeUTF(request);

                // Ожидание начала игры
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                String response = dataInputStream.readUTF();
                if (response.startsWith("GAME_STARTED")) {
                    // Закрываем лобби и запускаем игру
                    dispose();
                    GameWindow gameWindow = new GameWindow(socket);
                    Thread gameThread = new Thread(gameWindow);
                    gameThread.setDaemon(true);
                    gameThread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ошибка подключения к серверу", "Ошибка", JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        }).start();
    }
}