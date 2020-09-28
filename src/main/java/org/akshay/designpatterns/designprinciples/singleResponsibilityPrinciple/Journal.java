package org.akshay.designpatterns.designprinciples.singleResponsibilityPrinciple;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Journal {

  private List<String> entries = new ArrayList<String>();
  private int count = 0;

  public void add(String entry){
    entries.add("" + (++count) + " :" + entry);
  }

  public void remove(int index){
    entries.remove(index);
  }

  @Override
  public String toString() {
    return String.join(System.lineSeparator(),entries);
  }

  public void save(String fileName) throws FileNotFoundException {
    try (PrintStream printStream = new PrintStream(fileName))
    {
      printStream.println(toString());
    }
  }

  public void load(String fileName){}
  public void load(URL url){}
}

class Persistence{

  public void saveToFile(Journal journal,
                         String fileName,
                         boolean overwrite) throws FileNotFoundException{
    if(overwrite || new File(fileName).exists()){
      try (PrintStream printStream = new PrintStream(fileName))
      {
        printStream.println(toString());
      }
    }
  }
}

class Demo {

  public static void main(String[] args) throws Exception{
    Journal journal = new Journal();
    journal.add("I got to learn design patterns");
    journal.add("Started with design principles");
    System.out.println(journal);

    Persistence persistence = new Persistence();
    String file =  System.getProperty("user.dir") + "/src/main/resources/journal.txt";
    persistence.saveToFile(journal,file,true);

    //Runtime.getRuntime().exec("vi " + file);
    Desktop.getDesktop().open(new File(file));
  }
}
