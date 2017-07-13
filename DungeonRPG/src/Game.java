import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Graphics;
public class Game extends JFrame implements KeyListener{

    //window vars
    private final int MAX_FPS;
    private final int WIDTH;
    private final int HEIGHT;

    //RECTANGLES
    private Rectangle[] rects;
    //double buffer
    private BufferStrategy strategy;

    //loop variables
    private boolean isRunning = true;
    private long rest = 0;

    //timing variables
    private float dt;
    private long lastFrame;
    private long startFrame;
    private int fps;
    public boolean goup = false;
    public boolean godown = false;
    public boolean goleft = false;
    public boolean goright = false;
    public int move = 1;
    public int domove = 0;
    public int b_over = 20;
    public int b_up = 500;
    public int hp = 100;
    public int select = 0;
    public int click = 1;
    public String Player_name = "";
    public String Player_name2 = "";
    public int Name_length = 0;
    public String health = "";
    public int m_up = 397;
    int access = 0;
    Font font1 = new Font ("Levi Adobe Dia", Font.BOLD, 10);
    Font font2 = new Font ("Levi Adobe Dia", Font.BOLD, 15);
    Font font3 = new Font ("Levi Adobe Dia", Font.BOLD, 20);
    Font font4 = new Font ("Levi Adobe Dia", Font.BOLD, 25);
    Font font5 = new Font ("Levi Adobe Dia", Font.BOLD, 30);
    Font font6 = new Font ("Levi Adobe Dia", Font.BOLD, 100);
    public int upppercase = 0;
    public enum game_state{
        menu,
        enter_name,
        play_game,
        end,
        encounter

    }
    game_state current_state = game_state.menu;

    //current_state = game_state(menu);

    //sprite1 variables
    private float x = 50;
    private float v = 10;

    //sprite2 variables
    private float x2 = 50.0f;
    private float v2 = 100.0f;


    private float wi = 280;
    private float wo = 380;

    private ArrayList<Integer> keys = new ArrayList<>();

    private void handlekeys(){
        for(int key : keys){
            switch(key){

            }
        }
    }

    public Game(int width, int height, int fps){
        super("JFrame Demo");
        this.MAX_FPS = fps;
        this.WIDTH = width;
        this.HEIGHT = height;
    }

    void init(){
        rects = new Rectangle[5];
        //make rectangles;
        rects[0] = new Rectangle(0, 550, 1000, 50 );

        rects[1] = new Rectangle(0, 15, 1000, 50 );

        rects[2] = new Rectangle(0, 0, 50, 1000 );

        rects[3] = new Rectangle(750, 350, 200, 250 );

        rects[4] = new Rectangle(750, 0, 200, 250 );
        //initialize JFrame
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(0, 0, WIDTH, HEIGHT);

        setIgnoreRepaint(true);

        setResizable(false);
        setVisible(true);

        //create double buffer strategy
        createBufferStrategy(2);
        strategy = getBufferStrategy();

        lastFrame = System.currentTimeMillis();

        addKeyListener(this);
        setFocusable(true);
    }

    private void update() {
        //update current fps
        fps = (int) (1f / dt);
        handlekeys();

        //update sprite
        x += v;
        if (x < 50 || x > (WIDTH - 50)) v *= -1;

        x2 += v2 * dt;
        if (x2 < 50 || x2 > (WIDTH - 50)) v2 *= -1.0f;

        if (move == 3 && domove == 1 && wo != 0){
            wo = wo-5;
            for(Rectangle rect : rects){
                if(wo < rect.x + rect.width && wo + 40 > rect.x && wi < rect.y + rect.height && wi + 40 > rect.y){
                    wo = rect.x + rect.width;
                }
            }
        }
        else if (move == 4 && domove == 1 && wo != 760){
            wo = wo+5;
            for(Rectangle rect : rects){
                if(wo < rect.x + rect.width && wo + 40 > rect.x && wi < rect.y + rect.height && wi + 40 > rect.y){
                    wo = rect.x - 40;
                }
            }
        }
        else if (move == 1 && domove == 1 && wi != 25){
            wi = wi-5;
            for(Rectangle rect : rects){
                if(wo < rect.x + rect.width && wo + 40 > rect.x && wi < rect.y + rect.height && wi + 40 > rect.y){
                    wi = rect.y + rect.height;
                }
            }
        }
        else if (move == 2 && domove == 1 && wi != 560){
            wi = wi+5;
            for(Rectangle rect : rects){
                if(wo < rect.x + rect.width && wo + 40 > rect.x && wi < rect.y + rect.height && wi + 40 > rect.y){
                    wi = rect.y - 40;
                }
            }
        }
        //if(wo < 1000 && wo + 40 > 0 && wi < 600 && wi + 40 > 550)
        if(hp == 0){
            current_state = game_state.end;
        }
        if(select != 0){
            click = 0;
        }
        if(select == 0){
            click = 1;
        }
        if(current_state == game_state.menu && select == 1 &&  m_up == 397 && access == 0){
            access = 1;
            current_state = game_state.enter_name;
        }
        if(current_state == game_state.menu && select == 1 &&  m_up == 397 && access == 1){
            current_state = game_state.play_game;
        }
        if(current_state == game_state.menu && select == 1 &&  m_up == 500){
            System.exit(0);
        }
    }




