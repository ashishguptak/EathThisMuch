package com.example.chkee.mealplanner;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.SharePhotoContent;

import java.util.Arrays;


public class MainFragment extends android.support.v4.app.Fragment {
    private CallbackManager mCallbackManager;
    private LoginManager manager;

    private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {

            SharedPreferences shre = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor edit=shre.edit();


            AccessToken accessToken = loginResult.getAccessToken();

            edit.putString("accessToken", accessToken.toString());

            edit.commit();
            // LoginManager.getInstance().logInWithPublishPermissions(MainFragment, Arrays.asList("publish_actions"));
            // shareButton.setVisibility(View.VISIBLE);
            // shareButton.setOnClickListener(this);
            startActivity(new Intent(getContext(),HomeActivity.class));
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {
            //shareButton.setVisibility(View.INVISIBLE);
        }
    };

    public MainFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        //    List<String> permissionNeeds = Arrays.asList("publish_actions");
        manager = LoginManager.getInstance();
        manager.registerCallback(mCallbackManager,mCallback);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onViewCreated(View view,Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton=(LoginButton) view.findViewById(R.id.login_button);
        // shareButton=(Button)view.findViewById(R.id.fb_share_button);
        // shareButton.se
        // shareButton.setReadPermissions(Arrays.asList("publish_actions"));
        // shareButton.setFragment(this);
        //  loginButton.setReadPermissions("user_photos");
        // loginButton.setReadPermissions(Arrays.asList("user_checkins"));
        loginButton.setPublishPermissions(Arrays.asList("publish_actions"));
        loginButton.setFragment(this);
        loginButton.registerCallback(mCallbackManager, mCallback);
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

}
