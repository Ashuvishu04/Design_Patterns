package org.akshay.designpatterns.StructuralPattern.ProxyPattern;

import com.google.inject.internal.asm.$AnnotationVisitor;

public class ProtectionProxyDemo {

  public static void main(String[] args) {
    Car car = new CarProxy(new Driver(12));
    car.drive();
  }
}

interface Drivable{
  void drive();
}

class Driver{

  public int age;

  public Driver(int age) {
    this.age = age;
  }
}

class Car implements Drivable{

  protected Driver driver;

  public Car(Driver driver) {
    this.driver = driver;
  }

  @Override
  public void drive() {
    System.out.println("Car being driven");
  }
}

class CarProxy extends Car{

  public CarProxy(Driver driver) {
    super(driver);
  }

  @Override
  public void drive() {
    if(driver.age >=16){
      super.drive();
    }
    else {
      System.out.println("Driver too young");
    }
  }
}
