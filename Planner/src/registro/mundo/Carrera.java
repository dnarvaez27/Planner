package registro.mundo;

import java.util.ArrayList;

public class Carrera
{
	private ArrayList<Materia> materias;

	private String nombre;

	private String url;

	public Carrera( String nombre, String url )
	{
		materias = new ArrayList<>( );
		this.nombre = nombre;
		this.url = url;
	}

	public ArrayList<Materia> getMaterias( )
	{
		return materias;
	}

	public String getNombre( )
	{
		return nombre;
	}

	public String getUrl( )
	{
		return url;
	}

	public void setMaterias( ArrayList<Materia> materias )
	{
		this.materias = materias;
	}

	@Override
	public String toString( )
	{
		return nombre;
	}

}