package geometry;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * Angle class to represent
 * and calculate with angles.
 * This class is immutable.
 */

public class Angle {

    // ***** FIELDS *****
    private final double degrees;


    // ***** CONSTRUCTOR *****

    /**
     * Construct a new Angle from two coordinates x and y.
     * @param dx a double x coordinates in a Cartesian plan.
     * @param dy a double y coordinates in a Cartesian plan.
     */
    public Angle(double dx, double dy) {
        double radian = Math.atan2(dy, dx);
        degrees = Math.toDegrees(radian);
    }


    // ***** STATIC FACTORY METHODS *****

    /**
     * Factory method returns a new Angle
     * from a double degree parameter.
     * @param value a double value which represents degrees.
     * @return a new Angle from degrees value.
     */
    public static Angle inDegrees(double value) {
        double dx = Math.cos(Math.toRadians(value));
        double dy = Math.sin(Math.toRadians(value));
        return new Angle(dx, dy);
    }

    /**
     * Factory method returns a new Angle
     * from a double radian parameter.
     * @param value a double value which represents radians.
     * @return a new Angle from radians value.
     */
    public static Angle inRadians(double value) {
        double dx = Math.cos(value);
        double dy = Math.sin(value);
        return new Angle(dx, dy);
    }


    // ***** GETTERS AND SETTERS *****

    /**
     * Get the double value of the current Angle in degrees.
     * @return the double value of the current Angle in degrees.
     */
    public double getDegrees() {
        return degrees;
    }

    /**
     * Get the double value of the current Angle in radians.
     * @return the double value of the current Angle in radians.
     */
    public double getRadians() {
        return Math.toRadians(degrees);
    }


    // ***** METHODS *****

    /**
     * Return the double value of the sinus function from java.lang.Math.
     * Values of sin(180) and sin(360) are rounded to 0.
     * @return the double value of the sinus of the current Angle.
     */
    public double sin() {
        if (degrees == 180 || degrees == 360) return 0;
        else return Math.sin(Math.toRadians(degrees));
    }

    /**
     * Return the double value of the cosinus from java.lang.Math.
     * Values of cos(90) and cos(270) are rounded to 0.
     * @return the double value of the cosinus of the current Angle.
     */
    public double cos() {
        if (degrees == 90 || degrees == 270) return 0;
        else return Math.cos(Math.toRadians(degrees));
    }

    /**
     * Addition the current Angle with the value of another one.
     * @param other another Angle to add.
     * @return a new Angle from the sum of the current Angle and the other one.
     */
    public Angle plus(Angle other) {
        return inDegrees(degrees + other.getDegrees());
    }

    /**
     * Subtract the current Angle with the value of another one.
     * @param other another Angle to subtract.
     * @return a new Angle from the subtraction of the current Angle and the other one.
     */
    public Angle minus(Angle other) {
        return inDegrees(degrees - other.getDegrees());
    }

    /**
     * Multiply the current Angle by a double type value.
     * @param factor a double type value representing a factor.
     * @return a new Angle from the multiplication of the current Angle and the factor.
     */
    public Angle multiply(double factor) {
        return inDegrees(degrees * factor);
    }

    /**
     * Return a String with the Angle value in degrees.
     * This method toString() is override from Object class.
     * @return a String with the Angle value in degrees.
     */
    @Override
    public String toString() {
        return "Angle{" +
                "degrees=" + degrees +
                '}';
    }

}
