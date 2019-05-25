package ru.sar.neo;

public class Point {

  public double x;
  public double y;


  public Point (double x, double y) {

    this.x = x;
    this.y = y;

  }
  // Метод расчёта расстояния между точками

  public double distance(Point p2) {
    return Math.sqrt((p2.x-this.x)*(p2.x-this.x) + (p2.y-this.y)*(p2.y-this.y));
  }

}
