package udemy.sergi.aplicativo_motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import udemy.sergi.aplicativo_motivation.R
import udemy.sergi.aplicativo_motivation.infra.MotivationConstants
import udemy.sergi.aplicativo_motivation.infra.SecurityPreferences


class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var  mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        mSecurityPreferences = SecurityPreferences(this)


        //supportActionBar?.hide()

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        button_Save.setOnClickListener (this)

        verifyName()

        val securityPreferences  = SecurityPreferences (this)
        securityPreferences.storeString("","")

        

    }

    override fun onClick(view: View?) {

        val id = view!!.id
        if (id == R.id.button_Save){
            handlerSave()

        }
    }


    private fun verifyName(){

        val name = mSecurityPreferences.getString(MotivationConstants.KEY.PERSON_NAME)
        if ( name != ""){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun handlerSave() {

        val name = ediText_Name.text.toString()

        if (name != ""){

            mSecurityPreferences.storeString("name", name)
            val intent = Intent ( this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            Toast.makeText(this, "Intreduza o seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
}