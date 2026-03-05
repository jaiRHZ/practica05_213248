package mx.edu.itson.practica5

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class Catalogo : AppCompatActivity() {

    var adapter: PeliculaAdapter? = null
    var seriesAdapter: PeliculaAdapter?= null
    var peliculas = ArrayList<Pelicula>()
    var series = ArrayList<Pelicula>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        cargarPeliculas()
        cargarSeries()

        adapter = PeliculaAdapter(this, peliculas)
        seriesAdapter = PeliculaAdapter(this, series)

        var gridPelis: GridView = findViewById(R.id.movies_catalog)
        var gridSeries: GridView = findViewById(R.id.series_catalog)

        gridPelis.adapter = adapter
        gridSeries.adapter = seriesAdapter
    }

    fun cargarPeliculas(){
        peliculas.add(Pelicula("Dune", R.drawable.dune,R.drawable.dunedos, "Aventura en el desierto"))
    }

    fun cargarSeries(){
        series.add(Pelicula("Halo", R.drawable.halo, R.drawable.halos, "Acompaña al Halo Verdecito en Reach"))
    }

    class PeliculaAdapter: BaseAdapter {
        var contexto: Context? = null
        var peliculas = ArrayList<Pelicula>()

        constructor(contexto: Context, peliculas: ArrayList<Pelicula>) {
            this.contexto = contexto
            this.peliculas = peliculas
        }

        override fun getCount(): Int {
            return peliculas.size
        }

        override fun getItem(position: Int): Any {
            return peliculas[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var pelicula = peliculas[position]
            var inflator = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.pelicula, null)
            var image: ImageView = vista.findViewById(R.id.image_movie_cell)
            var title: TextView = vista.findViewById(R.id.movie_title_cell)

            image.setImageResource(pelicula.image)
            title.setText(pelicula.titulo)

            image.setOnClickListener {
                var intento = Intent(contexto, detalle_pelicula::class.java)
                intento.putExtra("titulo", pelicula.titulo)
                intento.putExtra("sinopsis", pelicula.sinopsis)
                intento.putExtra("header", pelicula.header)
                intento.putExtra("image", pelicula.image)

                contexto!!.startActivity(intento)
            }

            return vista
        }


    }

}