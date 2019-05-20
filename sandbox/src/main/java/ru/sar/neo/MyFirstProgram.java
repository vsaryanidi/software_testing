package ru.sar.neo;

public class MyFirstProgram {

  public static void main(String[] args) {
	  hello("Lera");
	  hello("world");
	  hello("Amigo");


	  Square s = new Square(5);
    System.out.println("Площадь квадрата: " + s.area());


    Rectangle r = new Rectangle(4,6);
    System.out.println("Площадь прямоугольника: " + r.area());
      }

      public static void hello(String s) {
        System.out.println("Hello, " + s + "!");

      }




}