package mssn.autochronos

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.content.Intent




class MainActivity : AppCompatActivity() {

//    private var mRecyclerView: RecyclerView? = null
    private var mSportsData: ArrayList<Sport>? = null
    private var mAdapter: SportsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //Initialize the RecyclerView
        val mRecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView

        //Set the Layout Manager
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))

        //Initialize the ArrayLIst that will contain the data
        mSportsData = ArrayList()

        //Initialize the adapter and set it ot the RecyclerView
        mAdapter = SportsAdapter(this, mSportsData)
        mRecyclerView.setAdapter(mAdapter)

        //Get the data
        initializeData()
    }

    /**
     * Method for initializing the sports data from resources.
     */
    private fun initializeData() {
        //Get the resources from the XML file
        val sportsList = resources.getStringArray(R.array.sports_titles)
        val sportsInfo = resources.getStringArray(R.array.sports_info)

        //Clear the existing data (to avoid duplication)
        mSportsData?.clear()

        //Create the ArrayList of Sports objects with the titles and information about each sport
        for (i in sportsList.indices) {
            mSportsData?.add(Sport(sportsList[i], sportsInfo[i]))
        }

        //Notify the adapter of the change
        mAdapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
