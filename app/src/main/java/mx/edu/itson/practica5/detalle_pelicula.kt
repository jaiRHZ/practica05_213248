package mx.edu.itson.practica5

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class detalle_pelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pelicula)

        var ns = 0
        var id = -1
        val detalle = intent.extras
        var title = ""

        if (detalle != null) {
            ns = detalle.getInt("numberSeats")

            title = detalle.getString("titulo")!!
            findViewById<TextView>(R.id.movie_title_detail).text = detalle.getString("titulo")
            findViewById<TextView>(R.id.movie_desc).text = detalle.getString("sinopsis")
            findViewById<TextView>(R.id.movie_desc).movementMethod = ScrollingMovementMethod()
            findViewById<ImageView>(R.id.image_movie_detail).setImageResource(detalle.getInt("header"))
            findViewById<TextView>(R.id.seatsLeft).setText("$ns seats available")
            id = detalle.getInt("pos")
        }
        if (ns==0) {
            findViewById<Button>(R.id.buyTickets).isActivated = false
        } else {
            findViewById<Button>(R.id.buyTickets).setOnClickListener{
                val intent: Intent = Intent(this, SeatsSelection::class.java)

                intent.putExtra("movie", id)
                intent.putExtra("name", title)

                this.startActivity(intent)
            }
        }

    }
}