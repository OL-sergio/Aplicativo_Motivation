package udemy.sergi.aplicativo_motivation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import udemy.sergi.aplicativo_motivation.R
import udemy.sergi.aplicativo_motivation.databinding.ActivityMainBinding
import udemy.sergi.aplicativo_motivation.infra.MotivationConstants
import udemy.sergi.aplicativo_motivation.infra.SecurityPreferences
import udemy.sergi.aplicativo_motivation.repository.Mock

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var  mSecurityPreferences: SecurityPreferences
    private var mPhaseFilter : Int = MotivationConstants.PHRASEFILTER.ALL
    private val mock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSecurityPreferences = SecurityPreferences(this)






        //Logica inicial Seleção
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.purple))


        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        showUserName()
        handleNewPhrase()
        setListener()
        handlerFilter(R.id.image_All)

    }

    private fun showUserName() {
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)

        binding.textViewName.text = buildString {
            append("Olá, ")
            append(name)
            append("!")
        }

    }

    private fun setListener() {
        binding.buttonNewPhrase.setOnClickListener (this)
        binding.imageAll.setOnClickListener (this)
        binding.imageHappy.setOnClickListener (this)
        binding.imageMorning.setOnClickListener (this)
    }

    override fun onClick(view: View?) {
       val id: Int = view!!.id

        val listFilter = listOf(R.id.image_All,R.id.image_Happy,R.id.image_Morning )

        if (id == R.id.buttonNewPhrase){

            handleNewPhrase()

        }else if( id in listFilter){

            handlerFilter(id)

        }
    }

    private fun handlerFilter(id : Int){

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageMorning.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id){
            R.id.image_All -> {
                mPhaseFilter = MotivationConstants.PHRASEFILTER.ALL
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))

            }
            R.id.image_Happy -> {
                mPhaseFilter = MotivationConstants.PHRASEFILTER.HAPPY
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))

            }
            R.id.image_Morning -> {
                mPhaseFilter = MotivationConstants.PHRASEFILTER.SUNNY
                binding.imageMorning.setColorFilter(ContextCompat.getColor(this, R.color.white))

            }
        }
    }

    private fun handleNewPhrase(){
        binding.textViewPhrase.text = mock.getPhrase(mPhaseFilter)

    }
}