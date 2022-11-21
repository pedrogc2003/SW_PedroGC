package com.example.sw_pedrogc

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sw_pedrogc.Modelos.PersonajesResponse
import com.example.sw_pedrogc.adapter.PersonajesAdapter
import com.example.sw_pedrogc.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        binding.BGuardar.setOnClickListener{
            this.miDialogo()
        }

        binding.BListado.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val call: Response<PersonajesResponse> = getRetrofit().create(APIService::class.java).getPersonajes("https://swapi.dev/api/people/")
                val peli = call.body()
                runOnUiThread {
                    if (call.isSuccessful) {

                        val resultado = peli?.results ?: emptyList()
                        PersonajesAdapter = resultado
                        PersonajesAdapter.notifyDataSetChanged()
                        Toast
                            .makeText(this@MainActivity, PersonajesAdapter.PersonajesList.first().title, Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast
                            .makeText(this@MainActivity, "Ha ocurrido un error", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/people/")
            .addConverterFactory((GsonConverterFactory.create()))
            .build()
    }

    //Función para que el dialogo se muestre para darle a un Ok o Cancel y depende de lo que se seleccione, saldrá un mensaje u otros
    private fun miDialogo(){
        var miDialogo = AlertDialog.Builder(this)
        miDialogo.setTitle("Guardar")
        miDialogo.setMessage("¿Desea guardar el listado?")
        miDialogo.setCancelable(false)
            .setPositiveButton(android.R.string.ok, { dialog, which -> Toast.makeText(applicationContext,"Ha guardado la acción", Toast.LENGTH_SHORT).show()})
            .setNegativeButton(android.R.string.cancel,  { dialog, which -> Toast.makeText(applicationContext, "Ha cancelado la acción", Toast.LENGTH_SHORT).show() }).show()
    }
}