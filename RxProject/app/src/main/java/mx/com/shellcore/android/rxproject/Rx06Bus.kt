package mx.com.shellcore.android.rxproject

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class Rx06Bus {

    companion object {
        private var instance: Rx06Bus? = null

        fun getInstance(): Rx06Bus {
            if (instance == null) {
                instance = Rx06Bus()
            }
            return instance!!
        }
    }

    private var bus: PublishSubject<Any> = PublishSubject.create()

    fun setEvents(message: Any) {
        bus.onNext(message)
    }

    fun getEvents(): Observable<Any> {
        return bus
    }
}