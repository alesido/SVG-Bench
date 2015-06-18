package org.alsiessko.android.svg;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Shader;

import java.util.ArrayList;

/**
 * Created by Alexander S. Sidorov on 6/18/15.
 */
public interface SvgDataHandler
{
    void start(RectF bounds);

    void setLimits(RectF limits);

    //
    // -- Geometry
    //

    void addRect(Float x, Float y, float width, float height);

    void addLine(Float x1, Float y1, Float x2, Float y2);

    void addCircle(Float centerX, Float centerY, Float radius);

    void addOval(RectF rect);

    /**
     * Add polygon or polyline
     * @param points
     * @param closed Polygon, if true.
     */
    void addPoly(ArrayList<Float> points, boolean closed);

    /**
     *
     */
    void startPath();
    void endPath();

    void moveToPathStart(float x, float y);
    void relativeMoveToPathSegmentStart(float xOffset, float yOffset);

    void addLineToPathSegment(float x, float y);
    void addRelativeLineToPathSegment(float xOffset, float yOffset);

    void addCubicToPathSegment(char svgCommand, float x1, float y1, float x2, float y2, float x, float y);

    void addArcPathSegment(float lastX, float lastY, float x, float y, float rx, float ry, float theta, int largeArc, int sweepArc);

    //
    // -- Styling
    //

    void setStyleFill();
    void setColorWhite();
    void setColorBlack();
    void setColor(int c);
    void setShader(Shader shader);

    void setAlpha(int i);

    void concatMatrix(Matrix matrix);

    void restoreMatrix();
}
