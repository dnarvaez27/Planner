package com.dnarvaez27.planner.utilidades;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.time.LocalTime;

import com.dnarvaez27.planner.interfaz.InterfazPlanner;
import com.dnarvaez27.resources.Resources;

import java.awt.Color;

public class Utilidades
{
	private static Resources resources;

	public static LocalTime formatearHora( String string )
	{
		LocalTime lt = LocalTime.now( );
		string = string.trim( );
		if( !string.isEmpty( ) )
		{
			try
			{
				int hora = Integer.parseInt( string.substring( 0, 2 ) );
				int mins = Integer.parseInt( string.substring( 2 ) );

				lt = lt.withHour( hora );
				lt = lt.withMinute( mins );
			}
			catch( Exception e )
			{
				e.printStackTrace( );
			}
		}
		return lt;
	}

	public static Resources getResources( )
	{
		if( resources == null )
		{
			resources = new Resources( InterfazPlanner.class, "Images" );
		}
		return resources;
	}

	public static boolean isColorDark( Color color )
	{
		double darkness = 1 - ( ( ( 0.299 * color.getRed( ) ) + ( 0.587 * color.getGreen( ) ) + ( 0.114 * color.getBlue( ) ) ) / 255 );
		if( darkness < 0.5 )
		{
			return false; // It's a light color
		}
		else
		{
			return true; // It's a dark color
		}
	}

	public static boolean isConnected( )
	{
		try
		{
			URL url = new URL( "http://www.google.com" );
			url.openConnection( ).connect( );
			return true;
		}
		catch( SocketTimeoutException e )
		{
			return false;
		}
		catch( IOException e )
		{
			return false;
		}
	}

	public static String timeToString( LocalTime hora )
	{
		if( hora != null )
		{
			String hora0 = String.valueOf( hora.getHour( ) ).length( ) == 1 ? "0" + hora.getHour( ) : String.valueOf( hora.getHour( ) );
			String mins0 = String.valueOf( hora.getMinute( ) ).length( ) == 1 ? "0" + hora.getMinute( ) : String.valueOf( hora.getMinute( ) );

			return hora0 + ":" + mins0;
		}
		else
		{
			return "--";
		}
	}
}