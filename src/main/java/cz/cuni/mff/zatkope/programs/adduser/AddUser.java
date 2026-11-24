package cz.cuni.mff.zatkope.programs.adduser;

import java.io.*;
import java.util.Objects;

public class AddUser {
    static final String PASSWD_FILE_LOCATION = "./passwd";

    public static void main(String[] args) {

    }

    private User getUserFromEntry(String entry) {
        if (entry == null) throw new IllegalArgumentException("Entry is not in the required format.");
        String[] fields = entry.split(":");
        if (fields.length != 7) throw new IllegalArgumentException("Entry is not in the required format.");

        return new User(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), fields[4], fields[5], fields[6]);
    }

    private boolean checkIfUsernameExists(String username) {
        BufferedReader file = getDataInputStream();
        for (String line : (String[]) file.lines().toArray()) {
            User currentUser = getUserFromEntry(line);
            if (Objects.equals(currentUser.name, username)) return true;
        }

        return false;
    }

    public BufferedReader getDataInputStream() {
        try (BufferedReader di = new BufferedReader(new InputStreamReader(new BufferedInputStream(new FileInputStream(PASSWD_FILE_LOCATION))))) {
            return di;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static class User {
        String name;
        String password;
        int uid;
        int gid;
        String gecos;
        String directory;
        String shell;

        public User(String field, String field1, int i, int i1, String field2, String field3, String field4) {
            name = field;
            password = field1;
            i = uid;
            i1 = gid;
            gecos = field2;
            directory = field3;
            shell = field4;
        }
    }
}
