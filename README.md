# OGP Taglets

These are the Javadoc taglets for course H01P1A, Objectgericht programmeren, at KU Leuven. In particular, they support `@pre`, `@post`, `@throws`, and `@invar` tags with formal parts,
as well as the more specialized `@immutable`, `@representationObject`, `@representationObjects`, `@peerObject`, `@peerObjects`, `@basic`, `@inspects`, `@mutates`, `@mutates_properties`, and `@may_throw`
tags.

For example, suppose you have the following class:

```java
/**
 * Instances of this class store an integer interval with a lower bound and an upper bound.
 * 
 * @invar This interval's upper bound is not less than its lower bound.
 *         | getLowerBound() <= getUpperBound()
 * @invar This interval's width equals its upper bound minus its lower bound.
 *      | getWidth() == getUpperBound() - getLowerBound()
 */
public class Interval {
    
    private int lowerBound;
    
    /**
     * This interval's upper bound.
     * 
     * @invar This interval's upper bound is not less than its lower bound.
     *      | lowerBound <= upperBound
     */
    private int upperBound;
    
    public int getLowerBound() {
        return lowerBound;
    }
    
    public int getUpperBound() {
        return upperBound;
    }
    
    public int getWidth() {
        return upperBound - lowerBound;
    }

    /**
     * Initializes this interval with the given lower and upper bounds.
     * 
     * @pre The given lower bound is not greater than the given upper bound.
     *    | lowerBound <= upperBound
     * @post This interval's lower bound equals the given lower bound.
     *    | getLowerBound() == lowerBound
     * @post This interval's upper bound equals the given upper bound.
     *    | getUpperBound() == upperBound
     */
    public Interval(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    
    /**
     * Initializes this interval with the given lower bound and width.
     * 
     * @pre The given width is nonnegative.
     *    | 0 <= width
     * @post This interval's lower bound equals the given lower bound.
     *    | getLowerBound() == lowerBound
     * @post This interval's width equals the given width.
     *    | getWidth() == width
     * @post The dummy argument equals true or false.
     *       (This dummy postcondition is here just to work around bug https://github.com/fsc4j/fsc4j/issues/2)
     *    | dummy == true || dummy == false
     */
    public Interval(int lowerBound, int width, boolean dummy) {
        this.lowerBound = lowerBound;
        this.upperBound = lowerBound + width;
    }

    /**
     * Sets this interval's lower bound to the given value.
     * 
     * @pre The given value is not greater than this interval's upper bound.
     *     | value <= getUpperBound()
     * @post This interval's lower bound equals the given value.
     *     | getLowerBound() == value
     * @post This interval's upper bound has remained unchanged.
     *     | getUpperBound() == old(getUpperBound())
     */
    public void setLowerBound(int value) {
        lowerBound = value;
    }
    
    /**
     * Sets this interval's upper bound to the given value.
     * 
     * @pre The given value is not less than this interval's lower bound.
     *    | getLowerBound() <= value
     * @post This interval's upper bound equals the given value.
     *    | getUpperBound() == value
     * @post This interval's lower bound has remained unchanged.
     *    | getLowerBound() == old(getLowerBound())
     */
    public void setUpperBound(int value) {
        upperBound = value;
    }
    
    /**
     * Sets this interval's width to the given value.
     * 
     * @pre The given value is nonnegative.
     *    | 0 <= value
     * @post This interval's width equals the given value.
     *    | getWidth() == value
     * @post This interval's lower bound has remained unchanged.
     *    | getLowerBound() == old(getLowerBound())
     */
    public void setWidth(int value) {
        upperBound = lowerBound + value;
    }

}
```

You can generate Javadoc for it by downloading the OGP Taglets .jar file from the [Releases](https://github.com/btj/ogptaglets/releases) page, and then:

1. In Eclipse, in the Project menu, choose **Generate Javadoc...**.
2. On the first page of the Generate Javadoc wizard, select the file containing the Interval class. Then click Next.
3. On the second page, select referenced archive `jrt-fs.jar`. Then click Next.
4. On the third page, enter the following extra Javadoc options:
    ```
    -tagletpath /Users/YOUR_USERNAME/Downloads/ogptaglets-0.2.jar -taglet ogptaglets.ImmutableTaglet -taglet ogptaglets.InvariantsTaglet -taglet ogptaglets.RepresentationObjectTaglet -taglet ogptaglets.RepresentationObjectsTaglet -taglet ogptaglets.PeerObjectTaglet -taglet ogptaglets.PeerObjectsTaglet -taglet ogptaglets.BasicTaglet -taglet ogptaglets.PreconditionsTaglet -taglet ogptaglets.InspectsTaglet -taglet ogptaglets.MutatesTaglet -taglet ogptaglets.MutatesPropertiesTaglet -taglet ogptaglets.ThrowsTaglet -taglet ogptaglets.MayThrowTaglet -taglet ogptaglets.PostconditionsTaglet
    ```
   Also, check the _Open generated index file in browser_ checkbox.
   Then, click Finish.
