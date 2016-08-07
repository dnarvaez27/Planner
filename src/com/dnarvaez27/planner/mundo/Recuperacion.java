package com.dnarvaez27.planner.mundo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;

import org.apache.commons.lang3.StringEscapeUtils;

import com.dnarvaez27.http.HttpGET;
import com.dnarvaez27.planner.utilidades.Utilidades;

import java.awt.Desktop;

public class Recuperacion
{
	private static final ArrayList<String> labels = new ArrayList<>( Arrays.asList( "CRN", "Materia", "Sección", "Créditos", "Título", "Cupo", "Inscritos", "Disponible", "Días", "Horas", "Salón", "F. Inicial", "F. Final", "Instructor(es)" ) );

	public static final String URL = "https://mibanner.uniandes.edu.co/";

	public static final String URL_OFERTA_LINK = "/semestre/adm_con_horario_joomla.php";

	public static final String URL_OFERTA_MAIN = "https://registroapps.uniandes.edu.co/scripts";

	public static final String URL_POST = "/pls/prod/bwckcoms.P_Regs";

	private LinkedList<Carrera> carreras;

	public Recuperacion( )
	{
		carreras = new LinkedList<>( );
	}

	private Carrera formatCarrera( String carr )
	{
		String nombre = "";
		String url = "";
		int i0 = carr.indexOf( "<a href=\"" ) + "<a href=\"".length( );
		String sub0 = carr.substring( i0 );
		int i1 = sub0.indexOf( ">" );
		url = sub0.substring( 0, i1 );
		int i2 = sub0.indexOf( "</a>" );
		nombre = sub0.substring( i1 + 1, i2 );

		nombre = nombre.replaceAll( "^\\s+|\\s+$", "" );
		url = url.replaceAll( "^\\s+|\\s+$", "" );
		url = url.substring( 2, url.length( ) - 1 );

		return new Carrera( nombre, url );
	}

	private Calendar formatearCalendario( String string )
	{
		Calendar c = Calendar.getInstance( );

		String[ ] month =
		{
				"ENE",
				"FEB",
				"MAR",
				"ABR",
				"MAY",
				"JUN",
				"JUL",
				"AGO",
				"SEP",
				"OCT",
				"NOV",
				"DIC"
		};
		ArrayList<String> meses = new ArrayList<>( Arrays.asList( month ) );

		String[ ] vals = string.split( "-" );

		try
		{
			c.set( Calendar.DAY_OF_MONTH, Integer.parseInt( vals[ 0 ] ) );
			c.set( Calendar.MONTH, meses.indexOf( vals[ 1 ] ) );
			c.set( Calendar.YEAR, Integer.parseInt( "20" + vals[ 2 ] ) );
		}
		catch( NumberFormatException ne )
		{
			String[ ] val = vals[ 0 ].split( "/" );
			c.set( Calendar.DAY_OF_MONTH, Integer.parseInt( val[ 0 ] ) );
			c.set( Calendar.MONTH, Integer.parseInt( val[ 1 ] ) );
			c.set( Calendar.YEAR, Integer.parseInt( "20" + val[ 2 ] ) );
		}
		return c;
	}

	public LinkedList<Carrera> getCarreras( )
	{
		return carreras;
	}

