package GameSource;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

public class HighScore extends JLabel implements ActionListener
{
	private static String[] scores;
	private static JCheckBox easyblocked, intermediateblocked, difficultblocked;
	private static JCheckBox easyportal, intermediateportal, difficultportal;

	private JPanel highscore_panel;
	private JLabel heading;
	private JButton back_to_menu, reset;

	public HighScore()
	{
		highscore_panel=new JPanel();
		heading=new JLabel("RECORDS");

		easyblocked=new JCheckBox();
		intermediateblocked=new JCheckBox();
		difficultblocked=new JCheckBox();

		easyportal=new JCheckBox();
		intermediateportal=new JCheckBox();
		difficultportal=new JCheckBox();

		reset=new JButton("Reset");
		back_to_menu=new JButton("Back");

		createEnvironment();

		this.setIcon(new ImageIcon(getClass().getResource("/imgs/HighScore.png")));
		this.setBounds(0, 0, 900, 700);
		this.add(highscore_panel);
		this.add(back_to_menu);
		this.add(reset);
	}

	private void createEnvironment()
	{
		readData();

		back_to_menu.setBounds(200, 500, 95, 35);
		back_to_menu.setFont(new Font("Segoe Print", Font.BOLD, 24));
		back_to_menu.setForeground(Color.ORANGE);
		back_to_menu.setBackground(Color.BLACK);
		back_to_menu.setFocusPainted(false);
		back_to_menu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GameWindow.cards.show(GameWindow.contentPane, "mainmenu");
			}
		});

		reset.setBounds(300, 500, 100, 35);
		reset.setFont(new Font("Segoe Print", Font.BOLD, 24));
		reset.setForeground(Color.ORANGE);
		reset.setBackground(Color.BLACK);
		reset.setFocusPainted(false);
		reset.addActionListener(this);

		heading.setBounds(190, 0, 200, 35);
		heading.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		heading.setForeground(new Color(150, 150, 150));

		easyblocked.setText("Easy (blocked): "+scores[0]);
		easyblocked.setBounds(10, 40, 450, 35);
		easyblocked.setFont(new Font("Segoe Print", Font.BOLD, 22));
		easyblocked.setForeground(new Color(26, 255, 26));
		easyblocked.setBackground(new Color(26, 26, 26));
		easyblocked.setFocusPainted(false);

		easyportal.setText("Easy (portal): "+scores[3]);
		easyportal.setBounds(10, 70, 450, 35);
		easyportal.setFont(new Font("Segoe Print", Font.BOLD, 22));
		easyportal.setForeground(new Color(26, 255, 26));
		easyportal.setBackground(new Color(26, 26, 26));
		easyportal.setFocusPainted(false);

		intermediateblocked.setText("Intermediate (blocked): "+scores[1]);
		intermediateblocked.setBounds(10, 120, 450, 35);
		intermediateblocked.setFont(new Font("Segoe Print", Font.BOLD, 22));
		intermediateblocked.setForeground(new Color(179, 0, 179));
		intermediateblocked.setBackground(new Color(26, 26, 26));
		intermediateblocked.setFocusPainted(false);

		intermediateportal.setText("Intermediate (portal): "+scores[4]);
		intermediateportal.setBounds(10, 150, 450, 35);
		intermediateportal.setFont(new Font("Segoe Print", Font.BOLD, 22));
		intermediateportal.setForeground(new Color(179, 0, 179));
		intermediateportal.setBackground(new Color(26, 26, 26));
		intermediateportal.setFocusPainted(false);

		difficultblocked.setText("Difficult (blocked): "+scores[2]);
		difficultblocked.setBounds(10, 200, 450, 35);
		difficultblocked.setFont(new Font("Segoe Print", Font.BOLD, 22));
		difficultblocked.setForeground(new Color(255, 0, 0));
		difficultblocked.setBackground(new Color(26, 26, 26));
		difficultblocked.setFocusPainted(false);

		difficultportal.setText("Difficult (portal): "+scores[5]);
		difficultportal.setBounds(10, 230, 450, 35);
		difficultportal.setFont(new Font("Segoe Print", Font.BOLD, 22));
		difficultportal.setForeground(new Color(255, 0, 0));
		difficultportal.setBackground(new Color(26, 26, 26));
		difficultportal.setFocusPainted(false);

		highscore_panel.setLayout(null);
		highscore_panel.setBounds(200, 210, 500, 285);
		highscore_panel.setBackground(new Color(26, 26, 26));
		highscore_panel.add(heading);
		highscore_panel.add(easyblocked);
		highscore_panel.add(easyportal);
		highscore_panel.add(intermediateblocked);
		highscore_panel.add(intermediateportal);
		highscore_panel.add(difficultblocked);
		highscore_panel.add(difficultportal);
	}

	private void readData()
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("data\\highscores.txt"));
			scores=br.readLine().split(" ");
			br.close();
		}
		catch(Exception e){}
	}

	public static void updateData()
	{
		try
		{
			PrintWriter w=new PrintWriter(new File("data\\highscores.txt"));

			if(Settings.blocked)
			{
				if(Levels.level.equals("Easy") && PlayGame.score>Integer.parseInt(scores[0]))
				{
					scores[0]=PlayGame.score+"";
					easyblocked.setText("Easy (blocked): "+scores[0]);
				}

				else if(Levels.level.equals("Intermediate") && PlayGame.score>Integer.parseInt(scores[1]))
				{
					scores[1]=PlayGame.score+"";
					intermediateblocked.setText("Intermediate (blocked): "+scores[1]);
				}

				else if(Levels.level.equals("Difficult") && PlayGame.score>Integer.parseInt(scores[2]))
				{
					scores[2]=PlayGame.score+"";
					difficultblocked.setText("Difficult (blocked): "+scores[2]);
				}

				w.print(scores[0]+" "+scores[1]+" "+scores[2]+" "+scores[3]+" "+scores[4]+" "+scores[5]);
			}
			else
			{
				if(Levels.level.equals("Easy") && PlayGame.score>Integer.parseInt(scores[3]))
				{
					scores[3]=PlayGame.score+"";
					easyportal.setText("Easy (portal): "+scores[3]);
				}

				else if(Levels.level.equals("Intermediate") && PlayGame.score>Integer.parseInt(scores[4]))
				{
					scores[4]=PlayGame.score+"";
					intermediateportal.setText("Intermediate (portal): "+scores[4]);
				}

				else if(Levels.level.equals("Difficult") && PlayGame.score>Integer.parseInt(scores[5]))
				{
					scores[5]=PlayGame.score+"";
					difficultportal.setText("Difficult (portal): "+scores[5]);
				}

				w.print(scores[0]+" "+scores[1]+" "+scores[2]+" "+scores[3]+" "+scores[4]+" "+scores[5]);
			}

			w.close();
		}
		catch(Exception e){}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			readData();

			PrintWriter w=new PrintWriter(new File("data\\highscores.txt"));
			if(easyblocked.isSelected())
			{
				scores[0]=0+"";
				easyblocked.setText("Easy (blocked): "+scores[0]);
				easyblocked.setSelected(false);
			}
			if(intermediateblocked.isSelected())
			{
				scores[1]=0+"";
				intermediateblocked.setText("Intermediate (blocked): "+scores[1]);
				intermediateblocked.setSelected(false);
			}
			if(difficultblocked.isSelected())
			{
				scores[2]=0+"";
				difficultblocked.setText("Difficult (blocked): "+scores[2]);
				difficultblocked.setSelected(false);
			}
			if(easyportal.isSelected())
			{
				scores[3]=0+"";
				easyportal.setText("Easy (portal): "+scores[3]);
				easyportal.setSelected(false);
			}
			if(intermediateportal.isSelected())
			{
				scores[4]=0+"";
				intermediateportal.setText("Intermediate (portal): "+scores[4]);
				intermediateportal.setSelected(false);
			}
			if(difficultportal.isSelected())
			{
				scores[5]=0+"";
				difficultportal.setText("Difficult (portal): "+scores[5]);
				difficultportal.setSelected(false);
			}
			w.print(scores[0]+" "+scores[1]+" "+scores[2]+" "+scores[3]+" "+scores[4]+" "+scores[5]);
			w.close();
		}
		catch(Exception ex){}
	}
}