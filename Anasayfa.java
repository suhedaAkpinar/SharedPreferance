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

public class Anasayfa extends Activity {
	Button cikis,kayit_sil_cikis;
	SharedPreferences preferences; //preferences nesne referansı
	SharedPreferences.Editor editor; //preferences editor nesnesi referansı .prefernces nesnesine veri ekleyip cıkarmak için
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anasayfa);

		preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//preferences nesnesi oluşturuluyor ve prefernces referansına bağlanıyor
		editor = preferences.edit(); //aynı şekil editor nesnesi oluşturuluyor
		
		cikis = (Button) findViewById(R.id.button1);
		cikis.setOnClickListener(new View.OnClickListener() {//çıkış butonu tıklanınca 
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(Anasayfa.this);//Bilgi alert ediliyor
    	        alertDialog.setTitle("Bilgi");
    	        alertDialog.setMessage("Çıkış Yaptıktan Sonra Aynı Bilgilerle Giriş Yapabilirsiniz");
    	        alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog,int which) {
    	            	//Burdaki amaç çıkış butonu ile kullanıcı bilgilerini silmeden çıkış yapmaktır.
    	            	//Kullanıcı bilgileri silinmeyip sharedpreference üzerinde tutulduğu için aynı bilgilerie tekrardan giriş yapılabilir.
    	            	//login değerini değiştirerek cıkıs yaptığı için değeri false yapıyoruz.
    	            	
    	            	editor.putBoolean("login", false);//uygulamaya tekrar girdiğinde kontrol için kullanılcak
    	            	editor.commit();
    	            	Intent i = new Intent(getApplicationContext(),LoginSayfa.class);
    	        		startActivity(i);
    	        		finish();

    	            }
    	        });
    	        alertDialog.show();
				
			}
		});
		
		kayit_sil_cikis = (Button) findViewById(R.id.button2);
		kayit_sil_cikis.setOnClickListener(new View.OnClickListener() {//SharedPreferences değerlerini  tamamen siler
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(Anasayfa.this);
    	        alertDialog.setTitle("Bilgi");
    	        alertDialog.setMessage("Kayıt Bilgileriniz Tamamen Silinecek.Tekrar Kayıt Olmanız Gerekecek ");
    	        alertDialog.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
    	            public void onClick(DialogInterface dialog,int which) {
    	            	
    	            	//Burda değerlerin hepsi silindiği için tekrardan uygulamaya giriş yapabilmesi için kayıt olmalıdır
    	            	editor.clear();//sharedpreferences değerlerinn hepsini siler
    	            	editor.commit();
    	            	
    	            	Intent i = new Intent(getApplicationContext(),LoginSayfa.class);
    	        		startActivity(i);
    	        		finish();
    	            }
    	        });
    	        alertDialog.show();
				
			}
		});
	}
}
