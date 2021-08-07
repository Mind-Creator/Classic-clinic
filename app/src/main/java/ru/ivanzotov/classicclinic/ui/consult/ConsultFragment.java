package ru.ivanzotov.classicclinic.ui.consult;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ru.ivanzotov.classicclinic.R;
import ru.ivanzotov.classicclinic.databinding.FragmentConsultBinding;

public class ConsultFragment extends Fragment {

    private ConsultViewModel consultViewModel;
    private FragmentConsultBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        consultViewModel =
                new ViewModelProvider(this).get(ConsultViewModel.class);

        binding = FragmentConsultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textConsult;
        consultViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}