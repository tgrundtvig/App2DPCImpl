package app2dpcimpl;


import app2dapi.App2D;
import app2dapi.Device;
import app2dapi.Platform;
import app2dapi.geometry.G2D;
import app2dapi.graphics.Screen;
import app2dpcimpl.geometry.G2DImpl;
import app2dpcimpl.graphics.CanvasImpl;
import app2dpcimpl.graphics.ScreenImpl;
import app2dpcimpl.input.EventHandlerImpl;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.MemoryImageSource;

/**
 *
 * @author tog
 */
public class PCPlatformImpl implements Platform
{

    @Override
    public void runApplication(App2D app)
    {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration gc = device.getDefaultConfiguration();
        Frame frame = new Frame(gc);    
        try
        {
           
            frame.setUndecorated(true);
            frame.setIgnoreRepaint(true);
            device.setFullScreenWindow(frame);
            
            //Mac hack begin (to make keyboard input work)
            frame.setVisible(false);
            frame.setVisible(true);
            //Mac hack end
            
            
            //Make mouse cursor invisible
            if(!app.showMouseCursor())
            {
                int[] pixels = new int[16 * 16];
                Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
                Cursor transparentCursor
                    = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
                frame.setCursor(transparentCursor);
            }
            
            
            //Create input event handler
            EventHandlerImpl eventHandler = new EventHandlerImpl();
            Toolkit.getDefaultToolkit().addAWTEventListener(eventHandler, eventHandler.getMask());
            
            
            Rectangle bounds = frame.getBounds();
            frame.createBufferStrategy(2);
            BufferStrategy strategy = frame.getBufferStrategy();
         
            Screen scr = new ScreenImpl(bounds.width, bounds.height);
            G2D g2d = new G2DImpl();
            Device dev = new DeviceImpl(scr, eventHandler, eventHandler, eventHandler, g2d);
            CanvasImpl canvas = new CanvasImpl(bounds, scr.getColorFactory(), g2d);
            if(!app.initialize(dev)) return;
            long startTimeMillis = System.currentTimeMillis();
            boolean running = app.update(0);
            eventHandler.start(startTimeMillis);
            float fps = 0;
            int frameCount = 0;
            //Render loop
            while (running)
            {
                Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
                if (!strategy.contentsLost())
                {
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
                    //Draw app
                    canvas.setGraphics(g);
                    app.draw(canvas);
                    //Draw statistics
                    g.setColor(java.awt.Color.BLACK);
                    g.drawString("FPS: " + fps, 10, bounds.height-10);
                    //Show
                    
                    strategy.show();
                    Toolkit.getDefaultToolkit().sync();
                    g.dispose();
                    Thread.sleep(2);
                    ++frameCount;  
                    
                    //Get time
                    long time = (System.currentTimeMillis() - startTimeMillis);
                    fps = ((float) frameCount*1000) / ((float) time);
                    
                    
                    
                    
                    //Dispatch input events
                    if(eventHandler.dispatch()) break;
                    
                    //Update application
                    double seconds = time * 0.001;
                    running = app.update(seconds);
                }
            }
            app.destroy();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            device.setFullScreenWindow(null);
            frame.setVisible(false);
            frame.dispose();
        }

    }
    
}