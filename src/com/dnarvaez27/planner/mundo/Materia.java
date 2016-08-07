package com.dnarvaez27.planner.mundo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.dnarvaez27.horario.ITask;
import com.dnarvaez27.planner.utilidades.Utilidades;

import java.awt.Color;

public class Materia implements ITask, Comparable<Materia>
{
	private Carrera carrera;

	private String cod;

	private Color color;

	private double creditos;

	private String crn;

	private int cupos;

	private String descripcion;

	private String dias;

	private Calendar end;

	private LocalTime[ ] hora;

	private Calendar ini;

	private int inscritos;

	private String nombre;

	private ArrayList<String> profesores;

	private String salon;

	private ArrayList<String> complementos;

	private int seccion;

	public Materia( Carrera carrera, String crn, String cod, double creditos, int cupos, String dias, int inscritos, Calendar end, LocalTime[ ] hora, Calendar ini, String nombre, ArrayList<String> profesores, String salon, int seccion )
	{
		this.carrera = carrera;

		this.cod = cod;
		this.creditos = creditos;
		this.crn = crn;
		this.cupos = cupos;
		this.dias = dias;
		this.inscritos = inscritos;
		this.end = end;
		this.hora = hora;
		this.ini = ini;
		this.nombre = nombre;
		this.profesores = profesores;
		this.salon = salon;
		this.seccion = seccion;
		descripcion = "";

		complementos = new ArrayList<>( );
		this.profesores = new ArrayList<>( );
	}

	public Materia( Carrera carrera, String crn, String cod, int seccion, double creditos, String nombre, int cupos, int inscritos )
	{
		this.carrera = carrera;

		this.cod = cod;
		this.creditos = creditos;
		this.crn = crn;
		this.cupos = cupos;
		this.inscritos = inscritos;
		this.nombre = nombre;
		this.seccion = seccion;
		descripcion = "";

		hora = new LocalTime[ 2 ];
		profesores = new ArrayList<>( );
		complementos = new ArrayList<>( );
	}

	public void addComplemento( String complemento )
	{
		complementos.add( complemento );
	}

	public ArrayList<String> getComplementos( )
	{
		return complementos;
	}

	public void agregarProfesor( String profesor )
	{
		if( ( profesor != null ) && !profesor.trim( ).replace( " ", "" ).isEmpty( ) && !profesor.equals( " " ) )
		{
			profesores.add( profesor );
		}
	}

	@Override
	public int compareTo( Materia m )
	{
		return crn.compareTo( m.crn );
	}

	@Override
	public boolean equals( Object o )
	{
		if( o instanceof Materia )
		{
			return ( ( Materia ) o ).crn.equals( crn );
		}
		return false;
	}

	private String formatCalendar( Calendar calendar )
	{
		if( calendar != null )
		{
			int y = calendar.get( Calendar.YEAR );
			int m = calendar.get( Calendar.MONTH ) + 1;
			int d = calendar.get( Calendar.DAY_OF_MONTH );

			return y + "." + m + "." + d;
		}
		else
		{
			return "-";
		}
	}

	@Override
	public Calendar[ ][ ] getCalendarEvents( )
	{
		if( ( dias != null ) && !dias.isEmpty( ) && ( ini != null ) && ( end != null ) && ( hora[ 0 ] != null ) )
		{
			String[ ] d = dias.split( "" );

			Calendar[ ][ ] eventos = new Calendar[ d.length ][ 2 ];

			ArrayList<String> days = new ArrayList<>( Arrays.asList( "D", "L", "M", "I", "J", "V", "S" ) );

			ini.set( Calendar.HOUR_OF_DAY, hora[ 0 ].getHour( ) );
			ini.set( Calendar.MINUTE, hora[ 0 ].getMinute( ) );

			end.set( Calendar.HOUR_OF_DAY, hora[ 1 ].getHour( ) );
			end.set( Calendar.MINUTE, hora[ 1 ].getMinute( ) );

			for( int i = 0; i < eventos.length; i++ )
			{
				Calendar temp = Calendar.getInstance( );

				temp.set( Calendar.DAY_OF_WEEK, days.indexOf( d[ i ] ) + 1 );
				temp.set( Calendar.HOUR_OF_DAY, ini.get( Calendar.HOUR_OF_DAY ) );
				temp.set( Calendar.MINUTE, ini.get( Calendar.MINUTE ) );

				eventos[ i ][ 0 ] = temp;
				eventos[ i ][ 1 ] = end;
			}

			return eventos;
		}

		return null;
	}

