package com.example.livingcostapp.presentation.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.livingcostapp.R

class WelcomeScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                WelcomeScreenView(
                    onLoginClick = {
                        // Obsługa nawigacji do ekranu logowania
                        //    findNavController().navigate
                    }
                )
            }
        }
    }
}

