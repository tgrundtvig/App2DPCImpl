/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.graphics;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Polygon;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.Color;
import app2dapi.graphics.ColorFactory;
import app2dpcimpl.geometry.PolygonImpl;
import app2dpcimpl.geometry.Transformation2DImpl;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 *
 * @author tog
 */
public class CanvasImpl implements Canvas
{
    private static final AffineTransform id = new AffineTransform(1,0,0,1,0,0);
    private final Graphics2D g;
    private final Rectangle bounds;
    private final ColorFactory colorFactory;
    private final G2D g2d;
    private ColorImpl curColor;
    private Transformation2DImpl curTrans;
    

    public CanvasImpl(Graphics2D g, Rectangle bounds, ColorFactory colorFactory, G2D g2d)
    {
        this.g = g;
        this.bounds = bounds;
        this.colorFactory = colorFactory;
        this.curColor = (ColorImpl) colorFactory.getBlack();
        this.g.setColor(curColor.getAWTColor());
        this.g2d = g2d;
        this.curTrans = (Transformation2DImpl) g2d.identity();
    }
    
    @Override
    public void clear(Color c)
    {
        AffineTransform cur = g.getTransform();
        g.setTransform(id);
        g.setColor(((ColorImpl)c).getAWTColor());
        g.fillRect(0, 0, bounds.width, bounds.height);
        g.setColor(curColor.getAWTColor());
        g.setTransform(cur);
    }

    @Override
    public void setColor(Color c)
    {
        curColor = (ColorImpl) c;
        g.setColor(curColor.getAWTColor());
    }

    @Override
    public Color getColor()
    {
        return curColor;
    }

    @Override
    public void setTransformation(Transformation2D t)
    {
        curTrans = (Transformation2DImpl) t;
        g.setTransform(curTrans.getJavaTransform());
    }

    @Override
    public Transformation2D getTransformation()
    {
        return curTrans;
    }

    @Override
    public void drawLine(Point2D p1, Point2D p2)
    {
        //Temporary implementation
        p1 = curTrans.transform(p1);
        p2 = curTrans.transform(p2);
        g.drawLine((int)p1.x(), (int)p1.y(), (int)p2.x(), (int)p2.y());
    }

    @Override
    public void drawFilledPolygon(Polygon polygon)
    {
        g.fill(((PolygonImpl) polygon).getShape());
    }

    @Override
    public void drawOutlinedPolygon(G2D.Polygon polygon)
    {
        g.draw(((PolygonImpl) polygon).getShape());
    }

    @Override
    public void drawPoint(G2D.Point2D p, float size)
    {
       //TODO
    }

    @Override
    public void drawPoint(G2D.Point2D p)
    {
        //TODO
    }
    
}
