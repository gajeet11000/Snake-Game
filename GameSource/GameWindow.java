package GameSource;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameWindow
{
	public static CardLayout cards;
	public static JPanel contentPane;
	public static JFrame window;

	public GameWindow()
	{
		contentPane=new JPanel();
		cards=new CardLayout();

		contentPane.setLayout(cards);

		contentPane.add(new MainMenu(), "mainmenu");
		cards.show(contentPane, "mainmenu");

		EventQueue.invokeLater(() ->
		{
            window=new JFrame("Snake Game");
            window.setBounds(350, 70, 905, 700);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.add(contentPane);
			window.setResizable(false);
			window.setVisible(true);
        });
	}
}