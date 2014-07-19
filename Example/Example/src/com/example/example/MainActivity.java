package com.example.example;

import java.io.IOException;
import java.net.URISyntaxException;

import org.hova.hover.sdk.common.SDKTools;
import org.hova.hover.sdk.http.Response;
import org.hova.hover.sdk.pojo.User;
import org.hova.hover.sdk.user.UserResource;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void saveUser(View v) throws IOException {
		
		SDKTools.initialize("sdk_config.properties", getApplicationContext());
		
		// Create my user
		User user = new User();
		user.setBirthday("2014-01-01");
		user.setBranch_id("495b65dc-d25c-467c-85e3-adfac2210a2a");
		user.setColoruser("black");
		user.setGender("MALE");
		user.setLanguage("es");
		user.setLastname("ALVAREZ");
		user.setLastname2("VAZQUEZ");
		user.setName("CARLOS VAN SCHEIDER");
		user.setPhase("phase1");
		user.setProfile_id("55c4e2db-d6c9-4a0b-ab76-d3023c5d0166");
		user.setUser_id("53922ae7-9568-45ac-a000-90e65ac20a62");
		// For user
		UserResource userResource = new UserResource();
		// Store responses
		Response response;

		// ******************* Register User *****************************//
		// ------------------------\/-------------------------------------//
		try {
			response = userResource.create(user);
			System.out.println("HTTP Status Code " + response.getHttpcode());
			System.out.println("HTTP Response Body " + response.getBody());

		} catch (IllegalStateException | IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
