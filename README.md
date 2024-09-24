# JavaTangram
University of Fribourg project 


 * ALL RIGHTS RESERVED :
 * UNIVERSITY OF FRIBOURG - SWISS

Project Tangram :

Step 1 :

    I decided to round sin() and cos() function if the angle is == 90, 180, 270 or 260.
    This is because java returns values like 6.123233005736766E-17
    for example with Math.cos(Math.toRadians(90)).

Step 2 :

    I implemented an extra method g2DFill for drawing shapes with a Graphics2D.
    (because I didn't use the library from the university for drawing.
    This method is abstract inside Shape abstract class.

Step 3 :

    Parallelogram :
        It was important for me to have the possibility to construct a Parallelogram
        with two points, even if they are not aligned.

                            d ------- c
                         .         .  |
                     .         .      height
                 .         .          |
              a ------- b -- shift -- t -- height-- t2

        For that, I had to create 4 static methods :
        - getAngleBAX : for having the angle between a - b and the horizontal x - axe.
        - computeT : t is b.translate(shift) and rotate with angle BAX
        - computeT2 : t2 is t.translate(height). t2 is rotating by 90 to become point c.
        - computePoints : that returns an list of a, b, c, d (d is c.translate(-a.distance(b)).

        I made the same thing for Triangle class.

                             c
                         .   |      .
                     .     height          .
                 .           |                     .
            a -- shift --- s --height-- s2 ---------- b

Step 4 :

    Group :

        I created 2 constructors :
            - One with (Color & List<? extends Shape>.
                In this case, the group center is computed by the mean of each shapes from the List.
                This computation is made by a static method.
            - One with (Color & List<? extends Shape> & Point)
                In this case, the point represent the "anchor" of the Group.
                This anchor is like the center, for transformations.
                The anchor is stored inside the field groupCenter.

        I added a method displayWithGroupColor(Display display)

    Segment :

        Segment crossing algorithm is made with the "orientation" method between 3 Points.


Step 5 :

    I added setVertices inside Polygon.
    I added getSegments() inside Polygon.

    The method alignShape() into my Controller loops
    inside all Polygon vertices to round their coordinates.
    When a Shape is moved or rotated, it will be aligned.

    To check if the Tangram is complete :

    1. Downsize each Tangram pieces for matching with
        Polygon containment and segment intersection algorithms :
        Necessary because it doesn't work if two Point are equals
        and if two Segments are parallels or collinear.

    2. Check if the Shape to fill contains all Tangram pieces vertices.

    3. Check if all Tangram pieces segments do not collide.

    4. After checking, resize all Tangram pieces and align it to the grid.
        Modify the View Background if it's win !



<img src="https://github.com/PixelPolo/TangramGame/blob/main/tangram.png" alt="tangram" width="400">



