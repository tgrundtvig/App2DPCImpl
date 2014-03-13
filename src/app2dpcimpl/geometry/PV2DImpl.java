/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dpcimpl.geometry;

import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Vector2D;

/**
 *
 * @author tog
 */
public class PV2DImpl implements Point2D, Vector2D
{

    private final float x;
    private final float y;

    public PV2DImpl(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public float x()
    {
        return x;
    }

    @Override
    public float y()
    {
        return y;
    }

    @Override
    public float sqrLength()
    {
        return x * x + y * y;
    }

    @Override
    public float length()
    {
        return (float) Math.sqrt(sqrLength());
    }
}
