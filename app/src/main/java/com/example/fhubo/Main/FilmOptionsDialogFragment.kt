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

    private var film: Main? = null

    companion object {
        fun newInstance(film: Main): FilmOptionsDialogFragment {
            val fragment = FilmOptionsDialogFragment()
            fragment.film = film
            return fragment
        }
    }

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
            Toast.makeText(context, "Editar: ${film?.name}", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        view.findViewById<TextView>(R.id.tv_delete).setOnClickListener {
            film?.let { (activity as? MainActivity)?.deleteFilm(it.id) }
            dismiss()
        }

        view.findViewById<TextView>(R.id.tv_add_favorite).setOnClickListener {
            Toast.makeText(context, "Afegir a favorits: ${film?.name}", Toast.LENGTH_SHORT).show()
            dismiss()
        }


    }
}