	public void getMaterias( Carrera carrera ) throws Exception
	{
		ArrayList<Materia> materias = new ArrayList<>( );

		HttpGET httpGET = new HttpGET( URL_OFERTA_MAIN + carrera.getUrl( ), StandardCharsets.UTF_8 );
		String rta = httpGET.getResponse( );
		rta = StringEscapeUtils.unescapeHtml4( rta );
		rta = rta.substring( rta.indexOf( "<body" ), rta.indexOf( "</body>" ) );
		String[ ] table = rta.split( "<table" );
		String matters = table[ 4 ];
		int ini = rta.indexOf( matters );
		String thisIs = rta.substring( ini );
		thisIs = thisIs.replaceAll( "^\\s+|\\s+$", "" );
		String[ ] tables = thisIs.split( "<table" );

		String sBuffer = "";

		Materia materia = null;

		for( int i = 1; i < ( tables.length - 2 ); i++ )
		{
			ArrayList<String> arreglo = new ArrayList<>( );

			System.out.println( "----------------------------" );

			String[ ] tds = tables[ i ].split( "<td" );
			for( int j = 0; j < tds.length; j++ )
			{
				String line = tds[ j ].replace( "\n", "" );
				// String[] cositos = line.split( ">" );
				boolean esTexto = false;
				int lastIndex = 0;
				while( !esTexto )
				{
					int indTemp = line.indexOf( ">", lastIndex ) + 1;
					String cor = line.substring( indTemp );
					cor = cor.replaceAll( "^\\s+|\\s+$", "" );
					if( !cor.startsWith( "<" ) && !cor.isEmpty( ) )
					{
						esTexto = true;
						int fin = cor.indexOf( "<" );
						int cont = 0;
						if( fin > 0 && cor.substring( fin ).startsWith( "<br" ) )
						{
							while( cor.substring( fin ).startsWith( "<br" ) && cont < 7 )
							{
								fin = cor.indexOf( "<br", fin + "<br".length( ) );// TODO
								cont++;
								if( cor.substring( fin ).startsWith( "<br> - - -" ) || cor.substring( fin ).startsWith( "<br>- - -" ) || cor.substring( fin ).startsWith( "- - -" ) )
								{
									break;
								}
							}
						}
						if( fin > 0 )
						{
							String value = cor.substring( 0, fin );
							value = value.replaceAll( "^\\s+|\\s+$", "" );
							if( !value.endsWith( ">" ) )
							{
								if( !labels.contains( value ) || value.equals( "CRN" ) || value.equals( " " ) )
								{
									System.out.println( "° " + value );// XXX
									arreglo.add( value );
									sBuffer += value + System.lineSeparator( );

									break;
								}
							}
						}
					}
					else
					{
						lastIndex = indTemp;
					}
				}
			}
			String iniCode = arreglo.get( 0 );
			if( iniCode.equals( "CRN" ) )
			{
				arreglo.remove( 0 );
				try
				{
					String crn = arreglo.get( 0 );
					String cod = arreglo.get( 1 );
					int seccion = Integer.parseInt( arreglo.get( 2 ) );
					double creditos = Double.parseDouble( arreglo.get( 3 ) );
					String nombre = arreglo.get( 4 );
					int cupos = Integer.parseInt( arreglo.get( 5 ) );
					int disponible = Integer.parseInt( arreglo.get( 6 ) );

					materia = new Materia( carrera, crn, cod, seccion, creditos, nombre, cupos, disponible );
					materias.add( materia );
					materia = materias.get( materias.size( ) - 1 );
				}
				catch( Exception e )
				{
					e.printStackTrace( );
				}
			}
			else if( iniCode.trim( ).replaceAll( " ", "" ).isEmpty( ) || iniCode.equals( " " ) )
			{
				if( materia != null )
				{
					try
					{
						String dato1 = arreglo.get( 2 );
						boolean may3 = arreglo.size( ) > 3;
						if( may3 && !isNumber( dato1.replace( "-", "" ).replace( " ", "" ).trim( ) ) )
						{
							String dias = dato1.trim( ).replaceAll( " ", "" );
							String horas = arreglo.get( 3 );
							String salon = arreglo.get( 4 );
							Calendar iniC = formatearCalendario( arreglo.get( 5 ) );
							Calendar endC = formatearCalendario( arreglo.get( 6 ) );

							String dato7 = arreglo.get( 7 );
							String profesor0 = "";
							String profesor1 = "";
							if( ( dato7.equals( " " ) || dato7.trim( ).replaceAll( " ", "" ).isEmpty( ) ) && ( arreglo.size( ) > 9 ) )
							{
								dias += arreglo.get( 8 ).trim( ).replaceAll( " ", "" );;
								String dato13 = arreglo.get( 13 );

								if( ( dato13.equals( " " ) || dato13.trim( ).replaceAll( " ", "" ).isEmpty( ) ) && ( arreglo.size( ) > 15 ) )// TODO
								{
									dias += arreglo.get( 14 ).trim( ).replaceAll( " ", "" );
									profesor0 = arreglo.get( 19 ).trim( ).replaceAll( "^\\s+|\\s+$", "" );
									profesor1 = arreglo.get( 20 ).trim( ).replaceAll( "^\\s+|\\s+$", "" );
								}
								else
								{
									profesor0 = arreglo.get( 13 ).trim( ).replaceAll( "^\\s+|\\s+$", "" );
									profesor1 = arreglo.get( 14 ).trim( ).replaceAll( "^\\s+|\\s+$", "" );
								}
							}
							else
							{
								profesor0 = arreglo.get( 7 ).trim( ).replaceAll( "^\\s+|\\s+$", "" );
								profesor1 = arreglo.get( 8 ).trim( ).replaceAll( "^\\s+|\\s+$", "" );
							}
							// String profesor2 = arreglo.get( 9 );

							LocalTime[ ] lTime = new LocalTime[ 2 ];
							if( !horas.trim( ).isEmpty( ) )
							{
								if( horas.trim( ).length( ) != 1 )
								{
									String[ ] time = horas.trim( ).split( "-" );
									if( time.length == 2 )
									{
										lTime[ 0 ] = Utilidades.formatearHora( time[ 0 ] );
										lTime[ 1 ] = Utilidades.formatearHora( time[ 1 ] );
									}
								}
							}

							materia.setDias( dias );
							materia.setHora( lTime );
							materia.setSalon( salon );
							materia.agregarProfesor( profesor0.trim( ).replace( " ", "" ).isEmpty( ) || profesor0.equals( "&nbsp;" ) ? null : profesor0 );
							materia.agregarProfesor( profesor1.trim( ).replace( " ", "" ).isEmpty( ) || profesor1.equals( "&nbsp;" ) ? null : profesor1 );
							materia.setIni( iniC );
							materia.setEnd( endC );
						}
						else
						{
							if( may3 )
							{
								String dias = "--";
								String horas = dato1;
								String salon = arreglo.get( 3 );
								Calendar iniC = formatearCalendario( arreglo.get( 4 ) );
								Calendar endC = formatearCalendario( arreglo.get( 5 ) );
								String profesor0 = arreglo.get( 6 ).trim( );
								String profesor1 = arreglo.get( 7 ).trim( );

								// String profesor2 = arreglo.get( 9 );

								LocalTime[ ] lTime = new LocalTime[ 2 ];
								if( !horas.trim( ).isEmpty( ) )
								{
									if( horas.trim( ).length( ) != 1 )
									{
										String[ ] time = horas.trim( ).split( "-" );
										if( time.length == 2 )
										{
											lTime[ 0 ] = Utilidades.formatearHora( time[ 0 ] );
											lTime[ 1 ] = Utilidades.formatearHora( time[ 1 ] );
										}
									}
								}

								materia.setDias( dias );
								materia.setHora( lTime );
								materia.setSalon( salon );
								materia.agregarProfesor( profesor0.trim( ).replace( " ", "" ).isEmpty( ) || profesor0.equals( "&nbsp;" ) ? null : profesor0 );
								materia.agregarProfesor( profesor1.trim( ).replace( " ", "" ).isEmpty( ) || profesor1.equals( "&nbsp;" ) ? null : profesor1 );
								materia.setIni( iniC );
								materia.setEnd( endC );
							}
						}
					}
					catch( Exception e )
					{
						e.printStackTrace( );
					}
				}
			}
			else if( ( arreglo.size( ) == 1 ) && ( materia != null ) )
			{
				// TODO
				String descripcion = arreglo.get( 0 );
				descripcion = descripcion.replace( "<br>", "\n@" );
				descripcion = descripcion.replaceAll( "^\\s+|\\s+$", "" );
				descripcion = descripcion.replace( "@ ", "@" );

				String[ ] complementos = descripcion.split( "@" );
				for( int j = 1; j < complementos.length; j++ )
				{
					if( !complementos[ j ].equals( "- - -" ) && !complementos[ j ].equals( " -  -  -" ) )
					{
						materia.addComplemento( complementos[ j ] );
					}
					descripcion = descripcion.replace( "@" + complementos[ j ], "" );
				}
				descripcion = descripcion.replace( "\n", "" );
				materia.setDescripcion( descripcion );
			}
			sBuffer += "----------------------" + System.lineSeparator( );
		}
		try
		{
			File f = new File( "./data/" + carrera.getNombre( ) + ".txt" );
			PrintWriter pw = new PrintWriter( f );
			pw.write( sBuffer );
			pw.close( );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}

		carrera.setMaterias( materias );
	}

