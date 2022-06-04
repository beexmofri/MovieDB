package com.binar.movieapp.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.binar.movieapp.R
import com.binar.movieapp.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*

open class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

//    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


//         mainViewModel.readDataStore.observe(viewLifecycleOwner,{ username ->
//             binding.tvlogin.text = username
//        })

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("DATA_INFO", Context.MODE_PRIVATE)

        binding.btnlogin.setOnClickListener {

            val str_email = binding.etemail.text.toString()
            val str_pass = binding.etpassword.text.toString()

            if(str_email.isNullOrEmpty() || str_pass.isNullOrEmpty()){
                Toast.makeText(activity, "Masukan Informasi", Toast.LENGTH_SHORT).show()
            }else{
                val email = sharedPreferences.getString("email", "defaultEmail")
                val pass = sharedPreferences.getString("pass", "defaultPass")

//                    val email_id = mainViewModel.readEmail.observe(viewLifecycleOwner,{
//
//                    })
//                    val pass_id = mainViewModel.readPass.observe(viewLifecycleOwner,{
//
//                    })

                if (email.equals(str_email) && pass.equals(str_pass)) {
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.apply()
//                    val confirm = mainViewModel.saveLog(str_email, str_pass)
//                    confirm.apply()
                        findNavController().navigate(R.id.action_loginFragment_to_nav_movie)
                    } else {
                        Toast.makeText(activity, "Data Kosong", Toast.LENGTH_SHORT).show()
                    }

            }
        }
        binding.btntvreg.setOnClickListener {

            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}