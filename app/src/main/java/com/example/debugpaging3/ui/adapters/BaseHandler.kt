package com.example.debugpaging3.ui.adapters

import android.view.View

class BaseHandler<T>(val onClick: ((View, T) -> Unit)? = null,
                     val onLongClick: ((View, T) -> Boolean)? = null)