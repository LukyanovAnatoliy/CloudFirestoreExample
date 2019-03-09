package lav.cloudfirestoreexample

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Anatoliy Lukyanov on 10/03/2019.
 *
 */
fun Disposable.addTo(compositeDisposable: CompositeDisposable){
    compositeDisposable.add(this)
}