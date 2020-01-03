package GameSource;

import java.util.Arrays;
import java.util.Random;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;


public class PlayGame extends JPanel implements ActionListener
{
	public static int score;

	private int[] xlocation;
	private int[] ylocation;
	private int[] temp;
	private int[] temp2;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private boolean alive;

	private Image lefthead;
	private Image righthead;
	private Image uphead;
	private Image downhead;
	private Image food;
	private Image body;

	private Timer timer;
	private int speed;
	private int growth;
	private int foodx;
	private int foody;

	private JLabel points;
	private JLabel boundary;
	private JLabel boundtype;
	private JLabel level;
	private JLabel leveltype;
	private JLabel game_over;
	private JButton back_to_menu;
	private JButton game_over_back_main;
	private JButton game_over_restart;

	private Random r;
	
	public PlayGame()
	{ 
		xlocation=new int[850];
		ylocation=new int[850];

		//Initial directions.
		left=false;
		right=true;
		up=false;
		down=false;

		//Initial state of snake.
		alive=true;

		//Speed of the snake.
		speed=Levels.speed;

		//Initial length of the snake.
		growth=3;

		//Initial score.
		score=0;

		//Initializing JComponents
		points=new JLabel();
		boundary=new JLabel("Boundary Type:");
		boundtype=new JLabel();
		level=new JLabel("Level:");
		leveltype=new JLabel();
		game_over=new JLabel("GAME OVER!");


		back_to_menu=new JButton("Back");
		game_over_back_main=new JButton("Main Menu");
		game_over_restart=new JButton("Play Again?");

		r=new Random();


		createEnvironment();
		startEngine();
	}

	//Consturctor Ends===============================================================================================

