/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dpcimpl.geometry;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Polygon;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.Iterator;

/**
 *
 * @author tog
 */
public class PolygonImpl implements Polygon
{

    private final G2D.Point2D[] points;
    private final Path2D.Float path;

    public PolygonImpl(G2D.Point2D[] points)
    {
        this.points = points;
        path = new Path2D.Float(Path2D.WIND_NON_ZERO, points.length + 1);
        path.moveTo(points[0].x(), points[0].y());
        for (int i = 1; i < points.length; ++i)
        {
            path.lineTo(points[i].x(), points[i].y());
        }
        path.lineTo(points[0].x(), points[0].y());
    }

    public Shape getShape()
    {
        return path;
    }

    @Override
    public int numberOfPoints()
    {
        return points.length;
    }

    @Override
    public G2D.Point2D getPoint(int index)
    {
        return points[index];
    }

    @Override
    public Iterator<G2D.Point2D> iterator()
    {
        return new PolygonIterator();
    }

    private class PolygonIterator implements Iterator<G2D.Point2D>
    {

        private int pos;

        public PolygonIterator()
        {
            pos = 0;
        }

        @Override
        public boolean hasNext()
        {
            return pos < points.length;
        }

        @Override
        public G2D.Point2D next()
        {
            return points[pos++];
        }
    }
}
