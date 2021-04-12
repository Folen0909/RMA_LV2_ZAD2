package hr.ferit.famouspersonsfragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.ferit.famouspersonsfragments.R
import hr.ferit.famouspersonsfragments.listeners.OnInspiringPersonSelectedListener
import hr.ferit.famouspersonsfragments.model.InspiringPerson

class InspiringPersonAdapter(inspiringPersons: List<InspiringPerson>,
                             private val listener: OnInspiringPersonSelectedListener
) : RecyclerView.Adapter<InspiringPersonViewHolder>() {

    private val inspiringPersons: MutableList<InspiringPerson> = mutableListOf()

    init {
        refreshData(inspiringPersons)
    }

    fun refreshData(inspiringPersons: List<InspiringPerson>) {
        this.inspiringPersons.clear()
        this.inspiringPersons.addAll(inspiringPersons)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspiringPersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_inspiring_person, parent, false)
        return InspiringPersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: InspiringPersonViewHolder, position: Int) {
        val inspiringPerson = inspiringPersons[position]
        holder.bind(inspiringPerson)
        holder.itemView.setOnClickListener { listener.onInspiringPersonSelected(inspiringPerson) }
    }

    override fun getItemCount(): Int {
        return inspiringPersons.size
    }
}