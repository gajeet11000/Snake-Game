package GameSource;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class MainMenu extends JLabel
{
	private JPanel mainmenu_panel;

	private JButton play;
	private JButton level;
	private JButton scores;
	private JButton setting;
	private JButton quit;

	public MainMenu()
	{
		mainmenu_panel=new JPanel();

		play=new JButton("Play Game!");
		level=new JButton("Levels");
		scores=new JButton("High Scores");
		setting=new JButton("Settings");
		quit=new JButton("Quit");


		createEnvironment();
		

		this.setIcon(new ImageIcon(getClass().getResource("/imgs/MainMenu.png")));
		this.setBounds(0, 0, 905, 700);
		this.add(mainmenu_panel);
	}

	private void createEnvironment()
	{
		//-------------------------------------------Main Menu Panel--------------------------------------------

		//Main Menu Panel Configuration
		mainmenu_panel.setLayout(null);
		mainmenu_panel.setBounds(285, 210, 328, 285);
		mainmenu_panel.setBackground(new Color(26, 26, 26));


		//Play Button Configuration
		play.setFont(new Font("Serif", Font.BOLD, 22));
		play.setBounds(90, 10, 150, 45);
		play.setForeground(Color.BLACK);
		play.setBackground(new Color(150, 150, 150));
		play.setFocusPainted(false);
		play.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GameWindow.contentPane.add(new PlayGame(), "playgame");
				GameWindow.cards.show(GameWindow.contentPane, "playgame");
			}
		});
		mainmenu_panel.add(play);


		//Levels Button Configuration
		level.setFont(new Font("Serif", Font.BOLD, 22));
		level.setBounds(90, 65, 150, 45);
		level.setForeground(Color.BLACK);
		level.setBackground(new Color(150, 150, 150));
		level.setFocusPainted(false);
		level.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GameWindow.cards.show(GameWindow.contentPane, "levels");
			}
		});
		mainmenu_panel.add(level);


		//High Score Button Configuration
		scores.setFont(new Font("Serif", Font.BOLD, 22));
		scores.setBounds(90, 120, 150, 45);
		scores.setForeground(Color.BLACK);
		scores.setBackground(new Color(150, 150, 150));
		scores.setFocusPainted(false);
		scores.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GameWindow.cards.show(GameWindow.contentPane, "highscore");
			}
		});
		mainmenu_panel.add(scores);


		//Settings Button Configuration
		setting.setFont(new Font("Serif", Font.BOLD, 22));
		setting.setBounds(90, 175, 150, 45);
		setting.setForeground(Color.BLACK);
		setting.setBackground(new Color(150, 150, 150));
		setting.setFocusPainted(false);
		setting.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				GameWindow.cards.show(GameWindow.contentPane, "settings");
			}
		});
		mainmenu_panel.add(setting);


		//Quit Button Configuration
		quit.setFont(new Font("Serif", Font.BOLD, 22));
		quit.setBounds(90, 230, 150, 45);
		quit.setForeground(Color.BLACK);
		quit.setBackground(new Color(150, 150, 150));
		quit.setFocusPainted(false);
		quit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		mainmenu_panel.add(quit);

		GameWindow.contentPane.add(new Levels(), "levels");
		GameWindow.contentPane.add(new Settings(), "settings");
		GameWindow.contentPane.add(new HighScore(), "highscore");
	}
}