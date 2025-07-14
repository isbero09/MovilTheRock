package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class login : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val correoInput = view.findViewById<EditText>(R.id.email_edit_text)
        val passwordInput = view.findViewById<EditText>(R.id.password_edit_text)
        val loginButton = view.findViewById<Button>(R.id.ir_a_menu_btn)

        loginButton.setOnClickListener{
            val correo = correoInput.text.toString()
            val password = passwordInput.text.toString()

            if (correo == "admin" && password == "12345"){
                findNavController().navigate(R.id.action_login_to_menu)
            }else{
                Toast.makeText(requireContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}