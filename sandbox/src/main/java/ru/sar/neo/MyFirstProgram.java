package ru.sar.neo;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("Lera");
    hello("world");
    hello("Amigo");

    Square s = new Square(5);
    System.out.println("Площадь квадрата: " + s.area());


    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника: " + r.area());


    //Задание №2
    Point p1 = new Point(1, 2);
    Point p2 = new Point(5, 5);

    System.out.println("Расстояние между точками p1 и p2 " + "= " + distance(p1,p2));

  }

    public static double distance(Point p1,Point p2) {
      return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y-p1.y));
    }



  public static void hello(String s) {
    System.out.println("Hello, " + s + "!");

  }


}