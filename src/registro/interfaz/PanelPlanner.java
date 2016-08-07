package registro.interfaz;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.dnarvaez27.horario.ITask;
import com.dnarvaez27.horario.PanelHorario;

import java.awt.BorderLayout;
import java.awt.Color;

import registro.mundo.Materia;
import registro.utilidades.Constantes.Colores;

public class PanelPlanner extends JPanel
{
	private static final long serialVersionUID = 3156696078195967715L;

	private PanelHorario panelHorario;

	public PanelPlanner( )
	{
		setLayout( new BorderLayout( ) );
		// setPreferredSize( new Dimension( 200, 0 ) );

		panelHorario = new PanelHorario( false, true, 6, 22 );
		Color fg = new Color( 33, 33, 33 );
		Color bg = Colores.COLOR_LOADER;
		Color label = fg;
		Color lines = bg.darker( );
		panelHorario.setColors( bg, fg, label, lines );
		panelHorario.setBorderToolTip( BorderFactory.createLineBorder( new Color( 33, 33, 33 ) ) );

		add( panelHorario );
	}

	public void addTask( ITask iTask )
	{
		try
		{
			panelHorario.addTask( iTask );
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace( );
		}
	}

	public void removeTask( ITask iTask )
	{
		panelHorario.removeTask( iTask );
	}

	public void actualizar( List<Materia> materias )
	{
		for( Materia materia : materias )
		{
			try
			{
				panelHorario.addTask( materia );
			}
			catch( IllegalAccessException e )
			{
				e.printStackTrace( );
			}
		}
	}
}