package com.binar.movieapp.ui.register

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.binar.movieapp.databinding.FragmentRegisterBinding
import com.binar.movieapp.ui.MainViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    private lateinit var mainViewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("DATA_INFO", Context.MODE_PRIVATE)

        binding.btnregister.setOnClickListener {
            val username = binding.etusernamereg.text.toString()
            val email = binding.etemailreg.text.toString()
            val pass = binding.etpasswordreg.text.toString()
            val passConf = binding.etpasswordconfirmreg.text.toString()



            if(username.isNullOrEmpty() || email.isNullOrEmpty() || pass.isNullOrEmpty() || passConf.isNullOrEmpty()){
                Toast.makeText(activity, "Isi informasi yang kosong", Toast.LENGTH_SHORT).show()
            }else{
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                if (pass == passConf){
//                    editor.putString("username", username)
                    editor.putString("email", email)
                    editor.putString("pass", pass)
                    editor.apply()
                    mainViewModel.saveDatastore(username)
                    Toast.makeText(activity, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    it.findNavController().navigateUp()
                }else{
                    Toast.makeText(activity, "Password tidak cocok, Coba lagi", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}