	private void createEnvironment()
	{
		// Back to Main Menu button Configuration.
		back_to_menu.setBounds(25, 7, 95, 35);
		back_to_menu.setFont(new Font("Segoe Print", Font.BOLD, 24));
		back_to_menu.setForeground(Color.BLACK);
		back_to_menu.setBackground(new Color(150, 150, 150));
		back_to_menu.setFocusPainted(false);
		back_to_menu.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				timer.stop();
				GameWindow.cards.show(GameWindow.contentPane, "mainmenu");
			}
		});

		// Boundary type description configuration
		boundary.setBounds(130, 7, 200, 35);
		boundary.setFont(new Font("Segoe Print", Font.BOLD, 22));
		boundary.setForeground(new Color(255, 255, 47));

		boundtype.setBounds(315, 7, 150, 35);
		
		if(Settings.blocked)
		{
			boundtype.setText("BLOCKED");
			boundtype.setFont(new Font("Segoe Print", Font.BOLD, 22));
			boundtype.setForeground(Color.RED);
		}
		else
		{
			boundtype.setText("PORTAL");
			boundtype.setFont(new Font("Segoe Print", Font.BOLD, 22));
			boundtype.setForeground(new Color(160, 66, 255));
		}

		level.setBounds(465, 7, 100, 35);
		level.setFont(new Font("Segoe Print", Font.BOLD, 22));
		level.setForeground(new Color(255, 255, 47));

		leveltype.setBounds(535, 7, 150, 35);

		if(Levels.level.equals("Easy"))
		{
			leveltype.setText("Easy");
			leveltype.setFont(new Font("Segoe Print", Font.BOLD, 22));
			leveltype.setForeground(new Color(26, 255, 26));
		}
		else if(Levels.level.equals("Intermediate"))
		{
			leveltype.setText("Intermediate");
			leveltype.setFont(new Font("Segoe Print", Font.BOLD, 22));
			leveltype.setForeground(new Color(179, 0, 179));
		}
		else
		{
			leveltype.setText("Difficult");
			leveltype.setFont(new Font("Segoe Print", Font.BOLD, 22));
			leveltype.setForeground(new Color(255, 0, 0));
		}

		// Score borad configuration.
		points.setText("Score: "+score);
		points.setBounds(740, 7, 150, 35);
		points.setForeground(new Color(255, 255, 47));
		points.setFont(new Font("Segoe Print", Font.BOLD, 22));



		//***********************************************************************************************************


		// Game over message configuration.
		game_over.setBounds(250, 200, 376, 60);
		game_over.setForeground(Color.BLACK);
		game_over.setFont(new Font("Impact", Font.BOLD, 72));
		game_over.setVisible(false);


		// Back to main menu after game over button configuration.
		game_over_back_main.setBounds(352, 280, 175, 40);
		game_over_back_main.setFont(new Font("Segoe Print", Font.BOLD, 24));
		game_over_back_main.setForeground(Color.ORANGE);
		game_over_back_main.setBackground(Color.BLACK);
		game_over_back_main.setFocusPainted(false);
		game_over_back_main.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				timer.stop();
				GameWindow.cards.show(GameWindow.contentPane, "mainmenu");
			}
		});
		game_over_back_main.setVisible(false);


		// Play again buttom configuration.
		game_over_restart.setBounds(340, 330, 200, 40);
		game_over_restart.setFont(new Font("Segoe Print", Font.BOLD, 24));
		game_over_restart.setForeground(Color.ORANGE);
		game_over_restart.setBackground(Color.BLACK);
		game_over_restart.setFocusPainted(false);
		game_over_restart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GameWindow.contentPane.add(new PlayGame(), "playgame");
				GameWindow.cards.show(GameWindow.contentPane, "playgame");
			}
		});
		game_over_restart.setVisible(false);



		// This Game Screen Configuration.
		this.requestFocus();
		this.setFocusable(true);
		this.setLayout(null);
		this.setSize(905, 700);
		this.setBackground(new Color(118, 118, 118));
		this.setBorder(BorderFactory.createMatteBorder(50, 25, 25, 25, Color.BLACK));
		this.add(back_to_menu);
		this.add(boundary);
		this.add(boundtype);
		this.add(level);
		this.add(leveltype);
		this.add(points);
		this.add(game_over);
		this.add(game_over_back_main);
		this.add(game_over_restart);
	}

	/**
     * Starts the game.
     *
     */
	private void startEngine()
	{
		gettingGraphics();
		initializeGame();
		Control();
	}

	/**
     * An abstract method for implementing the Key Bindings for handling the
     * the keyboard input.
     *
     * @param key         gets an integer key code of the key pressed.
     * @param operation   gets an string ID for the for the InputMap's put function.
     * @param listener    a reference for ActionListener's object passed to it.
     *
     */
	private void keyBindings(int key, String operation, ActionListener listener)
	{
		InputMap im=this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am=this.getActionMap();

		im.put(KeyStroke.getKeyStroke(key, 0, false), operation);

		am.put(operation, new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				listener.actionPerformed(e);
			}
		});
	}

	/**
     * A function to control the direction movement
     * of the snake using the above key binding
     * funtion.
     *
     */
	private void Control()
	{
		keyBindings(KeyEvent.VK_LEFT, "left", (handle) -> 
		{
			if(ylocation[0]>=50 && ylocation[0]<=625)
			{
				if(!right)
				left=true;

				up=false;
				down=false;
			}
		});

		keyBindings(KeyEvent.VK_RIGHT, "right", (handle) -> 
		{
			if(ylocation[0]>=50 && ylocation[0]<=625)
			{
				if(!left)
				right=true;

				up=false;
				down=false;
			}
		});

		keyBindings(KeyEvent.VK_UP, "up", (handle) -> 
		{
			if(xlocation[0]>=25 && xlocation[0]<=850)
			{
				if(!down)
				up=true;

				right=false;
				left=false;
			}
		});

		keyBindings(KeyEvent.VK_DOWN, "down", (handle) -> 
		{
			if(xlocation[0]>=25 && xlocation[0]<=850)
			{
				if(!up)
				down=true;

				right=false;
				left=false;
			}
		});
	}

	/**
     * Loads the images requried images
     * for the graphics.
     * 
     */
	private void gettingGraphics()
	{
		lefthead=new ImageIcon(getClass().getResource("/imgs/lefthead.png")).getImage();
		righthead=new ImageIcon(getClass().getResource("/imgs/righthead.png")).getImage();
		uphead=new ImageIcon(getClass().getResource("/imgs/uphead.png")).getImage();
		downhead=new ImageIcon(getClass().getResource("/imgs/downhead.png")).getImage();
		body=new ImageIcon(getClass().getResource("/imgs/body.png")).getImage();
		food=new ImageIcon(getClass().getResource("/imgs/food.png")).getImage();
	}


	/**
     * Initializes the starting point
     * of game.
     *
     */
	private void initializeGame()
	{
		for(int i=0 ; i<growth ; i++)
		{
			xlocation[i]=75-i*25;
			ylocation[i]=50;
		}

		locateFood();
		timer=new Timer(speed, this);
		timer.start();
	}

	@Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        drawGame(g);
    }

	/**
     * Draws the graphics loaded by
     * the gettingGraphics function by
     * the graphics, if the sanke is alive.
     *
     * @param g  gets a Graphic's reference object passed
     *           by the paintComponent function.
     */
	private void drawGame(Graphics g)
	{
		if(alive)
		{
			g.drawImage(food, foodx, foody, this);

			for(int i = 0; i < growth; i++)
			{
				if(i==0 && left)
				g.drawImage(lefthead, xlocation[i], ylocation[i], this);

				if(i==0 && right)
				g.drawImage(righthead, xlocation[i], ylocation[i], this);

				if(i==0 && up)
				g.drawImage(uphead, xlocation[i], ylocation[i], this);

				if(i==0 && down)
				g.drawImage(downhead, xlocation[i], ylocation[i], this);

				if(i!=0)
				g.drawImage(body, xlocation[i], ylocation[i], this);

			}
		}
		else
		{
			HighScore.updateData();
			game_over.setVisible(true);
			game_over_back_main.setVisible(true);
			game_over_restart.setVisible(true);
		}
	}

	/**
     * Checks if the head of the snake collides with
     * the food.
     *
     */
	private void ifFoodEaten()
	{
		if((xlocation[0]==foodx)&&(ylocation[0]==foody))
		{
			growth++;
			score++;
			points.setText("Score: "+score);
			
			locateFood();
		}
	}

	/**
     * Checks and handles the collision of the
     * snake head with it's body. Also handles
     * the situation when the snake reaches the
     * boundary.
     *
     */
	private void checkCollosion()
	{
		for(int i=1 ; i<growth ; i++)
		if((i>3) && (xlocation[0]==xlocation[i]) && (ylocation[0]==ylocation[i]))
		alive=false;

		if(Settings.blocked)
		{
			if(xlocation[0]==25 && left)
			alive=false;

			if(xlocation[0]==850 && right) 
			alive=false;

			if(ylocation[0]==50 && up) 
			alive=false;

			if(ylocation[0]==625 && down)
			alive=false;
		}
		else
		{
			if(xlocation[0]==0 && left)
			xlocation[0]=875;

			if(xlocation[0]==875 && right)
			xlocation[0]=0;

			if(ylocation[0]==25 && up)
			ylocation[0]=650;

			if(ylocation[0]==650 && down)
			ylocation[0]=25;
		}

		if(!alive)timer.stop();
	}

	/**
     * Ensures how the snake moves each 25 pixel.
     *
     */
	private void crawl()
	{
		for(int i=growth ; i>0 ; i--)
		{
			xlocation[i]=xlocation[i-1];
			ylocation[i]=ylocation[i-1];
		}

		if(left)
		xlocation[0]-=25;

		if(right)
		xlocation[0]+=25;

		if(up)
		ylocation[0]-=25;

		if(down)
		ylocation[0]+=25;
	}

	/**
     * Generates a new random location
     * for the food.
     *
     */
	private void locateFood()
	{
		int temploc;

		temploc=r.nextInt(35);
		foodx=(temploc>1)? temploc*25 : 25;
		
		temploc=r.nextInt(26);
       	foody=(temploc>2)? temploc*25 : 50;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(alive)
		{
			ifFoodEaten();
			checkCollosion();
			crawl();
		}
		repaint();
	}
}