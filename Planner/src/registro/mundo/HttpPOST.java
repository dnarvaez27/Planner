package registro.mundo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.awt.Desktop;

public class HttpPOST
{
	private File response;

	public HttpPOST( String string )
	{
		HttpURLConnection urlConnection = null;
		try
		{
			URL url = new URL( string );

			urlConnection = ( HttpURLConnection ) url.openConnection( );
			HttpURLConnection.setFollowRedirects( true );
			urlConnection.setDoOutput( true );
			urlConnection.setRequestMethod( "POST" );

			PrintStream ps = new PrintStream( urlConnection.getOutputStream( ) );

			String params = "";
			params += "TERM_IN=" + "";
			params += "&";
			params += "RSTS_IN=" + "";
			params += "&";
			params += "ASSOC_TERM_IN=" + "";
			params += "&";
			// params += "CRN_IN=" + "";
			// params += "&";
			params += "START_DATE_IN=" + "";
			params += "&";
			params += "START_DATE_IN=" + "";
			params += "&";
			params += "SUBJ=" + "";
			params += "&";
			params += "CRSE=" + "";
			params += "&";
			params += "SEC=" + "";
			params += "&";
			params += "LEVL=" + "";
			params += "&";
			params += "CRED=" + "";
			params += "&";
			params += "GMOD=" + "";
			params += "&";
			params += "TITLE=" + "";
			params += "&";
			params += "MESG=" + "";
			params += "&";
			params += "REG_BTN=" + "";
			params += "&";
			params += "REGS_ROW=" + "";
			params += "&";
			params += "ADD_ROW=" + "";
			params += "&";
			params += "WAIT_ROW=" + "";
			params += "&";
			params += "END_DATE_IN=" + "";
			params += "&";
			params += "CRN_IN=" + "23563";
			// params += "&";
			// params += "DAD=" + "";

			ps.print( params );
			ps.close( );

			urlConnection.connect( );

			if( HttpURLConnection.HTTP_OK == urlConnection.getResponseCode( ) )
			{
				response = new File( "output.html" );
				InputStream is = urlConnection.getInputStream( );
				OutputStream os = new FileOutputStream( response );
				int data;
				while( ( data = is.read( ) ) != -1 )
				{
					os.write( data );
				}
				is.close( );
				os.close( );
				urlConnection.disconnect( );
			}
			else
			{
				response = new File( "error.html" );
				InputStream is = urlConnection.getErrorStream( );
				OutputStream os = new FileOutputStream( response );
				int data;
				while( ( data = is.read( ) ) != -1 )
				{
					os.write( data );
				}
				is.close( );
				os.close( );
				urlConnection.disconnect( );

				System.err.println( "Error: " + urlConnection.getResponseCode( ) );
			}
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
	}

	public File getResponse( )
	{
		return response;
	}

	public static void main( String[ ] args )
	{
		HttpPOST t = new HttpPOST( Recuperacion.URL + Recuperacion.URL_POST );
		try
		{
			Desktop.getDesktop( ).open( t.getResponse( ) );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
	}
}
