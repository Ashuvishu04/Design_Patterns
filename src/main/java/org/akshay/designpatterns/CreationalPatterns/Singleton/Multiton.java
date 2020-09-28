package org.akshay.designpatterns.CreationalPatterns.Singleton;

import java.util.HashMap;

public class Multiton {

  public static void main(String[] args) {
    Printer printer1 = Printer.get(Subsystem.PRIMARY);
    Printer printer2 = Printer.get(Subsystem.AUXILIARY);
    Printer printer3 = Printer.get(Subsystem.AUXILIARY);
  }
}

enum Subsystem{
  PRIMARY,
  AUXILIARY,
  FALLBACK
}

class Printer{
  private static int instanceCount = 0;

  private Printer(){
    instanceCount++;
    System.out.println("Number of instances build for printer " + instanceCount);
  }

  private static HashMap<Subsystem,Printer> instances = new HashMap<>();

  public static Printer get(Subsystem ss){
    if(instances.containsKey(ss))
      return instances.get(ss);

    Printer printer = new Printer();
    instances.put(ss,printer);
    return printer;
  }
}
