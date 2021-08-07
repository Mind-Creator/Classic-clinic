package ru.ivanzotov.classicclinic.ui.articles;

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
import ru.ivanzotov.classicclinic.databinding.FragmentArticlesBinding;


public class ArticlesFragment extends Fragment {

    private ArticlesViewModel articlesViewModel;
    private FragmentArticlesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        articlesViewModel =
                new ViewModelProvider(this).get(ArticlesViewModel.class);

        binding = FragmentArticlesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textArticles;
        articlesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) { textView.setText(s); }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}