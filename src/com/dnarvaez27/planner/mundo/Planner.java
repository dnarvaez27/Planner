package com.dnarvaez27.planner.mundo;

import java.util.LinkedList;
import java.util.List;

public class Planner
{
	private LinkedList<Materia> materias;
	
	private Recuperacion recuperacion;
	
	public Planner( )
	{
		recuperacion = new Recuperacion( );
		
		materias = new LinkedList<>( );
	}
	
	public boolean agregarMateria( Materia materia )
	{
		if( !materias.contains( materia ) )
		{
			materias.add( materia );
			return true;
		}
		return false;
	}
	
	public void eliminarMateria( Materia materia )
	{
		materias.remove( materia );
	}
	
	public List<Materia> getMaterias( )
	{
		return materias;
	}
	
	public Recuperacion getRecuperacion( )
	{
		return recuperacion;
	}
	
	public static void main( String[ ] args )
	{
		Planner pl = new Planner( );
		
		try
		{
			pl.getRecuperacion( ).iniciarCarreras( );
			LinkedList<Carrera> c = pl.getRecuperacion( ).getCarreras( );
			
			for( Carrera carrera : c )
			{
				
				pl.getRecuperacion( ).getMaterias( carrera );
				
				for( Materia m : carrera.getMaterias( ) )
				{
					if( m.getSalon( ).equals( ".ML_604" ) )
					{
						System.out.println( m.getName( ) + ": " + m .getSalon( ) );
					}
				}
			}
			
		}
		catch( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace( );
		}
		
	}
}