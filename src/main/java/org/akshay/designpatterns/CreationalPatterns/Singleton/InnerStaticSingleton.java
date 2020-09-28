package org.akshay.designpatterns.CreationalPatterns.Singleton;

public class InnerStaticSingleton {

  private InnerStaticSingleton(){}

  private static class Impl{
    private static InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
  }

  public InnerStaticSingleton getInstance(){
    return Impl.INSTANCE;
  }

}
