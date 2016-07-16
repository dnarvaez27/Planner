package registro.interfaz;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.dnarvaez27.componentes.ButtonTip;
import com.dnarvaez27.componentes.ScrollColor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import registro.mundo.Carrera;
import registro.utilidades.Constantes.Colores;
import registro.utilidades.Constantes.Imagenes;

public class PanelCarreras extends JPanel implements ListSelectionListener, KeyListener
{
	private static final long serialVersionUID = 3632566221049479068L;

	private ScrollColor scroll;

	private JList<Carrera> listaCarreras;

	private InterfazPlanner interfaz;

	private JLabel loading;

	private JTextField busqueda;

	public PanelCarreras( InterfazPlanner interfaz )
	{
		this.interfaz = interfaz;

		setLayout( new BorderLayout( ) );

		listaCarreras = new JList<>( );
		listaCarreras.setCellRenderer( new ListCellRenderer<Carrera>( )
		{
			@Override
			public Component getListCellRendererComponent( JList<? extends Carrera> list, Carrera value, int index, boolean isSelected, boolean cellHasFocus )
			{
				ButtonTip label = new ButtonTip( value.getNombre( ) + " ( " + value.getMaterias( ).size( ) + " )" );
				label.setPreferredSize( new Dimension( 200, 40 ) );
				if( isSelected )
				{
					label.setBackground( Color.GRAY );
					label.setForeground( Colores.COLOR_LOADER );
				}
				else
				{
					label.setBackground( Colores.COLOR_LOADER );
				}
				label.setBorderPainted( true );
				label.setBorder( BorderFactory.createLineBorder( Color.GRAY ) );
				return label;
			}
		} );
		listaCarreras.addListSelectionListener( this );

		JPanel panelLoading = new JPanel( );
		panelLoading.setLayout( new BorderLayout( ) );

		loading = new JLabel( Imagenes.LOADER );
		loading.setHorizontalAlignment( SwingConstants.CENTER );
		panelLoading.add( loading, BorderLayout.CENTER );
		panelLoading.setBackground( Colores.COLOR_LOADER );

		scroll = new ScrollColor( panelLoading, 2, Colores.COLOR_LOADER, Color.GRAY );
		scroll.setPreferredSize( new Dimension( 230, 0 ) );

		Border line = BorderFactory.createLineBorder( Color.GRAY.darker( ) );
		Border empt = BorderFactory.createEmptyBorder( 4, 4, 4, 4 );

		busqueda = new JTextField( );
		busqueda.setPreferredSize( new Dimension( 0, 30 ) );
		busqueda.setBorder( BorderFactory.createCompoundBorder( line, empt ) );
		busqueda.addKeyListener( this );

		// add( loading, BorderLayout.NORTH );
		add( busqueda, BorderLayout.NORTH );
		add( scroll, BorderLayout.CENTER );
	}

	public void loading( boolean state )
	{
		loading.setVisible( state );
		if( !state )
		{
			scroll.setViewportView( listaCarreras );
		}
	}

	public void actualizar( )
	{
		listaCarreras.setListData( interfaz.getCarreras( ).toArray( new Carrera[ 0 ] ) );
		listaCarreras.revalidate( );
		listaCarreras.repaint( );
	}

	@Override
	public void valueChanged( ListSelectionEvent e )
	{
		if( !e.getValueIsAdjusting( ) )
		{
			Carrera sel = listaCarreras.getSelectedValue( );
			if( sel != null )
			{
				setCursor( new Cursor( Cursor.WAIT_CURSOR ) );
				interfaz.getMaterias( sel );
				interfaz.actualizarMaterias( sel );
				setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );

				listaCarreras.revalidate( );
				listaCarreras.repaint( );
			}
		}
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
		ArrayList<Carrera> coincidencias = new ArrayList<>( );
		for( Carrera carrera : interfaz.getCarreras( ) )
		{
			if( carrera.getNombre( ).toUpperCase( ).contains( busqueda.getText( ).toUpperCase( ) ) )
			{
				coincidencias.add( carrera );
			}
		}
		listaCarreras.setListData( coincidencias.toArray( new Carrera[ 0 ] ) );
		listaCarreras.revalidate( );
		listaCarreras.repaint( );
	}
}