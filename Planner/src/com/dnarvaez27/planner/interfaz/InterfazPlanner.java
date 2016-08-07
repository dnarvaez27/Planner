package com.dnarvaez27.planner.interfaz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.dnarvaez27.planner.mundo.Carrera;
import com.dnarvaez27.planner.mundo.Materia;
import com.dnarvaez27.planner.mundo.Planner;
import com.dnarvaez27.planner.threads.ThreadCarreras;
import com.dnarvaez27.planner.utilidades.Constantes.Colores;
import com.dnarvaez27.planner.utilidades.Constantes.Imagenes;
import com.dnarvaez27.planner.utilidades.Constantes.Strings;

import java.awt.BorderLayout;

public class InterfazPlanner extends JFrame
{
	private static final long serialVersionUID = 7812556408365934279L;

	private PanelCarreras panelCarreras;

	private PanelMaterias panelMaterias;

	private PanelPlanner panelPlanner;

	private Planner planner;

	public InterfazPlanner( )
	{
		setTitle( Strings.PLANNER );
		setIconImage( Imagenes.ICONO.getImage( ) );
		setLayout( new BorderLayout( ) );
		setBackground( Colores.COLOR_LOADER );

		planner = new Planner( );

		panelMaterias = new PanelMaterias( this );

		panelPlanner = new PanelPlanner( this );

		panelCarreras = new PanelCarreras( this );

		add( panelCarreras, BorderLayout.WEST );
		add( panelMaterias, BorderLayout.CENTER );
		add( panelPlanner, BorderLayout.EAST );

		setSize( 1000, 500 );
		setExtendedState( MAXIMIZED_BOTH );
		setLocationRelativeTo( null );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setVisible( true );

		ThreadCarreras threadCarreras = new ThreadCarreras( this );
		threadCarreras.start( );
	}

	public void actionMateria( Materia materia )
	{
		if( materia.getCalendarEvents( ) != null )
		{
			if( !planner.agregarMateria( materia ) )
			{
				panelPlanner.removeTask( materia );
				planner.eliminarMateria( materia );
			}
			else
			{
				panelPlanner.addTask( materia );
			}
		}
		// panelPlanner.actualizar( planner.getMaterias( ) );
	}

	public void actualizarCarreras( ) throws Exception
	{
		try
		{
			planner.getRecuperacion( ).iniciarCarreras( );
			panelCarreras.actualizar( );
			// TODO
		}
		catch( Exception e )
		{
			throw e;
		}
	}

	public void actualizarMaterias( Carrera carrera )
	{
		panelMaterias.actualizar( carrera );
	}

	public void error404( )
	{
		panelMaterias.setImage( Imagenes.ERROR_404 );
	}

	public List<Carrera> getCarreras( )
	{
		return planner.getRecuperacion( ).getCarreras( );
	}

	public List<Materia> getMaterias( Carrera carrera )
	{
		try
		{
			planner.getRecuperacion( ).getMaterias( carrera );
			return carrera.getMaterias( );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
		return new LinkedList<>( );
	}

	public ArrayList<Materia> getMateriasPlanner( )
	{
		return panelPlanner.getSelectedMaterias( );
	}

	public ArrayList<Materia> getMateriasPlannerByCarrera( Carrera carrera )
	{
		ArrayList<Materia> materiasByCarrera = new ArrayList<>( );
		for( Materia m : panelPlanner.getSelectedMaterias( ) )
		{
			if( m.getCarrera( ).equals( carrera ) )
			{
				materiasByCarrera.add( m );
			}
		}
		return materiasByCarrera;
	}

	public PanelCarreras getPanelCarreras( )
	{
		return panelCarreras;
	}

	public PanelMaterias getPanelMaterias( )
	{
		return panelMaterias;
	}

	public void setMateriaImage( ImageIcon image )
	{
		panelMaterias.setImage( image );
	}

	public void showLoaderMaterias( boolean state )
	{
		panelMaterias.showLoader( state );
	}

	public void showLoadingCarreras( boolean state )
	{
		panelCarreras.loading( state );
	}

	public static void main( String[ ] args )
	{
		new InterfazPlanner( );
	}
}
