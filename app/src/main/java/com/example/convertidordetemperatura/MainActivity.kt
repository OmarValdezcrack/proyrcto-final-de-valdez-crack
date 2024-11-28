package com.example.convertidordetemperatura

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de las vistas
    private lateinit var temperatureInput: EditText
    private lateinit var originUnitSpinner: Spinner
    private lateinit var destinationUnitSpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de las vistas
        temperatureInput = findViewById(R.id.temperature_input)
        originUnitSpinner = findViewById(R.id.origin_unit_spinner)
        destinationUnitSpinner = findViewById(R.id.destination_unit_spinner)
        convertButton = findViewById(R.id.convert_button)
        resultText = findViewById(R.id.result_text)

        // Lista de unidades de temperatura
        val units = listOf("Celsius", "Fahrenheit", "Kelvin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Asignar el adaptador a los Spinners
        originUnitSpinner.adapter = adapter
        destinationUnitSpinner.adapter = adapter

        // Configuración del botón de conversión
        convertButton.setOnClickListener {
            convertTemperature()
        }
    }

    // Función para convertir la temperatura
    private fun convertTemperature() {
        val temperature = temperatureInput.text.toString().toDoubleOrNull()
        if (temperature != null) {
            val originUnit = originUnitSpinner.selectedItem.toString()
            val destinationUnit = destinationUnitSpinner.selectedItem.toString()

            // Lógica de conversión
            val result = when {
                originUnit == "Celsius" && destinationUnit == "Fahrenheit" -> (temperature * 9 / 5) + 32
                originUnit == "Celsius" && destinationUnit == "Kelvin" -> temperature + 273.15
                originUnit == "Fahrenheit" && destinationUnit == "Celsius" -> (temperature - 32) * 5 / 9
                originUnit == "Fahrenheit" && destinationUnit == "Kelvin" -> (temperature - 32) * 5 / 9 + 273.15
                originUnit == "Kelvin" && destinationUnit == "Celsius" -> temperature - 273.15
                originUnit == "Kelvin" && destinationUnit == "Fahrenheit" -> (temperature - 273.15) * 9 / 5 + 32
                else -> temperature
            }

            // Mostrar el resultado
            resultText.text = "$result $destinationUnit"
        } else {
            Toast.makeText(this, "Por favor ingresa una temperatura válida", Toast.LENGTH_SHORT).show()
        }
    }
}
