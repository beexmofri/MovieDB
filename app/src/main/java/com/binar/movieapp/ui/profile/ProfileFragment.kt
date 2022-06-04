package com.binar.movieapp.ui.profile

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.binar.movieapp.R
import com.binar.movieapp.databinding.FragmentProfileBinding
import com.binar.movieapp.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val pickImage = 100
    private var imageUri: Uri? = null

    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.readDataStore.observe(viewLifecycleOwner,{update ->
            binding.tvnamaprofile.text = update
        })


        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("DATA_INFO", AppCompatActivity.MODE_PRIVATE)
//
//        val username = sharedPreferences.getString("username", "defaultUname")

//        view.tvnamaprofile.text = "Wellcome, $username"

        binding.btlogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }

        binding.btnupdate.setOnClickListener {
            val update = binding.etupdate.text.toString()
            mainViewModel.saveDatastore(update)
        }

        binding.imageprofile.setOnClickListener{
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

            startActivityForResult(gallery, pickImage)
        }

//        binding.btnsaveimage.setOnClickListener {
//            takePicture()
//
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage){
            imageUri = data?.data
            binding.imageprofile.setImageURI(imageUri)
        }
    }
}