package com.example.accesosicenet.data.baseDatos

import com.example.accesosicenet.modelos.Cardex
import com.example.accesosicenet.modelos.CardexR

data class TablaCardexConPromedio(
    val cardexLista: List<Cardex>,
    val cardexObjeto: CardexR
)