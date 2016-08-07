package registro.interfaz;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import registro.threads.ThreadCarreras;
import registro.mundo.Carrera;
import registro.mundo.Materia;
import registro.mundo.Planner;
import registro.utilidades.Constantes.Colores;
import registro.utilidades.Constantes.Imagenes;

public class InterfazPlanner extends JFrame
{
	private static final long serialVersionUID = 7812556408365934279L;

	private PanelCarreras panelCarreras;

	private PanelMaterias panelMaterias;

	private PanelPlanner panelPlanner;

	private Planner planner;

	public InterfazPlanner( )
	{
		setLayout( new BorderLayout( ) );
		setBackground( Colores.COLOR_LOADER );

		planner = new Planner( );

		panelMaterias = new PanelMaterias( this );

		panelPlanner = new PanelPlanner( );

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
		if( !planner.agregarMateria( materia ) )
		{
			panelPlanner.removeTask( materia );
			planner.eliminarMateria( materia );
		}
		else
		{
			panelPlanner.addTask( materia );
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

	public void showLoadingCarreras( boolean state )
	{
		panelCarreras.loading( state );
	}

	public static void main( String[ ] args )
	{
		new InterfazPlanner( );
	}
}
