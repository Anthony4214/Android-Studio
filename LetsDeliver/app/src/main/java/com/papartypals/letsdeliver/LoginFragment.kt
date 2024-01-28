package com.papartypals.letsdeliver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        auth = FirebaseAuth.getInstance()

        view.signInButton.setOnClickListener {
            signInAnonymously()
        }

        return view
    }

    private fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                } else {
                    // If sign-in fails, display a message to the user.
                    // Handle error
                }
            }
    }
}
