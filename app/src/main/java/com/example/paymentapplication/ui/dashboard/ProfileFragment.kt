package com.example.paymentapplication.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.paymentapplication.R
import com.example.paymentapplication.databinding.FragmentProfileBinding
import com.example.paymentapplication.ui.dashboard.profile.activity.notification.NotificationProfileActivity
import com.example.paymentapplication.ui.dashboard.profile.activity.profile.ProfileActivity
import com.example.paymentapplication.ui.dashboard.profile.activity.security.SecurityActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton


class ProfileFragment : Fragment() {

    companion object{
        private val TAG = "Profile Fragment"
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var myContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)



        return binding.root
    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editProfileBtn.setOnClickListener {
            startActivity(Intent(myContext, ProfileActivity::class.java))
        }

        binding.notificationBtn.setOnClickListener {
            startActivity(Intent(myContext, NotificationProfileActivity::class.java))
        }

        binding.securityBtn.setOnClickListener {
            startActivity(Intent(myContext, SecurityActivity::class.java))
        }

        binding.logoutBtn.setOnClickListener {
            val mBottomSheetDialog = BottomSheetDialog(myContext)
            val sheetView: View = layoutInflater.inflate(R.layout.logout_bottomsheet_layout, null)
            mBottomSheetDialog.setContentView(sheetView)
            mBottomSheetDialog.show()

            val cancelBtn = sheetView.findViewById<MaterialButton>(R.id.cancel_btn)
            val logoutBtn = sheetView.findViewById<MaterialButton>(R.id.yes_logout_btn)

            cancelBtn.setOnClickListener {
                mBottomSheetDialog.dismiss()
            }

            logoutBtn.setOnClickListener {
                mBottomSheetDialog.dismiss()
            }
        }

    }

}