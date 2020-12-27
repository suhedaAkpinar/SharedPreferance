package com.mobilhanem.sharedpreferencs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Kayit extends Activity{

	Button kayit_ol;
	EditText isim,email,sifre;
	String isim_string,email_string,sifre_string;
	SharedPreferences preferences; //preferences nesne referansı
	SharedPreferences.Editor editor; //preferences editor nesnesi referansı .prefernces nesnesine veri ekleyip cıkarmak için
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kayit);

		kayit_ol = (Button) findViewById(R.id.button1);
		isim = (EditText)findViewById(R.id.editText1);
		email = (EditText)findViewById(R.id.editText2);
		sifre = (EditText)findViewById(R.id.editText3);
		
		
		kayit_ol.setOnClickListener(new View.OnClickListener() {//Kayıt ol butonu tıklanınca
			
			@Override
			public void onClick(View v) {
				isim_string = isim.getText().toString();
				email_string = email.getText().toString();
				sifre_string = sifre.getText().toString();
				if(isim_string.matches("") || email_string.matches("") || sifre_string.matches("")){//Veriler Boş  ise
					
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(Kayit.this);
        	        alertDialog.setTitle("Uyarı");
        	        alertDialog.setMessage("Eksiksiz Doldurunz!");
        	        alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
        	            public void onClick(DialogInterface dialog,int which) {
        	            	
        	            }
        	        });
        	        alertDialog.show();
				}else{//veriler boş değilse kayıt işlemine geçilir
					
					preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//preferences nesnesi oluşturuluyor ve prefernces referansına bağlanıyor
					editor = preferences.edit(); //aynı şekil editor nesnesi oluşturuluyor
					
					
					editor.putString("isim", isim_string); //isim değeri 
					editor.putString("email", email_string);//email değeri
					editor.putString("sifre", sifre_string);//şifre değeri
					editor.putBoolean("login", true);//uygulamaya tekrar girdiğinde kontrol için kullanılcak
					//editor.putInt("sayısalDeger", 1000);// uygulamamızda kullanılmıyor ama göstermek amacıyla
					
					editor.commit();//yapılan değişiklikler kaydedilmesi için editor nesnesinin commit() metodu çağırılır.
					//Değerlerimizi sharedPreferences a kaydettik.Artık bu bilgiler ile giriş yapabiliriz.
					Intent i = new Intent(getApplicationContext(),Anasayfa.class);
	        		startActivity(i);
	        		finish();
				}
				
			}
		});
	}
}
