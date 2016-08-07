package registro.interfaz;

import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.dnarvaez27.componentes.ButtonTip;
import com.dnarvaez27.componentes.ScrollColor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import registro.mundo.Carrera;
import registro.mundo.Materia;
import registro.utilidades.Constantes.Colores;
import registro.utilidades.Constantes.Imagenes;

public class PanelMaterias extends JPanel implements KeyListener
{
	private class PanelMateria extends JPanel
	{
		private static final long serialVersionUID = 4464585982988679866L;

		private JLabel cod;

		private JLabel creditos;

		private JLabel crn;

		private JLabel cupos;

		private JTextArea descripcion;

		private JLabel dias;

		private JLabel horas;

		private JLabel inscritos;

		private Materia materia;

		private JLabel nombre;

		private JLabel profesor;

		private JLabel salon;

		private JLabel seccion;

		private JLabel fecha;

		private boolean selected;

		private ButtonTip add;

		public PanelMateria( Materia pMateria )
		{
			this.materia = pMateria;

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

			panelNombre.add( cod );
			panelNombre.add( nombre );
			panelNombre.add( seccion );

			crn = new JLabel( materia.getCrn( ) );
			crn.setForeground( Color.WHITE );

			creditos = new JLabel( "" + materia.getCreditos( ) );
			creditos.setForeground( Color.WHITE );

			cupos = new JLabel( "" + materia.getCupos( ) );
			cupos.setForeground( Color.WHITE );

			inscritos = new JLabel( "" + materia.getInscritos( ) );
			inscritos.setForeground( Color.WHITE );

			JPanel panelSuperior = new JPanel( );
			panelSuperior.setBackground( null );
			panelSuperior.setLayout( new FlowLayout( FlowLayout.LEFT ) );
			panelSuperior.setBorder( BorderFactory.createEmptyBorder( 0, 10, 0, 10 ) );
			panelSuperior.setVisible( false );

			panelSuperior.add( crn );
			panelSuperior.add( creditos );
			panelSuperior.add( cupos );
			panelSuperior.add( inscritos );

			JPanel panelInferior = new JPanel( );
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

			add = new ButtonTip( "-" );
			add.addActionListener( new ActionListener( )
			{
				@Override
				public void actionPerformed( ActionEvent e )
				{
					selected = !selected;
					interfaz.actionMateria( materia );
					if( selected )
					{
						setBackground( Colores.VERDE );
						descripcion.setBackground( Colores.VERDE.darker( ) );
					}
					else
					{
						setBackground( Color.GRAY );
						descripcion.setBackground( Color.GRAY.darker( ) );
					}
				}
			} );

			panelInferior.add( dias );
			panelInferior.add( horas );
			panelInferior.add( salon );
			panelInferior.add( profesor );
			panelInferior.add( fecha );

			panelInferior.add( add );

			descripcion = new JTextArea( materia.getDescripcion( ) );
			descripcion.setWrapStyleWord( true );
			descripcion.setLineWrap( true );
			descripcion.setEditable( false );
			descripcion.setBackground( Color.GRAY.darker( ) );
			descripcion.setForeground( Color.WHITE );
			descripcion.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
			descripcion.setVisible( false );

			panelNombre.addMouseListener( new MouseListener( )
			{
				@Override
				public void mouseReleased( MouseEvent e )
				{
				}

				@Override
				public void mousePressed( MouseEvent e )
				{
				}

				@Override
				public void mouseExited( MouseEvent e )
				{
				}

				@Override
				public void mouseEntered( MouseEvent e )
				{
				}

				@Override
				public void mouseClicked( MouseEvent e )
				{
					panelSuperior.setVisible( !panelSuperior.isVisible( ) );
					panelInferior.setVisible( !panelInferior.isVisible( ) );
					descripcion.setVisible( !materia.getDescripcion( ).isEmpty( ) && !descripcion.isVisible( ) );
				}
			} );

			add( panelNombre );
			add( panelSuperior );
			add( panelInferior );
			add( descripcion );
		}
	}

	private static final long serialVersionUID = -3945647275543629173L;

	private InterfazPlanner interfaz;

	private JPanel panelLista;

	private ScrollColor scroll;

	private JLabel imagen;

	private JTextField busqueda;

	private Carrera carrera;

	public PanelMaterias( InterfazPlanner interfaz )
	{
		this.interfaz = interfaz;

		setLayout( new BorderLayout( ) );
		setBackground( Colores.COLOR_LOADER );

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

		add( busqueda, BorderLayout.NORTH );
		add( scroll, BorderLayout.CENTER );
	}

	public void setImage( ImageIcon icon )
	{
		imagen.setIcon( icon );
	}

	public void actualizar( Carrera carrera )
	{
		this.carrera = carrera;

		panelLista.removeAll( );

		if( carrera != null )
		{
			for( Materia m : carrera.getMaterias( ) )
			{
				PanelMateria panelMateria = new PanelMateria( m );

				panelLista.add( panelMateria );
			}
		}

		scroll.setViewportView( panelLista );
		scroll.getVerticalScrollBar( ).setValue( 0 );

		revalidate( );
		repaint( );
	}

	@Override
	public void keyTyped( KeyEvent e )
	{

	}

	@Override
	public void keyPressed( KeyEvent e )
	{

	}

	@Override
	public void keyReleased( KeyEvent e )
	{
		LinkedList<Materia> materias = new LinkedList<>( );

		for( Materia materia : carrera.getMaterias( ) )
		{
			if( materia.getNombre( ).toUpperCase( ).contains( busqueda.getText( ).toUpperCase( ) ) )
			{
				materias.add( materia );
			}
		}

		panelLista.removeAll( );

		for( Materia m : materias )
		{
			PanelMateria panelMateria = new PanelMateria( m );

			panelLista.add( panelMateria );
		}

		scroll.setViewportView( panelLista );
		scroll.getVerticalScrollBar( ).setValue( 0 );

		revalidate( );
		repaint( );
	}
}