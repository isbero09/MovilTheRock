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
import androidx.navigation.fragment.findNavController
import com.example.therockmovilapi.Apis.ProductoApiService
import com.example.therockmovilapi.Entities.Producto
import kotlinx.coroutines.launch

class editarProducto : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_editar_producto, container, false)

        lifecycleScope.launch{
            val producto = ProductoApiService.getApiManager()
                .getProducto(arguments?.getInt("id") ?: 0)

            view.findViewById<EditText>(R.id.producto_nombre_editado)
                .setText(producto.nombre)

            view.findViewById<EditText>(R.id.producto_precio_editado)
                .setText(producto.precio.toString())

            view.findViewById<EditText>(R.id.producto_stock_editado)
                .setText(producto.stock.toString())

            view.findViewById<EditText>(R.id.producto_categoriaid_editado)
                .setText(producto.categoria_id.toString())

            view.findViewById<EditText>(R.id.producto_descripcion_editado)
                .setText(producto.descripcion)

            view.findViewById<EditText>(R.id.producto_estado_editado)
                .setText(producto.estado)
        }

        view.findViewById<Button>(R.id.btn_update_producto).setOnClickListener {
            val nombre = view.findViewById<EditText>(R.id.producto_nombre_editado)
                .text.toString().trim()

            val precio = view.findViewById<EditText>(R.id.producto_precio_editado)
                .text.toString().trim().toDouble()

            val stock = view.findViewById<EditText>(R.id.producto_stock_editado)
                .text.toString().trim().toInt()

            val categoria_id = view.findViewById<EditText>(R.id.producto_categoriaid_editado)
                .text.toString().trim().toInt()

            val descripcion = view.findViewById<EditText>(R.id.producto_descripcion_editado)
                .text.toString().trim()

            val estado = view.findViewById<EditText>(R.id.producto_estado_editado)
                .text.toString().trim()

            lifecycleScope.launch {
                try {
                    ProductoApiService.getApiManager()
                        .putProducto(
                            Producto(
                                arguments?.getInt("id") ?: 0,
                                nombre,
                                precio,
                                stock,
                                categoria_id,
                                descripcion,
                                estado
                            ),
                            arguments?.getInt("id") ?: 0
                        )
                    Toast.makeText(
                        context, "Producto actualizado exitosamente",
                        Toast.LENGTH_SHORT
                    ).show()

                    findNavController().navigate(R.id.action_editarProducto_to_verProducto, Bundle().apply {
                        putInt("id", arguments?.getInt("id") ?: 0)
                    })
                } catch (e: Exception) {
                    Toast.makeText(
                        context, "Error al actualizar producto: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


        return view
    }
}