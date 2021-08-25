
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class GameFile {
  private File file;
  private String separator;

  public GameFile(String name, String separator){
    this.file = new File(name);
    this.separator = separator;
  }

  public List<String[]> read() {
    try {
      List <String[]> out = new ArrayList<>();
      Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
          String data = reader.nextLine();
          out.add(data.split(this.separator));
        }
      reader.close();
      return out;
    } catch (Exception e){
      System.out.println(e.getMessage());
      return null;
    }
  }

  public boolean write(List<String[]> data) {
    try {
      if (!file.exists()){
        file.createNewFile();
      }
      FileWriter writer = new FileWriter(file);
      StringBuilder out = new StringBuilder();
      for (String[] row : data){
        StringBuilder sb = new StringBuilder();
        for (String teks : row){
          sb.append(teks+this.separator);
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");
        out.append(sb.toString());
      }
      writer.write(out.toString());
      writer.close();
      return true;
    } catch (Exception e){
      return false;
    }
  }

  public boolean write(List<String[]> data, String filename) {
    try {
      File file = new File(filename);
      if (!file.exists()){
        file.createNewFile();
      }
      FileWriter writer = new FileWriter(file);
      StringBuilder out = new StringBuilder();
      for (String[] row : data){
        StringBuilder sb = new StringBuilder();
        for (String teks : row){
          sb.append(teks+this.separator);
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("\n");
        out.append(sb.toString());
      }
      writer.write(out.toString());
      writer.close();
      return true;
    } catch (Exception e){
      return false;
    }
  }
}