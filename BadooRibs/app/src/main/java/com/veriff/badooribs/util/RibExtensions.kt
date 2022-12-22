package com.veriff.badooribs.util

import android.view.LayoutInflater
import android.view.View
import com.badoo.ribs.core.view.ViewFactory

fun ViewFactory.Context.inflater(): LayoutInflater =
    LayoutInflater.from(parent.context)

fun View.inflater(): LayoutInflater =
    LayoutInflater.from(context)
