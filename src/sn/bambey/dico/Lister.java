package sn.bambey.dico;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Lister extends Activity

{

	
	private TextView listermots = null;
	String str1 = new String();
	
	private Button menu = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
			
			{
				super.onCreate(savedInstanceState);
				setContentView(R.layout.layoutlister);
				
				final  SQLiteDatabase mabase = openOrCreateDatabase("lettre",MODE_PRIVATE,null); //Creation de la base de donnees (lettre).
		    	mabase.execSQL("CREATE TABLE IF NOT EXISTS fw(id INTEGER PRIMARY KEY AUTOINCREMENT ,fr TEXT NOT NULL, wl TEXT NOT NULL);");//Creation de la table Francais_Wolof "fw".
				
				listermots = new TextView(this);
				
				listermots = (TextView)findViewById(R.id.textviewlister);
				
				menu = new Button(this);
				menu = (Button)findViewById(R.id.listermenu);
		    	
		    	Cursor resultSet = mabase.rawQuery("Select * from fw ",null);
		    	
		    	
		    	for(resultSet.moveToFirst(); !resultSet.isAfterLast(); resultSet.moveToNext())
		    	      {		    				        
						final String fr = resultSet.getString(1);
						final String wl = resultSet.getString(2);										
										
						str1+="\n"+fr;
						str1+="  <--->  "+wl;
					
		    	      }
		    	
		    	listermots.setText(str1);
		    	
		    	
				
				menu.setOnClickListener(new OnClickListener()
														{															
														
															public void onClick(View v) 
															
																	{
																         Intent int1 = new Intent(getApplicationContext(),Rechercher.class); //Instanciation d'un Intent "int1".
														                 startActivity(int1); //Lancement de l'activite avec notre Intent "int1".
																		
																	}
															
														}
				                           );
			
		}
				

	
}
