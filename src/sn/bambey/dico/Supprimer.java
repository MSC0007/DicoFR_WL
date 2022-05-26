package sn.bambey.dico;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Supprimer extends Activity 

{
	
	
	//Declaration des Boutons,EditText,etc...
    private  Button supprimer = null;
    private Button supprimerretour = null;
    
    private EditText edittextsupprimerfr = null;
	private EditText edittextsupprimerwl = null;
    
    private String str1 = new String();
    private String str2 = new String();
    private String str3 = new String();
    private String str4 = new String();
    
    
	protected void onCreate(Bundle savedInstanceState)
	
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layoutsupprimer);
		
		final  SQLiteDatabase mabase = openOrCreateDatabase("lettre",MODE_PRIVATE,null); //Creation de la base de donnees (lettre).
    	mabase.execSQL("CREATE TABLE IF NOT EXISTS fw(id INTEGER PRIMARY KEY AUTOINCREMENT ,fr TEXT NOT NULL, wl TEXT NOT NULL);");//Creation de la table Francais_Wolof "fw".
		
		//Initialisation des Boutons
		supprimer = new Button(this);
		supprimer = (Button)findViewById(R.id.supprimermot);
		supprimerretour = new Button(this);
		
		//Recuperation des vues
		supprimerretour = (Button)findViewById(R.id.supprimerretour);
		edittextsupprimerfr = (EditText)findViewById(R.id.edittextsupprimerfr);
		edittextsupprimerwl = (EditText)findViewById(R.id.edittextsupprimerwl);
		
		
		//Ecoute des boutons
		supprimer.setOnClickListener(new OnClickListener()
											{
												
												
												public void onClick(View v)
															{
													
													           try {
																
																	             str3 = edittextsupprimerfr.getText().toString();
																	             str3= str3.toLowerCase();  //Formatage en minuscules pour eviter les conflits de casse.
																	             
															                     str4 = edittextsupprimerwl.getText().toString();
															                     str4= str4.toLowerCase();  //Formatage en minuscules pour eviter les conflits de casse.
															                     
															                     str1 = str3.trim(); //Suppression des espaces
																		         str2 = str4.trim(); //Suppression des espaces
															                    
															                    if  (str1.equals("") && str2.equals(""))
														                    	    {
														                    	        Toast.makeText(Supprimer.this, "Veuillez saisir un mot à supprimer !",Toast.LENGTH_SHORT).show();
														                    	    }
															                    
															                    if (!str1.equals("") || !str2.equals(""))
																                    {																                   
																		                  
																		                    Cursor resultSet = mabase.rawQuery("Select * from fw where fr= '"+str1+"' or wl='"+str1+"'  or fr='"+str2+"'  or wl='"+str2+"' ",null);
																					        resultSet.moveToFirst();
																					        
																							 String fr = resultSet.getString(1);
																							 String wl = resultSet.getString(2);
																							 
																							 
																							  
																			                   
																			                    if ((fr.equals(str1) || fr.equals(str2)) || (( wl.equals(str1) || wl.equals(str2))))
																			                    {																                   
																					                    mabase.execSQL("DELETE  from fw  where fr='"+str1+"' or wl= '"+str2+"' ");																			
																					                    Toast.makeText(Supprimer.this, "Mot Supprimé!",Toast.LENGTH_SHORT).show();
																			                    }
																			               
																                    }
															            
																	 } 
													           
																catch (Exception e)
																		            
																       {
																          Toast.makeText(Supprimer.this, "Ce mot n'existe pas dans le Dictionnaire !",Toast.LENGTH_SHORT).show(); 
																	   }
													           
													        }
												           
											});
		
		
		supprimerretour.setOnClickListener(new OnClickListener()
												{
													
													@Override
													public void onClick(View v)
																{
																	Intent int1 = new Intent(getApplicationContext(), Rechercher.class);
																	startActivity(int1);
																	
																}
												});
				
	}

	
}
