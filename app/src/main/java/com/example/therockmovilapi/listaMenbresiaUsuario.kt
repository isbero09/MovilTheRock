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
import com.example.therockmovilapi.Apis.MenbresiasUsuarioApiService
import kotlinx.coroutines.launch

class listaMenbresiaUsuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lista_menbresia_usuario, container, false)

        val rv_lista_membresiausuario = view.findViewById<RecyclerView>(R.id.rv_lista_membresiausuario)

        view.findViewById<Button>(R.id.btn_nueva_membresiausuario).setOnClickListener {
            findNavController().navigate(R.id.action_listaMenbresiaUsuario_to_crearMenbresiausuario);
        }

        lifecycleScope.launch {
            try {
                val membresiasUsuario = MenbresiasUsuarioApiService
                    .getApiManager()
                    .getMenbresiasUsuario()
                rv_lista_membresiausuario.layoutManager = LinearLayoutManager(requireContext())
                rv_lista_membresiausuario.adapter = lista_membresiausuario_Adapter(membresiasUsuario)
            } catch (e: Exception) {
                val message = "Error: ${e.localizedMessage}"
                AlertDialog.Builder(view.context)
                    .setTitle("Membresía Usuario")
                    .setMessage(message)
                    .setPositiveButton("OK", null)
                    .show()
            }
        }


        return view
    }
}