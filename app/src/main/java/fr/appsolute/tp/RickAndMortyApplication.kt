package fr.appsolute.tp

import android.app.Application
import fr.appsolute.tp.data.database.DatabaseManager

/**
 * New entry point of the application (Referenced in the manifests)
 */
class RickAndMortyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    // Init the database access
    private fun initDatabase() {
        DatabaseManager.getInstance(this)
    }
}