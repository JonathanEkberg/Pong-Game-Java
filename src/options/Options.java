package options;

import main.Pong;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Options {

    public static HashMap<String, Object> map = new HashMap<>();
    public static String[][] settings;

    public static int paddleHeight = (short) (Pong.HEIGHT / 6);
    public static int paddleWidth = (short) (Pong.WIDTH / 77);
    public static int ballSize = (short) (Pong.WIDTH / 60);
    public static int fontSize = Pong.PREFERRED.height / 8;
    public static int startFontSize = Pong.PREFERRED.height / 12;

    public static Font font = new Font("DPComic", Font.PLAIN, fontSize);
    public static Font startFont = new Font("DPComic", Font.PLAIN, startFontSize);

    private static File settingsFile = new File("src\\settings.ini");
    private static File settingsDefaultFile = new File("src\\settings_default.ini");

    public void readSettings() {
        readSettingsFromFile(settingsFile);
    }

    public void saveSettings() {
        saveSettingsToFile(settingsFile);
    }

    // Saves setting name and value in settings two dimensional array.
    public void readSettingsFromFile(File file) {
        try {
            if (validate(file)) file = settingsDefaultFile;
            System.out.println("Reading settings from " + file.toString() + "...\n");
            settings = new String[fileLength(file)][2];
            readSettingsFromFile(fileLength(file), file);
            saveSettingsToHashMap(file);
            System.out.println("\nSettings: " + Arrays.deepToString(settings) + "\n\nFinished\n");
        } catch (IOException e) {
            System.err.println(e + "\n");
        }
    }


    public void saveSettingsToFile(File file) {
        System.out.println("Saving settings to " + file.toString() + "...\n");
        saveHashMapToSettings();
        writeSettingsToFile(file);
    }

    private void readSettingsFromFile(int length, File file) throws IOException {
        String line;
        StringBuilder setting;
        StringBuilder value;
        BufferedReader br = new BufferedReader(new FileReader(file));

        for (int i = 0; i < length; i++) {
            setting = new StringBuilder();
            value = new StringBuilder();

            line = br.readLine();
            int j;

            if (line.charAt(0) == '#' || line.charAt(0) == '/') continue;

            for (j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '=') {
                    break;
                } else {
                    setting.append(line.charAt(j));
                }
            }

            for (int g = j + 1; g < line.length(); g++) {
                value.append(line.charAt(g));
            }

            settings[i][0] = setting.toString();
            settings[i][1] = value.toString();
        }
        br.close();
    }

    private void saveSettingsToHashMap(File file) {
        System.out.println("Adding settings to HashMap...\n");
        for (int i = 0; i < settings.length; i++) {
            if (validate(file)) {
                resetSettings();
                break;
            }

            String key = settings[i][0];
            String value = settings[i][1];

            if (value.charAt(value.length() - 1) == 'f') {
                map.put(key, Float.parseFloat(value));
                System.out.println("Saved setting " + key + " as Float with value " + map.get(key));
            }
            else if (value.contains("#")) {
                map.put(key, Color.decode(value));
                System.out.println("Saved setting " + key + " as Color with value " + colorFormatter((Color) map.get(key)));
            }
            else if (value.contains("false") || value.contains("true")) {
                map.put(key, Boolean.parseBoolean(value));
                System.out.println("Saved setting " + key + " as Boolean with value " + map.get(key));
            }
            else if (isNumeric(value)) {
                map.put(key, Integer.parseInt(value));
                System.out.println("Saved setting " + key + " as Integer with value " + map.get(key));
            }
            else {
                try {
                    map.put(key, value);
                    System.out.println("Saved setting " + key + " as String with value " + map.get(key));
                } catch (Exception e) {
                    System.err.println("Setting on line " + (i + 1) + " couldn't be read.\n");
                    System.out.println("Reading from " + settingsDefaultFile + " instead...");
                    resetSettings();
                    break;
                }
            }
        }
    }

    public void saveHashMapToSettings() {
        a:
        for (String key : map.keySet()) {
            for (int i = 0; i < settings.length; i++) {
                if (key.equals(settings[i][0])) {
                    if (map.get(key).toString().contains("java.awt.Color")) {
                        settings[i][1] = colorFormatter((Color) map.get(key));
                    } else {
                        settings[i][1] = map.get(key).toString();
                    }
                    continue a;
                }
            }
        }
    }

    private void writeSettingsToFile(File file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            StringBuilder setting;
            for (int i = 0; i < settings.length; i++) {
                boolean isFloat = false;
                if (settings[i][1].contains(".") && (!settings[i][1].contains("wav"))) isFloat = true;
                setting = new StringBuilder();
                setting.append(settings[i][0]).append("=").append(settings[i][1]).append(isFloat ? "f" : "");
                bw.write(setting.toString() + "\n");
                System.out.println("Writing " + setting.toString() + " on line " + (i + 1));
            }
            bw.close();
        } catch (IOException e) {
            System.err.println();
        }
    }

    private void resetSettings() {
        readSettingsFromFile(settingsDefaultFile);
        saveSettingsToHashMap(settingsDefaultFile);
        writeSettingsToFile(settingsFile);
    }

    public boolean validate(File file) {
        try {
            return fileLength(file) <= 0;
        } catch (IOException e) {
            System.err.println();
        }
        return true;
    }

    private int fileLength(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        int length = 0;
        while (br.readLine() != null) length++;
        br.close();
        return length;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static String colorFormatter(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
}