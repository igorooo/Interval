package com.example.intervals

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.AdapterView.OnItemLongClickListener
import com.akaita.android.circularseekbar.CircularSeekBar
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CreateIntervalFragment(val storage: Storage) : Fragment() {
    private val TAG = "CreateIntervalFragment"

    private var listener: OnCreateIntervalInteractionListener? = null

    private lateinit var mFrameLayout_active_exercise: FrameLayout
    private lateinit var mTextView_active_exercise: TextView
    private lateinit var mTextView_exercise_time : TextView
    private lateinit var mTextView_break_time : TextView
    private lateinit var mSeekBar_exercise_time: CircularSeekBar
    private lateinit var mSeekBar_break_time: CircularSeekBar
    private lateinit var mButton_submit_exercise : ImageButton
    private lateinit var mListView_avilable_exercises : ListView
    private lateinit var mListView_interval_exercises : ListView
    private lateinit var mFloatingButton_add_exercise : FloatingActionButton
    private lateinit var mArrayOfExercises: ArrayList<Exercise>
    private lateinit var mArrayOfIntervalExercises: ArrayList<Exercise>
    private lateinit var mActiveExercise: Exercise
    private lateinit var mListAdapter_available_exercises: ListAdapter
    private lateinit var mListAdapter_interval_exercises: IntervalListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        var view = inflater.inflate(R.layout.fragment_create_interval, container, false)
        initView(view)
        initListeners()
        mArrayOfExercises = storage.getAll(MainActivity.STORAGE_POS_KEY)
        mArrayOfIntervalExercises = ArrayList()
        mListAdapter_available_exercises = ListAdapter()
        mListAdapter_interval_exercises = IntervalListAdapter()
        mListView_avilable_exercises.adapter = mListAdapter_available_exercises
        mListView_interval_exercises.adapter = mListAdapter_interval_exercises

        return view
    }

    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach")
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
        mFrameLayout_active_exercise = view.findViewById(R.id.FrameLayout_display_active_exercise)
        mTextView_active_exercise = view.findViewById(R.id.TextView_active_exercise)
        mTextView_exercise_time = view.findViewById(R.id.TextView_exercise_time)
        mTextView_break_time = view.findViewById(R.id.TextView_break_time)
        mSeekBar_exercise_time = view.findViewById(R.id.SeekBar_exercise_time)
        mSeekBar_break_time = view.findViewById(R.id.SeekBar_break_time)
        mListView_avilable_exercises = view.findViewById(R.id.ListView_avilable_exercises)
        mListView_interval_exercises = view.findViewById(R.id.ListView_interval_exercises)
        mFloatingButton_add_exercise = view.findViewById(R.id.FLoatingButton_add_exercise)
    }

    private fun initListeners(){
        mFloatingButton_add_exercise.setOnClickListener{v -> onAddExerciseButtonClick()}

        mListView_avilable_exercises.onItemClickListener = object : OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                mActiveExercise = mArrayOfExercises.get(position)
                notifyNewActiveExercise()
            }
        }

        mListView_avilable_exercises.onItemLongClickListener = object : OnItemLongClickListener {
            override fun onItemLongClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long): Boolean {
                mActiveExercise = mArrayOfExercises.get(position)
                notifyNewActiveExercise()
                onSubmitExerciseButtonClick()
                return true
            }
        }
    }

    private fun notifyNewActiveExercise() {
        mTextView_active_exercise.setText(mActiveExercise.name)
    }

    //name is a bit obsolate
    private fun onSubmitExerciseButtonClick() {
        mArrayOfIntervalExercises.add(mActiveExercise)
        mListAdapter_interval_exercises.notifyDataSetChanged()
    }

    private fun onAddExerciseButtonClick(){
        Log.d(TAG, "onAddExerciseButtonClick")
        listener!!.onCreateIntervalInteractionListener()
    }

    fun notifyNewExerciseAdded(){
        Log.d(TAG, "notifyNewExerciseAdded")
        mArrayOfExercises = storage.getAll(MainActivity.STORAGE_POS_KEY)
        mListAdapter_available_exercises.notifyDataSetChanged()
    }

    interface OnCreateIntervalInteractionListener {
        fun onCreateIntervalInteractionListener()
    }

    inner class ListAdapter : BaseAdapter() {
        override fun getView(position: Int,convertView: View?, viewGroup: ViewGroup?): View {
            var tempView = convertView
            if(convertView == null) {
                tempView = layoutInflater.inflate(R.layout.exercise_position_list, viewGroup, false)
            }

            val exercise = mArrayOfExercises.get(position)

            tempView!!.findViewById<TextView>(R.id.TextView_exercies_name_list).setText(exercise.name)

            return tempView
        }

        override fun getItem(p0: Int): Any {
            return mArrayOfExercises.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return mArrayOfExercises.size
        }
    }

    inner class IntervalListAdapter : BaseAdapter() {
        override fun getView(position: Int,convertView: View?, viewGroup: ViewGroup?): View {
            var tempView = convertView
            if(convertView == null) {
                tempView = layoutInflater.inflate(R.layout.exercise_position_list, viewGroup, false)
            }

            val exercise = mArrayOfIntervalExercises.get(position)

            tempView!!.findViewById<TextView>(R.id.TextView_exercies_name_list).setText(exercise.name)

            return tempView
        }

        override fun getItem(p0: Int): Any {
            return mArrayOfIntervalExercises.get(p0)
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return mArrayOfIntervalExercises.size
        }
    }
}
