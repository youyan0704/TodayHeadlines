package com.youyan.android.headlines.ui.fragement.main


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.youyan.android.headlines.R

class MiniHeadlinesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mini_headlines, container, false)
    }

    companion object {

        fun newInstance(): MiniHeadlinesFragment {
            return MiniHeadlinesFragment()
        }
    }

}
