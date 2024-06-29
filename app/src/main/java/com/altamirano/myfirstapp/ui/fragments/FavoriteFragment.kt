package com.altamirano.myfirstapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.altamirano.myfirstapp.R
import com.altamirano.myfirstapp.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.bind(
            inflater.inflate(
                R.layout.fragment_favorites,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()

    }

    private fun initListeners() {
//Aqui realizo la navegation
/*
        binding.btnGoToListar.setOnClickListener {
            findNavController()
                .navigate(
                    FavoritesFragmentDirections.actionFavoritesFragmentToModificarFragment("Jhonatan")

                )
        }*/

    }
}