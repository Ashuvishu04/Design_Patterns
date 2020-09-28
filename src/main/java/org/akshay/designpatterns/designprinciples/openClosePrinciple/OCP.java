package org.akshay.designpatterns.designprinciples.openClosePrinciple;


import com.sun.tools.javac.util.List;
import java.util.stream.Stream;

// Open for extension and close for modification
// Specification pattern to demonstrate OCP
public class OCP {

public static void main(String args[]){
  Product apple = new Product("Apple",Color.Yellow,Size.SMALL);
  Product tree = new Product("Tree",Color.Black,Size.LARGE);
  Product house = new Product("House",Color.Red,Size.LARGE);

  List<Product> products = List.of(apple,tree,house);

  ProductFilter pf = new ProductFilter();

  System.out.println("Red products (old):");
  pf.filterByColor(products,Color.Red).forEach(p->System.out.println("-" + p.name + " is Red"));

  BetterFilter bf = new BetterFilter();
  System.out.println("Red products (new):");
  bf.filter(products,new ColorSpecification(Color.Red)).forEach(p->System.out.println("-" + p.name + " is Red"));

  bf.filter(products,new AndSpecification<>(new ColorSpecification(Color.Black)
                                            ,new SizeSpecification(Size.LARGE)))
              .forEach(p->System.out.println("-" + p.name + " is large and black"));
}

}

enum Color {
  Yellow, Red, Black
}

enum Size {
  SMALL, MEDIUM, LARGE, HUGE
}

class Product{

    public String name;
    public Color color;
    public Size size;

  public Product(String name, Color color, Size size) {
    this.name = name;
    this.color = color;
    this.size = size;
  }
}

class ProductFilter{

  public Stream<Product> filterByColor(List<Product> products,Color color){
    return products.stream().filter(p -> p.color == color);
  }

  public Stream<Product> filterBySize(List<Product> products,Size size){
    return products.stream().filter(p -> p.size == size);
  }

  public Stream<Product> filterBySizeAndColor(List<Product> products,Size size,Color color){
    return products.stream().filter(p -> p.size == size).filter(p -> p.color == color);
  }
}

interface Specification<T>{

  boolean isSatisfied(T item);
}

interface Filter<T>{

  Stream<T> filter(List<T> items,Specification<T> spec);
}

class ColorSpecification implements Specification<Product>{

  private Color color;

  public ColorSpecification(Color color) {
    this.color = color;
  }

  @Override
  public boolean isSatisfied(Product item) {
    return item.color == color;
  }
}

class SizeSpecification implements Specification<Product>{

  private Size size;

  public SizeSpecification(Size size) {
    this.size = size;
  }

  @Override
  public boolean isSatisfied(Product item) {
    return item.size == size;
  }
}

class BetterFilter implements Filter<Product>{

  @Override
  public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
    return items.stream().filter(p -> spec.isSatisfied(p));
  }
}

class AndSpecification<T> implements Specification<T>{

  private Specification<T> first,second;

  public AndSpecification(
      Specification<T> first,
      Specification<T> second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public boolean isSatisfied(T item) {
    return first.isSatisfied(item) && second.isSatisfied(item);
  }
}