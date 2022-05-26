package sn.bambey.dico;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Rechercher extends Activity 

{

	//Declaration des Boutons,EditText, ...
	private Button traduction = null;
	private Button ajouter = null;
	private Button modifier = null;
	private Button lister = null;
	private Button supprimer = null;
	
	private  EditText recherchetexte1 = null;
	private  TextView resultat = null;
	
	private String str1 = new String();	
	private String str2 = new String();

	
 
    protected void onCreate(Bundle savedInstanceState) 
    
    {
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layoutrecherche);
        
        
        final  SQLiteDatabase mabase = openOrCreateDatabase("lettre",MODE_PRIVATE,null); //Creation de la base de donnees (Lettre).
    	mabase.execSQL("CREATE TABLE IF NOT EXISTS fw(id INTEGER PRIMARY KEY AUTOINCREMENT ,fr TEXT NOT NULL, wl TEXT NOT NULL);"); //Creation de la table Francais_Wolof "fw".
     
        //Instanciation des boutons
        traduction = new Button(this);
        ajouter = new Button(this);
        modifier = new Button(this);
        lister = new Button(this);
        supprimer = new Button(this);
        resultat = new TextView(this);
        
        recherchetexte1 = new EditText(this);       
        
        
        //Recuperation des vues.
        traduction = (Button) findViewById(R.id.traduction);
        ajouter = (Button) findViewById(R.id.ajouter);
        modifier = (Button) findViewById(R.id.modifier);
        lister = (Button) findViewById(R.id.lister);
        supprimer = (Button) findViewById(R.id.supprimer);        
        recherchetexte1 = (EditText)findViewById(R.id.edittext1);       
        resultat = (TextView)findViewById(R.id.textviewfrwl);
     
	   
  //******************************************************************************************************************************************************************************************************      
        
	 
	   recherchetexte1.addTextChangedListener(new TextWatcher() 
		 
														{
														
														@Override
														public void onTextChanged(CharSequence s, int start, int before, int count)
																	{
															                 
															
																	       resultat.setText(""); //On remet le texte par defaut des que l'on commence a ecrire de nouveau dans le textView "edt2".
																	
																	}
							
							
																	public void beforeTextChanged(CharSequence s, int start, int count, int after)
																	{
																	// TODO Auto-generated method stub
																	
																	}
							
							
																	public void afterTextChanged(Editable s) 
																	{
																	// TODO Auto-generated method stub
																	
																	}
							                                  }
                                            );

	  //**************************************************************************************************************************************
	 
	   
	//Ecoute du Bouton traduction   
	    traduction.setOnClickListener(new OnClickListener() 
        
		{													
			@Override
			public void onClick(View v) 
			
						{
				
				            try {
				            	
											           str2=  recherchetexte1.getText().toString();  //Recuperation du texte saisie
											           str2= str2.toLowerCase();  //Formatage en minuscules pour eviter les conflits de casse.
											           str1 = str2.trim();  //Suppression des espaces        
								                              
												
											            if(!str1.equals(""))
													            {
																	    Cursor resultSet = mabase.rawQuery("Select * from fw where fr= '"+str1+"' or wl='"+str1+"' ",null);
																        resultSet.moveToFirst();
																        
																		 String fr = resultSet.getString(1);
																		 String wl = resultSet.getString(2);
																		 
																		 if (fr.equals(str1))
																			      {
																			        resultat.setText(wl);																			
																			      }  
																		 
																		 else   {
																			      resultat.setText(fr);	
																		        }
																	 
													            }
											            
											            else 
											            		{																		            	        
											            	        Toast.makeText(Rechercher.this, "Veuillez saisir un mot à traduire !",Toast.LENGTH_SHORT).show();
											            		}
													
				               } 
				            
							catch (Exception e) 
							
											{
												e.printStackTrace();
												Toast.makeText(Rechercher.this, "Ce mot n'existe pas dans le Dictionnaire!",Toast.LENGTH_SHORT).show(); 
											}
				             
							
						             
				        }
			
         }
);
	    
//*********************************************************************************************************************************************************
        
        ajouter.setOnClickListener(new OnClickListener() 
										                
        												{													
															@Override
															public void onClick(View v)
															
																		{

																            Intent int1 = new Intent(getApplicationContext(),Ajouter.class); //Instanciation d'un Intent "int1".
																            startActivity(int1); //Lancement de l'activite avec notre Intent "int1".																	
																        }
															
									                     }
                                    );
        
        
        
        
        modifier.setOnClickListener(new OnClickListener() 
        
									{													
										@Override
										public void onClick(View v)
										
													{
							
											            Intent int2 = new Intent(getApplicationContext(),Modifier.class); //Instanciation d'un Intent "int2".
											            startActivity(int2); //Lancement de l'activite avec notre Intent "int2".																	
											        }
										
							         }
							);
        
        
        
        lister.setOnClickListener(new OnClickListener() 
        
									{													
										@Override
										public void onClick(View v)
										
													{
							
											            Intent int3 = new Intent(getApplicationContext(),Lister.class); //Instanciation d'un Intent "int3".
											            startActivity(int3); //Lancement de l'activite avec notre Intent "int3".																	
											        }
										
							         }
							);
							        
        
        supprimer.setOnClickListener(new OnClickListener() 
        
										{													
											@Override
											public void onClick(View v)
											
														{
								
												            Intent int4 = new Intent(getApplicationContext(),Supprimer.class); //Instanciation d'un Intent "int4".
												            startActivity(int4); //Lancement de l'activite avec notre Intent "int4".																	
												        }
											
								         }
								);
     
    }

   
    
}
