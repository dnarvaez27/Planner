package com.dnarvaez27.planner.interfaz;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.dnarvaez27.componentes.ButtonTip;
import com.dnarvaez27.componentes.ScrollColor;
import com.dnarvaez27.planner.mundo.Carrera;
import com.dnarvaez27.planner.mundo.Materia;
import com.dnarvaez27.planner.threads.ThreadMaterias;
import com.dnarvaez27.planner.utilidades.Constantes.Colores;
import com.dnarvaez27.planner.utilidades.Constantes.Imagenes;
import com.dnarvaez27.planner.utilidades.Constantes.Media;
import com.dnarvaez27.planner.utilidades.Constantes.Strings;
import com.dnarvaez27.planner.utilidades.Utilidades;
import com.dnarvaez27.time_listener.TimeEventListener;
import com.dnarvaez27.time_listener.TimeListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMaterias extends JPanel implements KeyListener
{
	private class FadeOut implements TimeEventListener
	{
		private double actual;

		private Color color;

		private final double FACTOR = 0.7;

		private PanelMateria panelMateria;

		public FadeOut( PanelMateria panelMateria )
		{
			this.panelMateria = panelMateria;
		}

		public void fade( Color color )
		{
			this.color = color;

			actual = FACTOR - 0.1;

			TimeListener timeListener = new TimeListener( this, 80 );
			timeListener.start( 7 );
		}

		@Override
		public void intervalReached( )
		{
			int r = color.getRed( );
			int g = color.getGreen( );
			int b = color.getBlue( );
			int alpha = color.getAlpha( );

			int i = ( int ) ( 1.0 / ( 1.0 - FACTOR ) );
			if( ( r == 0 ) && ( g == 0 ) && ( b == 0 ) )
			{
				Color colorTemp = new Color( i, i, i, alpha );
				panelMateria.setBackground( colorTemp );
				return;
			}

			if( ( r > 0 ) && ( r < i ) )
			{
				r = i;
			}
			if( ( g > 0 ) && ( g < i ) )
			{
				g = i;
			}
			if( ( b > 0 ) && ( b < i ) )
			{
				b = i;
			}

			int red = Math.min( ( int ) ( r / ( FACTOR + actual ) ), 255 );
			int green = Math.min( ( int ) ( g / ( FACTOR + actual ) ), 255 );
			int blue = Math.min( ( int ) ( b / ( FACTOR + actual ) ), 255 );

			Color colorTemp = new Color( red, green, blue, alpha );
			panelMateria.setBackground( colorTemp );

			actual -= 0.1;
		}
	}

	private class PanelMateria extends JPanel
	{
		private static final long serialVersionUID = 4464585982988679866L;

		private ButtonTip add;

		private JLabel cod;

		private JLabel creditos;

		private JLabel crn;

		private JLabel cupos;

		private JTextArea descripcion;

		private JLabel dias;

		private JLabel fecha;

		private JLabel horas;

		private JLabel inscritos;

		private JList<String> listaComplementos;

		private Materia materia;

		private JLabel nombre;

		private JPanel panelInferior;

		private JPanel panelListaPM;

		private JPanel panelSuperior;

		private JLabel profesor;

		private JLabel salon;

		private JLabel seccion;

		private boolean selected;

		public PanelMateria( Materia pMateria )
		{
			materia = pMateria;

			setBorder( BorderFactory.createLineBorder( Colores.COLOR_LOADER ) );
			setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS ) );
			setBackground( Color.GRAY );

			cod = new JLabel( materia.getCod( ) );
			cod.setForeground( Color.WHITE );

			seccion = new JLabel( "" + materia.getSeccion( ) );
			seccion.setForeground( Color.WHITE );

			nombre = new JLabel( materia.getNombre( ) );
			nombre.setForeground( Color.WHITE );

			JPanel panelNombre = new JPanel( );
			panelNombre.setBackground( null );
			panelNombre.setLayout( new FlowLayout( FlowLayout.LEFT ) );
			panelNombre.setBorder( BorderFactory.createEmptyBorder( 0, 10, 0, 10 ) );
			panelNombre.setCursor( Media.HAND );

			add = new ButtonTip( Imagenes.CHECK );
			add.setVisible( false );
			add.setBackground( null );
			add.addActionListener( new ActionListener( )
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					setSelected( !selected, true );
				}
			} );

			panelNombre.add( cod );
			panelNombre.add( nombre );
			panelNombre.add( seccion );
			panelNombre.add( add );

			crn = new JLabel( materia.getCrn( ) );
			crn.setForeground( Color.WHITE );

			creditos = new JLabel( "" + materia.getCreditos( ) );
			creditos.setForeground( Color.WHITE );

			cupos = new JLabel( "" + materia.getCupos( ) );
			cupos.setForeground( Color.WHITE );

			inscritos = new JLabel( "" + materia.getInscritos( ) );
			inscritos.setForeground( Color.WHITE );

			panelSuperior = new JPanel( );
			panelSuperior.setBackground( null );
			panelSuperior.setLayout( new FlowLayout( FlowLayout.LEFT ) );
			panelSuperior.setBorder( BorderFactory.createEmptyBorder( 0, 10, 0, 10 ) );
			panelSuperior.setVisible( false );

			panelSuperior.add( crn );
			panelSuperior.add( creditos );
			panelSuperior.add( cupos );
			panelSuperior.add( inscritos );

			panelInferior = new JPanel( );
			panelInferior.setBackground( null );
			panelInferior.setLayout( new FlowLayout( FlowLayout.LEFT ) );
			panelInferior.setBorder( BorderFactory.createEmptyBorder( 0, 10, 0, 10 ) );
			panelInferior.setVisible( false );

			dias = new JLabel( materia.getDias( ) );
			dias.setForeground( Color.WHITE );

			horas = new JLabel( materia.getHoraString( ) );
			horas.setForeground( Color.WHITE );

			salon = new JLabel( materia.getSalon( ) );
			salon.setForeground( Color.WHITE );

			String s = materia.getProfesores( ).size( ) != 0 ? materia.getProfesores( ).get( 0 ) : null;
			profesor = new JLabel( s != null ? s : "Sin Asignar" );
			profesor.setForeground( Color.WHITE );

			fecha = new JLabel( materia.getIniString( ) + " - " + materia.getEndString( ) );
			fecha.setForeground( Color.WHITE );

			panelInferior.add( dias );
			panelInferior.add( horas );
			panelInferior.add( salon );
			panelInferior.add( profesor );
			panelInferior.add( fecha );

			descripcion = new JTextArea( materia.getDescripcion( ) );
			descripcion.setWrapStyleWord( true );
			descripcion.setLineWrap( true );
			descripcion.setEditable( false );
			descripcion.setBackground( Color.GRAY.darker( ) );
			descripcion.setForeground( Color.WHITE );
			descripcion.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
			descripcion.setVisible( false );

			PanelMateria instance = this;

			panelListaPM = new JPanel( );
			panelListaPM.setLayout( new BorderLayout( ) );
			listaComplementos = new JList<>( materia.getComplementos( ).toArray( new String[ 0 ] ) );
			listaComplementos.setCellRenderer( new ListCellRenderer<String>( )
			{
				@Override
				public Component getListCellRendererComponent( JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus )
				{
					if( value != null )
					{
						Border line = BorderFactory.createLineBorder( Color.BLACK );
						Border empty = BorderFactory.createEmptyBorder( 5, 5, 5, 5 );

						JPanel panel = new JPanel( );
						panel.setLayout( new GridBagLayout( ) );
						panel.setBorder( BorderFactory.createCompoundBorder( line, empty ) );

						String[ ] datos = value.split( "-" );

						JLabel labelSuperior = new JLabel( datos[ 1 ] + datos[ 2 ] + " - " + datos[ 4 ] + " [ " + datos[ 3 ] + " ] " );

						JLabel labelInferior = new JLabel( datos[ 0 ] );

						GridBagConstraints constraints = new GridBagConstraints( );
						constraints.gridx = 0;
						constraints.gridy = 0;
						constraints.weightx = 1;
						panel.add( labelSuperior, constraints );

						constraints.gridx = 0;
						constraints.gridy = 1;
						panel.add( labelInferior, constraints );

						if( isSelected )
						{
							panel.setBackground( instance.getBackground( ).darker( ).darker( ) );
							labelSuperior.setForeground( Color.WHITE );
							labelInferior.setForeground( Color.WHITE );
						}

						return panel;
					}
					return null;
				}
			} );
			listaComplementos.addListSelectionListener( new ListSelectionListener( )
			{
				@Override
				public void valueChanged( ListSelectionEvent e )
				{
					if( e.getValueIsAdjusting( ) )
					{
						String selected = listaComplementos.getSelectedValue( );
						String crn = selected.split( "-" )[ 0 ].trim( ).replaceAll( " ", "" );
						showMateria( crn );
					}
				}
			} );
			panelListaPM.add( listaComplementos, BorderLayout.CENTER );
			panelListaPM.setVisible( false );

			panelNombre.addMouseListener( new MouseAdapter( )
			{
				@Override
				public void mouseClicked( MouseEvent e )
				{
					open( );
				}
			} );

			if( Utilidades.isColorDark( materia.getColor( ) ) )
			{
				cod.setForeground( Color.WHITE );
				creditos.setForeground( Color.WHITE );
				crn.setForeground( Color.WHITE );
				cupos.setForeground( Color.WHITE );
				descripcion.setForeground( Color.WHITE );
				dias.setForeground( Color.WHITE );
				fecha.setForeground( Color.WHITE );
				horas.setForeground( Color.WHITE );
				inscritos.setForeground( Color.WHITE );
				nombre.setForeground( Color.WHITE );
				profesor.setForeground( Color.WHITE );
				salon.setForeground( Color.WHITE );
				seccion.setForeground( Color.WHITE );
			}
			else
			{
				cod.setForeground( Color.BLACK );
				creditos.setForeground( Color.BLACK );
				crn.setForeground( Color.BLACK );
				cupos.setForeground( Color.BLACK );
				descripcion.setForeground( Color.BLACK );
				dias.setForeground( Color.BLACK );
				fecha.setForeground( Color.BLACK );
				horas.setForeground( Color.BLACK );
				inscritos.setForeground( Color.BLACK );
				nombre.setForeground( Color.BLACK );
				profesor.setForeground( Color.BLACK );
				salon.setForeground( Color.BLACK );
				seccion.setForeground( Color.BLACK );
			}

			add( panelNombre );
			add( panelSuperior );
			add( panelInferior );
			add( descripcion );
			add( panelListaPM );
		}

		public void open( )
		{
			panelSuperior.setVisible( !panelSuperior.isVisible( ) );
			panelInferior.setVisible( !panelInferior.isVisible( ) );
			add.setVisible( !add.isVisible( ) );
			descripcion.setVisible( !materia.getDescripcion( ).isEmpty( ) && !descripcion.isVisible( ) );
			listaComplementos.setListData( materia != null ? materia.getComplementos( ).toArray( new String[ 0 ] ) : null );
			panelListaPM.setVisible( !materia.getComplementos( ).isEmpty( ) && !panelListaPM.isVisible( ) );
		}

		public void open( boolean open )
		{
			panelSuperior.setVisible( open );
			panelInferior.setVisible( open );
			add.setVisible( open );
			descripcion.setVisible( !materia.getDescripcion( ).isEmpty( ) && open );
			listaComplementos.setListData( materia != null ? materia.getComplementos( ).toArray( new String[ 0 ] ) : null );
			panelListaPM.setVisible( !materia.getComplementos( ).isEmpty( ) && open );
		}

		public void setSelected( boolean selected, boolean changeState )
		{
			if( materia.getCalendarEvents( ) != null )
			{
				this.selected = selected;
				if( changeState )
				{
					interfaz.actionMateria( materia );
				}
				if( selected )
				{
					setBackground( materia.getColor( ) );
					descripcion.setBackground( materia.getColor( ).darker( ) );
					add.setIcon( Imagenes.REMOVE );
				}
				else
				{
					setBackground( Color.GRAY );
					descripcion.setBackground( Color.GRAY.darker( ) );
					add.setIcon( Imagenes.CHECK );
				}

				if( Utilidades.isColorDark( materia.getColor( ) ) )
				{
					cod.setForeground( Color.WHITE );
					creditos.setForeground( Color.WHITE );
					crn.setForeground( Color.WHITE );
					cupos.setForeground( Color.WHITE );
					descripcion.setForeground( Color.WHITE );
					dias.setForeground( Color.WHITE );
					fecha.setForeground( Color.WHITE );
					horas.setForeground( Color.WHITE );
					inscritos.setForeground( Color.WHITE );
					nombre.setForeground( Color.WHITE );
					profesor.setForeground( Color.WHITE );
					salon.setForeground( Color.WHITE );
					seccion.setForeground( Color.WHITE );
				}
				else
				{
					cod.setForeground( Color.BLACK );
					creditos.setForeground( Color.BLACK );
					crn.setForeground( Color.BLACK );
					cupos.setForeground( Color.BLACK );
					descripcion.setForeground( Color.BLACK );
					dias.setForeground( Color.BLACK );
					fecha.setForeground( Color.BLACK );
					horas.setForeground( Color.BLACK );
					inscritos.setForeground( Color.BLACK );
					nombre.setForeground( Color.BLACK );
					profesor.setForeground( Color.BLACK );
					salon.setForeground( Color.BLACK );
					seccion.setForeground( Color.BLACK );
				}
			}
		}
	}

	public class PanelMenu extends JPanel
	{
		private static final long serialVersionUID = 6645872761285433292L;

		private ButtonTip btnLoad;

		private ButtonTip btnSave;

		public PanelMenu( )
		{
			btnSave = new ButtonTip( );
			btnSave.setBackground( null );

			btnLoad = new ButtonTip( );
			btnLoad.setBackground( null );

			add( btnSave );
			add( btnLoad );
		}
	}

	private static final long serialVersionUID = -3945647275543629173L;

	private JTextField busqueda;

	private Carrera carrera;

	private JLabel imagen;

	private InterfazPlanner interfaz;

	private ArrayList<PanelMateria> panelesMateria;

	private JPanel panelLista;

	private JPanel panelLoader;

	private PanelMenu panelMenu;

	private ButtonTip refresh;

	private ScrollColor scroll;

	public PanelMaterias( InterfazPlanner interfaz )
	{
		this.interfaz = interfaz;
		panelesMateria = new ArrayList<>( );

		setLayout( new BorderLayout( ) );
		setBackground( Colores.COLOR_LOADER );

		panelLoader = new JPanel( );
		JLabel loader = new JLabel( Imagenes.LOADER_BOOKS );
		loader.setHorizontalAlignment( SwingConstants.CENTER );
		panelLoader.setBackground( Color.WHITE );
		panelLoader.add( loader );

		panelLista = new JPanel( );
		panelLista.setLayout( new BoxLayout( panelLista, BoxLayout.PAGE_AXIS ) );
		panelLista.setBackground( Colores.COLOR_LOADER.brighter( ) );

		JPanel panelImagen = new JPanel( );
		panelImagen.setBackground( Colores.COLOR_LOADER.brighter( ) );
		panelImagen.setLayout( new BorderLayout( ) );
		imagen = new JLabel( Imagenes.BOOKS );
		panelImagen.add( imagen, BorderLayout.CENTER );

		scroll = new ScrollColor( panelImagen, 2, Color.GRAY, Color.WHITE );

		Border line = BorderFactory.createLineBorder( Color.GRAY.darker( ) );
		Border empt = BorderFactory.createEmptyBorder( 4, 4, 4, 4 );

		busqueda = new JTextField( );
		busqueda.addKeyListener( this );
		busqueda.setBorder( BorderFactory.createCompoundBorder( line, empt ) );
		busqueda.setPreferredSize( new Dimension( 0, 30 ) );
		busqueda.setSelectionColor( Colores.COLOR_LOADER.darker( ).darker( ) );
		busqueda.setSelectedTextColor( Color.WHITE );
		busqueda.setFont( busqueda.getFont( ).deriveFont( Font.BOLD ) );

		refresh = new ButtonTip( Imagenes.REFRESH );
		refresh.setBackground( null );
		refresh.setToolTipText( Strings.REFRESH );
		refresh.cambiarColorTip( Colores.VERDE, Color.WHITE );
		refresh.configurar( BorderFactory.createEmptyBorder( 5, 5, 5, 5 ), CENTER_ALIGNMENT );
		refresh.addActionListener( new ActionListener( )
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				ThreadMaterias threadMaterias = new ThreadMaterias( interfaz, carrera, true );
				threadMaterias.start( );
				// actualizar( carrera );
			}
		} );

		panelMenu = new PanelMenu( );
		panelMenu.setBackground( null );

		JPanel panelSuperior = new JPanel( );
		panelSuperior.setBackground( Color.WHITE );
		panelSuperior.setLayout( new BorderLayout( ) );
		panelSuperior.add( refresh, BorderLayout.WEST );
		panelSuperior.add( busqueda, BorderLayout.CENTER );
		panelSuperior.add( panelMenu, BorderLayout.NORTH );

		add( panelSuperior, BorderLayout.NORTH );
		add( scroll, BorderLayout.CENTER );
	}

	public void actualizar( Carrera carrera )
	{
		this.carrera = carrera;
		busqueda.setText( "" );

		panelLista.removeAll( );

		ArrayList<Materia> materiasByCarrera = interfaz.getMateriasPlannerByCarrera( carrera );

		if( carrera != null )
		{
			for( Materia m : carrera.getMaterias( ) )
			{
				PanelMateria panelMateria = new PanelMateria( m );

				if( materiasByCarrera.contains( m ) )
				{
					panelMateria.setBackground( m.getColor( ) );
					panelMateria.descripcion.setBackground( m.getColor( ).darker( ) );
					panelMateria.add.setIcon( Imagenes.REMOVE );
					panelMateria.selected = true;
				}

				panelesMateria.add( panelMateria );

				panelLista.add( panelMateria );
			}
		}

		scroll.setViewportView( panelLista );
		scroll.getVerticalScrollBar( ).setValue( 0 );

		revalidate( );
		repaint( );
	}

	public JTextField getBusqueda( )
	{
		return busqueda;
	}

	@Override
	public void keyPressed( KeyEvent e )
	{

	}

	@Override
	public void keyReleased( KeyEvent e )
	{
		LinkedList<Materia> materias = new LinkedList<>( );

		char ch = e.getKeyChar( );
		boolean bckSpace = ch == KeyEvent.VK_BACK_SPACE;
		boolean space = ch == KeyEvent.VK_SPACE;
		boolean supr = ch == KeyEvent.VK_DELETE;
		boolean isLetter = Character.isLetter( ch );
		boolean isNumber = Character.isDigit( ch );
		boolean period = ch == KeyEvent.VK_PERIOD;
		if( isNumber || isLetter || bckSpace || space || supr || period )
		{
			for( Materia materia : carrera.getMaterias( ) )
			{
				boolean nombre = materia.getNombre( ).toUpperCase( ).contains( busqueda.getText( ).toUpperCase( ) );
				boolean cod = materia.getCod( ).toUpperCase( ).contains( busqueda.getText( ).toUpperCase( ) );
				if( nombre || cod )
				{
					materias.add( materia );
				}
			}

			panelesMateria.clear( );
			panelLista.removeAll( );

			for( Materia m : materias )
			{
				PanelMateria panelMateria = new PanelMateria( m );
				if( interfaz.getMateriasPlanner( ).contains( m ) )
				{
					panelMateria.setBackground( m.getColor( ) );
					panelMateria.descripcion.setBackground( m.getColor( ).darker( ) );
					panelMateria.add.setIcon( Imagenes.REMOVE );
					panelMateria.selected = true;
				}
				panelesMateria.add( panelMateria );
				panelLista.add( panelMateria );
			}

			scroll.setViewportView( panelLista );
			scroll.getVerticalScrollBar( ).setValue( 0 );

			revalidate( );
			repaint( );
		}
		else
		{
			e.consume( );
		}
	}

	@Override
	public void keyTyped( KeyEvent e )
	{
		char ch = e.getKeyChar( );
		boolean bckSpace = ch == KeyEvent.VK_BACK_SPACE;
		boolean space = ch == KeyEvent.VK_SPACE;
		boolean supr = ch == KeyEvent.VK_DELETE;
		boolean isLetter = Character.isLetter( ch );
		boolean isNumber = Character.isDigit( ch );
		boolean period = ch == KeyEvent.VK_PERIOD;
		if( !( isNumber || isLetter || bckSpace || space || supr || period ) )
		{
			e.consume( );
		}
	}

	public void setImage( ImageIcon icon )
	{
		imagen.setIcon( icon );
	}

	public void setSelected( Materia materia, boolean selected, boolean changeState )
	{
		if( materia.getCarrera( ).equals( carrera ) )
		{
			for( PanelMateria panelMateria : panelesMateria )
			{
				if( ( panelMateria.materia != null ) && panelMateria.materia.equals( materia ) )
				{
					panelMateria.setSelected( selected, changeState );
					break;
				}
			}
		}
	}

	public void showLoader( boolean state )
	{
		scroll.setViewportView( state ? panelLoader : panelLista );
	}

	public void showMateria( String crn )
	{
		for( PanelMateria panelMateria : panelesMateria )
		{
			if( ( panelMateria.materia != null ) && panelMateria.materia.getCrn( ).equals( crn ) )
			{
				scroll.getVerticalScrollBar( ).setValue( panelMateria.getY( ) );
				panelMateria.open( true );

				panelMateria.setBackground( panelMateria.getBackground( ).darker( ) );
				Color original = panelMateria.getBackground( );
				TimeListener timeListener = new TimeListener( new TimeEventListener( )
				{
					@Override
					public void intervalReached( )
					{
						FadeOut fadeOut = new FadeOut( panelMateria );
						fadeOut.fade( original );
					}
				}, 5000 );
				timeListener.startAndKill( );

				break;
			}
		}
	}
}