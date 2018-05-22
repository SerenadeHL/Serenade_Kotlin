package me.serenadehl.shellaccount.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-27 09:05:22
 */
abstract class BaseFragment : Fragment() {
    lateinit var mRootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = inflater.inflate(layout(), null)
        onViewCreated()
        return mRootView
    }

    /**
     * 设置布局
     * @return 布局id
     */
    abstract fun layout(): Int

    abstract fun onViewCreated()
}