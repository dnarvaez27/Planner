package com.dnarvaez27.planner.threads;

import com.dnarvaez27.planner.interfaz.InterfazPlanner;

public class ThreadCarreras extends Thread
{
	private InterfazPlanner interfaz;

	public ThreadCarreras( InterfazPlanner interfaz )
	{
		this.interfaz = interfaz;
	}

	@Override
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