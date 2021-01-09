package client;

import gui.ChatFrame;
import helpers.FileHelper;

public class ClientChatAdapter {
    private ChatFrame chatFrame;
    private Client client;

    public ClientChatAdapter(String host, int port) {
        client = new Client(host, port);
        chatFrame = new ChatFrame(messageFromFormSubmitListener -> client.sendMessage(messageFromFormSubmitListener));
        //2
        chatFrame.append(FileHelper.readHistory(100));
        read();
    }

    private void read() {
        new Thread(() -> {
            try {

                while (true) {
                    String message = client.receiveMessage();
                    chatFrame.append(message);
                    //1
                    FileHelper.writeHistory(message);
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
