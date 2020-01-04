/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab08;

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
public class cohenSutherlandClipping implements GLEventListener {

    static GLProfile profile = GLProfile.get(GLProfile.GL2);
    static GLCapabilities capabilities = new GLCapabilities(profile);
    // The canvas 
    static GLCanvas glcanvas = new GLCanvas(capabilities);
    static GL2 gl = null;
    double top = 0.3;
    double bottom = -0.3;
    double right = 0.4;
    double left = -0.4;
    int tline = 0;

    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile

        cohenSutherlandClipping l = new cohenSutherlandClipping();
        //creating frame
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(1000, 800);

        final JFrame frame = new JFrame("straight Line");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);

    }

    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        gl.glBegin(GL2.GL_LINES);//static field
        gl.glVertex2d(-0.4f, 0.3f);
        gl.glVertex2d(0.4f, 0.3f);
        gl.glVertex2d(-0.4f, 0.3f);
        gl.glVertex2d(-0.4f, -0.3f);
        gl.glVertex2d(-0.4f, -0.3f);
        gl.glVertex2d(0.4f, -0.3f);
        gl.glVertex2d(0.4f, -0.3f);
        gl.glVertex2d(0.4f, 0.3f);
        for (int i = 0; i < 200; i++) {
            double x0 = randNumb();
            double y0 = randNumb();
            double x1 = randNumb();
            double y1 = randNumb();
            CohenSutherlandLineClipping(x0, y0, x1, y1);
        }
        gl.glEnd();

    }

    public double randNumb() {
        double c = Math.random();
        if (c > 0.50) {
            c = 1;
        } else {
            c = -1;
        }
        if (c == 1) {
            c = Math.random();
        } else {
            c = -Math.random();
        }
        return c;
    }

    int makeCode(double x, double y) {
        int outcode;
        outcode = 0;
        if (y > 0.3) {
            outcode += 8;
        } else if (y < -0.3) {
            outcode += 4;
        }
        if (x > 0.4) {
            outcode += 2;
        } else if (x < -0.4) {
            outcode += 1;
        }
        return outcode;
    }

    public void CohenSutherlandLineClipping(double x0, double y0, double x1, double y1) {
        double x = 0, y = 0;
        int code0 = 0, code1 = 0, code = 0;
        code0 = makeCode(x0, y0);
        code1 = makeCode(x1, y1);
        int ex = 0;
        while (true) {
            if (ex > 6) {
                break;
            }
            ex++;
            if (code0 == 0 && code1 == 0) {
                gl.glColor3f(0, 0, 1);
                gl.glBegin(GL2.GL_LINES);
                gl.glVertex2d(x0, y0);
                gl.glVertex2d(x1, y1);
                break;
            } else if ((code0 & code1) > 0) {
                gl.glColor3f(1, 1, 1);
                gl.glBegin(GL2.GL_LINES);
                gl.glVertex2d(x0, y0);
                gl.glVertex2d(x1, y1);
                break;
            } else {
                if (code0 > 0) {
                    code = code0;
                } else {
                    code = code1;
                }
            }
            if (tline == 0){
                gl.glColor3f(1, 1, 1);
                gl.glBegin(GL2.GL_LINES);
                gl.glVertex2d(x0, y0);
                gl.glVertex2d(x1, y1);
                tline++;
            }
            if ((code & 8) == 8) {
                y = top;
                x = x0 + ((top - y0) / (y1 - y0)) * (x1 - x0);
            } else if ((code & 4) == 4) {
                y = bottom;
                x = x0 + ((bottom - y0) / (y1 - y0)) * (x1 - x0);
            } else if ((code & 2) == 2) {
                x = right;
                y = y0 + ((right - x0) / (x1 - x0)) * (y1 - y0);
            } else if ((code & 1) == 1) {
                x = left;
                y = y0 + ((left - x0) / (x1 - x0)) * (y1 - y0);
            }
            if (code == code0) {
                x0 = x;
                y0 = y;
                code0 = makeCode(x0, y0);
            } else if (code == code1) {
                x1 = x;
                y1 = y;
                code1 = makeCode(x1, y1);
            }
            if (code0 == 0 && code1 == 0) {
                gl.glColor3f(0, 1, 0);
                gl.glBegin(GL2.GL_LINES);
                gl.glVertex2d(x0, y0);
                gl.glVertex2d(x1, y1);
                tline--;
                break;
            }
        }
    }

    public void dispose(GLAutoDrawable arg0) {
        //method body
    }

    public void init(GLAutoDrawable drawable) {
        // method body
        //4. drive the display() in a loop
    }

    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }
    //end of main
}
