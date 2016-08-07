package com.dnarvaez27.planner.utilidades;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;

public class Constantes
{
	public static class Colores
	{
		public static final Color COLOR_LOADER = new Color( 255, 254, 247 );
		public static final Color GRIS = new Color( 33, 33, 33 );
		public static final Color VERDE = new Color( 175, 203, 32 );
	}

	public static class Local
	{
		public static final String PATH_SQL = "./data/";
	}

	public static class Imagenes
	{
		public static final ImageIcon BOOKS = new ImageIcon( Utilidades.getResources( ).getPathFile( "Books.png" ) );
		public static final ImageIcon CHECK = new ImageIcon( Utilidades.getResources( ).getPathFile( "Check.png" ) );
		public static final ImageIcon COLOR = new ImageIcon( Utilidades.getResources( ).getPathFile( "Color.png" ) );
		public static final ImageIcon ERROR_404 = new ImageIcon( Utilidades.getResources( ).getPathFile( "404.png" ) );
		public static final ImageIcon ICONO = new ImageIcon( Utilidades.getResources( ).getPathFile( "Icon.png" ) );
		public static final ImageIcon LOADER = new ImageIcon( Utilidades.getResources( ).getPathFile( "Loader.gif" ) );
		public static final ImageIcon LOADER_BOOKS = new ImageIcon( Utilidades.getResources( ).getPathFile( "BooksLoader.gif" ) );
		public static final ImageIcon LOADER_SMALL = new ImageIcon( Utilidades.getResources( ).getPathFile( "LoaderSmall.gif" ) );
		public static final ImageIcon REFRESH = new ImageIcon( Utilidades.getResources( ).getPathFile( "Refresh.png" ) );
		public static final ImageIcon REMOVE = new ImageIcon( Utilidades.getResources( ).getPathFile( "Remove.png" ) );
		public static final ImageIcon REMOVE_WHITE = new ImageIcon( Utilidades.getResources( ).getPathFile( "RemoveWhite.png" ) );
	}

	public static class Media
	{
		public static final Cursor HAND = new Cursor( Cursor.HAND_CURSOR );
	}

	public static class Strings
	{
		public static final String COLOR = "Color";
		public static final String CREDITOS = "Créditos";
		public static final String CRN = "CRN";
		public static final String CUPOS = "Cupos";
		public static final String DIAS = "Días";
		public static final String DISPONIBLES = "Disponibles";
		public static final String ELIMINAR = "Eliminar";
		public static final String HORAS = "Horas";
		public static final String PLANNER = "Planner";
		public static final String PROFESORES = "Profesores";
		public static final String REFRESH = "Refresh";
		public static final String SALON = "Salón";
	}

}