package com.geekbrains.clientchat.controllers;

import com.geekbrains.clientchat.ClientChat;
import com.geekbrains.clientchat.Network;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.function.Consumer;

public class AuthController {

    public static final String AUTH_COMMAND = "/auth";
    public static final String AUTH_OK_COMMAND = "/authOk";

    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Button authButton;

    private ClientChat clientChat;


    @FXML
    public void executeAuth() {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            clientChat.showErrorDialog("Логин и пароль должны быть указаны");
            return;
        }

        String authCommandMessage = String.format("%s %s %s", AUTH_COMMAND, login, password);

        try {
            Network.getInstance().sendMessage(authCommandMessage);
        } catch (IOException e) {
            clientChat.showErrorDialog("Ошибка передачи данных по сети");
            e.printStackTrace();
        }

    }

    public void setClientChat(ClientChat clientChat) {
        this.clientChat = clientChat;
    }

    public void initializeMessageHandler() {
        Network.getInstance().waitMessages(new Consumer<String>() {
            @Override
            public void accept(String message) {
                if (message.startsWith(AUTH_OK_COMMAND)) {
                    Thread.currentThread().interrupt();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            String[] parts = message.split(" ");
                            String userName = parts[1];
                            clientChat.getChatStage().setTitle(userName);
                            clientChat.getAuthStage().close();
                        }
                    });
                } else {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            clientChat.showErrorDialog("Пользователя с таким логином и паролем не существует");
                        }
                    });
                }
            }
        });
    }
}
