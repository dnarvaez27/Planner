package com.dnarvaez27.planner.interfaz;

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
import com.dnarvaez27.planner.mundo.Carrera;
import com.dnarvaez27.planner.threads.ThreadMaterias;
import com.dnarvaez27.planner.utilidades.Constantes.Colores;
import com.dnarvaez27.planner.utilidades.Constantes.Imagenes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelCarreras extends JPanel implements ListSelectionListener, KeyListener
{
	private static final long serialVersionUID = 3632566221049479068L;

	private JTextField busqueda;

	private InterfazPlanner interfaz;

	private JList<Carrera> listaCarreras;

	private JLabel loading;

	private ScrollColor scroll;

	private ThreadMaterias threadMaterias;

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
		busqueda.setSelectionColor( Colores.COLOR_LOADER.darker( ).darker( ) );
		busqueda.setSelectedTextColor( Color.WHITE );
		busqueda.setFont( busqueda.getFont( ).deriveFont( Font.BOLD ) );
		busqueda.addKeyListener( this );

		// add( loading, BorderLayout.NORTH );
		add( busqueda, BorderLayout.NORTH );
		add( scroll, BorderLayout.CENTER );
	}

	public void actualizar( )
	{
		listaCarreras.setListData( interfaz.getCarreras( ).toArray( new Carrera[ 0 ] ) );
		listaCarreras.revalidate( );
		listaCarreras.repaint( );
	}

	public JTextField getBusqueda( )
	{
		return busqueda;
	}

	public JList<Carrera> getListaCarreras( )
	{
		return listaCarreras;
	}

	@Override
	public void keyPressed( KeyEvent e )
	{

	}

	@Override
	public void keyReleased( KeyEvent e )
	{
		char ch = e.getKeyChar( );
		boolean bckSpace = ch == KeyEvent.VK_BACK_SPACE;
		boolean space = ch == KeyEvent.VK_SPACE;
		boolean supr = ch == KeyEvent.VK_DELETE;
		boolean isLetter = Character.isLetter( ch );
		boolean period = ch == KeyEvent.VK_PERIOD;
		if( isLetter || bckSpace || space || supr || period )
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
		boolean period = ch == KeyEvent.VK_PERIOD;

		if( !( isLetter || bckSpace || space || supr || period ) )
		{
			e.consume( );
		}
	}

	public void loading( boolean state )
	{
		loading.setVisible( state );
		if( !state )
		{
			scroll.setViewportView( listaCarreras );
		}
	}

	@Override
	public void valueChanged( ListSelectionEvent e )
	{
		if( !e.getValueIsAdjusting( ) )
		{
			Carrera sel = listaCarreras.getSelectedValue( );
			if( sel != null )
			{
				if( threadMaterias != null )
				{
					threadMaterias.interrupt( );// XXX
				}
				threadMaterias = new ThreadMaterias( interfaz, sel );
				threadMaterias.start( );
			}
		}
	}
}