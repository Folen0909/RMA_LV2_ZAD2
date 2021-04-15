package hr.ferit.famouspersonsfragments.model

import java.io.Serializable

data class InspiringPerson
    (val name: String,
     val dateOfBirth: String,
     val dateOfDeath: String,
     val shortDescription: String,
     val imageLink: String,
     val quotes: MutableList<String>
) : Serializable