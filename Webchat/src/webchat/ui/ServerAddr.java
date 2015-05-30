package webchat.ui;

public class ServerAddr 
{
	ServScene scene;
	String address;
	public ServerAddr (ServScene scene1)
	{
		scene= scene1;
	}
	
	public void getAddress()
	{
		address = scene.ipBox.getText();
	}
	
	public boolean infoCheck()
	{
		if (!scene.ipBox.getText().equals(""))
		{
			scene.warning.setVisible(false);
			return true;
		}
		else
		{
			scene.warning.setVisible(true);
			return false;
		}
	}
}
