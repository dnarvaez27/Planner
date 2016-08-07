package com.dnarvaez27.planner.utilidades;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.dnarvaez27.planner.utilidades.Constantes.Local;

public class BaseDeDatos
{
	private Connection connection;

	private static final String MAX = "32672";

	public BaseDeDatos( ) throws SQLException, Exception
	{
		File data = new File( Local.PATH_SQL );
		System.setProperty( "derby.system.home", data.getAbsolutePath( ) );
		conectarDB( );
		// TODO INI TABLES
	}

	private void conectarDB( ) throws SQLException, Exception
	{
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		Class.forName( driver ).newInstance( );

		String url = "jdbc:derby:fundacion;create=true";
		connection = DriverManager.getConnection( url );
	}

	public void desconectarBD( ) throws SQLException
	{
		connection.close( );
		String down = "jdbc:derby:;shutdown=true";
		try
		{
			DriverManager.getConnection( down );
		}
		catch( SQLException e )
		{
			// Al bajar la base de datos se produce siempre una excepción
		}
	}

	private void inicializarTablaMaterias( )
	{
		String comando = "";
		comando += "CREATE TABLE Materias ";
		comando += "( ";
		comando += "cod vaarchar(10), ";
		comando += "creditos real, ";
		comando += "crn varchar(5) PRIMARY KEY, ";
		comando += "cupos int, ";
		comando += "descripcion varchar( " + MAX + " ), ";
		comando += "dias varchar( 32 ), ";
		comando += "inscritos int, ";
		comando += "nombre varchar( " + MAX + " ), ";
		comando += "salon varchar( 32 ), ";
		comando += "seccion int, ";
		comando += "";
	}
}