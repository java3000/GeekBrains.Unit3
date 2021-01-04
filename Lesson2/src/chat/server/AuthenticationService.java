package chat.server;

//import com.mysql.jdbc.Driver

import java.util.Set;

public class AuthenticationService {
    private Set<CredentialsEntry> entries;


    public AuthenticationService() {
        update();
    }

    public void update() {
        entries = DataBaseHelper.getUsers();
    }

    public String findNicknameByLoginAndPassword(String login, String password) {
        for (CredentialsEntry entry : entries) {
            if (entry.getLogin().equals(login) && entry.getPassword().equals(password)) {
                return entry.getNickname();
            }
        }
        return null;
    }

    public static class CredentialsEntry {
        private String login;
        private String password;
        private String nickname;

        public CredentialsEntry(String login, String password, String nickname) {
            this.login = login;
            this.password = password;
            this.nickname = nickname;
        }


        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
