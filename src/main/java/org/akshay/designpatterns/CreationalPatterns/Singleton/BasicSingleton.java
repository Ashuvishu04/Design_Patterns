package org.akshay.designpatterns.CreationalPatterns.Singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BasicSingleton implements Serializable {


  private BasicSingleton(){

  }

  private static final BasicSingleton INSTANCE = new BasicSingleton();

  public static BasicSingleton getInstance(){
    return INSTANCE;
  }

  private int value = 0;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  protected Object readResolve(){
    return INSTANCE;
  }
}

class Demo{

  static void saveToFile(BasicSingleton basicSingleton,String fileName) throws Exception{
    try(FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
      objectOutputStream.writeObject(basicSingleton);
    }
  }

  static BasicSingleton readFromFile(String fileName) throws Exception{
    try (FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
      return (BasicSingleton) objectInputStream.readObject();
    }
  }

  public static void main(String[] args) throws Exception {
    BasicSingleton singleton = BasicSingleton.getInstance();
    singleton.setValue(123);

    String fileName =  System.getProperty("user.dir") + "/src/main/resources/singleton.bin";

    saveToFile(singleton,fileName);

    singleton.setValue(222);

    BasicSingleton singleton1 = readFromFile(fileName);

    System.out.println(singleton == singleton1);
    System.out.println(singleton.getValue());
    System.out.println(singleton1.getValue());

  }
}
