package com.example.app_proyecto2.view.Lista.NavigationBar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app_proyecto2.databinding.FragmentListaActividadesBinding

class ListaActividadesFragment : Fragment() {

    //private lateinit var listaActividadesViewModel: ListaActividadesViewModel
    private var _binding: FragmentListaActividadesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //listaActividadesViewModel = ViewModelProvider(this).get(ListaActividadesViewModel::class.java)

        _binding = FragmentListaActividadesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}