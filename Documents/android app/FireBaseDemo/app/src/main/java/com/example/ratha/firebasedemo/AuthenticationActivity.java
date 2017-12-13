package com.example.ratha.firebasedemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ratha.firebasedemo.dialog.CreateAccountDialog;
import com.example.ratha.firebasedemo.entity.User;
import com.example.ratha.firebasedemo.util.UserSession;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

public class AuthenticationActivity extends AppCompatActivity implements
        CreateAccountDialog.DialogCallBack{

    private static final String TAG = "FireBase Auth";
    private static final int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private  static String email ="rathanavoy@gmail.com";
    private String[] loginTypes={"With Email","With Phone","With Facebook","With Google"};
    //UI render object
    private RadioGroup rdGroup;
    private EditText edUserName,edPassword;
    private Button btnLogin, btnSignUp;
    LinearLayout signUpLayout;
    private SignInButton signInButton;
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    private static String loginType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        rdGroup=findViewById(R.id.rdGroup);
        edPassword=findViewById(R.id.edPassword);
        edUserName=findViewById(R.id.edUserName);
        signUpLayout=findViewById(R.id.signUpLayout);
        btnSignUp=findViewById(R.id.btnSignUp);
        signInButton =findViewById(R.id.googleSignIn);
        //setup firebase Auth
        //get FirebaeAuth instance
        mAuth=FirebaseAuth.getInstance();
        //check if user is sign-in and update UI accordingly
        currentUser=mAuth.getCurrentUser();
        configureGoogleSignIn();
        loginType=loginTypes[0];
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rd=findViewById(group.getCheckedRadioButtonId());
                if(rd.isChecked()){
                    loginType=rd.getText().toString();
                    Log.e(TAG, "onCheckedChanged: "+loginType);
                }

            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });
    }

    private void googleSignIn() {
        Intent signInIntent= mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account=task.getResult(ApiException.class);
                Log.e(TAG, "onActivityResult: work" );
                firebaseAuthWithGoogle(account);
            }catch (ApiException e){
                Log.e(TAG, "onActivityResult: "+e.toString() );
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential= GoogleAuthProvider.getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.e(TAG, "onComplete: success" );
                            FirebaseUser user= mAuth.getCurrentUser();
                            String mess=" Name :"+user.getDisplayName()+" Email: "+user.getEmail()+
                                    " image : "+user.getPhotoUrl()+
                                    " Uid "+user.getUid();
                            Log.e(TAG, "onComplete: "+ mess);
                        }else{
                            Log.e(TAG, "onComplete: failure", task.getException());
                        }
                    }
                });
    }

    public void configureGoogleSignIn(){
        //GoogleSignInOptions gso;
        //GoogleSignInClient mGoogleSignInClient;
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();

        // update UI
    }

    public void onLoginWithEmailPassword(View view) {

    }

    private void createUserLogin(String userName,String password){
        mAuth.createUserWithEmailAndPassword(userName,password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.e(TAG ,TAG+":success");
                    //update UI
                    FirebaseUser firebaseUser=mAuth.getCurrentUser();
                    User user=new User();
                    user.setPassword(firebaseUser.getEmail());
                    user.setName(firebaseUser.getDisplayName());
                    UserSession.saveUser(AuthenticationActivity.this,user);

                }else{
                    Log.e(TAG,"CreateUserWithEmailPassword: failure",task.getException());
                    //update UI
                }
            }
        });
    }

    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        Log.e(TAG,"you Logout ");
    }

    public void onLoginWithEmailPasswordSingOut(View view) {
        signOut();
    }

    public void onLoginWithEmailPasswordUpdate(View view) {

    }

    public void onLogin(View view) {

        if (loginType.equalsIgnoreCase(loginTypes[0])){
            loginWithEmail();
            Log.e(TAG, "onLogin: "+loginType );
        }else if(loginType.equalsIgnoreCase(loginTypes[1])){
            Log.e(TAG, "onLogin: "+loginType );
        }else if(loginType.equalsIgnoreCase(loginTypes[2])){
            Log.e(TAG, "onLogin: "+loginType );
        }else if(loginType.equalsIgnoreCase(loginTypes[3])){
            Log.e(TAG, "onLogin: "+loginType );
        }
    }

    private void loginWithEmail() {
        String name=edUserName.getText().toString();
        String password=edPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(name,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user=new User();
                                FirebaseUser firebaseUser=mAuth.getCurrentUser();
                                user.setEmail(firebaseUser.getEmail());
                                UserSession.saveUser(AuthenticationActivity.this,user);
                                Toast.makeText(AuthenticationActivity.this, "Authentication success",
                                        Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "signInWithEmail:"+user.toString());
                            }else {
                                // If sign in fails, display a message to the user.
                                Log.e(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(AuthenticationActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

            User user =UserSession.getUserSession(AuthenticationActivity.this);
            if(null!=user) Log.e(TAG, "loginWithEmail: "+user.toString() );

    }

    public void onCreateAccount(View view) {
        new CreateAccountDialog().show(getFragmentManager(),"create account dialog");
    }

    @Override
    public void saveUserAccount(String name, String email) {
        createUserLogin(name,email);
    }

    public void onLogout(View view) {
        signOut();
    }

    public void SignInWithGoogle(){

    }

    public void onGoogleSignOut(View view) {
        FirebaseAuth.getInstance().signOut();
    }
}
