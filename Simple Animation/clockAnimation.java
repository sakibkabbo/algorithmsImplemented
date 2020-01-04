/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab08;

import com.jogamp.opengl.util.Animator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JFrame;

/**
 *
 * @author Muhtasim
 */
public class clockAnimation implements GLEventListener {

    private double theta1 = 0;
    private double theta2 = 0;
    private double s = 0;
    private double c = 0;
    private double s2 = 0;
    private double c2 = 0;
    int rev = 0;
    public static Animator animator;
    GL2 gl = null;

    public static void main(String[] args) {
        clockAnimation s = new clockAnimation();
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        caps.setDoubleBuffered(true);
        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(s);
        JFrame frame = new JFrame("Animating triangle");
        frame.setSize(800, 800);
        frame.add(canvas);
        frame.setVisible(true);

        canvas.addGLEventListener(new clockAnimation());
        animator = new Animator(canvas);
        animator.start();
    }

    public void display(GLAutoDrawable drawable) {
        theta1 += Math.toRadians(-6.0);
        rev++;
        if (rev == 60) {
            theta2 += Math.toRadians(-6.0);
            rev = 0;
        }
        s = Math.sin(theta1);
        c = Math.cos(theta1);
        s2 = Math.sin(theta2);
        c2 = Math.cos(theta2);
        gl = drawable.getGL().getGL2();        
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL2.GL_POINTS);
        drawCircle(0.5);
        gl.glEnd();
        gl.glBegin(GL2.GL_LINES);
        double d = 0;
        int cnt = 0;
        while (true){
           gl.glVertex2d(0 * Math.cos(d) - 0.45 * Math.sin(d), 0 * Math.sin(d) + 0.45 * Math.cos(d));
           gl.glVertex2d(0 * Math.cos(d) - 0.5 * Math.sin(d), 0 * Math.sin(d) + 0.5 * Math.cos(d));
           d += (Math.PI / 6.0); 
           cnt++;
           if (cnt == 24) {
               break;
           }
        }
        gl.glVertex2d(rotateX1(0, 0), rotateY1(0, 0));
        gl.glVertex2d(rotateX1(0, 0.45), rotateY1(0, 0.45));
        gl.glVertex2d(rotateX2(0, 0), rotateY2(0, 0));
        gl.glVertex2d(rotateX2(0, 0.48), rotateY2(0, 0.48));
        gl.glEnd();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(clockAnimation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void drawCircle(double r) {
        double x = r, y = 0;
        double dinit = 0.0005 - (0.0004 * r);
        draw8Way(x, y);
        while (y < x) {
            if (dinit < 0) {
                dinit += ((2 * y) + 0.0003) * 0.0004;
                y += 0.00001;
            } else {
                dinit += (-(2 * x) + (2 * y) + 0.0005) * 0.0004;
                x -= 0.00001;
                y += 0.00001;
            }
            draw8Way(x, y);
        }
    }

    public void draw8Way(double x, double y) {
        gl.glVertex2d(x, y);
        gl.glVertex2d(y, x);
        gl.glVertex2d(-y, x);
        gl.glVertex2d(-x, y);
        gl.glVertex2d(-x, -y);
        gl.glVertex2d(-y, -x);
        gl.glVertex2d(y, -x);
        gl.glVertex2d(x, -y);
    }

    public double rotateX1(double x, double y) {
        return (x * c - y * s);
    }

    public double rotateY1(double x, double y) {
        return (x * s + y * c);
    }

    public double rotateX2(double x, double y) {
        return (x * c2 - y * s2);
    }

    public double rotateY2(double x, double y) {
        return (x * s2 + y * c2);
    }

    public void dispose(GLAutoDrawable drawable) {

    }

    public void init(GLAutoDrawable drawable) {
        drawable.getGL().setSwapInterval(1);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

}
