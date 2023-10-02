package com.example.myekasari.ui.detail


import android.view.View
import com.example.myekasari.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PaymentPresenter (private val view: PaymentContract.View) : PaymentContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?
    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getCheckout(FoodId:String, userId:String, quantity:String, total:String, viewParms: View) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.checkout(
            FoodId,
            userId,
            quantity,
            total,
            "ON_DELIVERY"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.meta?.status.equals("success",true)){
                        it.data?.let { it1 -> view.onCheckoutSuccess(it1, viewParms) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onCheckoutFailed(it1) }
                    }
                },
                {
                    view.dismissLoading()
                    view.onCheckoutFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }


    override fun subscribe() {

    }

    override fun unSubscribe() {
        mCompositeDisposable!!.clear()
    }

}