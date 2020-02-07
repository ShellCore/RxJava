package mx.com.shellcore.android.rxproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_rx06_bus.*

class Rx06BusFragment : Fragment() {

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rx06_bus, container, false)
        configFragment()
        return view
    }

    private fun configFragment() {
        val observable = Rx06Bus.getInstance()
            .getEvents()
        compositeDisposable.add(observable.subscribe {
            txtFragment.text = "$it"
        })
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
