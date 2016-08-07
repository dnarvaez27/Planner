package registro.mundo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import com.dnarvaez27.horario.ITask;

import java.awt.Color;

public class Materia implements ITask
{
	private String cod;

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

	private int seccion;

	public Materia( String cod, double creditos, String crn, int cupos, String dias, int inscritos, Calendar end, LocalTime[ ] hora, Calendar ini, String nombre, ArrayList<String> profesores, String salon, int seccion )
	{
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

		this.profesores = new ArrayList<>( );
	}

	public Materia( String crn, String cod, int seccion, double creditos, String nombre, int cupos, int inscritos )
	{
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
	}

	public void agregarProfesor( String profesor )
	{
		if( profesor != null )
		{
			profesores.add( profesor );
		}
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

	public String getCod( )
	{
		return cod;
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
			String hora0 = String.valueOf( hora[ 0 ].getHour( ) ).length( ) == 1 ? "0" + hora[ 0 ].getHour( ) : String.valueOf( hora[ 0 ].getHour( ) );
			String mins0 = String.valueOf( hora[ 0 ].getMinute( ) ).length( ) == 1 ? "0" + hora[ 0 ].getMinute( ) : String.valueOf( hora[ 0 ].getMinute( ) );
			String hora1 = String.valueOf( hora[ 1 ].getHour( ) ).length( ) == 1 ? "0" + hora[ 1 ].getHour( ) : String.valueOf( hora[ 1 ].getHour( ) );
			String mins1 = String.valueOf( hora[ 1 ].getMinute( ) ).length( ) == 1 ? "0" + hora[ 1 ].getMinute( ) : String.valueOf( hora[ 1 ].getMinute( ) );

			return hora0 + ":" + mins0 + " - " + hora1 + ":" + mins1;
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

	public void setCod( String cod )
	{
		this.cod = cod;
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

	@Override
	public Color getColor( )
	{
		return new Color( 0, 150, 150 );
	}

	@Override
	public String getName( )
	{
		return nombre;
	}

	@Override
	public String getToolTip( )
	{
		return "";
	}

	@Override
	public Calendar[ ][ ] getCalendarEvents( )
	{
		if( dias != null && !dias.isEmpty( ) )
		{
			String[ ] d = dias.split( "" );

			Calendar[ ][ ] eventos = new Calendar[ d.length ][ 2 ];

			ArrayList<String> days = new ArrayList<>( Arrays.asList( new String[ ]
			{
					"D",
					"L",
					"M",
					"I",
					"J",
					"V",
					"S"
			} ) );

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
}