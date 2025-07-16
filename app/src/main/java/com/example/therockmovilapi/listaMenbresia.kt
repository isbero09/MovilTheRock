package com.example.therockmovilapi

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.therockmovilapi.Apis.MenbresiaApiService
import kotlinx.coroutines.launch

class listaMenbresia : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_menbresia, container, false)

        val rv_lista_membresia = view.findViewById<RecyclerView>(R.id.rv_lista_membresia)

        view.findViewById<Button>(R.id.btn_nueva_membresia).setOnClickListener {
            findNavController().navigate(R.id.action_listaMenbresia_to_crearMembresia);
        }

        lifecycleScope.launch {
            try {
                val membresias = MenbresiaApiService
                    .getApiManager()
                    .getMenbresia()
                rv_lista_membresia.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_membresia.adapter = lista_membresia_adapter(membresias)
            } catch (e: Exception) {
                val message = "Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Membresía")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }


        return view
    }
}