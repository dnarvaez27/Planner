package registro.threads;

import registro.interfaz.InterfazPlanner;

public class ThreadCarreras extends Thread
{
	private InterfazPlanner interfaz;

	public ThreadCarreras( InterfazPlanner interfaz )
	{
		this.interfaz = interfaz;
	}

	public void run( )
	{
		interfaz.showLoadingCarreras( true );

		try
		{
			interfaz.actualizarCarreras( );
			interfaz.showLoadingCarreras( false );
		}
		catch( Exception e )
		{
			interfaz.error404( );
			e.printStackTrace( );
		}
	}
}