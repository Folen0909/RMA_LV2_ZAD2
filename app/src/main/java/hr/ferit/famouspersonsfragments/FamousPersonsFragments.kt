package hr.ferit.famouspersonsfragments

import android.app.Application

class FamousPersonsFragments : Application() {

    companion object {
        lateinit var application: FamousPersonsFragments
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}