package com.utilidad.calcularDimesiones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TableRow
import android.widget.Toast
import androidx.core.view.children
import com.utilidad.calcularDimesiones.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner = binding.spFormato
        val adaptador = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            getArrayFormatos())
        spinner.adapter = adaptador
        spinner.setSelection(2)

        binding.btnLimpiar.setOnClickListener {
            val cajaAncho = binding.etAncho
            cajaAncho.text.clear()
            val cajaAlto = binding.etAlto
            cajaAlto.text.clear()
        }

        binding.btnCalcular.setOnClickListener {
            val cajaAncho = binding.etAncho.text.toString()
            val cajaAlto = binding.etAlto.text.toString()

            if (cajaAlto.isNotEmpty() && cajaAncho.isNotEmpty()) {
                val ancho = cajaAncho.toDouble()
                val alto = cajaAlto.toDouble()

                val tabla = binding.tlResultados
                tabla.visibility = View.VISIBLE
                for (fila in tabla.children) {
                    fila as TableRow
                    fila.setBackgroundColor(getColor(R.color.bg))
                }


                var formato = 0
                when (spinner.selectedItemPosition) {
                    0 -> {
                        formato = Constantes.MDPI
                        binding.filaM.setBackgroundColor(getColor(R.color.boton))
                    }
                    1 -> {
                        formato = Constantes.HDPI
                        binding.filaH.setBackgroundColor(getColor(R.color.boton))
                    }
                    2 -> {
                        formato = Constantes.XHDPI
                        binding.filaX.setBackgroundColor(getColor(R.color.boton))
                    }
                    3 -> {
                        formato = Constantes.XXHDPI
                        binding.filaXX.setBackgroundColor(getColor(R.color.boton))
                    }
                    else -> {
                        formato = Constantes.XXXHDPI
                        binding.filaXXX.setBackgroundColor(getColor(R.color.boton))

                    }
                }

                // ANCHO
                binding.xxxAncho.text = obtenerNuevaMedida(formato, Constantes.XXXHDPI, ancho).toString()
                binding.xxAncho.text = obtenerNuevaMedida(formato, Constantes.XXHDPI, ancho).toString()
                binding.xAncho.text = obtenerNuevaMedida(formato, Constantes.XHDPI, ancho).toString()
                binding.hAncho.text = obtenerNuevaMedida(formato, Constantes.HDPI, ancho).toString()
                binding.mAncho.text = obtenerNuevaMedida(formato, Constantes.MDPI, ancho).toString()

                // ALTO
                binding.xxxAlto.text = obtenerNuevaMedida(formato, Constantes.XXXHDPI, alto).toString()
                binding.xxAlto.text = obtenerNuevaMedida(formato, Constantes.XXHDPI, alto).toString()
                binding.xAlto.text = obtenerNuevaMedida(formato, Constantes.XHDPI, alto).toString()
                binding.hAlto.text = obtenerNuevaMedida(formato, Constantes.HDPI, alto).toString()
                binding.mAlto.text = obtenerNuevaMedida(formato, Constantes.MDPI, alto).toString()
            }
        }
    }

    private fun obtenerNuevaMedida(tipoOrigen: Int, tipoNuevo: Int, medida: Double) :Double {
        val base: Double = when (tipoOrigen) {
            Constantes.XXXHDPI -> medida / 4
            Constantes.XXHDPI -> medida / 3
            Constantes.XHDPI -> medida / 2
            Constantes.HDPI -> medida /1.5
            else -> medida / 1
        }

        val final = when (tipoNuevo) {
            Constantes.XXXHDPI -> base*4
            Constantes.XXHDPI -> base*3
            Constantes.XHDPI -> base*2
            Constantes.HDPI -> base*1.5
            else -> base*1
        }
        return final
    }

    private fun getArrayFormatos() : ArrayList<String>{
        val lista = arrayListOf<String>()
        lista.add("MDPI")
        lista.add("HDPI")
        lista.add("XHDPI")
        lista.add("XXHDPI")
        lista.add("XXXHDPI")
        return lista
    }

}

object Constantes {
    const val XXXHDPI = 1
    const val XXHDPI = 2
    const val XHDPI = 3
    const val HDPI = 4
    const val MDPI = 5
}