import javax.swing.JButton;
import java.awt.Dimension;


public class ButtonBan extends JButton
{
	int index;

	public ButtonBan(String name, int index)
	{
		this.setText(name);
		this.setFocusable(false);
		this.setPreferredSize(new Dimension(80, 30));
		this.index = index;
	}

	public int getIndex()
	{
		return index;
	}
}