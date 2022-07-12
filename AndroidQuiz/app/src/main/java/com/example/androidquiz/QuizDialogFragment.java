package com.example.androidquiz;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizDialogFragment extends DialogFragment {

    public interface DialogClickListener {
        void dialogListenerSave(String newQuestionAmt);
        void dialogListenerCancel();
    }
    public DialogClickListener listener;
    public static final String Tag = "tag";
/*    TODO: Rename parameter arguments, choose names that match
     the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

     TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/

    public QuizDialogFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static QuizDialogFragment newInstance() {
        QuizDialogFragment fragment = new QuizDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz_dialog, container, false);
        EditText newAmount = v.findViewById(R.id.newQuestionAmount);
        Button save = v.findViewById(R.id.saveNewQuestions);
        Button cancel = v.findViewById(R.id.CancelChange);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!newAmount.getText().toString().isEmpty())
                    listener.dialogListenerSave(newAmount.getText().toString());
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.dialogListenerCancel();
                dismiss();
            }
        });

        return v;
    }
}