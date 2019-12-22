package com.tjeit.a20191222_01_loginandsignupapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.tjeit.a20191222_01_loginandsignupapi.R
import com.tjeit.a20191222_01_loginandsignupapi.datas.BlackListData

class BlackListAdapter (context: Context, resId: Int, list:ArrayList<BlackListData>)
    : ArrayAdapter<BlackListData> (context, resId, list) {

    val mContext = context
    val mList = list
    val inf = LayoutInflater.from(mContext)


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView

        tempRow?.let {

        }.let {
            tempRow = inf.inflate(R.layout.black_list_item, null)
        }

        val row = tempRow!!

        val data = mList.get(position)

        val titleTxt = row.findViewById<TextView>(R.id.titleTxt)
        val phoneNumTxt = row.findViewById<TextView>(R.id.phoneNumTxt)
        val contentTxt = row.findViewById<TextView>(R.id.contentTxt)

        titleTxt.text = data.title
        phoneNumTxt.text = "(${data.phoneNum})"
        contentTxt.text = data.content



        return row
    }


}







