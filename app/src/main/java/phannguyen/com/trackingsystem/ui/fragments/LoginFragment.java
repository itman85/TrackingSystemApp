package phannguyen.com.trackingsystem.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phannguyen.com.trackingsystem.R;
import phannguyen.com.trackingsystem.Utils;
import phannguyen.com.trackingsystem.ui.HomeActivity;
import phannguyen.com.trackingsystem.ui.parse.ParseHelper;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        // Snippet from "Navigate to the next Fragment" section goes here.
        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        final TextInputEditText usernameEditText = view.findViewById(R.id.username_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLoginOk(usernameEditText.getText().toString(),passwordEditText.getText().toString())) {
                    passwordTextInput.setError("Login failed!");
                } else {
                    passwordTextInput.setError(null); // Clear the error
                    //The false parameter in navigateTo() tells the activity to not add the current fragment to the backstack,
                    // so the user will not be able to return to the login screen using their back key.
                    //((NavigationHost) getActivity()).navigateTo(new DashboardFragment(), false); // Navigate to the next Fragment
                    getActivity().startActivity(new Intent(getActivity(), HomeActivity.class));
                    Utils.setLoginUser(getActivity(),usernameEditText.getText().toString());
                    ParseHelper.submitUserStatusToServer(usernameEditText.getText().toString(),1);


                }
            }
        });

        // Clear the error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText().toString())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });
        return view;
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.equals("123");
    }

    private boolean isLoginOk(String username,String password){
        return isPasswordValid(password)&&Utils.userCredentails.containsKey(username) ;
    }
}
