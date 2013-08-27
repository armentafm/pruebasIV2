package org.monik.pruebamonik;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.ohka.interesse.interesselib.model.LoginRequest;
import com.ohka.interesse.interesselib.model.LoginResponse;
import com.ohka.interesse.interesselib.view.ViewNotification;
import com.ohka.interesse.interesselib.ws.LoginClient;

public class MainActivity extends Activity implements ViewNotification {
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		addListenerOnButton();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	LoginClient lc  = new LoginClient();
	 public void addListenerOnButton() {
    	 lc.setController(this);
			button = (Button) findViewById(R.id.button1);			
			 button.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	 Context context = getApplicationContext();
	            	 System.out.println("Inicia....");
	         		LoginRequest rq = new LoginRequest();
	         		//rq.setPassword("minovio188");
	         		//rq.setUser("ysuarez@ohkasystems.com");
	         		
	         		rq.setPassword("1751d49d00");
	         	    rq.setUser("eframirez@ohkasystems.com");
	         		lc.login(rq, context);
	             }
	         });
			
		}



	@Override
	public void receivedData(Object r) {
		// TODO Auto-generated method stub
		// Aquí tendría que recibir el VO de Salida de la llamada al WS 
		LoginResponse respuesta = (LoginResponse)r;
		Log.d("HASTA VIEW","wiii por fin xD "+respuesta.getMensaje());
	}

}
