package org.monik.pruebamonik;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


import com.ohka.interesse.interesselib.model.request.BeneficiariosRequest;
import com.ohka.interesse.interesselib.model.request.LoginRequest;
import com.ohka.interesse.interesselib.model.request.PhoneRequest;
import com.ohka.interesse.interesselib.model.response.LoginResponse;
import com.ohka.interesse.interesselib.model.response.PhoneResponse;
import com.ohka.interesse.interesselib.view.ViewNotification;
import com.ohka.interesse.interesselib.ws.BeneficiariosClient;
import com.ohka.interesse.interesselib.ws.LoginClient;
import com.ohka.interesse.interesselib.ws.PhoneClient;

public class MainActivity extends Activity implements ViewNotification {
	Button button;
	Button button_phone;
	Button button_beneficiarios;
	Context mcontexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mcontexto = getApplicationContext();
		
		addListenerOnButton();
		addListenerOnButtonPhone();
		addListenerOnButtonBeneficiarios();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	LoginClient lc  = new LoginClient();
	PhoneClient pc = new PhoneClient();
	BeneficiariosClient bc = new BeneficiariosClient();	
	
	public void addListenerOnButton() {
    	 	lc.setController(this);
			button = (Button) findViewById(R.id.button1);			
			 button.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	System.out.println("Inicia....");
	         		LoginRequest rq = new LoginRequest();
	         		rq.setPassword("1751d49d");
	         	    rq.setUser("eframirez@ohkasystems.com");
	         		lc.login(rq, mcontexto);
	         		
	         			         		
	             }
	         });
			
		}

	public void addListenerOnButtonPhone() {
		pc.setController(this);
		button = (Button) findViewById(R.id.button2);			
		 button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	System.out.println("Buscando Telefonos....");
         		PhoneRequest pr = new PhoneRequest();
         		pr.setId_aseguradora("13");
         		pc.insurer_phone(pr, mcontexto);
         	         			         	
             }
         });
		
	}
	
	public void addListenerOnButtonBeneficiarios() {
		bc.setController(this);
		button = (Button) findViewById(R.id.button3);			
		 button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	System.out.println("Buscando Beneficiarios....");
         		BeneficiariosRequest pr = new BeneficiariosRequest();
         		pr.setUser("113163");
         		bc.getBeneficiarios(pr, mcontexto);         	         			         	
             }
         });
		
	}


	@Override
	public void receivedData(Object r) {
		// TODO Auto-generated method stub
		// Aquí tendría que recibir el VO de Salida de la llamada al WS 
		
		if(r instanceof LoginResponse){
			LoginResponse respuesta = (LoginResponse)r;
			Log.d("HASTA VIEW","wiii por fin xD "+respuesta.getNombre());
		}else if(r instanceof PhoneResponse){
			PhoneResponse respuesta = (PhoneResponse)r;
			Log.d("HASTA VIEW","otra veeez xD "+respuesta.getAseguradora());
		}
		
	}

	@Override
	public void receivedDataError(Object r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receivedDataServerError(Object r) {
		// TODO Auto-generated method stub
		
	}

}
