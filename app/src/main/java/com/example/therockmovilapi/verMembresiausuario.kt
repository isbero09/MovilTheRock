package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.MenbresiasUsuarioApiService
import kotlinx.coroutines.launch

class verMembresiausuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_membresiausuario, container, false)

        view.findViewById<Button>(R.id.btn_ver_membresiausuario_atras).setOnClickListener {
            findNavController().navigate(R.id.action_verMembresiausuario_to_listaMenbresiausuario)
        }

        view.findViewById<Button>(R.id.btn_ver_membresiausuario_eliminar).setOnClickListener {
            lifecycleScope.launch {
                MenbresiasUsuarioApiService.getApiManager()
                    .deleteMenbresiasUsuario(arguments?.getInt("id") ?: 0)
                findNavController()
                    .navigate(R.id.action_verMembresiausuario_to_listaMenbresiausuario)
            }
        }


        view.findViewById<Button>(R.id.btn_ver_membresiausuario_editar).setOnClickListener {
            val id = arguments?.getInt("id")?: 0
            var bundle = Bundle().apply {
                putInt("id", id)
            }

            findNavController().navigate(R.id.action_verMembresiausuario_to_editarMembresiausuario, bundle)
        }

        lifecycleScope.launch {
            val membresiaUsuario = MenbresiasUsuarioApiService.getApiManager().getMenbresiasUsuario(
                arguments?.getInt("id") ?: 0
            )

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_id)
                .text = "ID: " + membresiaUsuario.id.toString()

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_usuario)
                .text = "Usuario: " + membresiaUsuario.usuario

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_membresia)
                .text = "Membresía: " + membresiaUsuario.membresia

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_membresiaid)
                .text = "ID Membresía: " + membresiaUsuario.membresia_id.toString()

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_precio)
                .text = "Precio: $" + membresiaUsuario.precio.toString()

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_fecha_pago)
                .text = "Fecha de Pago: " + membresiaUsuario.fecha_pago

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_fecha_inicio)
                .text = "Inicio: " + membresiaUsuario.fecha_inicio

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_fecha_fin)
                .text = "Fin: " + membresiaUsuario.fecha_fin

            view.findViewById<TextView>(R.id.tv_ver_membresiausuario_estado)
                .text = "Estado: " + membresiaUsuario.estado
        }


        return view
    }
}