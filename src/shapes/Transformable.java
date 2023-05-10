package shapes;


import geometry.Angle;
import geometry.Point;
import geometry.Vector;

/**
 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

 * interface for transformations (translation, rotation, scaling)
 * that all shapes should implement
 */
public interface Transformable {

    /**
     * Translation method.
     * @param vector a Vector that determines the
     * direction and the distance to move.
     */
    public void translate(Vector vector);

    /**
     * Rotation method (from a rotation center Point).
     * @param center a Point which is the center for the rotation.
     * @param angle an Angle to rotate.
     */
    public void rotate(Point center, Angle angle);

    /**
     * Homothéties / Scaling method.
     * @param refPoint a Point which is the reference from the scaling.
     * @param factor a double value which determines the scaling factor.
     */
    public void scale(Point refPoint, Double factor);

}
