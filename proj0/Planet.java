public class Planet {
  public double xxPos, yyPos, xxVel, yyVel, mass;
  public String imgFileName;

  public static final double G = 6.67e-11;

  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  /**  */
  public double calcDistance(Planet rocinate) {
    return Math.pow((rocinate.xxPos - this.xxPos) * (rocinate.xxPos - this.xxPos)
        + (rocinate.yyPos - this.yyPos) * (rocinate.yyPos - this.yyPos), 0.5);
  }

  public double calcForceExertedBy(Planet rocinate) {
    double temp = calcDistance(rocinate);
    return G * this.mass * rocinate.mass / (temp * temp);
  }

  public double calcForceExertedByX(Planet rocinate) {
    return calcForceExertedBy(rocinate) * (rocinate.xxPos - this.xxPos) / calcDistance(rocinate);
  }

  public double calcForceExertedByY(Planet rocinate) {
    return calcForceExertedBy(rocinate) * (rocinate.yyPos - this.yyPos) / calcDistance(rocinate);
  }

  public double calcNetForceExertedByX(Planet[] allPlanets) {
    double netForceExertedByX = 0;
    for (int i = 0; i < allPlanets.length; i++) {
      if (this.equals(allPlanets[i])) {
        continue;
      }
      netForceExertedByX += calcForceExertedByX(allPlanets[i]);
    }
    return netForceExertedByX;
  }

  public double calcNetForceExertedByY(Planet[] allPlanets) {
    double netForceExertedByY = 0;
    for (int i = 0; i < allPlanets.length; i++) {
      if (this.equals(allPlanets[i])) {
        continue;
      }
      netForceExertedByY += calcForceExertedByY(allPlanets[i]);
    }
    return netForceExertedByY;
  }

  public void update(double dt, double fX, double fY) {
    double aX = fX / this.mass, aY = fY / this.mass;
    this.xxVel += dt * aX;
    this.yyVel += dt * aY;
    this.xxPos += this.xxVel * dt;
    this.yyPos += this.yyVel * dt;
  }


  public void draw() {
      StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
  }
}
