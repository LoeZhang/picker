package com.loe.test

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.loe.picker.*
import com.loe.picker.util.OnLoadOkListener
import com.loe.picker.util.OptionsPickerNetView
import com.loe.picker.util.PickerBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pickerDialog = PickerDialog(this, listOf(
            PickerBean("哈哈"),
            PickerBean("多少"),
            PickerBean("断点顺丰到付"),
            PickerBean("434"),
            PickerBean("同仁堂"),
            PickerBean("灌灌灌灌付拖"),
            PickerBean("sd"),
            PickerBean("欧体"),
            PickerBean("谷")
        ))
        { i, bean ->
            button.text = bean.name
        }

        var i = 0

        val pickerNetDialog = PickerNetDialog(this, object : PickerNetDialog.PickerCallback
        {
            override fun onSelect(index: Int, bean: PickerBean)
            {
                button.text = bean.name
            }

            override fun onLoadList(onLoadOkListener: OnLoadOkListener)
            {
                Handler().postDelayed({

                    if(i == 0)
                    {
                        i++
                        onLoadOkListener.onLoadOk(null);
                        return@postDelayed
                    }
                    onLoadOkListener.onLoadOk(listOf(
                        PickerBean("哈哈"),
                        PickerBean("多少"),
                        PickerBean("断点顺丰到付"),
                        PickerBean("434"),
                        PickerBean("同仁堂"),
                        PickerBean("灌灌灌灌付拖"),
                        PickerBean("sd"),
                        PickerBean("欧体"),
                        PickerBean("谷")
                    ))

                },2000)

            }
        })

        var pickerDoubleDialog = PickerDoubleDialog(this, listOf(
            PickerBean("哈哈"),
            PickerBean("多少"),
            PickerBean("断点顺丰到付"),
            PickerBean("434"),
            PickerBean("同仁堂"),
            PickerBean("灌灌灌灌付拖"),
            PickerBean("sd"),
            PickerBean("欧体"),
            PickerBean("谷")
        ), object : PickerDoubleDialog.PickerCallback
        {
            override fun onSelect(index1: Int, index2: Int, bean1: PickerBean, bean2: PickerBean): Boolean
            {
                button.text = bean2.name
                return true
            }

            override fun onLoadList2(pi1: Int, parent: PickerBean, onLoadOkListener: OnLoadOkListener)
            {
                onLoadOkListener.onLoadOk(
                    listOf(
                        PickerBean(parent.name + "4343"),
                        PickerBean(parent.name + "多少"),
                        PickerBean(parent.name + "谷歌"),
                        PickerBean(parent.name + "辅导辅导辅导"),
                        PickerBean(parent.name + "忐忑热热热"),
                        PickerBean(parent.name + "灌灌付"),
                        PickerBean(parent.name + "退入"),
                        PickerBean(parent.name + "他"),
                        PickerBean(parent.name + "让54")
                    )
                )
            }
        })

        var pickerNetDoubleDialog = PickerNetDoubleDialog(this, object : PickerNetDoubleDialog.PickerCallback
        {
            override fun onSelect(index1: Int, index2: Int, bean1: PickerBean, bean2: PickerBean): Boolean
            {
                button.text = bean2.name
                return true
            }

            override fun onLoadList1(onLoadOkListener: OnLoadOkListener)
            {
                Handler().postDelayed({

                    if(i == 0)
                    {
                        i++
                        onLoadOkListener.onLoadOk(null);
                        return@postDelayed
                    }
                    onLoadOkListener.onLoadOk(listOf(
                        PickerBean("哈哈"),
                        PickerBean("多少"),
                        PickerBean("断点顺丰到付"),
                        PickerBean("434"),
                        PickerBean("同仁堂"),
                        PickerBean("灌灌灌灌付拖"),
                        PickerBean("sd"),
                        PickerBean("欧体"),
                        PickerBean("谷")
                    ))

                },2000)

            }

            override fun onLoadList2(pi1: Int, parent: PickerBean, onLoadOkListener: OnLoadOkListener)
            {
                onLoadOkListener.onLoadOk(
                    listOf(
                        PickerBean(parent.name + "4343"),
                        PickerBean(parent.name + "多少"),
                        PickerBean(parent.name + "谷歌"),
                        PickerBean(parent.name + "辅导辅导辅导"),
                        PickerBean(parent.name + "忐忑热热热"),
                        PickerBean(parent.name + "灌灌付"),
                        PickerBean(parent.name + "退入"),
                        PickerBean(parent.name + "他"),
                        PickerBean(parent.name + "让54")
                    )
                )
            }
        })


        var pickerNetThreeDialog = PickerNetThreeDialog(this, object : PickerNetThreeDialog.PickerCallback
        {
            override fun onSelect(index1: Int, index2: Int, index3: Int, bean1: PickerBean, bean2: PickerBean, bean3: PickerBean): Boolean
            {
                button.text = bean3.name
                return true
            }

            override fun onLoadList1(onLoadOkListener: OnLoadOkListener)
            {
                Handler().postDelayed({

                    if(i == 0)
                    {
                        i++
                        onLoadOkListener.onLoadOk(null);
                        return@postDelayed
                    }
                    onLoadOkListener.onLoadOk(listOf(
                        PickerBean("哈哈"),
                        PickerBean("多少"),
                        PickerBean("断点顺丰到付"),
                        PickerBean("434"),
                        PickerBean("同仁堂"),
                        PickerBean("灌灌灌灌付拖"),
                        PickerBean("sd"),
                        PickerBean("欧体"),
                        PickerBean("谷")
                    ))

                },2000)
            }

            override fun onLoadList2(pi1: Int, parent: PickerBean, onLoadOkListener: OnLoadOkListener)
            {
                onLoadOkListener.onLoadOk(
                    listOf(
                        PickerBean(parent.name + "4343"),
                        PickerBean(parent.name + "多少"),
                        PickerBean(parent.name + "谷歌"),
                        PickerBean(parent.name + "辅导辅导辅导"),
                        PickerBean(parent.name + "忐忑热热热"),
                        PickerBean(parent.name + "灌灌付"),
                        PickerBean(parent.name + "退入"),
                        PickerBean(parent.name + "他"),
                        PickerBean(parent.name + "让54")
                    )
                )
            }

            override fun onLoadList3(pi1: Int, pi2: Int, parent: PickerBean, onLoadOkListener: OnLoadOkListener)
            {
                onLoadOkListener.onLoadOk(
                    listOf(
                        PickerBean(parent.name + "1"),
                        PickerBean(parent.name + "2"),
                        PickerBean(parent.name + "3"),
                        PickerBean(parent.name + "4"),
                        PickerBean(parent.name + "5"),
                        PickerBean(parent.name + "6"),
                        PickerBean(parent.name + "7"),
                        PickerBean(parent.name + "8"),
                        PickerBean(parent.name + "9")
                    )
                )
            }
        })
        pickerNetThreeDialog.setTail("申", "全", "元")

        val pickerTimeDialog = PickerTimeDialog(this, PickerTimeDialog.Type.DATE_MINUTE)
        {
        }

        button.setOnClickListener()
        {
//            picker.selectSameName("付出")
//            picker.show()
//            pickerNetDialog.show()
//            pickerNetThreeDialog.show()
            pickerTimeDialog.show()
        }
    }
}