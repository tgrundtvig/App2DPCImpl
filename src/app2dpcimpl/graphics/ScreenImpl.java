/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.graphics;

import app2dapi.graphics.Color;
import app2dapi.graphics.ColorFactory;
import app2dapi.graphics.Screen;

/**
 *
 * @author tog
 */
public class ScreenImpl implements Screen, ColorFactory
{
    private final int width;
    private final int height;
    private static final Color WHITE = new ColorImpl(255,255,255,255);
    private static final Color BLACK = new ColorImpl(0,0,0,255);
    private static final Color RED = new ColorImpl(255,0,0,255);
    private static final Color GREEN = new ColorImpl(0,255,0,255);
    private static final Color BLUE = new ColorImpl(0,0,255,255);
    private static final Color YELLOW = new ColorImpl(255,255,0,255);

    public ScreenImpl(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public int getPixelWidth()
    {
        return width;
    }

    @Override
    public int getPixelHeight()
    {
        return height;
    }

    @Override
    public ColorFactory getColorFactory()
    {
        return this;
    }

    @Override
    public Color newColor(float r, float g, float b, float a)
    {
        return new ColorImpl(r,g,b,a);
    }

    @Override
    public Color newColor(float r, float g, float b)
    {
        return new ColorImpl(r,g,b);
    }
    
    @Override
    public Color newColorInt(int r, int g, int b, int a)
    {
        return new ColorImpl(r,g,b,a);
    }

    @Override
    public Color newColorInt(int r, int g, int b)
    {
        return new ColorImpl(r,g,b);
    }

    @Override
    public Color getWhite()
    {
        return WHITE;
    }

    @Override
    public Color getBlack()
    {
        return BLACK;
    }

    @Override
    public Color getRed()
    {
        return RED;
    }

    @Override
    public Color getGreen()
    {
        return GREEN;
    }

    @Override
    public Color getBlue()
    {
        return BLUE;
    }

    @Override
    public Color getYellow()
    {
        return YELLOW;
    }
}
