/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class StarterApplication extends Application {
//http://ec2-52-14-108-106.us-east-2.compute.amazonaws.com/login
  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("9d453866a31822a096c1fe6678a994837e377014")
            .clientKey("872796cc6267d4ee749f80cb68d5d876157c923f")
            .server("http://52.14.108.106:80/parse/")
            .build()
    );

   ParseObject score = new ParseObject("Score");
   score.put("username", "Tiwari1");
   score.put("score", 65);
   score.saveInBackground(new SaveCallback() {
       @Override
       public void done(ParseException e) {
         if(e == null){
             Log.i("Sucess",  "Saved the score");
         }else
         {

         }
       }
   });

      ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
      query.getInBackground("Vv94ev9ayn", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {
            if(e== null && object!=null ){
               String username = object.getString("username");
               Log.i("User name",username );
               Integer score = object.getInt("score");
                object.put("score", 85);
                object.saveInBackground();
                Log.i("User Score ",Integer.toString(score) );

            }
          }
      });


    ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
