package GameSource;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.BorderFactory;

public class Settings extends JLabel
{
	public static boolean blocked;

	private JPanel setting_panel;
	private JLabel boundary;
	private JButton switch_boundary;
	private JButton back_to_menu;

	public Settings()
	{
		blocked=true;

		setting_panel=new JPanel();
		boundary=new JLabel("Set Boundary:");
		switch_boundary=new JButton("Blocked");
		back_to_menu=new JButton("Back");

		createEnvironment();
	}

	private void createEnvironment()
	{
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

		boundary.setBounds(5, 5, 170, 35);
		boundary.setFont(new Font("Segoe Print", Font.BOLD, 23));
		boundary.setForeground(Color.ORANGE);
		boundary.setBackground(new Color(150, 150, 150));

		switch_boundary.setBounds(180, 10, 140, 35);
		switch_boundary.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 0, 0)));
		switch_boundary.setFont(new Font("Segoe Print", Font.BOLD, 22));
		switch_boundary.setForeground(Color.RED);
		switch_boundary.setBackground(Color.BLACK);
		switch_boundary.setFocusPainted(false);
		switch_boundary.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(switch_boundary.getText().equals("Portal"))
				{
					blocked=true;
					switch_boundary.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(255, 0, 0)));
					switch_boundary.setText("Blocked");
					switch_boundary.setForeground(Color.RED);
					switch_boundary.setBackground(Color.BLACK);
					
				}
				else
				{
					blocked=false;
					switch_boundary.setBorder(UIManager.getBorder("Button.border"));
					switch_boundary.setText("Portal");
					switch_boundary.setForeground(new Color(160, 66, 255));
					switch_boundary.setBackground(Color.BLACK);
					
				}
			}
		});

		setting_panel.setLayout(null);
		setting_panel.setBounds(285, 210, 328, 285);
		setting_panel.setBackground(new Color(26, 26, 26));
		setting_panel.add(boundary);
		setting_panel.add(switch_boundary);
		setting_panel.add(back_to_menu);

		this.setIcon(new ImageIcon(getClass().getResource("/imgs/Settings.png")));
		this.setBounds(0, 0, 900, 700);
		this.add(setting_panel);
	}
}