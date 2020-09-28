package org.akshay.designpatterns.CreationalPatterns.Prototype;

public class PTCopyConstructorDemo {

  public static void main(String[] args) {
    Employee akshay = new Employee("Akshay",
        new EmployeeAddress("Maharajpur","Rudrapur","India"));

    Employee vishav = new Employee(akshay);
    vishav.name = "Vishav";
    System.out.println(akshay);
    System.out.println(vishav);
  }
}

class EmployeeAddress {

  public String streetAddress, city, country;

  public EmployeeAddress(String streetAddress, String city, String country) {
    this.streetAddress = streetAddress;
    this.city = city;
    this.country = country;
  }

  public EmployeeAddress(EmployeeAddress other){
    this(other.streetAddress,other.city,other.country);
  }

  @Override
  public String toString() {
    return "Address{" +
        "streetAddress='" + streetAddress + '\'' +
        ", city='" + city + '\'' +
        ", country='" + country + '\'' +
        '}';
  }
}

class Employee{
  public String name;
  public EmployeeAddress address;

  public Employee(String name,
      EmployeeAddress address) {
    this.name = name;
    this.address = address;
  }

  public Employee(Employee other){
    name = other.name;
    address = new EmployeeAddress(other.address);
  }

  @Override
  public String toString() {
    return "Employee{" +
        "name='" + name + '\'' +
        ", address=" + address +
        '}';
  }
}
