package com.example.ads_dr2_tp1_tipcalculator

import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var txtTotalConta : EditText
    private lateinit var txtPessoas : EditText
    private lateinit var skGorjeta : SeekBar

    private val formatador = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTotalConta = this.findViewById<EditText>(R.id.txtTotalConta)
        txtTotalConta.setOnFocusChangeListener(this)

        txtPessoas = this.findViewById<EditText>(R.id.txtPessoas)
        txtPessoas.setOnFocusChangeListener(this)

        skGorjeta = this.findViewById<SeekBar>(R.id.skGorjeta)
        skGorjeta.setOnSeekBarChangeListener(this)



    }

    private fun atualizaDadosConta(){
        if (txtTotalConta.text.toString().isNotEmpty()
            && txtPessoas.text.toString().isNotEmpty()) {

            var valorConta = txtTotalConta.text.toString().toDouble()
            var qtdPessoas = txtPessoas.text.toString().toInt()

            var lblValorRealGorjeta = this.findViewById<TextView>(R.id.lblValorRealGorjeta)
            var valorRealGorjeta = valorConta * skGorjeta.progress / 100
            lblValorRealGorjeta.setText(formatador.format(valorRealGorjeta))

            var lblValorRealConta = this.findViewById<TextView>(R.id.lblValorRealConta)
            lblValorRealConta.setText(formatador.format(valorConta + valorRealGorjeta))
            var lblValorRealPorPessoa = this.findViewById<TextView>(R.id.lblValorRealPorPessoa)
            lblValorRealPorPessoa.setText(formatador.format((valorConta + valorRealGorjeta)/ qtdPessoas))


        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        this.atualizaDadosConta()

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

        var lblPercentualGorjeta = this.findViewById<TextView>(R.id.lblPercentualGorjeta)
        lblPercentualGorjeta.setText(skGorjeta.progress.toString() + "%")

        this.atualizaDadosConta()

    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}