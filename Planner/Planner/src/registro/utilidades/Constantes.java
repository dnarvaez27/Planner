package registro.utilidades;

import javax.swing.ImageIcon;

import java.awt.Color;

public class Constantes
{
	public static class Imagenes
	{
		public static final ImageIcon LOADER = new ImageIcon( Utilidades.getResources( ).getPathFile( "Loader.gif" ) );
		public static final ImageIcon BOOKS = new ImageIcon( Utilidades.getResources( ).getPathFile( "Books.png" ) );
		public static final ImageIcon ERROR_404 = new ImageIcon( Utilidades.getResources( ).getPathFile( "404.png" ) );
	}

	public static class Colores
	{
		public static final Color COLOR_LOADER = new Color( 255, 254, 247 );
		public static final Color VERDE = new Color( 175, 203, 32 );
	}
}