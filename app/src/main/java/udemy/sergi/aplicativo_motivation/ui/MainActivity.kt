package udemy.sergi.aplicativo_motivation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import udemy.sergi.aplicativo_motivation.R
import udemy.sergi.aplicativo_motivation.infra.MotivationConstants
import udemy.sergi.aplicativo_motivation.infra.SecurityPreferences
import udemy.sergi.aplicativo_motivation.repository.Mock

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var  mSecurityPreferences: SecurityPreferences
    private var mPhaseFilter : Int = MotivationConstants.PHRASEFILTER.ALL


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSecurityPreferences = SecurityPreferences(this)
        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        textView_Name.text = "Olá, $name!"


        //Logica inicial Seleção

        image_All.setColorFilter(resources.getColor(R.color.colorAccent))
        HandleNewPhrase()

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonNewPhrase.setOnClickListener (this)
        image_All.setOnClickListener (this)
        image_Happy.setOnClickListener (this)
        image_Morning.setOnClickListener (this)

    }

    override fun onClick(view: View?) {
       val id = view!!.id

        val listFilter = listOf(R.id.image_All,R.id.image_Happy,R.id.image_Morning )

        if (id == R.id.buttonNewPhrase){

            HandleNewPhrase()

        }else if( id in listFilter){

            HandlerFilter(id)

        }


    }

    private fun HandlerFilter(id : Int){

        image_All.setColorFilter(resources.getColor(R.color.white))
        image_Happy.setColorFilter(resources.getColor(R.color.white))
        image_Morning.setColorFilter(resources.getColor(R.color.white))

        when (id){
            R.id.image_All -> {
                image_All.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhaseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.image_Happy -> {
                image_Happy.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhaseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.image_Morning -> {
                image_Morning.setColorFilter(resources.getColor(R.color.colorAccent))
                mPhaseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }
    }

    private fun HandleNewPhrase(){

        textView_Phrase.text = Mock().getPhrase(mPhaseFilter)

    }
}