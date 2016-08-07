package registro.mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import registro.utilidades.Utilidades;

public class HttpGET
{
	private String rta;

	public HttpGET( String string ) throws Exception
	{
		if( Utilidades.isConnected( ) )
		{
			HttpURLConnection urlConnection = null;
			BufferedReader reader = null;
			try
			{
				URL url = new URL( string );

				urlConnection = ( HttpURLConnection ) url.openConnection( );
				// urlConnection.setRequestMethod( "GET" );
				urlConnection.connect( );
				InputStream inputStream = urlConnection.getInputStream( );
				StringBuffer buffer = new StringBuffer( );
				if( inputStream == null )
				{
					return;
				}
				reader = new BufferedReader( new InputStreamReader( inputStream ) );

				String line;
				while( ( line = reader.readLine( ) ) != null )
				{
					buffer.append( line + "\n" );
				}

				if( buffer.length( ) == 0 )
				{
					return;
				}

				rta = buffer.toString( );
			}
			catch( IOException e )
			{
				e.printStackTrace( );
			}
		}
		else
		{
			throw new Exception( "No hay conexión a internet" );
		}
	}

	public String getRta( )
	{
		if( rta != null )
		{
			rta = rta.replace( "</", "\n</" );
		}
		return rta;
	}

	public static void main( String[ ] args )
	{
		System.out.println( "Escriba la dirección web: " );
		try( Scanner sc = new Scanner( System.in ) )
		{
			HttpGET t = new HttpGET( sc.nextLine( ) );
			System.out.println( t.getRta( ) );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}