package mx.edu.itson.practica5

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class detalle_pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        val detalle = intent.extras

        if (detalle != null) {
            findViewById<TextView>(R.id.tv_nombre_pelicula).text = detalle.getString("titulo")
            findViewById<TextView>(R.id.tv_pelicula_desc).text = detalle.getString("sinopsis")
            findViewById<TextView>(R.id.tv_pelicula_desc).movementMethod = ScrollingMovementMethod()
            findViewById<ImageView>(R.id.iv_pelicula_imagen).setImageResource(detalle.getInt("header"))
        }
    }
}