    @Override
    public void keyTyped(KeyEvent keyEvent) {


    }

    public void keyPressed(KeyEvent w) {
        if(!keys.contains(w.getKeyCode()))
            keys.add(w.getKeyCode());

        switch (current_state) {

            case menu:
                if(click == 1) {
                    switch (w.getKeyCode()) {
                        case KeyEvent.VK_W:
                            m_up = 397;
                            break;
                        case KeyEvent.VK_S:
                            m_up = 500;
                            break;
                        case KeyEvent.VK_ENTER:
                            select = 5;
                            break;
                    }
                }
                break;
            case enter_name:
                if (Name_length != 10) {
                    switch (w.getKeyCode()) {
                        case KeyEvent.VK_SHIFT:
                            upppercase = 1;
                            break;
                        case KeyEvent.VK_A:
                            if(upppercase == 1){
                                Player_name = Player_name + "A";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "a";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_B:
                            if(upppercase == 1){
                                Player_name = Player_name + "B";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "b";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_C:
                            if(upppercase == 1){
                                Player_name = Player_name + "C";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "c";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_D:
                            if(upppercase == 1){
                                Player_name = Player_name + "D";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "d";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_E:
                            if(upppercase == 1){
                                Player_name = Player_name + "E";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "e";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_F:
                            if(upppercase == 1){
                                Player_name = Player_name + "F";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "f";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_G:
                            if(upppercase == 1){
                                Player_name = Player_name + "G";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "g";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_H:
                            if(upppercase == 1){
                                Player_name = Player_name + "H";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "h";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_I:
                            if(upppercase == 1){
                                Player_name = Player_name + "I";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "i";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_J:
                            if(upppercase == 1){
                                Player_name = Player_name + "J";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "j";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_K:
                            if(upppercase == 1){
                                Player_name = Player_name + "K";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "k";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_L:
                            if(upppercase == 1){
                                Player_name = Player_name + "L";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "l";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_M:
                            if(upppercase == 1){
                                Player_name = Player_name + "M";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "m";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_N:
                            if(upppercase == 1){
                                Player_name = Player_name + "N";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "n";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_O:
                            if(upppercase == 1){
                                Player_name = Player_name + "O";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "o";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_P:
                            if(upppercase == 1){
                                Player_name = Player_name + "P";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "p";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_Q:
                            if(upppercase == 1){
                                Player_name = Player_name + "Q";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "q";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_R:
                            if(upppercase == 1){
                                Player_name = Player_name + "R";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "r";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_S:
                            if(upppercase == 1){
                                Player_name = Player_name + "S";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "s";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_T:
                            if(upppercase == 1){
                                Player_name = Player_name + "T";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "t";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_U:
                            if(upppercase == 1){
                                Player_name = Player_name + "U";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "u";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_V:
                            if(upppercase == 1){
                                Player_name = Player_name + "V";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "v";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_W:
                            if(upppercase == 1){
                                Player_name = Player_name + "W";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "w";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_X:
                            if(upppercase == 1){
                                Player_name = Player_name + "X";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "x";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_Y:
                            if(upppercase == 1){
                                Player_name = Player_name + "Y";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "y";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_Z:
                            if(upppercase == 1){
                                Player_name = Player_name + "Z";
                            }
                            if(upppercase == 0){
                                Player_name = Player_name + "z";
                            }
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_SPACE:
                            Player_name = Player_name + " ";
                            Name_length = Name_length + 1;
                            break;
                        case KeyEvent.VK_BACK_SPACE:
                            Player_name = Player_name2;
                            Name_length = 0;
                            break;
                        case KeyEvent.VK_ENTER:
                            current_state = game_state.play_game;
                            break;
                        case KeyEvent.VK_1:
                            Player_name = Player_name + "1";
                            Name_length = Name_length + 1;
                            break;

                        case KeyEvent.VK_0:
                            Player_name = Player_name + "0";
                            Name_length = Name_length + 1;
                            break;
                    }
                }
                switch (w.getKeyCode()){
                    case KeyEvent.VK_ENTER:
                        current_state = game_state.play_game;
                        break;
                    case KeyEvent.VK_BACK_SPACE:
                        Player_name = Player_name2;
                        Name_length = 0;
                        break;
                }

                break;
            case play_game:
                switch (w.getKeyCode()) {

                    case KeyEvent.VK_W:
                        move = 1;
                        domove = 1;
                        break;
                    case KeyEvent.VK_S:
                        move = 2;
                        domove = 1;
                        break;
                    case KeyEvent.VK_A:
                        move = 3;
                        domove = 1;
                        break;
                    case KeyEvent.VK_D:
                        move = 4;
                        domove = 1;
                        break;
                    case KeyEvent.VK_MINUS:
                        current_state = game_state.encounter;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        current_state = game_state.menu;
                        break;

                }
                break;
            case encounter:
                if(click == 1){
                    switch (w.getKeyCode()) {
                        case KeyEvent.VK_W:
                            b_up = 500;
                            break;
                        case KeyEvent.VK_S:
                            b_up = 550;
                            break;
                        case KeyEvent.VK_A:
                            b_over = 20;
                            break;
                        case KeyEvent.VK_D:
                            b_over = 230;
                            break;
                        case KeyEvent.VK_ESCAPE:
                            current_state = game_state.play_game;
                            break;
                        case KeyEvent.VK_MINUS:
                            hp = hp - 1;
                            break;
                        case KeyEvent.VK_EQUALS:
                            if (hp != 100) {
                                hp = hp + 1;
                            }
                            break;
                        case KeyEvent.VK_ENTER:
                            select = 5;
                    }
                }
                break;
            case end:
                switch (w.getKeyCode()){
                    case KeyEvent.VK_ENTER:
                        hp = 100;
                        access = 0;
                        Player_name = Player_name2;
                        current_state = game_state.menu;
                }
        }
    }
    @Override
    public void keyReleased(KeyEvent w) {
        switch(current_state) {
            case menu:
                break;
            case enter_name:
                switch (w.getKeyCode()) {
                    case KeyEvent.VK_SHIFT:
                        upppercase = 0;
                        break;
                }
                break;
            case play_game:
                switch (w.getKeyCode()) {
                    case KeyEvent.VK_W:
                        if (move == 1) {
                            domove = 0;
                        }
                        break;
                    case KeyEvent.VK_S:
                        if (move == 2) {
                            domove = 0;
                        }
                        break;
                    case KeyEvent.VK_A:
                        if (move == 3) {
                            domove = 0;
                        }
                        break;
                    case KeyEvent.VK_D:
                        if (move == 4) {
                            domove = 0;
                        }
                        break;
                }
                break;
        }
    }
    private void draw(){

//get canvas
        Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
        switch(current_state){

            case menu:
                //clear screen
                g.setColor(Color.black);
                g.fillRect(0,0,WIDTH, HEIGHT);



                g.setColor(Color.GRAY);
                g.fillRect(200, 500, 400, 100 );

                g.setColor(Color.GRAY);
                g.fillRect(200, 397, 400, 100 );
                g.setFont(font5);
                g.setColor(Color.black);
                g.drawString("Play Game", 358, 455);

                g.setColor(Color.black);
                g.drawString("Quit", 386, 560);

                g.setColor(Color.red);
                g.fillRect(200,(int)m_up,20, 100);
                if(select > 0){
                    g.setColor(Color.white);
                    g.fillRect(200,(int)m_up,400, 100);
                    select = select-1;
                }
                break;
            case enter_name:
                g.setColor(Color.black);
                g.fillRect(0,0,WIDTH, HEIGHT);

                g.setFont(font3);
                g.setColor(Color.gray);
                g.drawString("Enter Name:", 100, 100);

                g.setColor(Color.white);
                g.fillRect(180, 85, 200, 20);

                g.setFont(font3);
                g.setColor(Color.black);
                g.drawString(Player_name, 185, 102);
                break;
            case play_game:

                //clear screen
                g.setColor(Color.black);
                g.fillRect(0,0,WIDTH, HEIGHT);

                //draw fps
                g.setColor(Color.GREEN);
                g.drawString(Long.toString(fps), 10, 40);

                g.setColor(Color.GRAY);
                g.fillRect( (int)wo, (int)wi, 40,40);

                for(Rectangle rect : rects){
                    g.fillRect(rect.x, rect.y, rect.width, rect.height);
                }

               /* g.setColor(Color.GRAY);
                g.fillRect(0, 550, 1000, 50 );

                g.setColor(Color.GRAY);
              g.fillRect(0, 15, 1000, 50 );

                g.setColor(Color.GRAY);
              g.fillRect(0, 0, 50, 1000 );

                g.setColor(Color.GRAY);
                g.fillRect(750, 350, 200, 250 );

                g.setColor(Color.GRAY);
                g.fillRect(750, 0, 200, 250 );
                */

                break;
            case encounter:
                g.setColor(Color.black);
                g.fillRect(0,0,WIDTH, HEIGHT);

                //draw fps
                g.setColor(Color.GREEN);
                g.drawString(Long.toString(fps), 10, 40);

                g.setColor(Color.gray);
                g.fillRect(20,500,200, 40);

                g.setColor(Color.gray);
                g.fillRect(20,550,200, 40);

                g.setColor(Color.gray);
                g.fillRect(230,500,200, 40);

                g.setColor(Color.gray);
                g.fillRect(230,550,200, 40);

                g.setColor(Color.red);
                g.fillRect((int)b_over,(int)b_up,20, 40);
                if(select > 0){
                    g.setColor(Color.white);
                    g.fillRect((int)b_over,(int)b_up,200, 40);
                    select = select-1;

                }

                g.setColor(Color.white);
                g.fillRect(455,498,315, 34);

                g.setFont(font3);
                g.setColor(Color.black);
                g.drawString(Player_name, 460, 522);

                g.setColor(Color.gray);
                g.fillRect(557,501,206, 28);

                if(hp >= 71) {
                    g.setColor(Color.green);
                }
                if(hp <= 70 && hp >= 31){
                    g.setColor(Color.yellow);
                }
                if(hp <= 30){
                    g.setColor(Color.red);
                }

                g.fillRect(560,504,(int)hp*2, 22);

                g.setColor(Color.black);
                g.drawString(Integer.toString(hp) + "/100", 650, 523);
                break;
            case end:
                g.setColor(Color.red);
                g.fillRect(0,0,WIDTH, HEIGHT);

                g.setFont(font6);
                g.setColor(Color.black);
                g.drawString("DEATH", 290, 330);


        }

        //draw fps
        g.setColor(Color.GREEN);
        g.drawString(Long.toString(fps), 10, 40);
        //release resources, show the buffer
        g.dispose();
        strategy.show();
    }


    public void run(){
        init();

        while(isRunning){
            //new loop, clock the start
            startFrame = System.currentTimeMillis();
            //calculate delta time
            dt = (float)(startFrame - lastFrame)/1000;
            //log the current time
            lastFrame = startFrame;

            //call update and draw methods
            update();
            draw();

            //dynamic thread sleep, only sleep the time we need to cap the framerate
            rest = (1000/MAX_FPS) - (System.currentTimeMillis() - startFrame);
            if(rest >0){
                try{ Thread.sleep(rest); }
                catch (InterruptedException e){ e.printStackTrace(); }
            }
        }

    }



    public static void main(String[] args){
        Game game = new Game(800, 600, 60);
        game.run();
    }

}