package client;


import gui.ChatFrame;

public class ClientChatAdapter {
    private ChatFrame chatFrame;
    private Client client;

    public ClientChatAdapter(String host, int port) {
        client = new Client(host, port);
        chatFrame = new ChatFrame(messageFromFormSubmitListener -> client.sendMessage(messageFromFormSubmitListener));
        read();
    }

    private void read() {
        new Thread(() -> {
            try {
                while (true) {
                    chatFrame.append(
                            client.receiveMessage()
                    );
                }
            } catch (ClientConnectionException e) {
                throw e;
            } finally {
                if (client != null) {
                    client.close();
                }
            }
        }).start();
    }
}
