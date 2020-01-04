import javax.swing.JFrame;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;


import com.jogamp.opengl.util.Animator;
public class SimpleAnime2 implements GLEventListener {

    private double theta = 0;
    private double s = 0;
    private double c = 0;
    public static Animator animator;
    public static void main(String[] args) {
    	SimpleAnime2 s= new SimpleAnime2();
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        caps.setDoubleBuffered(true);
        GLCanvas canvas = new GLCanvas(caps);
        canvas.addGLEventListener(s);
        JFrame frame = new JFrame("Animating triangle");
        frame.setSize(800, 800);
        frame.add(canvas);
        frame.setVisible(true);
      
        canvas.addGLEventListener(new SimpleAnime2());
        animator = new Animator(canvas);
        animator.start();
    }

    
    public void display(GLAutoDrawable drawable) {
    	theta += 0.01;
        s = Math.sin(theta);
        c = Math.cos(theta);
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glBegin(GL2.GL_LINES);     
        gl.glVertex3d(rotateX(-0.2, -0.2), rotateY(-0.2, -0.2) , 0);
        gl.glVertex3d(rotateX(-0.2, 0.2), rotateY(-0.2, 0.2), 0);
        gl.glVertex3d(rotateX(0.2, 0.2), rotateY(0.2, 0.2), 0);
        gl.glVertex3d(rotateX(0.2, -0.2), rotateY(0.2, -0.2), 0);
        gl.glVertex3d(rotateX(-0.2, 0.2), rotateY(-0.2, 0.2), 0);
        gl.glVertex3d(rotateX(0.2, 0.2), rotateY(0.2, 0.2), 0);
        gl.glVertex3d(rotateX(-0.2, -0.2), rotateY(-0.2, -0.2), 0);
        gl.glVertex3d(rotateX(0.2, -0.2), rotateY(0.2, -0.2), 0);//
        gl.glVertex3d(rotateX(-0.2, 0.2), rotateY(-0.2, 0.2), 0);
        gl.glVertex3d(rotateX(0, 0.4), rotateY(0, 0.4), 0);
        gl.glVertex3d(rotateX(0.2, 0.2), rotateY(0.2, 0.2), 0);
        gl.glVertex3d(rotateX(0.4, 0.4), rotateY(0.4, 0.4), 0);
        gl.glVertex3d(rotateX(0, 0.4), rotateY(0, 0.4), 0);
        gl.glVertex3d(rotateX(0.4, 0.4), rotateY(0.4, 0.4), 0);
        gl.glVertex3d(rotateX(-0.2, -0.2), rotateY(-0.2, -0.2), 0);
        gl.glVertex3d(rotateX(0, 0), rotateY(0, 0), 0);//
        gl.glVertex3d(rotateX(0, 0), rotateY(0, 0), 0);
        gl.glVertex3d(rotateX(0, 0.4), rotateY(0, 0.4), 0);
        gl.glVertex3d(rotateX(0.2, -0.2), rotateY(0.2, -0.2), 0);
        gl.glVertex3d(rotateX(0.4, 0), rotateY(0.4, 0), 0);
        gl.glVertex3d(rotateX(0.4, 0), rotateY(0.4, 0), 0);
        gl.glVertex3d(rotateX(0.4, 0.4), rotateY(0.4, 0.4), 0);
        gl.glVertex3d(rotateX(0.4, 0), rotateY(0.4, 0), 0);
        gl.glVertex3d(rotateX(0, 0), rotateY(0, 0), 0);
        gl.glEnd(); 
        }
    public double rotateX(double x, double y) {
        return (x * c - y * s);
    }

    public double rotateY(double x, double y) {
        return (x * s + y * c);
    }
  
    public void dispose(GLAutoDrawable drawable) {
    	
    }




   
    public void init(GLAutoDrawable drawable) {
    	drawable.getGL().setSwapInterval(1);
    }

    
    public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
    }

   
}
