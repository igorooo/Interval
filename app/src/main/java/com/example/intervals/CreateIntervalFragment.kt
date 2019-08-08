package com.example.intervals

import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsSeekBar
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CreateIntervalFragment : Fragment() {
    private var listener: OnCreateIntervalInteractionListener? = null
    private lateinit var mTextView_exercise_time : TextView
    private lateinit var mTextView_break_time : TextView
    private lateinit var mSeekBar_exercise_time: SeekBar
    private lateinit var mSeekBar_break_time: SeekBar
    private lateinit var mButton_submit_time : ImageButton
    private lateinit var mRecyclerView_avilable_exercises : RecyclerView
    private lateinit var mRecyclerView_interval_exercises : RecyclerView
    private lateinit var mFloatingButton_add_exercise : FloatingActionButton
    private lateinit var mExercise: Exercise



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_create_interval, container, false)
        initView(view)
        initListeners()

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnCreateIntervalInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnCreateIntervalInteractionListener")
        }

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initView(view : View){
        mTextView_exercise_time = view.findViewById(R.id.TextView_exercise_time)
        mTextView_break_time = view.findViewById(R.id.TextView_break_time)
        mSeekBar_exercise_time = view.findViewById(R.id.SeekBar_exercise_time)
        mSeekBar_break_time = view.findViewById(R.id.SeekBar_break_time)
        mButton_submit_time = view.findViewById(R.id.Button_submit_time)
        mRecyclerView_avilable_exercises = view.findViewById(R.id.RecyclerView_avilable_exercises)
        mRecyclerView_interval_exercises = view.findViewById(R.id.RecyclerView_interval_exercises)
        mFloatingButton_add_exercise = view.findViewById(R.id.FLoatingButton_add_exercise)
    }

    private fun initListeners(){
        mFloatingButton_add_exercise.setOnClickListener{v -> onAddExerciseButtonClick()}
    }

    private fun onAddExerciseButtonClick(){
        listener!!.onCreateIntervalInteractionListener()
    }

    fun captureExerciseFromMainActivity(exercise: Exercise?){
        // TODO shared prefs array with exercises
    }

    interface OnCreateIntervalInteractionListener {
        fun onCreateIntervalInteractionListener()
    }
}
