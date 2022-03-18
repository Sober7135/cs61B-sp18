public class NBody {
  public static double readRadius(String path) {
    In in = new In(path);
    in.readDouble();
    return in.readDouble();
  }

  public static Planet[] readPlanets(String path) {
    In in = new In(path);
    int num = in.readInt();
    in.readDouble();
    // System.out.println(num);
    Planet[] planets = new Planet[5];
    for (int i = 0; i < num; i++) {
      Planet temp = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(),
          in.readString());
      planets[i] = temp;
    }
    return planets;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double r = readRadius(filename);
    double t = 0;

    StdDraw.enableDoubleBuffering();
    StdDraw.setScale(-r, r);
    StdDraw.picture(0, 0, "images/starfield.jpg");
    Planet[] planets = readPlanets(filename);
    for (Planet planet : planets) {
      planet.draw();
    }
    StdDraw.show();
    while (t <= T) {
      int num = planets.length;
      double[] xForces = new double[num], yForces = new double[num];
      for (int i = 0; i < num; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i = 0; i < num; i++) {
        planets[i].update(dt, xForces[i], yForces[i]);
      }
      StdDraw.picture(0, 0, "images/starfield.jpg");
      for (int i = 0; i < num; i++) {
        planets[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
      t += dt;
    }
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", r);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
