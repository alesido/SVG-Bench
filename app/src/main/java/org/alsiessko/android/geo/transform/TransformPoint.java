package org.alsiessko.android.geo.transform;

/**
 * Created by Alexander S. Sidorov on 19.06.2015.
 */
public class TransformPoint
{
    private Double x, y;

    public TransformPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public TransformPoint(float x, float y) {
        this.x = Double.valueOf(x);
        this.y = Double.valueOf(y);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setLatitude(Double latitude) {
        this.y = latitude;
    }

    public Double getLatitude() {
        return y;
    }

    public void setLongitude(Double longitude) {
        this.x = longitude;
    }

    public Double getLongitude() {
        return x;
    }
}