	public Carrera getCarrera( )
	{
		return carrera;
	}

	public String getCod( )
	{
		return cod;
	}

	@Override
	public Color getColor( )
	{
		if( color == null )
		{
			color = new Color( 0, 150, 150 );
		}
		return color;
	}

	public double getCreditos( )
	{
		return creditos;
	}

	public String getCrn( )
	{
		return crn;
	}

	public int getCupos( )
	{
		return cupos;
	}

	public String getDescripcion( )
	{
		return descripcion;
	}

	public String getDias( )
	{
		return dias;
	}

	public int getDisponibles( )
	{
		return cupos - inscritos;
	}

	public Calendar getEnd( )
	{
		return end;
	}

	public String getEndString( )
	{
		return formatCalendar( end );
	}

	public LocalTime[ ] getHora( )
	{
		return hora;
	}

	public String getHoraString( )
	{
		if( ( hora != null ) && ( hora[ 0 ] != null ) && ( hora[ 1 ] != null ) )
		{
			String startHour = Utilidades.timeToString( hora[ 0 ] );
			String endHour = Utilidades.timeToString( hora[ 1 ] );

			return startHour + " - " + endHour;
		}
		else
		{
			return "--";
		}
	}

	public Calendar getIni( )
	{
		return ini;
	}

	public String getIniString( )
	{
		return formatCalendar( ini );
	}

	public int getInscritos( )
	{
		return inscritos;
	}

	@Override
	public String getName( )
	{
		return nombre;
	}

	public String getNombre( )
	{
		return nombre;
	}

	public ArrayList<String> getProfesores( )
	{
		return profesores;
	}

	public String getSalon( )
	{
		return salon;
	}

	public int getSeccion( )
	{
		return seccion;
	}

	@Override
	public String getToolTip( )
	{
		if( ( hora != null ) && ( hora[ 0 ] != null ) )
		{
			return "<html><center><b>" + cod + "<br>" + nombre + "<br>" + getHoraString( ) + "</html>";
		}
		return null;
	}

	public void setCod( String cod )
	{
		this.cod = cod;
	}

	public void setColor( Color color )
	{
		this.color = color;
	}

	public void setCreditos( double creditos )
	{
		this.creditos = creditos;
	}

	public void setCrn( String crn )
	{
		this.crn = crn;
	}

	public void setCupos( int cupos )
	{
		this.cupos = cupos;
	}

	public void setDescripcion( String descripcion )
	{
		this.descripcion = descripcion;
	}

	public void setDias( String dias )
	{
		this.dias = dias;
	}

	public void setEnd( Calendar end )
	{
		this.end = end;
	}

	public void setHora( LocalTime[ ] hora )
	{
		this.hora = hora;
	}

	public void setIni( Calendar ini )
	{
		this.ini = ini;
	}

	public void setInscritos( int inscritos )
	{
		this.inscritos = inscritos;
	}

	public void setNombre( String nombre )
	{
		this.nombre = nombre;
	}

	public void setProfesores( ArrayList<String> profesores )
	{
		this.profesores = profesores;
	}

	public void setSalon( String salon )
	{
		this.salon = salon;
	}

	public void setSeccion( int seccion )
	{
		this.seccion = seccion;
	}

	@Override
	public String toString( )
	{
		return nombre;
	}
}