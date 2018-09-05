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
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    /*
     ParseUser user = new ParseUser();
    user.setUsername("Avinash");
    user.setPassword("Avinash");
    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        if(e == null){
          Log.i("Avinash Sig up",  "Ok we did it");

        }else
        {
          e.printStackTrace();
        }
      }
    });*/

    ParseUser.logInInBackground(
            "Avinash",
            "Avinash",
            new LogInCallback() {
              @Override
              public void done(ParseUser user, ParseException e) {
                if(user !=null )
                {
                    Log.i("Avinash User Logged" , "user Logged");

                }else
                {
                    Log.i("Avinash User Logged" , "user failed to Logged");
                }
              }
            }
    );

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}