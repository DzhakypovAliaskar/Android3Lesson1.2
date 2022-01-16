package com.example.android3lesson11.ui.detail_films;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;

import com.example.android3lesson11.App;
import com.example.android3lesson11.data.models.Film;
import com.example.android3lesson11.data.remote.OnDetailFilmsCallback;
import com.example.android3lesson11.databinding.FragmentDetailFilmsBinding;

public class DetailFilmsFragment extends Fragment {




    private FragmentDetailFilmsBinding binding;
    private String id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailFilmsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        id = getArguments().getString("id");
        getInformation();
    }

    private void getInformation() {
        Log.e("TAG", id);
        App.apiService.getDetailFilms(id, new OnDetailFilmsCallback() {
            @SuppressLint("SetTextI18n")
            @Override
            public void success(Film film) {
                binding.descriptionFilm.setText(film.getDescription());
                binding.nameFilm.setText(film.getTitle());
                binding.timeFilm.setText(Integer.toString(film.getRunningTime()));
                binding.directorFilm.setText(film.getDirector());
                Glide.with(binding.getRoot()).load(film.getImage()).centerCrop().into(binding.imageIv);
            }

            @Override
            public void serverError() {
                Log.e("TAG", "serverError: ");
            }

            @Override
            public void failure(String msg) {
                Log.e("TAG", msg);
            }
        });
    }
}