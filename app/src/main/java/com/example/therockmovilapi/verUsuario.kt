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
import com.example.therockmovilapi.Apis.UsuarioApiService
import kotlinx.coroutines.launch
import kotlin.random.Random

class verUsuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_ver_usuario, container, false)

        view.findViewById<Button>(R.id.btn_ver_usuario_atras).setOnClickListener {
            findNavController().navigate(R.id.action_verUsuario_to_listaUsuario)
        }

        view.findViewById<Button>(R.id.btn_ver_usuario_eliminar).setOnClickListener {
            lifecycleScope.launch {
                UsuarioApiService.getApiManager()
                    .deleteUsuario(arguments?.getString("cedula") ?: "")
                findNavController()
                    .navigate(R.id.action_verUsuario_to_listaUsuario)
            }
        }

        view.findViewById<Button>(R.id.btn_ver_usuario_editar).setOnClickListener {
            val cedula = arguments?.getString("cedula")?: ""
            var bundle = Bundle().apply {
                putString("cedula", cedula)
            }

            findNavController().navigate(R.id.action_verUsuario_to_editarUsuario, bundle)
        }

        lifecycleScope.launch {
            val cedula = arguments?.getString("cedula") ?: return@launch

            val usuario = UsuarioApiService.getApiManager().getUsuario(cedula)

            view.findViewById<TextView>(R.id.tv_ver_usuario_cedula)
                .text = "Cédula: " + usuario.cedula

            view.findViewById<TextView>(R.id.tv_ver_usuario_nombre)
                .text = "Nombres: " + usuario.nombres

            view.findViewById<TextView>(R.id.tv_ver_usuario_apellido)
                .text = "Apellidos: " + usuario.apellidos

            view.findViewById<TextView>(R.id.tv_ver_usuario_email)
                .text = "Email: " + usuario.email

            view.findViewById<TextView>(R.id.tv_ver_usuario_telefono)
                .text = "Teléfono: " + usuario.telefono

            view.findViewById<TextView>(R.id.tv_ver_usuario_estado)
                .text = "Estado: " + usuario.estado

            view.findViewById<TextView>(R.id.tv_ver_usuario_fechanacimiento)
                .text = "Fecha de Nacimiento: " + usuario.fecha_nacimiento
        }

        return view
    }
}