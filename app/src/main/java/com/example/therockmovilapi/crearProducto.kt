package com.example.therockmovilapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.therockmovilapi.Apis.ProductoApiService
import com.example.therockmovilapi.Entities.Producto
import kotlinx.coroutines.launch

class crearProducto : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_crear_producto, container, false)

        view.findViewById<Button>(R.id.btn_add_producto).setOnClickListener {
            val nombre = view.findViewById<EditText>(R.id.producto_nombre_nuevo)
                .text.toString().trim()

            val precio = view.findViewById<EditText>(R.id.producto_precio_nuevo)
                .text.toString().trim().toDouble()

            val stock = view.findViewById<EditText>(R.id.producto_stock_nuevo)
                .text.toString().trim().toInt()

            val categoriaId = view.findViewById<EditText>(R.id.producto_categoria_id_nuevo)
                .text.toString().trim().toInt()

            val descripcion = view.findViewById<EditText>(R.id.producto_descripcion_nuevo)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.producto_estado_nuevo)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    ProductoApiService.getApiManager().postProducto(
                        Producto(0, nombre, precio, stock, categoriaId, descripcion,
                            estado)
                    )
                    Toast.makeText(
                        context, "Producto registrada exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al registrar producto: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        return view
    }

}