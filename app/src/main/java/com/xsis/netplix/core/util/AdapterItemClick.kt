package com.xsis.netplix.core.util

abstract class AdapterItemClickListener<T> {
    abstract fun onTransactionItemClick(failure: T)
}