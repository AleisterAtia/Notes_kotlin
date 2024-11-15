package com.fadhiil2010.bukucatatan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadhiil2010.bukucatatan.Adapter.NoteAdapter
import com.fadhiil2010.bukucatatan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db : NoteDatabaseHelper
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        noteAdapter = NoteAdapter(db.getAllNotes(), this)

        binding.notesRecycle.layoutManager = LinearLayoutManager(this)
        binding.notesRecycle.adapter = noteAdapter



        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        val  notes = db.getAllNotes()
        Log.d("MainActivity","Notes count ${notes.size}")
        noteAdapter.refreshData(notes)
    }
}