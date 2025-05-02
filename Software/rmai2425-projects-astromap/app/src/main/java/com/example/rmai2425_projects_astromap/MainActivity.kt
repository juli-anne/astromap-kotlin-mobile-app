import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.rmai2425_projects_astromap.database.DatabaseProvider
import com.example.rmai2425_projects_astromap.database.DatabaseInitializer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // kod za sučelja ...

        val db = DatabaseProvider.getDatabase(this)
        val dao = db.entitiesDao()
        lifecycleScope.launch {
            DatabaseInitializer.initDatabase(dao)
        }
    }
}
