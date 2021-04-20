package HWFiles;

import java.io.*;
import java.util.*;

public class Main {

    public static List<File> directories = new ArrayList<>();
    public static List<File> files = new ArrayList<>();

    public static void writeFilesInTemp (List <File> list) {
        for (File i : list) {
            try (FileWriter writer = new FileWriter("C:\\Games\\temp\\temp.txt",true)) {
                if (i.isDirectory()) {
                    writer.write("Директория - '" + i.getName() + "'");
                }else {
                    writer.write("Файл - '" + i.getName() + "'");
                }
                writer.append('\n');
                writer.flush();
            }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    public static void createDirectory(File dir) {
        if (dir.mkdir()) {
            System.out.println("Директория '" + dir.getName() + "' создана ");
            directories.add(dir);
        } else {
            System.out.println("Директория '" + dir.getName() + "' не создана");
        }

    }

    public static void createFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("Файл '" + file.getName() + "' был создан");
                files.add(file);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void readTemp () {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Games\\temp\\temp.txt"))) {
            String s;
            while((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {

        createDirectory(new File("C:\\Games"));
        createDirectory(new File("C:\\Games\\src"));
        createDirectory(new File("C:\\Games\\res"));
        createDirectory(new File("C:\\Games\\savegames"));
        createDirectory(new File("C:\\Games\\temp"));
        createDirectory(new File("C:\\Games\\src\\main"));
        createDirectory(new File("C:\\Games\\src\\test"));
        createFile(new File("C:\\Games\\src\\main\\Main.java"));
        createFile(new File("C:\\Games\\src\\main\\Utils.java"));
        createDirectory(new File("C:\\Games\\res\\drawables"));
        createDirectory(new File("C:\\Games\\res\\vectors"));
        createDirectory(new File("C:\\Games\\res\\icons"));
        createFile(new File("C:\\Games\\temp\\temp.txt"));

        writeFilesInTemp(files);
        writeFilesInTemp(directories);
        System.out.println();
        readTemp();
    }
}


