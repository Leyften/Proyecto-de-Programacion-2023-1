package javaapplication2;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;


public class JavaApplication2  extends JFrame implements Runnable{
    public static final int WIDTH = 1280, HEIGHT =720;
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g;
    
    private final int FPS = 600;
    private double targettime = 1000000000/FPS;
    private double delta = 0;
    private int averagefps = FPS;
    
    public JavaApplication2(){
        setTitle("ordenar cajas");
        setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        canvas.setFocusable(true);
        
        add(canvas);
    }

 
    public static void main(String[] args) {
        new JavaApplication2().start();
    }
    
    int y=0;
    int x = 0;
    private void update(){
        x++;
        y++;
    }
    
    private void draw(){
        bs = canvas.getBufferStrategy();
        
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        
        g = bs.getDrawGraphics();
        
        g.clearRect(0,0,WIDTH,HEIGHT);
        
        g.setColor(Color.BLACK);
        g.drawString(" "+averagefps*10,10,20);
        
        g.drawRect(x, y, 100, 100);
                
        
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        
        long now= 0;
        long lasttime= System.nanoTime();
        int frames = 0;
        long time = 0;
        
        while(running){
            
          now = System.nanoTime();
          delta +=(now-lasttime)/targettime;
          time+= (now - lasttime);
          lasttime = now;
          
          if(delta>=1){
              update();
              draw();
              delta--;
              frames++;
          }
          if(time>= 100000000){
              averagefps = frames;
              frames=0;
              time=0;
          }
        }
        stop();  
    }
    
    private void start(){
        thread = new Thread(this);
        thread.start();
        running=true;
    }
    
    private void stop(){
        try{
            thread.join();
            running=false;
        } catch (InterruptedException e){
            e.printStackTrace();
        }
       
    }

    
}
