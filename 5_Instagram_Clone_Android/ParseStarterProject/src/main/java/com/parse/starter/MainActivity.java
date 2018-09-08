/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

  public void signUpClikced(View view){
    EditText userNameEditText =(EditText) findViewById(R.id.usernameEditText);
    EditText passwordEditText =(EditText) findViewById(R.id.passwordEditText);

    if(userNameEditText.getText().toString() == "" ||
            passwordEditText.getText().toString() == "" ){
      Toast.makeText(this, "Username and password is Required", Toast.LENGTH_SHORT).show();
    }else
    {
        ParseUser user = new ParseUser();
        user.setUsername(userNameEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e== null){
                    Log.i("Avinash Sign up ", "Sucess");
                }else
                {
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}