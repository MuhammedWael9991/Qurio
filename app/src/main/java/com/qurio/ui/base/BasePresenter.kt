package com.qurio.ui.base

interface BasePresenter<V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}
