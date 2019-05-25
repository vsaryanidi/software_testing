package ru.sar.neo;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  @Test
  public void testDistance1(){
    Point p1 = new Point(4,4);
    Point p2 = new Point(8,7);
    Assert.assertEquals(p1.distance(p2),5.0);

  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(-4,-4);
    Point p2 = new Point(-8,-7);
    Assert.assertEquals(p1.distance(p2),5.0);

  }

  @Test
  public void testDistance3(){
    Point p1 = new Point(0,0);
    Point p2 = new Point(0,0);
    Assert.assertEquals(p1.distance(p2),0.0);

  }

  @Test
  public void testDistance4(){
    Point p1 = new Point(5,3);
    Point p2 = new Point(2,4);
    Assert.assertEquals(p1.distance(p2),3.1622776601683795);


  }
}
