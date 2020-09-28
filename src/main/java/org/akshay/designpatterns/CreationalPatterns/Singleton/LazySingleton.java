package org.akshay.designpatterns.CreationalPatterns.Singleton;

public class LazySingleton {

  private static LazySingleton instance;

  private LazySingleton(){

  }

/*  public static synchronized LazySingleton getInstance(){
    if(instance==null){
      instance = new LazySingleton();
    }
    return instance;
  }*/

  // double-checked locking
  public static LazySingleton getInstance(){
    if(instance==null){
      synchronized (LazySingleton.class) {
        if (instance == null) {
          instance = new LazySingleton();
        }
      }
    }
    return instance;
  }



}
