package hr.ferit.famouspersonsfragments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import hr.ferit.famouspersonsfragments.R
import hr.ferit.famouspersonsfragments.databinding.FragmentInspiringPersonListBinding
import hr.ferit.famouspersonsfragments.databinding.FragmentNewInspiringPersonBinding
import hr.ferit.famouspersonsfragments.databinding.ItemInspiringPersonBinding
import hr.ferit.famouspersonsfragments.model.InspiringPerson
import hr.ferit.famouspersonsfragments.repository.InspiringPersonRepository

class NewInspiringPersonFragment : Fragment() {

    lateinit var newInspiringPersonBinding: FragmentNewInspiringPersonBinding
    private val inspiringPersonRepository = InspiringPersonRepository
    private lateinit var inspiringPerson: InspiringPerson

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newInspiringPersonBinding = FragmentNewInspiringPersonBinding.inflate(inflater, container, false)
        newInspiringPersonBinding.bNewPersonSave.setOnClickListener{ saveInspiringPerson() }
        newInspiringPersonBinding.bDeletePerson.isEnabled = false
        newInspiringPersonBinding.bDeletePerson.isClickable = false
        arguments?.let {
            inspiringPerson = it.getSerializable(KEY_INSPIRING_PERSON) as InspiringPerson
            newInspiringPersonBinding.etNewPersonNameInput.setText(inspiringPerson.name)
            newInspiringPersonBinding.etNewPersonBirthInput.setText(inspiringPerson.dateOfBirth)
            newInspiringPersonBinding.etNewPersonDeathInput.setText(inspiringPerson.dateOfDeath)
            newInspiringPersonBinding.etNewPersonDescriptionInput.setText(inspiringPerson.shortDescription)
            newInspiringPersonBinding.etNewPersonImageLinkInput.setText(inspiringPerson.imageLink)
            var quotes = ""
            for(quote in inspiringPerson.quotes) quotes += quote + "\n"
            quotes = quotes.trimEnd()
            newInspiringPersonBinding.etNewPersonQuotesInput.setText(quotes)
            newInspiringPersonBinding.bDeletePerson.setOnClickListener{ deleteInspiringPerson(inspiringPerson) }
            newInspiringPersonBinding.bDeletePerson.isEnabled = true
            newInspiringPersonBinding.bDeletePerson.isClickable = true
        }
        return newInspiringPersonBinding.root
    }

    private fun deleteInspiringPerson(inspiringPerson: InspiringPerson) {
        inspiringPersonRepository.delete(inspiringPerson)
        fragmentManager?.popBackStack();
    }

    private fun saveInspiringPerson() {
        if (this::inspiringPerson.isInitialized) inspiringPersonRepository.delete(inspiringPerson)
        val name = newInspiringPersonBinding.etNewPersonNameInput.text.toString()
        val birth = newInspiringPersonBinding.etNewPersonBirthInput.text.toString()
        val death = newInspiringPersonBinding.etNewPersonDeathInput.text.toString()
        val description = newInspiringPersonBinding.etNewPersonDescriptionInput.text.toString()
        val imageLink = newInspiringPersonBinding.etNewPersonImageLinkInput.text.toString()
        val quotes = newInspiringPersonBinding.etNewPersonQuotesInput.text.toString().split("\n")
        inspiringPerson = InspiringPerson(name, birth, death, description, imageLink, quotes.toMutableList())
        inspiringPersonRepository.insert(inspiringPerson)
        fragmentManager?.popBackStack();
    }

    companion object {
        const val TAG = "New Inspiring Person"
        private const val KEY_INSPIRING_PERSON = "Inspiring Person"

        fun create(inspiringPerson: InspiringPerson): NewInspiringPersonFragment {
            val args = Bundle()
            args.putSerializable(KEY_INSPIRING_PERSON, inspiringPerson)
            val fragment = NewInspiringPersonFragment()
            fragment.arguments = args
            return fragment
        }
        fun create(): NewInspiringPersonFragment {
            return NewInspiringPersonFragment()
        }
    }
}