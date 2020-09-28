package org.akshay.designpatterns.StructuralPattern.DecoratorPattern;

import java.util.function.Supplier;
import org.w3c.dom.css.CSSImportRule;

public class DynamicDecoratorCompositionDemo {

  public static void main(String[] args) {

  /*  Circle circle = new Circle(10);
    System.out.println(circle.info());

    ColoredShape coloredShape = new ColoredShape(new Square(20),"Blue");
    System.out.println(coloredShape.info());

    TransparentShape myCircle = new TransparentShape(
        new ColoredShape(
            new Circle(5)
            , "Green")
        , 50);

    System.out.println(myCircle.info());*/

  ColoredShape<Square> blueSquare = new ColoredShape<>(
      () -> new Square(20),"Blue"
  );

  System.out.println(blueSquare.info());

  TransparentShape<ColoredShape<Circle>> myCircle = new TransparentShape<>(
      () -> new ColoredShape<>(
          () -> new Circle(5),"Red"),
      50
      );

  System.out.println(myCircle.info());
  
  }
}

interface Shape{
  String info();
}

class Circle implements Shape{

  private float radius;

  public Circle(){

  }

  public Circle(float radius) {
    this.radius = radius;
  }

  void resize(float factor){
    radius *= factor;
  }

  @Override
  public String info() {
    return "A circle of radius " + radius;
  }
}

class Square implements Shape{

  private float side;

  public Square(){

  }

  public Square(float side) {
    this.side = side;
  }

  @Override
  public String info() {
    return "A Square of side " + side;
  }
}

class ColoredShape<T extends Shape> implements Shape{

  private Shape shape;
  private String color;

  public ColoredShape(Supplier<? extends T> ctor
      ,String color){
    shape = ctor.get();
    this.color = color;
  }

  @Override
  public String info() {
    return shape.info() + " has a color " + color;
  }
}

class TransparentShape<T extends Shape> implements Shape{

  private Shape shape;
  private int transparency;;

  public TransparentShape(Supplier<? extends T> ctor
      ,int transparency){
    shape = ctor.get();
    this.transparency = transparency;
  }

  @Override
  public String info() {
    return shape.info() + " has " + transparency + "%transparency";
  }
}

/*
class ColoredShape implements Shape{

  private Shape shape;
  private String color;

  public ColoredShape(Shape shape, String color) {
    this.shape = shape;
    this.color = color;
  }

  @Override
  public String info() {
    return shape.info() + " has a color " + color;
  }
}

class TransparentShape implements Shape{

  private Shape shape;
  private int transparency;

  public TransparentShape(Shape shape, int transparency) {
    this.shape = shape;
    this.transparency = transparency;
  }

  @Override
  public String info() {
    return shape.info() + " has " + transparency + "%transparency";
  }
}*/
