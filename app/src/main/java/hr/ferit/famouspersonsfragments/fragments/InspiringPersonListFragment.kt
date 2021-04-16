package hr.ferit.famouspersonsfragments.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hr.ferit.famouspersonsfragments.R
import hr.ferit.famouspersonsfragments.adapters.InspiringPersonAdapter
import hr.ferit.famouspersonsfragments.databinding.FragmentInspiringPersonListBinding
import hr.ferit.famouspersonsfragments.listeners.OnInspiringPersonClickListener
import hr.ferit.famouspersonsfragments.repository.InspiringPersonRepository

class InspiringPersonListFragment : Fragment() {

    private lateinit var onInspiringPersonClickListener: OnInspiringPersonClickListener
    private lateinit var inspiringPersonListBinding: FragmentInspiringPersonListBinding
    private val inspiringPersonRepository = InspiringPersonRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inspiringPersonListBinding = FragmentInspiringPersonListBinding.inflate(inflater, container, false)
        inspiringPersonListBinding.fabAddNote.setOnClickListener{ createNewInspiringPerson() }
        setupRecyclerView()
        return inspiringPersonListBinding.root
    }

    private fun createNewInspiringPerson() {
        // This is the only way I could figure out how to replace fragment within same fragment
        activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fl_fragmentContainer,
                        NewInspiringPersonFragment.create(),
                        NewInspiringPersonFragment.TAG)
                .addToBackStack(null)
                .commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnInspiringPersonClickListener) onInspiringPersonClickListener = context
    }
    override fun onResume() {
        super.onResume()
        (inspiringPersonListBinding.rvPersons.adapter as InspiringPersonAdapter).refreshData(inspiringPersonRepository.getInspiringPersons())
    }
    private fun setupRecyclerView() {
        inspiringPersonListBinding.rvPersons.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        inspiringPersonListBinding.rvPersons.adapter = InspiringPersonAdapter(inspiringPersonRepository.getInspiringPersons(), onInspiringPersonClickListener)
    }


    companion object {
        const val TAG = "Inspiring Person List"
        fun create(): InspiringPersonListFragment {
            return InspiringPersonListFragment()
        }
    }
}