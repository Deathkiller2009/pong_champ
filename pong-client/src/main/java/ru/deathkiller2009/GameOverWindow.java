package ru.deathkiller2009;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverWindow extends JFrame {

    public GameOverWindow(String winnerName) {
        setTitle("Игра окончена");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);

        JLabel winnerLabel = new JLabel("Победитель: " + winnerName);
        winnerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        winnerLabel.setForeground(Color.WHITE);

        // Кнопка "В меню"
        JButton menuButton = createButton("В меню", new Font("Monospaced", Font.BOLD, 14));
        menuButton.addActionListener(e -> {
            dispose(); // Закрыть окно
        });


        JButton exitButton = createButton("Выход", new Font("Monospaced", Font.BOLD, 14));
        exitButton.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        panel.add(winnerLabel, gbc);
        panel.add(menuButton, gbc);
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
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }
}