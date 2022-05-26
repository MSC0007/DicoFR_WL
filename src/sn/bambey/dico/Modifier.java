package sn.bambey.dico;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modifier extends Activity 

{
	//Declaration des Boutons
	private Button modifier = null;
	private Button modifierretour = null;
	
	String str1 = new String();
	String str2 = new String();
	
	private EditText editfr;
	private EditText editwl;
	private String str3;
	private String str4;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layoutmodifier);
		
		final  SQLiteDatabase mabase = openOrCreateDatabase("lettre",MODE_PRIVATE,null); //Creation de la base de donnees (lettre).
    	mabase.execSQL("CREATE TABLE IF NOT EXISTS fw(id INTEGER PRIMARY KEY AUTOINCREMENT ,fr TEXT NOT NULL, wl TEXT NOT NULL);");//Creation de la table Francais_Wolof "fw".
		
		
		//Instanciation des Boutons
		modifier = new Button(this);
		modifier = (Button)findViewById(R.id.modifiermot);
		
		editfr = new EditText (this);
		editwl = new EditText (this);
		modifierretour = new Button(this);
		
		//Recuperation des vues		
		editfr = (EditText)findViewById(R.id.edittextmodifierfr);
		editwl = (EditText)findViewById(R.id.edittextmodifierwl);		
		
		modifierretour = (Button)findViewById(R.id.modifierretour);
		
		
		//Ecoute des Boutons
		modifier.setOnClickListener(new OnClickListener() 
								{
									
									@Override
									public void onClick(View v)
										 		{
										             try 
										             {
															
														             str3 = editfr.getText().toString();
														             str3= str3.toLowerCase(); //Formatage en minuscules pour eviter les conflits de casse.
														             
												                     str4 = editwl.getText().toString();
												                     str4= str4.toLowerCase(); //Formatage en minuscules pour eviter les conflits de casse.
												                     
												                     str1 = str3.trim(); //Suppression des espaces
															         str2 = str4.trim(); //Suppression des espaces
												                  
												                    if (!str1.equals("") && !str2.equals(""))
													                 {
												                    
																	    	mabase.execSQL("UPDATE fw  set fr='"+str1+"' where wl='"+str2+"'");
																	    	mabase.execSQL("UPDATE fw  set wl='"+str2+"' where fr='"+str1+"'");																     
																	    	
																			Toast.makeText(Modifier.this, "Mot modifié!",Toast.LENGTH_SHORT).show(); 
													                 }
												                    
												                    else 
												                    		{
												                    	       Toast.makeText(Modifier.this, "Vous devez saisir un mot et son correspondant pour le modifier !",Toast.LENGTH_SHORT).show(); 
												                    	
												                    		}
										             } 
										             
													  catch (Exception e)
														{
														    Toast.makeText(Modifier.this, "Ce mot n'existe pas dans le Dictionnaire !",Toast.LENGTH_SHORT).show();
														}
													
										 		
										 		}
								});
		
		
		
						modifierretour.setOnClickListener(new OnClickListener() 
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
