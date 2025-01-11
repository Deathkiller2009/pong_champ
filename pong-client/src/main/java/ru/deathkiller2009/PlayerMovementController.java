package ru.deathkiller2009;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.net.Socket;

@Data
@AllArgsConstructor
public class PlayerMovementController {

    public PlayerMovementController(Paddle paddle, Socket socket) {
        this.paddle = paddle;
        this.socket = socket;
        this.userInputProcessor = null;
    }

    private UserInputProcessor userInputProcessor;

    private Paddle paddle;

    private final Socket socket;

    public void update() {
        if (userInputProcessor != null) {
            if (userInputProcessor.isKeyPressed(KeyEvent.VK_DOWN)) {
                moveDown();
            } else if (userInputProcessor.isKeyPressed(KeyEvent.VK_UP)) {
                moveUp();
            }
        }
    }

    @SneakyThrows
    public void moveUp() {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("UP");
    }

    @SneakyThrows
    public void moveDown() {
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("DOWN");
    }

}
