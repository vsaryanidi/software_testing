package ru.sar.neo;

public class MyFirstProgram {

  public static void main(String[] args) {
	  hello("Lera");
	  hello("world");
	  hello("Amigo");

	  double l = 5;
    System.out.println("Площадь квадрата: " + area(l));

    double a = 5.0;
    double b = 6.0;
    System.out.println("Площадь прямоугольника: " + area(a,b));
      }

      public static void hello(String s) {
        System.out.println("Hello, " + s + "!");

      }

      public static double area (double l){
        return l*l;
      }

      public static double area (double a, double b){
        return a*b;
      }
}