package com.dnarvaez27.planner.interfaz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.dnarvaez27.componentes.ButtonTip;
import com.dnarvaez27.componentes.Popup;
import com.dnarvaez27.componentes.ScrollColor;
import com.dnarvaez27.horario.PanelHorario;
import com.dnarvaez27.horario.PanelHorario.TaskButton;
import com.dnarvaez27.planner.mundo.Materia;
import com.dnarvaez27.planner.utilidades.Constantes.Colores;
import com.dnarvaez27.planner.utilidades.Constantes.Imagenes;
import com.dnarvaez27.planner.utilidades.Constantes.Strings;
import com.dnarvaez27.planner.utilidades.Utilidades;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PanelPlanner extends JPanel implements ActionListener, MouseListener
{
	public class PanelInfo extends JPanel
	{
		private static final long serialVersionUID = 2912680317135361462L;

		private JLabel cod;

		private JLabel creditos;

		private JLabel crn;

		private JLabel cupos;

		private JLabel dias;

		private JLabel disponibles;

		private JLabel hora;

		private JLabel lblCreditos;

		private JLabel lblCrn;

		private JLabel lblCupos;

		private JLabel lblDias;

		private JLabel lblDisponibles;

		private JLabel lblHora;

		private JLabel lblProfesores;

		private JLabel lblSalon;

		private JLabel nombre;

		private JLabel profesores;

		private JLabel salon;

		private JLabel seccion;

		public PanelInfo( Materia materia )
		{
			setLayout( new GridBagLayout( ) );
			setBackground( Colores.COLOR_LOADER.darker( ) );

			cod = new JLabel( materia != null ? materia.getCod( ) : " " );

			crn = new JLabel( materia != null ? materia.getCrn( ) : " " );

			creditos = new JLabel( materia != null ? String.valueOf( materia.getCreditos( ) ) : " " );

			nombre = new JLabel( materia != null ? materia.getNombre( ) : " " );

			profesores = new JLabel( materia != null ? materia.getProfesores( ).get( 0 ) : " " );

			salon = new JLabel( materia != null ? materia.getSalon( ) : " " );

			seccion = new JLabel( materia != null ? String.valueOf( materia.getSeccion( ) ) : " " );

			cupos = new JLabel( materia != null ? String.valueOf( materia.getCupos( ) ) : " " );

			disponibles = new JLabel( materia != null ? String.valueOf( materia.getDisponibles( ) ) : " " );

			dias = new JLabel( materia != null ? materia.getDias( ) : " " );

			hora = new JLabel( materia != null ? materia.getHoraString( ) : " " );

			lblCreditos = new JLabel( Strings.CREDITOS );

			lblCrn = new JLabel( Strings.CRN );

			lblSalon = new JLabel( Strings.SALON );

			lblCupos = new JLabel( Strings.CUPOS );

			lblDisponibles = new JLabel( Strings.DISPONIBLES );

			lblDias = new JLabel( Strings.DIAS );

			lblHora = new JLabel( Strings.HORAS );

			lblProfesores = new JLabel( Strings.PROFESORES );

			GridBagConstraints constraints = new GridBagConstraints( );

			JPanel panelSuperior = new JPanel( );
			panelSuperior.setBackground( null );
			panelSuperior.setLayout( new FlowLayout( FlowLayout.CENTER ) );
			panelSuperior.add( cod );
			panelSuperior.add( nombre );
			panelSuperior.add( seccion );

			constraints.gridx = 0; // El área de texto empieza en la columna cero.
			constraints.gridy = 0; // El área de texto empieza en la fila cero
			constraints.gridwidth = 2; // El área de texto ocupa dos columnas.
			constraints.gridheight = 1; // El área de texto ocupa 1 filas.
			constraints.weighty = 1.0;
			constraints.weightx = 1.0;
			constraints.insets = new Insets( 2, 10, 2, 10 );
			add( panelSuperior, constraints );

			constraints.gridx = 0;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 0.0;
			add( lblCreditos, constraints );

			constraints.gridx = 1;
			constraints.gridy = 1;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1.0;
			add( creditos, constraints );

			constraints.gridx = 0;
			constraints.gridy = 2;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 0.0;
			add( lblCrn, constraints );

			constraints.gridx = 1;
			constraints.gridy = 2;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1.0;
			add( crn, constraints );

			constraints.gridx = 0;
			constraints.gridy = 3;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 0.0;
			add( lblSalon, constraints );

			constraints.gridx = 1;
			constraints.gridy = 3;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1.0;
			add( salon, constraints );

			constraints.gridx = 0;
			constraints.gridy = 4;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 0.0;
			add( lblCupos, constraints );

			constraints.gridx = 1;
			constraints.gridy = 4;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1.0;
			add( cupos, constraints );

			constraints.gridx = 0;
			constraints.gridy = 5;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 0.0;
			add( lblDisponibles, constraints );

			constraints.gridx = 1;
			constraints.gridy = 5;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1.0;
			add( disponibles, constraints );

			constraints.gridx = 0;
			constraints.gridy = 6;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 0.0;
			add( lblDias, constraints );

			constraints.gridx = 1;
			constraints.gridy = 6;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1.0;
			add( dias, constraints );

			constraints.gridx = 0;
			constraints.gridy = 7;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 0.0;
			add( lblHora, constraints );

			constraints.gridx = 1;
			constraints.gridy = 7;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1.0;
			add( hora, constraints );

			constraints.gridx = 0;
			constraints.gridy = 8;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 0.0;
			add( lblProfesores, constraints );

			constraints.gridx = 1;
			constraints.gridy = 8;
			constraints.gridwidth = 1;
			constraints.gridheight = 1;
			constraints.anchor = GridBagConstraints.WEST;
			constraints.weightx = 1.0;
			add( profesores, constraints );

		}

		public void actualizar( Materia materia )
		{
			if( materia != null )
			{
				boolean isDark = Utilidades.isColorDark( materia.getColor( ) );
				if( isDark )
				{
					cod.setForeground( Color.WHITE );
					crn.setForeground( Color.WHITE );
					creditos.setForeground( Color.WHITE );
					nombre.setForeground( Color.WHITE );
					profesores.setForeground( Color.WHITE );
					salon.setForeground( Color.WHITE );
					seccion.setForeground( Color.WHITE );
					cupos.setForeground( Color.WHITE );
					disponibles.setForeground( Color.WHITE );
					dias.setForeground( Color.WHITE );
					hora.setForeground( Color.WHITE );

					lblCrn.setForeground( Color.WHITE );
					lblCreditos.setForeground( Color.WHITE );
					lblProfesores.setForeground( Color.WHITE );
					lblSalon.setForeground( Color.WHITE );
					lblCupos.setForeground( Color.WHITE );
					lblDisponibles.setForeground( Color.WHITE );
					lblDias.setForeground( Color.WHITE );
					lblHora.setForeground( Color.WHITE );
				}
				else
				{
					cod.setForeground( Color.BLACK );
					crn.setForeground( Color.BLACK );
					creditos.setForeground( Color.BLACK );
					nombre.setForeground( Color.BLACK );
					profesores.setForeground( Color.BLACK );
					salon.setForeground( Color.BLACK );
					seccion.setForeground( Color.BLACK );
					cupos.setForeground( Color.BLACK );
					disponibles.setForeground( Color.BLACK );
					dias.setForeground( Color.BLACK );
					hora.setForeground( Color.BLACK );

					lblCrn.setForeground( Color.BLACK );
					lblCreditos.setForeground( Color.BLACK );
					lblProfesores.setForeground( Color.BLACK );
					lblSalon.setForeground( Color.BLACK );
					lblCupos.setForeground( Color.BLACK );
					lblDisponibles.setForeground( Color.BLACK );
					lblDias.setForeground( Color.BLACK );
					lblHora.setForeground( Color.BLACK );
				}
			}

			setBackground( materia != null ? materia.getColor( ) : Colores.COLOR_LOADER.darker( ) );

			cod.setText( materia != null ? materia.getCod( ) + " - " : "" );

			crn.setText( materia != null ? materia.getCrn( ) : "" );

			creditos.setText( materia != null ? String.valueOf( materia.getCreditos( ) ) : "" );

			nombre.setText( materia != null ? materia.getNombre( ) : "" );

			salon.setText( materia != null ? materia.getSalon( ) : "" );

			seccion.setText( materia != null ? "[ " + String.valueOf( materia.getSeccion( ) + " ]" ) : "" );

			cupos.setText( materia != null ? String.valueOf( materia.getCupos( ) ) : "" );

			disponibles.setText( materia != null ? String.valueOf( materia.getDisponibles( ) ) : "" );
			if( ( materia != null ) && ( materia.getDisponibles( ) <= 0 ) )
			{
				disponibles.setForeground( Color.RED );
			}
			else
			{
				disponibles.setForeground( cupos.getForeground( ) );
			}

			if( materia != null )
			{
				ArrayList<String> full_dias = new ArrayList<>( Arrays.asList( "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" ) );
				ArrayList<String> start_dias = new ArrayList<>( Arrays.asList( "D", "L", "M", "I", "J", "V", "S" ) );

				String[ ] matDias = materia.getDias( ).split( "" );
				String compStringDias = "";
				for( int i = 0; i < matDias.length; i++ )
				{
					if( !matDias[ i ].trim( ).replace( " ", "" ).isEmpty( ) )
					{
						compStringDias += full_dias.get( start_dias.indexOf( matDias[ i ] ) );
						if( ( i + 1 ) < matDias.length )
						{
							compStringDias += ", ";
						}
					}
				}
				dias.setText( compStringDias );
			}
			else
			{
				dias.setText( "" );
			}

			hora.setText( materia != null ? materia.getHoraString( ) : "" );

			if( ( materia != null ) && ( materia.getProfesores( ).size( ) > 0 ) )
			{
				String profe = "<html>";
				int size = materia.getProfesores( ).size( );
				for( int j = 0; j < size; j++ )
				{
					profe += materia.getProfesores( ).get( j );
					if( ( j + 1 ) < size )
					{
						profe += "<br>";
					}
				}
				profe += "</html>";
				profesores.setText( profe );
			}
			else
			{
				profesores.setText( "" );
			}
		}
	}

	private static final long serialVersionUID = 3156696078195967715L;

	private InterfazPlanner interfazPlanner;

	private ArrayList<Materia> iTasks;

	private Materia materia;

	private PanelHorario panelHorario;

	private PanelInfo panelInfo;

	private Popup popup;

	private ScrollColor scroll;

	public PanelPlanner( InterfazPlanner interfazPlanner )
	{
		this.interfazPlanner = interfazPlanner;

		setLayout( new BorderLayout( ) );
		// setPreferredSize( new Dimension( 200, 0 ) );

		panelHorario = new PanelHorario( false, true, 6, 22 );
		Color fg = new Color( 33, 33, 33 );
		Color bg = Colores.COLOR_LOADER;
		Color label = fg;
		Color lines = bg.darker( );
		panelHorario.setColors( bg, fg, label, lines );
		panelHorario.setBorderToolTip( BorderFactory.createLineBorder( new Color( 33, 33, 33 ) ) );
		panelHorario.setActionListener( this );
		panelHorario.setMouseListener( this );
		panelHorario.addMouseListener( this );

		iTasks = new ArrayList<>( );

		scroll = new ScrollColor( panelHorario, 2, bg, bg.darker( ) );

		panelInfo = new PanelInfo( null );

		add( scroll, BorderLayout.CENTER );
		add( panelInfo, BorderLayout.SOUTH );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource( ) instanceof TaskButton )
		{
			materia = ( Materia ) ( ( TaskButton ) e.getSource( ) ).getiTask( );
			panelInfo.actualizar( materia );
		}
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

	public void addTask( Materia iTask )
	{
		try
		{
			if( ( iTask != null ) && ( iTask.getIni( ) != null ) && ( iTask.getCalendarEvents( ) != null ) )
			{
				panelHorario.addTask( iTask );
				iTasks.add( iTask );
			}
		}
		catch( IllegalAccessException e )
		{
			e.printStackTrace( );
		}
	}

	public ArrayList<Materia> getSelectedMaterias( )
	{
		return iTasks;
	}

	@Override
	public void mouseClicked( MouseEvent e )
	{
		if( e.getButton( ) == MouseEvent.BUTTON3 )
		{
			if( e.getSource( ) instanceof TaskButton )
			{
				TaskButton taskButton = ( TaskButton ) e.getSource( );
				Materia materia = ( Materia ) taskButton.getiTask( );
				if( materia != null )
				{
					if( ( popup != null ) && popup.isShown( ) )
					{
						popup.hide( );
					}

					Border line = BorderFactory.createLineBorder( Colores.GRIS );
					Border empty = BorderFactory.createEmptyBorder( 5, 5, 5, 5 );

					JPanel panelPopup = new JPanel( );
					panelPopup.setLayout( new BoxLayout( panelPopup, BoxLayout.PAGE_AXIS ) );
					panelPopup.setBackground( materia.getColor( ) );
					panelPopup.setBorder( BorderFactory.createCompoundBorder( line, empty ) );

					ButtonTip color = new ButtonTip( Imagenes.COLOR );
					color.setText( Strings.COLOR );
					color.setBackground( null );
					color.addActionListener( new ActionListener( )
					{
						@Override
						public void actionPerformed( ActionEvent e )
						{
							popup.hide( );
							Color col = JColorChooser.showDialog( color, "Elige un color", materia.getColor( ) );
							if( col != null )
							{
								materia.setColor( col );
								try
								{
									panelHorario.removeTask( materia );
									panelHorario.addTask( materia );
								}
								catch( IllegalAccessException e1 )
								{
									e1.printStackTrace( );
								}
								panelInfo.actualizar( materia );
								interfazPlanner.getPanelMaterias( ).setSelected( materia, true, false );
							}
						}
					} );

					ButtonTip remove = new ButtonTip( Imagenes.REMOVE_WHITE );
					remove.setText( Strings.ELIMINAR );
					remove.setBackground( null );
					remove.addActionListener( new ActionListener( )
					{
						@Override
						public void actionPerformed( ActionEvent e )
						{
							popup.hide( );

							panelHorario.removeTask( materia );
							iTasks.remove( materia );
							interfazPlanner.getPanelMaterias( ).setSelected( materia, false, true );
							panelInfo.actualizar( null );
						}
					} );

					if( Utilidades.isColorDark( materia.getColor( ) ) )
					{
						remove.setForeground( Color.WHITE );
						color.setForeground( Color.WHITE );
					}
					else
					{
						remove.setForeground( Color.BLACK );
						color.setForeground( Color.BLACK );
					}

					panelPopup.add( color );
					panelPopup.add( remove );

					popup = new Popup( this, panelPopup, e.getXOnScreen( ), e.getYOnScreen( ) );
					popup.show( );
				}
			}
			else
			{
				if( ( popup != null ) && popup.isShown( ) )
				{
					popup.hide( );
				}
			}
		}
		else
		{
			if( ( popup != null ) && popup.isShown( ) )
			{
				popup.hide( );
			}
		}

	}

	@Override
	public void mouseEntered( MouseEvent e )
	{
	}

	@Override
	public void mouseExited( MouseEvent e )
	{
	}

	@Override
	public void mousePressed( MouseEvent e )
	{
	}

	@Override
	public void mouseReleased( MouseEvent e )
	{
	}

	public void removeTask( Materia iTask )
	{
		panelHorario.removeTask( iTask );
		iTasks.remove( iTask );
	}
}