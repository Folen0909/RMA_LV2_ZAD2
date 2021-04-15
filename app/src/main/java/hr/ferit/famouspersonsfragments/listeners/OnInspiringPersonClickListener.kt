package hr.ferit.famouspersonsfragments.listeners

import hr.ferit.famouspersonsfragments.model.InspiringPerson

interface OnInspiringPersonClickListener {
    fun onInspiringPersonSelected(inspiringPerson: InspiringPerson)
    fun onInspiringPersonQuote(inspiringPerson: InspiringPerson)
}