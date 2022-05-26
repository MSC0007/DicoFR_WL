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

public class Ajouter extends Activity

{
	
    //Declaration des Boutons, EditText, etc
	private Button ajoutretour = null;
	private Button ajouter = null;
	
	private EditText edittextajouter1 = null;
	private EditText edittextajouter2 = null;
	
	private String str1 = new String();
	private String str2 = new String();
	
	private String str3 = new String();
	private String str4 = new String();
	
	

	protected void onCreate(Bundle savedInstanceState) 
	
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layoutajouter);		
		
		final  SQLiteDatabase mabase = openOrCreateDatabase("lettre",MODE_PRIVATE,null); //Creation de la base de donnees (lettre).
	 	mabase.execSQL("CREATE TABLE IF NOT EXISTS fw(id INTEGER PRIMARY KEY AUTOINCREMENT ,fr TEXT NOT NULL, wl TEXT NOT NULL);");//Creation de la table Francais_Wolof "fw".
	 	
		
		//Instanciation des Boutons
		ajoutretour = new Button(this);
		ajouter = new Button(this);
		
		edittextajouter1 = new EditText(this);
		edittextajouter2 = new EditText(this);
		
		
		//Recuperation des vues
		ajoutretour = (Button)findViewById(R.id.ajouterretour);
		ajouter = (Button)findViewById(R.id.ajoutermot);
		
		
		edittextajouter1 = (EditText)findViewById(R.id.edittextajoutfr);
		edittextajouter2 = (EditText)findViewById(R.id.edittextajoutwl);
		
		
        
		//Ecoute des boutons
		ajouter.setOnClickListener(new OnClickListener()
							{
								
								@Override
								public void onClick(View v) 
								
										{
									         									
									            str3 = edittextajouter1.getText().toString();
									            str3= str3.toLowerCase(); //Formatage en minuscules pour eviter les conflits de casse.
									            
									            str4 = edittextajouter2.getText().toString();
									            str4= str4.toLowerCase(); //Formatage en minuscules pour eviter les conflits de casse.
									            
									            str1 = str3.trim(); //Suppression des espaces
									            str2 = str4.trim(); //Suppression des espaces
									           
									           if (!str1.equals("") && !str2.equals(""))
									                 {
									           
									            	        mabase.execSQL("INSERT INTO fw(id,fr,wl) VALUES(NULL,'"+str1+"','"+str2+"');");
									            			Toast.makeText(Ajouter.this, "Mot ajouté!",Toast.LENGTH_SHORT).show(); 										
									            		
									                 }
									           
									           else 
									           		{
									        	         Toast.makeText(Ajouter.this, "Veuillez saisir un mot et son correspondant !",Toast.LENGTH_SHORT).show(); 									        	   
									           		}
									           
										}
							});

		
	
		
		ajoutretour.setOnClickListener(new OnClickListener()
												{
													
													@Override
													public void onClick(View v) 
													
															{
														         Intent int1 = new Intent(getApplicationContext(),Rechercher.class); //Instanciation d'un Intent "int1".
												                 startActivity(int1); //Lancement de l'activite avec notre Intent "int1".
																
															}
												});
		
		
      }


}
