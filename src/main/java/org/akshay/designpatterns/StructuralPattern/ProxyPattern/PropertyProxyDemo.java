package org.akshay.designpatterns.StructuralPattern.ProxyPattern;

import java.util.Objects;

public class PropertyProxyDemo {

  public static void main(String[] args) {
    Creature creature = new Creature();
    creature.setAgility(20);
  }
}

class Property<T>{
  private T value;

  public Property(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Property<?> property = (Property<?>) o;
    return Objects.equals(value, property.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}

class Creature{

  private Property<Integer> agility = new Property<>(10);

  public void setAgility(int value){
    agility.setValue(value);
  }

  public Integer getAgility(){
    return agility.getValue();
  }

}
