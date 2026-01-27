package com.example.fhubo.Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.fhubo.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilmOptionsDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_popup_help1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_edit).setOnClickListener {
            Toast.makeText(context, "Editar (próximamente)", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        view.findViewById<TextView>(R.id.tv_delete).setOnClickListener {
            Toast.makeText(context, "Eliminar (próximamente)", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        view.findViewById<TextView>(R.id.tv_add_favorite).setOnClickListener {
            Toast.makeText(context, "Añadido a favoritos (próximamente)", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        view.findViewById<TextView>(R.id.tv_share).setOnClickListener {
            Toast.makeText(context, "Compartir (próximamente)", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }
}
