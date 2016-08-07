package com.dnarvaez27.planner.mundo;

import com.dnarvaez27.http.HttpGET;

public class TestPost
{
	public TestPost( )
	{
		try
		{
			HttpGET httpGET = new HttpGET( "http://www.w3schools.com/tags/tryit.asp?filename=tryhtml_form_method_post" );
			String rta = httpGET.getResponse( );
			rta = rta.replace( "<input type=\"text\" name=\"fname\">", "<input type=\"text\" name=\"fname\" value=\"David\">" );
			System.out.println( rta );
		}
		catch( Exception e )
		{

		}
	}

	public static void main( String[ ] args )
	{
		new TestPost( );
	}
}
