import java.io.*;

public class ReadFile {

  public static boolean doesFileExist(String path) {
    File file = new File(path);
    return file.exists();
  }

  public static void main(String[] args) throws IOException {
    try {
      if (!doesFileExist("src/main/resources/file.txt")) {
        throw new FileNotFoundException("The file doesn\'t exist");
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    FileReader fileReader = new FileReader("src/main/resources/file.txt");
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line;
    int number = 1;
    while ((line = bufferedReader.readLine()) != null) {
      String[] word = line.split("–");
      System.out.println("Word" + number + " " + word[0].trim());

      String[] meanings = word[1].split(",");
      for (int i = 0; i < meanings.length; i++) {
        System.out.println("meaning" + (i + 1) + " " + meanings[i].trim());
      }

      number++;
    }
    bufferedReader.close();
  }


}
