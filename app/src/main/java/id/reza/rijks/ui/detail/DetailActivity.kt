package id.reza.rijks.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import id.reza.rijks.databinding.ActivityDetailBinding
import id.reza.rijks.model.ArtObjects

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var data : ArtObjects

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }

    fun initView(){
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Detail"
        data = intent.getSerializableExtra("DATA") as ArtObjects

        binding.txtDetail.text = data.title

        Glide.with(this).load(data.webImage!!.headerIurlmage).into(binding.imgArt)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}