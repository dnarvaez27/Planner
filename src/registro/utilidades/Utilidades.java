package registro.utilidades;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;

import com.dnarvaez27.resources.Resources;

import registro.interfaz.InterfazPlanner;

public class Utilidades
{
	private static Resources resources;

	public static Resources getResources( )
	{
		if( resources == null )
		{
			resources = new Resources( InterfazPlanner.class, "Images" );
		}
		return resources;
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
}