package org.alsiessko.android.svg;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Shader;

import com.ekito.simpleKML.model.Document;
import com.ekito.simpleKML.model.Kml;
import com.ekito.simpleKML.model.LatLonAltBox;
import com.ekito.simpleKML.model.Region;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Alexander S. Sidorov on 6/17/15.
 *
 * @see "http://simplekml.readthedocs.org/en/latest/gettingstarted.html"
 */
public class SvgKmlConvertor implements SvgDataHandler
{
    CoordinateConvertor coordinateConvertor;
    Kml kml;

    public SvgKmlConvertor(CoordinateConvertor coordinateConvertor) {
        this.coordinateConvertor = coordinateConvertor;
    }

    @Override
    public void start(RectF bounds)
    {
        kml = new Kml();

        Document kmlDocument = new Document();

        LatLonAltBox box  = new LatLonAltBox();
        // coordinateConvertor ...
        // box.setWest(); ...

        Region roi = new Region();
        roi.setLatLonAltBox(box);

        kmlDocument.setRegion( roi );

    }

    @Override
    public void setLimits(RectF limits) {

    }

    @Override
    public void addRect(Float x, Float y, float width, float height) {

    }

    @Override
    public void addLine(Float x1, Float y1, Float x2, Float y2) {

    }

    @Override
    public void addCircle(Float centerX, Float centerY, Float radius) {

    }

    @Override
    public void addOval(RectF rect) {

    }

    @Override
    public void addPoly(ArrayList<Float> points, boolean closed) {

    }

    @Override
    public void startPath() {

    }

    @Override
    public void endPath() {

    }

    @Override
    public void moveToPathStart(float x, float y) {

    }

    @Override
    public void relativeMoveToPathSegmentStart(float xOffset, float yOffset) {

    }

    @Override
    public void addLineToPathSegment(float x, float y) {

    }

    @Override
    public void addRelativeLineToPathSegment(float xOffset, float yOffset) {

    }

    @Override
    public void addCubicToPathSegment(char svgCommand, float x1, float y1, float x2, float y2, float x, float y) {

    }

    @Override
    public void addArcPathSegment(float lastX, float lastY, float x, float y, float rx, float ry, float theta, int largeArc, int sweepArc) {

    }

    @Override
    public void setStyleFill() {

    }

    @Override
    public void setColorWhite() {

    }

    @Override
    public void setColorBlack() {

    }

    @Override
    public void setColor(int c) {

    }

    @Override
    public void setShader(Shader shader) {

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void concatMatrix(Matrix matrix) {

    }

    @Override
    public void restoreMatrix() {

    }

    public void write(FileOutputStream fos) {

    }

    public String getKmlString() {
        return kmlString;
    }
}
