package org.akshay.designpatterns.CreationalPatterns.BuilderDesignPattern;

public class BDP {

  public static void main(String[] args) {
    Person akshay = new PersonBuilder().addName("Akshay").addAge("26").build();
    System.out.println(akshay);

  }

}

class Person{
  String name;
  String age;

  public Person() {
  }

  public Person(String name, String age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age='" + age + '\'' +
        '}';
  }
}

class PersonBuilder{

  protected Person person= new Person();

  public PersonBuilder addName(String name){
    person.name = name;
    return this;
  }

  public PersonBuilder addAge(String age){
    person.age = age;
    return this;
  }

  public Person build(){
    return person;
  }
}
