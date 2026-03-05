package mx.edu.itson.practica5

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SeatsSelection : AppCompatActivity() {
    private var selectedSeat: String = ""
    private lateinit var movieName: String
    private lateinit var cliente: String
    private var movieImage: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seats_selection)

        // Obtener datos de la película desde el Bundle
        val title: TextView = findViewById(R.id.titleSeats)
        val bundle = intent.extras

        if (bundle != null) {
            movieName = bundle.getString("name") ?: "Película"
            movieImage = bundle.getInt("id", -1)
            cliente = bundle.getString("cliente_nombre") ?: "Cliente"
            title.text = movieName
        }

        // Configurar RadioGroups
        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

        // Listener para la selección de asientos
        val seatSelectionListener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            // El if evita crasheos cuando usamos clearCheck() (que devuelve -1)
            if (checkedId > -1) {
                val radioButton = findViewById<RadioButton>(checkedId)
                selectedSeat = radioButton.text.toString()

                // Desmarcar los otros grupos para simular selección única global
                when (group.id) {
                    R.id.row1 -> { row2.clearCheck(); row3.clearCheck(); row4.clearCheck() }
                    R.id.row2 -> { row1.clearCheck(); row3.clearCheck(); row4.clearCheck() }
                    R.id.row3 -> { row1.clearCheck(); row2.clearCheck(); row4.clearCheck() }
                    R.id.row4 -> { row1.clearCheck(); row2.clearCheck(); row3.clearCheck() }
                }
            }
        }

        row1.setOnCheckedChangeListener(seatSelectionListener)
        row2.setOnCheckedChangeListener(seatSelectionListener)
        row3.setOnCheckedChangeListener(seatSelectionListener)
        row4.setOnCheckedChangeListener(seatSelectionListener)

        // Botón de confirmación
        val confirm: Button = findViewById(R.id.confirm)
        confirm.setOnClickListener {
            // Validar que se haya elegido un asiento
            if (selectedSeat.isEmpty()) {
                Toast.makeText(this, "Por favor, selecciona un asiento", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ya no hay ResumeActivity, así que mostramos mensaje de éxito directamente
            Toast.makeText(this, "¡Listo! Asiento $selectedSeat reservado para $movieName.", Toast.LENGTH_LONG).show()

            // Opcional: Descomenta la siguiente línea si quieres que al confirmar
            // la pantalla se cierre y regreses a la lista de películas.
            // finish()
        }
    }
}