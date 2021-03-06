package org.akshay.designpatterns.CreationalPatterns.FactoryMethod;

public class FMDemo {

  public static void main(String[] args) {
    //Point point = Point.newPolarPoint(2, 3);
    Point point = Point.Factory.newPolarPoint(2, 3);
  }
}

class Point{

  private double x,y;

/*  private Point(double a,double b,CoordinateSystem cs) {
    switch (cs){
      case CARTESIAN:
        this.x = a;
        this.y = b;
        break;
      case POLAR:
        this.x = a * Math.cos(b);
        this.y = a * Math.sin(b);
        break;
    }
  }*/

  private Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public static class Factory{

    public static Point newCartesianPoint(double x, double y){
      return new Point(x,y);
    }

    public static Point newPolarPoint(double rho, double theta){
      return new Point(rho * Math.cos(theta),rho * Math.sin(theta));
    }
  }

}

enum CoordinateSystem{
  CARTESIAN,
  POLAR
}


