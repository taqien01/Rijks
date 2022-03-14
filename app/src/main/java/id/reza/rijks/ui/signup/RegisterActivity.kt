package id.reza.rijks.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import id.reza.rijks.MainAppActivity
import id.reza.rijks.databinding.ActivityRegisterBinding
import id.reza.rijks.ui.login.LoginActivity
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val vm: RegisterViewModel by viewModel()

    override fun onBackPressed() {
        super.onBackPressed()
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initObserver()
        initView()
        initListener()
    }

    private fun initListener() {
        binding.btnRegister.setOnClickListener {
            vm.register(binding.edtUsername.text.toString(), binding.edtPassword.text.toString(), binding.checkTnC.isChecked)
        }
    }

    private fun initObserver() {
        vm.isLoadingEvent.observe(this){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }

        vm.errorEvent.observe(this){
            Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
        }

        vm.isRegisterEvent.observe(this){
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun initView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}