package hr.ferit.famouspersonsfragments.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hr.ferit.famouspersonsfragments.R
import hr.ferit.famouspersonsfragments.databinding.ActivityMainBinding
import hr.ferit.famouspersonsfragments.fragments.InspiringPersonListFragment
import hr.ferit.famouspersonsfragments.fragments.NewInspiringPersonFragment
import hr.ferit.famouspersonsfragments.listeners.OnInspiringPersonClickListener
import hr.ferit.famouspersonsfragments.model.InspiringPerson
import hr.ferit.famouspersonsfragments.repository.InspiringPersonRepository

class MainActivity : AppCompatActivity(), OnInspiringPersonClickListener {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainBinding.fabAddNote.setOnClickListener{createNewInspiringPerson()}
        setContentView(mainBinding.root)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                    .add(R.id.fl_fragmentContainer, InspiringPersonListFragment.create(), InspiringPersonListFragment.TAG)
                    .commit()
        }
    }

    private fun createNewInspiringPerson() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_fragmentContainer,
                    NewInspiringPersonFragment.create(),
                    NewInspiringPersonFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun onInspiringPersonSelected(inspiringPerson: InspiringPerson) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fl_fragmentContainer,
                    NewInspiringPersonFragment.create(inspiringPerson),
                    NewInspiringPersonFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun onInspiringPersonQuote(inspiringPerson: InspiringPerson) {
        Toast.makeText(applicationContext, inspiringPerson.quotes.random(), Toast.LENGTH_SHORT).show()
    }

}