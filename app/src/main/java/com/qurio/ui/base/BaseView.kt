package com.qurio.ui.base

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun showError(message: String)
}