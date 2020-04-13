package com.example.newsreader.utils.dialogs;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.newsreader.R;

public class ErrorDialog extends DialogFragment {
    
    public static ErrorDialog newInstance() {
        Bundle args = new Bundle();
        
        ErrorDialog fragment = new ErrorDialog();
        fragment.setArguments( args );
        
        return fragment;
    }
    
    /**
     * Builds ErrorDialog.
     *
     * @param savedInstanceState The last saved instance state of the Fragment or null if this is a
     *                           freshly created Fragment.
     * @return Return a new Dialog instance to be displayed by the Fragment.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog( @Nullable Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        
        builder.setTitle( R.string.error_dialog_title );
        builder.setMessage( R.string.error_dialog_message );
        builder.setNegativeButton( getString( R.string.error_dialog_button ), ( dialog, which ) -> dismiss() );
        
        return builder.create();
    }
    
}
