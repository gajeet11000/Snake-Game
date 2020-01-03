import GameSource.GameWindow;
import javax.swing.SwingUtilities;
public class Main
{
	public static void main(String[] args)
	{
		try
		{
			SwingUtilities.invokeAndWait(new Runnable()
			{
				public void run()
				{
					new GameWindow();
				}
			});
		}
		catch(Exception e){}
	}
}