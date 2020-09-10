package com.magda.gadleaders.userInterface.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.magda.gadleaders.apis.ProjectPostApi;
import com.magda.gadleaders.R;
import com.magda.gadleaders.models.Project;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.magda.gadleaders.utils.Constants.PROJECT;


public class CertainDialogFragment extends AppCompatDialogFragment {
    private static final String TAG = "Certain Dialog Fragment";
    private ImageView mCancel;
    private Button mYes;
    private Project mProject;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
       LayoutInflater inflater =requireActivity().getLayoutInflater();
       View  view = inflater.inflate(R.layout.fragment_certain_dialog,null);


       Bundle arg = getArguments();
       if (arg!=null){
           mProject= arg.getParcelable(PROJECT);
       } else Log.d(TAG, "onCreateDialog: Error encountered: arg is null");

       AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
       builder.setView(view);
       Dialog dialog = builder.create();
       dialog.setCancelable(true);
       initViews(dialog,view,mProject);
        return dialog;
    }

    private void initViews(Dialog dialog, View view, Project project) {
        mCancel = view.findViewById(R.id.ivCancel);
        mYes = view.findViewById(R.id.btYes);

        mCancel.setOnClickListener(v -> toDismissDialog(dialog));
        mYes.setOnClickListener(v -> toSubmitProject(project));

    }

    private void toSubmitProject(Project project) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProjectPostApi.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProjectPostApi api = retrofit.create(ProjectPostApi.class);
        Call<ResponseBody> call = api.submitProject(project.getEmailAddress(),project.getFirstName(),
                project.getLastName(), project.getProjectLink());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SuccessDialogFragment successDialogFragment = new SuccessDialogFragment();
                successDialogFragment.show(getChildFragmentManager(),"Success Dialog fragment");

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
                errorDialogFragment.show(getChildFragmentManager(),"Error Dialog Fragment");

            }
        });

    }


    private void toDismissDialog(Dialog dialog) {
        dialog.dismiss();
    }
}