package id.reza.rijks.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import id.reza.rijks.MainAppActivity
import id.reza.rijks.databinding.ActivityLoginBinding
import id.reza.rijks.ui.signup.RegisterActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val vm : LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initObserver()
        initListener()
    }

    private fun initObserver() {
        vm.loadingEvent.observe(this){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }

        vm.isErrorEvent.observe(this){
            Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
        }

        vm.isLoginEvent.observe(this){
            val i = Intent(this, MainAppActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    private fun initListener() {
        binding.txtDaftar.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.btnLogin.setOnClickListener {
            vm.cekLogin(binding.edtUsername.text.toString(), binding.edtPassword.text.toString())
        }
    }

}