	public void goToInIndex( int i )
	{
		Carrera c = carreras.get( i );
		System.out.println( c.getNombre( ) );
		try
		{
			Desktop.getDesktop( ).browse( new URI( URL_OFERTA_MAIN + c.getUrl( ) ) );
		}
		catch( IOException | URISyntaxException e )
		{
			e.printStackTrace( );
		}
	}

	public void iniciarCarreras( ) throws Exception
	{
		HttpGET httpGET = new HttpGET( URL_OFERTA_MAIN + URL_OFERTA_LINK );
		String rta = httpGET.getResponse( );
		rta = StringEscapeUtils.unescapeHtml4( rta );

		int i0 = rta.indexOf( "</blockquote>" );
		String sub0 = rta.substring( i0 );
		int i1 = sub0.indexOf( "<table" );
		int i2 = sub0.indexOf( "</table>" );
		String sub1 = sub0.substring( i1, i2 );

		String[ ] carr = sub1.split( "<div" );
		int ex = 0;
		for( String string : carr )
		{
			if( ex != 0 )
			{
				Carrera c = formatCarrera( string );
				carreras.add( c );
			}
			ex++;
		}
	}

	private boolean isNumber( String string )
	{
		if( string.isEmpty( ) )
		{
			return true;
		}

		boolean isNumber = false;
		try
		{
			Integer.parseInt( string );
			isNumber = true;
		}
		catch( NumberFormatException e )
		{
			isNumber = false;
		}
		return isNumber;
	}
}