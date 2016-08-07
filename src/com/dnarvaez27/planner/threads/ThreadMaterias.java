package com.dnarvaez27.planner.threads;

import com.dnarvaez27.planner.interfaz.InterfazPlanner;
import com.dnarvaez27.planner.mundo.Carrera;
import com.dnarvaez27.planner.utilidades.Utilidades;

import java.awt.Cursor;

public class ThreadMaterias extends Thread
{
	private Carrera carrera;

	private InterfazPlanner interfaz;

	private boolean recuperar;

	public ThreadMaterias( InterfazPlanner interfaz, Carrera sel )
	{
		super( );
		this.interfaz = interfaz;
		carrera = sel;
	}

	public ThreadMaterias( InterfazPlanner interfaz, Carrera carrera, boolean recuperar )
	{
		super( );
		this.interfaz = interfaz;
		this.carrera = carrera;
		this.recuperar = recuperar;
	}

	@Override
	public void run( )
	{
		if( carrera != null )
		{
			// Cursor cust = Toolkit.getDefaultToolkit( ).createCustomCursor( Imagenes.LOADER_SMALL.getImage( ), new Point( 2, 2 ), "CustomCursor" );
			if( Utilidades.isConnected( ) )
			{
				interfaz.getPanelMaterias( ).getBusqueda( ).setText( "" );
				interfaz.getPanelMaterias( ).getBusqueda( ).setEditable( false );
				interfaz.showLoaderMaterias( true );
			}
			else
			{
				interfaz.showLoaderMaterias( true );
				interfaz.error404( );
			}
			interfaz.setCursor( new Cursor( Cursor.WAIT_CURSOR ) );
			if( recuperar || ( carrera.getMaterias( ).size( ) == 0 ) )
			{
				interfaz.getMaterias( carrera );
			}

			interfaz.actualizarMaterias( carrera );
			interfaz.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
			interfaz.getPanelMaterias( ).getBusqueda( ).setEditable( true );
			interfaz.showLoaderMaterias( false );

			interfaz.getPanelCarreras( ).getListaCarreras( ).revalidate( );
			interfaz.getPanelCarreras( ).getListaCarreras( ).repaint( );
		}
	}
}