package org.alsiessko.android.svgbench;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.Log;

import com.ekito.simpleKML.Serializer;
import com.ekito.simpleKML.model.Document;
import com.ekito.simpleKML.model.Kml;
import com.ekito.simpleKML.model.LatLonAltBox;
import com.ekito.simpleKML.model.Region;

import org.alsiessko.android.geo.transform.Transform;
import org.alsiessko.android.geo.transform.TransformPoint;
import org.alsiessko.android.svg.SvgDataHandler;
import org.alsiessko.android.geo.transform.GM2WGS84;

import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *  Convertor from SVG for satellite beam footprints obtained from Azercosmos to KML document.
 *
 * Created by Alexander S. Sidorov on 6/17/15.
 */
public class AzercosmosFootprintConverter implements SvgDataHandler
{
    private static final String TAG = AzercosmosFootprintConverter.class.getSimpleName();

    GM2WGS84 transform;

    Kml kml;

    public AzercosmosFootprintConverter(TransformPoint targetSouthWest, TransformPoint targetNorthEast)
    {
        transform = new GM2WGS84();
        transform.addKeyPoint(Transform.keyPointType.TARGET, 0, targetSouthWest)
                .addKeyPoint(Transform.keyPointType.TARGET, 1, targetNorthEast);
    }

    @Override
    public void start(RectF bounds)
    {
        // setup coordinate transformation
        TransformPoint lb = new TransformPoint(bounds.left, bounds.bottom);
        TransformPoint rt = new TransformPoint(bounds.top, bounds.right);

        transform.addKeyPoint(Transform.keyPointType.SOURCE, 0, lb)
                .addKeyPoint(Transform.keyPointType.SOURCE, 1, rt);

        transform.build();

        // create empty KML document to receive input converted from SVG
        kml = new Kml();

        Document kmlDocument = new Document();

        // set KML region, i.e. geographical bounding box
        transform.apply(lb);
        transform.apply(rt);

        TransformPoint lt = new TransformPoint(bounds.left, bounds.top);
        TransformPoint rb = new TransformPoint(bounds.right, bounds.bottom);
        transform.apply(lt);
        transform.apply(rb);

        double south = lb.getLatitude(), west = lb.getLongitude(),
                north = rt.getLatitude(), east = rt.getLongitude();
        for (TransformPoint p : new TransformPoint[] {lb, lt, rt, rb} ) {
            if (south > p.getLatitude()) south = p.getLatitude();
            if (west > p.getLongitude()) west = p.getLongitude();
            if (north < p.getLatitude()) north = p.getLatitude();
            if (east < p.getLongitude()) east = p.getLongitude();
        }

        LatLonAltBox latLonBox = new LatLonAltBox();
        latLonBox.setSouth(String.valueOf(south));
        latLonBox.setWest(String.valueOf(west));
        latLonBox.setNorth(String.valueOf(north));
        latLonBox.setEast(String.valueOf(east));

        Region kmlRegion = new Region();
        kmlRegion.setLatLonAltBox(latLonBox);

        kmlDocument.setRegion( kmlRegion );
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

    private enum PathSegmentType {
        LINE, ARC, CURVE
    }

    private enum PathCommand {
        m, M, l, L, v, V, h, H, c, C, s, S, z, Z
    }

    private class PathCommands {
        char command;
        List<TransformPoint> coordinates;
    }

    List<PathCommands> currentPath;

    TransformPoint currentPoint;

    @Override
    public void startPath() {
        currentPath = new ArrayList<>();
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

    public boolean write(FileOutputStream fos) {
        Serializer kmlSerializer = new Serializer();
        try {
            kmlSerializer.write(kml, fos);
        }
        catch (Exception e) {
            Log.e(TAG, "Cannot serialize KML!", e);
            return false;
        }

        return true;
    }

    public String getKmlString()
    {
        Serializer kmlSerializer = new Serializer();
        StringWriter kmlStringWriter = new StringWriter();

        try {
            kmlSerializer.write(kml, kmlStringWriter);
        }
        catch (Exception e) {
            Log.e(TAG, "Cannot serialize KML!", e);
            return null;
        }

        return kmlStringWriter.toString();
    }
}
