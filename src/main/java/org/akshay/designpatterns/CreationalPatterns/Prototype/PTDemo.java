package org.akshay.designpatterns.CreationalPatterns.Prototype;

import java.util.Arrays;

public class PTDemo {

  public static void main(String[] args) throws Exception {

    Person akshay = new Person(new String[]{"Akshay", "Chawla"},
        new Address("Maharajpur", 4));

    Person vishav = (Person) akshay.clone();
    vishav.names[0] = "Vishav";
    vishav.address.streetName = "Delhi";

    System.out.println(akshay);
    System.out.println(vishav);
  }

}

class Address implements Cloneable{

  public String streetName;
  public int houseNumber;

  public Address(String streetName, int houseNumber) {
    this.streetName = streetName;
    this.houseNumber = houseNumber;
  }

  @Override
  public String toString() {
    return "Address{" +
        "streetName='" + streetName + '\'' +
        ", houseNumber=" + houseNumber +
        '}';
  }

  // deep copy
  @Override
  public Object clone() throws CloneNotSupportedException {
    return new Address(streetName,houseNumber);
  }
}

class Person implements Cloneable{

  public String[] names;
  public Address address;

  public Person(String[] names,
      Address address) {
    this.names = names;
    this.address = address;
  }

  @Override
  public String toString() {
    return "Person{" +
        "names=" + Arrays.toString(names) +
        ", address=" + address +
        '}';
  }

  @Override
  public Object clone() throws CloneNotSupportedException {
    return new Person(names.clone(),(Address) address.clone());
  }
}
