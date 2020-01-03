package GameSource;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class Levels extends JLabel implements ActionListener
{
	public static int speed;
	public static String level;

	private JPanel level_panel;
	private JLabel select;
	private JButton back_to_menu;
	private JRadioButton easy, intermediate, difficult;
	private ButtonGroup toggle;

	public Levels()
	{
		speed=110;
		level="Intermediate";
		
		level_panel=new JPanel();
		select=new JLabel("SELECT LEVEL !!");
		back_to_menu=new JButton("Back");

		toggle=new ButtonGroup();

		easy=new JRadioButton("Easy", false);
		intermediate=new JRadioButton("Intermediate", true);
		difficult=new JRadioButton("Difficult", false);

		createEnvironment();
		
	}
	private void createEnvironment()
	{
		select.setBounds(75, 0, 200, 50);
		select.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		select.setForeground(new Color(150, 150, 150));


		back_to_menu.setBounds(5, 245, 95, 35);
		back_to_menu.setFont(new Font("Segoe Print", Font.BOLD, 24));
		back_to_menu.setForeground(Color.BLACK);
		back_to_menu.setBackground(new Color(150, 150, 150));
		back_to_menu.setFocusPainted(false);
		back_to_menu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GameWindow.cards.show(GameWindow.contentPane, "mainmenu");
			}
		});


		easy.setBounds(75, 60, 80, 35);
		easy.setFont(new Font("Segoe Print", Font.BOLD, 24));
		easy.setForeground(new Color(26, 255, 26));
		easy.setBackground(new Color(26, 26, 26));
		easy.setFocusPainted(false);
		easy.addActionListener(this);


		intermediate.setBounds(75, 120, 200, 35);
		intermediate.setFont(new Font("Segoe Print", Font.BOLD, 24));
		intermediate.setForeground(new Color(179, 0, 179));
		intermediate.setBackground(new Color(26, 26, 26));
		intermediate.setFocusPainted(false);
		intermediate.addActionListener(this);


		difficult.setBounds(75, 180, 150, 35);
		difficult.setFont(new Font("Segoe Print", Font.BOLD, 24));
		difficult.setForeground(new Color(255, 0, 0));
		difficult.setBackground(new Color(26, 26, 26));
		difficult.setFocusPainted(false);
		difficult.addActionListener(this);


		toggle.add(easy);
		toggle.add(intermediate);
		toggle.add(difficult);


		level_panel.setLayout(null);
		level_panel.setBounds(285, 210, 328, 285);
		level_panel.setBackground(new Color(26, 26, 26));
		level_panel.add(select);
		level_panel.add(easy);
		level_panel.add(intermediate);
		level_panel.add(difficult);
		level_panel.add(back_to_menu);

		this.setIcon(new ImageIcon(getClass().getResource("/imgs/Levels.png")));
		this.setBounds(0, 0, 900, 700);
		this.add(level_panel);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		level=e.getActionCommand();

		if(level.equals("Easy"))
		speed=140;
		else if(level.equals("Intermediate"))
		speed=106;
		else
		speed=82;
	}
}