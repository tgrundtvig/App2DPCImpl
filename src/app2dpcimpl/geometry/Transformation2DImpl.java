/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.geometry;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Transformation2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author tog
 */
public class Transformation2DImpl implements Transformation2D
{
    private final AffineTransform trans;

        public Transformation2DImpl(AffineTransform trans)
        {
            this.trans = trans;
        }
        
        
        public AffineTransform getJavaTransform()
        {
            return trans;
        }
        
        @Override
        public G2D.Point2D transform(G2D.Point2D p)
        {
            java.awt.geom.Point2D.Double jp = new java.awt.geom.Point2D.Double(p.x(), p.y());
            trans.transform(jp, jp);
            return new PVD2DImpl(jp.x, jp.y);
        }

        @Override
        public G2D.Vector2D transform(G2D.Vector2D v)
        {
            java.awt.geom.Point2D.Double jp = new java.awt.geom.Point2D.Double(v.x(), v.y());
            trans.deltaTransform(jp, jp);
            return new PVD2DImpl(jp.x, jp.y);
        }
}
