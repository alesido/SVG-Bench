package org.alsiessko.android.geo.transform;

/**
 * Created by Alexander S. Sidorov on 6/19/15.
 */
abstract public class Transform
{
    public enum keyPointType {
        SOURCE, TARGET
    }

    public Transform addKeyPoint(keyPointType keyPointType, int id, TransformPoint coordinate) {
        return this;
    }

    abstract public boolean build();

    /**
     * Apply coordinate transformation to the given point
     * @param subject
     */
    abstract public void applyTo(TransformPoint subject);

    /**
     * Return transformed coordinates and save the source object intact.
     * @param source
     * @return
     */
    abstract public TransformPoint apply(TransformPoint source);

}
