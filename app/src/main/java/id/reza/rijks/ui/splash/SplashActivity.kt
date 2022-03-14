package id.reza.rijks.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import id.reza.rijks.MainAppActivity
import id.reza.rijks.R
import id.reza.rijks.databinding.ActivitySplashBinding
import id.reza.rijks.ui.login.LoginActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashBinding

    private val vm: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
        initObserver()
    }

    fun initView(){
        Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    vm.checkLogin()
                },
                1000
            )
    }

    fun initObserver(){
        vm.isLoginEvent.observe(this) {
            if (it) {
                val i = Intent(this, MainAppActivity::class.java)
                startActivity(i)
                finish()
            }else{
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
            }
        }
    }
}