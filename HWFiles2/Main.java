package HWFiles2;

import java.io.*;
import java.util.zip.*;

public class Main {

    private static int saveNum = 0;

    public static GameProgress game1 = new GameProgress(110, 5, 22, 40.10);
    public static GameProgress game2 = new GameProgress(150, 6, 30, 50.55);
    public static GameProgress game3 = new GameProgress(250, 10, 53, 60.75);

    public static String zipArchive = "C:\\Games\\savegames\\SavedGames.zip";
    public static File savedGamesFolder = new File("C:\\Games\\savegames\\");

    public static void saveGame(File folderToSave, GameProgress progress) {
        saveNum++;
        File newSaveGame = new File(folderToSave.getPath() + "\\Save" + saveNum + ".dat");
        try (FileOutputStream fos = new FileOutputStream(newSaveGame);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(progress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("файл сохранения " + newSaveGame.getName() + " успешно создан");
    }

    public static void zipAllSavedGamesInFolder(String archivePath, File folderToZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(archivePath))) {
            for (File save : folderToZip.listFiles()) {
                if (save.getPath().equals(archivePath)) {
                    continue;
                }
                FileInputStream fis = new FileInputStream(save);
                {
                    ZipEntry entry = new ZipEntry("packed" + save.getName());
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    fis.close();
                    System.out.println("Файл " + save.getName() + " помещён в архив");
                    save.delete();
                }
            }
            zout.closeEntry();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        saveGame(savedGamesFolder, game1);
        saveGame(savedGamesFolder, game2);
        saveGame(savedGamesFolder, game3);
        zipAllSavedGamesInFolder(zipArchive, savedGamesFolder);
    }
}
