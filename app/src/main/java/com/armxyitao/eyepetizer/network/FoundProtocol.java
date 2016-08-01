package com.armxyitao.eyepetizer.network;

import com.armxyitao.eyepetizer.base.BaseProtocol;
import com.armxyitao.eyepetizer.bean.ItemList;
import com.armxyitao.eyepetizer.constants.NetRequestCons;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * @author 熊亦涛
 * @time 16/7/27  22:09
 * @desc ${TODD}
 */
public class FoundProtocol extends BaseProtocol {
    @Override
    public void handleFailure(String error) {

    }

    @Override
    public void handleJson(int action, String responseString) {
        Gson gson = new Gson();
        switch (action) {
            case NetRequestCons.GET_FOUND_DATA:
                try {
                    JSONObject j = new JSONObject(responseString);
                    JSONArray itemArray = j.optJSONArray("itemList");
                    //处理下第一条数据
                    JSONObject bannerJson = itemArray.getJSONObject(0);
                    String bannerStr = bannerJson.optJSONObject("data").
                            optJSONArray("itemList").toString();
                    List<ItemList> banner = gson.fromJson(bannerStr,
                            new TypeToken<List<ItemList>>() {}.getType());
                    itemArray.remove(0);
                    String itemList = j.optJSONArray("itemList").toString();
                    List<ItemList> o = gson.fromJson(itemList,
                            new TypeToken<List<ItemList>>() {}.getType());
                    //将banner的数据添加到集合中
                    o.addAll(0, banner);
                    Logger.d(o);
                    mListener.onModelChanged(NetRequestCons.GET_FOUND_DATA_RESULT,o);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
