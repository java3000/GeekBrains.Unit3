package helpers;

import java.io.*;

public class FileHelper {

    public static void writeHistory(String message) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("history.txt", true))) {
            bw.newLine();
            bw.write(message);
            bw.flush();
        } catch (Exception e) {
            throw new RuntimeException("history writing error", e);
        }
    }

/*    public static String readHistory(int count) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("history.txt"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null || i != count) {
                sb.append(line);
                i++;
            }
        } catch (Exception e) {
            throw new RuntimeException("history reading error", e);
        }
        return sb.toString();
    }*/

    public static String readHistory(int count) {
        StringBuilder result = new StringBuilder();
        File file = new File("history.txt");

        if (file.exists()) {
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                StringBuilder tmp = new StringBuilder();
                long fileLength = raf.length() - 1;
                raf.seek(fileLength);
                int i = 0;
                long pointer = fileLength;

                do {
                    for (; pointer >= 0; pointer--) {
                        raf.seek(pointer);
                        char c = (char) raf.read();
                        if (c == '\n') {
                            i++;
                            pointer--;
                            break;
                        }
                        tmp.append(c);
                    }
                    tmp.append("\n");
                    result.insert(0, tmp.reverse());
                    tmp.setLength(0);
                } while (i <= count && pointer >= 0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
