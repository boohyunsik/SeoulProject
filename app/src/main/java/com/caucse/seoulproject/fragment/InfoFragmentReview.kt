package com.caucse.seoulproject.fragment

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


import com.caucse.seoulproject.R
import com.caucse.seoulproject.data.UserDTO
import com.caucse.seoulproject.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.fragment_info_fragment_review.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [InfoFragmentReview.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [InfoFragmentReview.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class InfoFragmentReview : Fragment() {
    private val TAG = this::class.java.simpleName
    private lateinit var mainViewModel : MainViewModel
    private lateinit var reviewRecyclerView : RecyclerView
    private lateinit var myInfo : List<String>
    private lateinit var linearLayoutManager : LinearLayoutManager
    private val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    private val firebaseSettings : FirebaseFirestoreSettings = FirebaseFirestoreSettings.Builder().setTimestampsInSnapshotsEnabled(true).build()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        Log.d(TAG, "onCreateView()")
        val view = inflater.inflate(R.layout.fragment_info_fragment_review, container, false)
        reviewRecyclerView = view.review_RecyclerView
        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        linearLayoutManager = object: LinearLayoutManager(activity?.applicationContext) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        reviewRecyclerView.layoutManager = linearLayoutManager
        val appButton: Button = view.findViewById(R.id.user_Review_Apply)
        val viewReview : EditText = view.findViewById(R.id.user_Review)
        Log.d(TAG, "$appButton")
        appButton.setOnClickListener{
            Log.d(TAG, "clicked")
            val tmpString = viewReview.text
            createData(tmpString.toString())
        }
        return view
    }

    fun createData(reviewText : String) {
        myInfo = mainViewModel.getMyInfo()
        var title = mainViewModel.getTitle()
        var userDTO = UserDTO(myInfo.get(14), (myInfo.get(5) + ":" + myInfo.get(6)), reviewText)
        Log.d(TAG, "$title")



        FirebaseFirestore.getInstance().collection(title).document(myInfo.get(14)+reviewText).set(userDTO).addOnSuccessListener {
            Log.d(TAG, "데이터입력성공")
        }.addOnFailureListener { exception ->
            Log.d(TAG, "데이터입력실패")
            Log.d(TAG,exception.toString())
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
