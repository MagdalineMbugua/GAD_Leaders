package com.magda.gadleaders.userInterface.activities;


import android.os.Bundle;
import android.os.PatternMatcher;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.magda.gadleaders.R;
import com.magda.gadleaders.models.Project;
import com.magda.gadleaders.userInterface.fragments.CertainDialogFragment;

import java.util.Objects;

import static com.magda.gadleaders.utils.Constants.PROJECT;

public class SubmitProjectActivity extends AppCompatActivity {
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmailAddress;
    private EditText mProjectLink;
    private Button mSubmit;
    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);


        initViews();

        setSupportActionBar(mToolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_press);

        getSupportActionBar().setLogo(R.drawable.gads);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mSubmit.setOnClickListener(v -> toCheckDataFilled());

    }

    private void toCheckDataFilled() {
        String firstName = mFirstName.getText().toString().trim();
        String lastName = mLastName.getText().toString().trim();
        String emailAddress = mEmailAddress.getText().toString().trim();
        String projectLink =mProjectLink .getText().toString().trim();
        if (firstName.isEmpty() || lastName.isEmpty() || emailAddress.isEmpty() || projectLink.isEmpty()) {
            View view = findViewById(R.id.submitProjectActivity);
            Snackbar snackbar = Snackbar.make(view, "Ooops! All fields required.", Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(getColor(R.color.colorAccent));
            snackbar.show();
        } else {
            if(Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
                toVerifySubmission(firstName,lastName,emailAddress, projectLink);
            } else {
                mEmailAddress.requestFocus();
                mEmailAddress.setError("Wrong email format");
            }



        }
    }

    private void toVerifySubmission(String firstName, String lastName, String emailAddress, String projectLink) {
        Project project = new Project (firstName,lastName,emailAddress,projectLink);
        CertainDialogFragment verifyDialog = new CertainDialogFragment();
        Bundle arg = new Bundle();
        arg.putParcelable(PROJECT,project);
        verifyDialog.setArguments(arg);
        verifyDialog.show(getSupportFragmentManager(),"Certain Dialog Fragment");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initViews() {
        mFirstName = findViewById(R.id.etFirstName);
        mLastName = findViewById(R.id.etLastName);
        mEmailAddress = findViewById(R.id.etEmailAddress);
        mProjectLink = findViewById(R.id.etProjectLink);
        mSubmit = findViewById(R.id.btSubmit);
        mToolbar = findViewById(R.id.toolbar);
    }
}